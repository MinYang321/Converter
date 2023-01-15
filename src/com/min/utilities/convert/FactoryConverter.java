package com.min.utilities.convert;

public class FactoryConverter {	
	
	public AbstractConverter createConverter(String converterType) {
		
		if (converterType == null || converterType.isEmpty())
			return null;
		switch (converterType) {
		case "Length": 
			return  new LengthConverter();
		case "Weight":
			//return new WeightConverter();
		default:
			throw new IllegalArgumentException("Unknown converter type" + converterType);
			
		}
	}
	
}