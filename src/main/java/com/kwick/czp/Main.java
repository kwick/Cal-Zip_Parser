package com.kwick.czp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	private static final Map<String, BufferedWriter> BUFFEREDWRITERS_BY_COUNTY = new HashMap<>();

	public static void main(String[] args) {
		// check that two arguments were provided
		if (args.length != 2) {
			LOGGER.info("Input directory and output directory are required as program arguments.");
			System.exit(0);
		}
		
		// check that the first argument is a directory that exists
		String inputDirectoryString = args[0];
		File inputDirectory = new File(inputDirectoryString);
		if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
			LOGGER.info(() -> "Declared input directory " + inputDirectoryString + " does not exist or is not a directory");
			System.exit(0);
		}
		
		// check that the second argument is a directory that exists
		String outputDirectoryString = args[1];
		File outputDirectory = new File(outputDirectoryString);
		if (!outputDirectory.exists() || !inputDirectory.isDirectory()) {
			LOGGER.info(() -> "Declared output directory " + outputDirectoryString + " does not exist or is not a directory");
			System.exit(0);
		}
		
		File[] inputFiles = inputDirectory.listFiles();
		
		int totalEntryCounter = 1;
		LOGGER.info("Parsing and aggregating...");
		long totalTime = 0;
		try {
			// iterate through every input file
			for (File inputFile : inputFiles) {
				try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
					long fileProcessingStartTime = System.currentTimeMillis();
					String line = null;
					int thisFileEntryCounter = 0;
					// for every line in the file we are currently processing
					while ((line = br.readLine()) != null) {
						// split the line read from the buffer by the TAB character
						String[] split = line.split("\t");
						
						// the last String in the array is the ZIP code, so we get the String at index split.length-1, and then take the first three characters in the string
						String countyPrefix = split[split.length-1].substring(0, 3);
						
						// the county prefix is the first three digits of the zip which we parsed out. Look up the county via our helper class.
						String county = ZipPrefixToCounty.getCounty(countyPrefix);

						// if the BufferedWriters-by-County map doesn't have an entry for the county we just looked up
						if (!BUFFEREDWRITERS_BY_COUNTY.containsKey(county)) {
							// put a new BufferedWriter in the map, which will write to the appropriate County.txt file in the output directory
							BUFFEREDWRITERS_BY_COUNTY.put(county, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputDirectory + File.separator + county + ".txt"), StandardCharsets.UTF_8)));
						}
						
						// we know it is in the map now, no matter what. So get it, write to it, and increment our counters.
						BufferedWriter writer = BUFFEREDWRITERS_BY_COUNTY.get(county);
						writer.write(line);
						writer.newLine();
						thisFileEntryCounter++;
						totalEntryCounter++;
					}
					
					long totalTimeSpentProcessingFile = (System.currentTimeMillis() - fileProcessingStartTime);
					totalTime = totalTime + totalTimeSpentProcessingFile;
					LOGGER.log(Level.INFO, "Processed {0} entries from input file {1} in {2}ms", 
							new Object[] { thisFileEntryCounter, inputFile.getName(), totalTimeSpentProcessingFile });
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Program failed while processing data", e);
		} finally {
			// try to close our writers
			for (BufferedWriter writer : BUFFEREDWRITERS_BY_COUNTY.values()) {
				try {
					writer.close();
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "Failed to close writer " + writer.toString(), e);
				}
			}
			
			// print the final output
			long millisecondsTotalTime = totalTime%1000;
			long secondsTotalTime = totalTime/1000;
			String totalTimeFormatted = secondsTotalTime + "." + millisecondsTotalTime;
			LOGGER.log(Level.INFO, "Processed {0} entries from {1} input files in {2}s",
					new Object[] { totalEntryCounter, inputFiles.length, totalTimeFormatted });
		}
	}
}
