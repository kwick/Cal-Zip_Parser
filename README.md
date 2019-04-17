# Cal-Zip_Parser
take text files and parse into mailing counties
my office receives 8 million addresses in 7 text files with non-standard delimiters
once the files are received, a manual process was used to create seperate documents for each of the California mailing centers (16 of
them) with a 17th being Reno Nevada
I started with creating an array of the first 3 digits of each necessary county, but ran into parsing issues. I then posed my issue to 
StackOverflow, where TheMadTechnician was able to provide a better solution. This currently chews up ram like no-ones business and takes
about 3 hours to complete.

Please feel free to use what has been created for your own needs, and share again.
Original source location https://stackoverflow.com/questions/55484600/use-arrays-and-if-or-for-loops-and-output-separate-files
