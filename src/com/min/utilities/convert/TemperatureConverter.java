/*
 * Author: Min Yang
 * Course:CST8284-OOP
 * Date: 21 Jan,2023
 * Project: Converter
 */
package com.min.utilities.convert;

/**
 * Build a concrete Temperature Converter class
 * It inherits the abstract converter class
 * It has its own fields TEMPERATURE_UNIT_LIST
 * It implements the logics of the abstract method convertUnits() inherited from the super class
 * @author Min Yang
 * @version 1.0
 * @since 11.0.1
 */
public class TemperatureConverter extends AbstractConverter {
	
	//Its own/new constant fields which its super class does not have
	private final String[] TEMPERATURE_UNIT_LIST = {"Celsius", "Fahrenheit", "Kevin"};
	
	/**
	 * Getter of the field TEMPERATURE_UNIT_LIST
	 * @return field TEMPERATURE_UNIT_LIST
	 */
	public String[] getTEMPERATURE_UNIT_LIST() {
		return TEMPERATURE_UNIT_LIST;
	}

	@Override
	/**
	 * The override worker method to compute the value of 'to' unit
	 * @param fromUnit
	 * @param toUnit
	 * @param fromUnitValue
	 * @return String.format("%.6f", toUnitValue);
	 * @throws NumberFormatException
	 */
	public String convertUnits(String fromUnit, String toUnit, String fromUnitValue) throws NumberFormatException {
		double dblValue = Double.parseDouble(fromUnitValue);
		
		/**
		 * switch-case block to individually compute the value of from-to unit paire
		 * by using the correct formulas
		 */
		switch(fromUnit + "-" + toUnit) {
		case "Celsius-Celsius":
			toUnitValue = dblValue;
			break;
		case "Celsius-Fahrenheit":
			toUnitValue = (dblValue * 9.0/5.0) + 32;
			break;
		case "Celsius-Kevin":
			toUnitValue = dblValue + 273.15;
			break;
		case "Fahrenheit-Fahrenheit":
			toUnitValue = dblValue;
			break;
		case "Fahrenheit-Celsius":
			toUnitValue = (dblValue - 32.0) * 5.0/9.0;
			break;
		case "Fahrenheit-Kevin":
			toUnitValue = (dblValue - 32.0) * 5.0/9.0 + 273.15;
			break;
		case "Kevin-Kevin":
			toUnitValue = dblValue;
			break;
		case "Kevin-Celsius":
			toUnitValue = dblValue - 273.15;
			break;
		case "Kevin-Fahrenheit":
			toUnitValue = ((dblValue - 273.15) * 9.0/5.0) + 32;
			break;
		default:
			break;
		}
		return String.format("%.6f", toUnitValue);//format the output
	}

}
