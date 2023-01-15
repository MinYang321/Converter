package com.min.utilities.convert;

import java.util.Arrays;

public class WeightConverter extends AbstractConverter {

	private final String[] WEIGHT_UNIT_LIST = { "Carats", "Milligrams", "Centigrams", "Decigrams", "Grams",
			"Decagrams", "Hectograms", "Kilograms", "Metric tonnes", "Ounces", "pounds", "Stones", "Short tons(US)", "Long tons(UK)"};
	private final double[] CONVERSION_RATE_IN_ORDER = {200, 0.1, 0.1, 0.1, 0.1, 0.1,
			0.1, 0.001, 35273.96, 0.0625, 0.071429, 0.007, 0.892857};
	
	public String[] getWEIGHT_UNIT_LIST() {
		return WEIGHT_UNIT_LIST;
	}

	public double[] getCONVERSION_RATE_IN_ORDER() {
		return CONVERSION_RATE_IN_ORDER;
	}
	
	@Override
	public String convertUnits(String fromUnit, String toUnit, String fromUnitValue) throws NumberFormatException {
		int indexofFromUnit = Arrays.asList(this.WEIGHT_UNIT_LIST).indexOf(fromUnit);
		int indexoftoUnit = Arrays.asList(this.WEIGHT_UNIT_LIST).indexOf(toUnit);
		double finalRate = 1.0;
		double dblValue = Double.parseDouble(fromUnitValue);
		if(indexofFromUnit < indexoftoUnit) {
			for (int index = indexofFromUnit; index < indexoftoUnit; index++) {
				finalRate *=  (double)CONVERSION_RATE_IN_ORDER[index];
			}
		}
		else if (indexofFromUnit < indexoftoUnit) {
			finalRate = 1.0;
		}
		else {
			for (int index = indexofFromUnit; index > indexoftoUnit; index--) {
				finalRate *= 1/CONVERSION_RATE_IN_ORDER[index - 1];
			}
		}
		toUnitValue = dblValue * finalRate;
		return String.format("%.6f", toUnitValue);
	}

}
