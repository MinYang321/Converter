/*
 * Author: Min Yang
 * Course:CST8284-OOP
 * Date: 21 Jan,2023
 * Project: Converter
 */
package com.min.utilities.convert;

import java.util.Arrays;

/**
 * Build a concrete Weight Converter class
 * It inherits the abstract converter class
 * It has its own fields WEIGHT_UNIT_LIST, CONVERSION_RATE_IN_ORDER.
 * It implements the logics of the abstract method convertUnits() inherited from the super class
 * @author Min Yang
 * @version 1.0
 * @since 11.0.1
 */
public class WeightConverter extends AbstractConverter {
	
	//Its own/new constant fields which its super class does not have
	private final String[] WEIGHT_UNIT_LIST = { "Carats", "Milligrams", "Centigrams", "Decigrams", "Grams",
			"Decagrams", "Hectograms", "Kilograms", "Metric tonnes", "Ounces", "pounds", "Stones", "Short tons(US)", "Long tons(UK)"};
	private final double[] CONVERSION_RATE_IN_ORDER = {200, 0.1, 0.1, 0.1, 0.1, 0.1,
			0.1, 0.001, 35273.96, 0.0625, 0.071429, 0.007, 0.892857};
	
	/**
	 * Getter of the field WEIGHT_UNIT_LIST
	 * @return field WEIGHT_UNIT_LIST
	 */
	public String[] getWEIGHT_UNIT_LIST() {
		return WEIGHT_UNIT_LIST;
	}

	/**
	 * Getter of the field CONVERSION_RATE_IN_ORDER
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
		int indexofFromUnit = Arrays.asList(this.WEIGHT_UNIT_LIST).indexOf(fromUnit);
		int indexoftoUnit = Arrays.asList(this.WEIGHT_UNIT_LIST).indexOf(toUnit);
		double finalRate = 1.0;
		double dblValue = Double.parseDouble(fromUnitValue);
		if(indexofFromUnit < indexoftoUnit) {
			for (int index = indexofFromUnit; index < indexoftoUnit; index++) {//if 'from' unit index is before 'to' unit
				//the final rate value is the product of each rate in the chain
				//the chain is the sub array started at 'from' unit and ended at 'to' unit
				finalRate *=  (double)CONVERSION_RATE_IN_ORDER[index];
			}
		}
		else if (indexofFromUnit == indexoftoUnit) { //if 'from' unit index equals 'to' unit
			finalRate = 1.0;
		}
		else {  //if 'from' unit index is after 'to' unit
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
