/*
 * Author: Min Yang
 * Course:CST8284-OOP
 * Date: 21 Jan,2023
 * Project: Converter
 */
package com.min.utilities.convert;

/**
 * This class build a converter factory in compliance with "Factory Design Pattern"
 * All converters will be instantiated by the worker method createConverter() worker
 * method in it. Depending on the user's input string to specify the converter type,
 * the intended converter (like length, temperature) will be instantiated accordingly.
 * @author Min Yang
 * @version 1.0
 * @since 11.0.15
 */
public class FactoryConverter {	
	
	/**
	 * The main worker method to take a converter type string as input.
	 * Depending on input string, the corresponding objects of specific converters
	 * will be created.
	 * @param converterType intakes a converter type string
	 * @return the type of converter
	 */
	public AbstractConverter createConverter(String converterType) {
		if (converterType == null || converterType.isEmpty())
			return null;
		/*
		 * switch block to realize the creation of corresponding converter objects
		 * as per input string
		 */
		switch (converterType) {
		case "Length": 
			return  new LengthConverter();
		case "Temperature":
			return  new TemperatureConverter();
		case "Weight":
			return  new WeightConverter();
		default:
			throw new IllegalArgumentException("Unknown converter type" + converterType);
		}
	}
}