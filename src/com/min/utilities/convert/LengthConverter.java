/*
 * Author: Min Yang
 * Course:CST8284-OOP
 * Date: 21 Jan,2023
 * Project: Converter
 */
package com.min.utilities.convert;

import java.util.Arrays;

/**
 * Build a concrete Length Converter class
 * It inherits the abstract converter class
 * It has its own fields LENGTH_UNIT_LIST, CONVERSION_RATE_IN_ORDER.
 * It implements the logics of the abstract method convertUnits() inherited from the super class
 * @author Min Yang
 * @version 1.0
 * @since 11.0.1
 */
public class LengthConverter extends AbstractConverter {
	
	//Its own/new constant fields which its super class does not have
	private final String[] LENGTH_UNIT_LIST = { "Nanometers", "Microns", "Millimeters", "Centimeters", "Meters",
			"Kilometers", "Inches", "Feet", "Yards", "Miles", "Nautical miles" };
	private final double[] CONVERSION_RATE_IN_ORDER = {0.001, 0.001, 0.1, 0.01, 0.001, 1e5 / 2.54, 1.0/12.0, 1.0/3.0, 1.0/1760.0, 1.0/1.15078};
	
	/**
	 * Getter of the field LENGTH_UNIT_LIST
	 * @return field LENGTH_UNIT_LIST
	 */
	public String[] getLENGTH_UNIT_LIST() {
		return LENGTH_UNIT_LIST;
	}

	/**
	 * getter of the field CONVERSION_RATE_IN_ORDER
	 * @return CONVERSION_RATE_IN_ORDER
	 */
	public double[] getCONVERSION_RATE_IN_ORDER() {
		return CONVERSION_RATE_IN_ORDER;
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
		
		int indexofFromUnit = Arrays.asList(this.LENGTH_UNIT_LIST).indexOf(fromUnit);
		int indexoftoUnit = Arrays.asList(this.LENGTH_UNIT_LIST).indexOf(toUnit);
		double finalRate = 1.0;
		double dblValue = Double.parseDouble(fromUnitValue);
		if(indexofFromUnit < indexoftoUnit) { //if 'from' unit index is before 'to' unit
			for (int index = indexofFromUnit; index < indexoftoUnit; index++) {
				//the final rate value is the product of each rate in the chain
				//the chain is the sub array started at 'from' unit and ended at 'to' unit
				finalRate *=  (double)CONVERSION_RATE_IN_ORDER[index]; 
			}
		}
		else if (indexofFromUnit == indexoftoUnit) {//if 'from' unit index equals 'to' unit
			finalRate = 1.0;
		}
		else { //if 'from' unit index is after 'to' unit
			for (int index = indexofFromUnit; index > indexoftoUnit; index--) {
				//the final rate value is the product of the inverse of each rate in the chain
				//the chain is the sub array started at 'from' unit and ended at 'to' unit
				finalRate *= 1/CONVERSION_RATE_IN_ORDER[index - 1];
			}
		}
		toUnitValue = dblValue * finalRate; //compute the value of 'to' unit
		return String.format("%.6f", toUnitValue); //format the output
	}


}
