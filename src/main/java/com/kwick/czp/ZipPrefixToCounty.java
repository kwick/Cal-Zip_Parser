package com.kwick.czp;

import java.util.HashMap;
import java.util.Map;

public class ZipPrefixToCounty {

	private static final Map<String, String> map = new HashMap<>();
	private static final String UNMAPPED_COUNTY = "Reno";
	static {
		map.put("900", "LA");
		map.put("901", "LA");
		map.put("902", "LA");
		map.put("903", "LA");
		map.put("904", "LA");
		map.put("905", "LA");
		map.put("907", "LA");
		map.put("908", "LA");
		
		map.put("906", "Industry");
		map.put("917", "Industry");
		map.put("918", "Industry");
		
		map.put("910", "SantaClarita");
		map.put("916", "SantaClarita");
		
		map.put("919", "SanDiego");
		map.put("921", "SanDiego");
		
		map.put("922", "SanBernardino");
		map.put("925", "SanBernardino");
		
		map.put("926", "SantaAna");
		map.put("927", "SantaAna");
		
		map.put("928", "Anaheim");
		
		map.put("930", "SantaBarbara");
		map.put("931", "SantaBarbara");
		map.put("934", "SantaBarbara");
		
		map.put("932", "Bakersfield");
		map.put("933", "Bakersfield");
		map.put("935", "Bakersfield");
		
		map.put("936", "Fresno");
		map.put("937", "Fresno");
		map.put("938", "Fresno");
		
		map.put("939", "SanJose");
		map.put("950", "SanJose");
		map.put("951", "SanJose");
		
		map.put("940", "SanFrancisco");
		map.put("941", "SanFrancisco");
		map.put("943", "SanFrancisco");
		map.put("944", "SanFrancisco");
		map.put("949", "SanFrancisco");
		map.put("954", "SanFrancisco");
		
		map.put("942", "Sacramento");
		map.put("952", "Sacramento");
		map.put("953", "Sacramento");
		map.put("956", "Sacramento");
		map.put("957", "Sacramento");
		map.put("958", "Sacramento");
		map.put("959", "Sacramento");
		
		map.put("945", "Oakland");
		map.put("946", "Oakland");
		map.put("947", "Oakland");
		map.put("948", "Oakland");
		
		map.put("955", "Eureka");
		
		map.put("960", "Redding");
	}
	
	public static String getCounty(String countyPrefix) {
		String county = map.get(countyPrefix);
		if (county == null) {
			county = UNMAPPED_COUNTY;
		} 
		return county;
	}
}
