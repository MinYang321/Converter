package com.min.utilities.convert;

import java.util.Arrays;

public class LengthConverter extends AbstractConverter {

	private final String[] LENGTH_UNIT_LIST = { "Nanometers", "Microns", "Millimeters", "Centimeters", "Meters",
			"Kilometers", "Inches", "Feet", "Yards", "Miles", "Nautical miles" };
	private final double[] CONVERSION_RATE_IN_ORDER = {0.001, 0.001, 0.1, 0.01, 0.001, 1e5 / 2.54, 1.0/12.0, 1.0/3.0, 1.0/1760.0, 1.0/1.15078};
	
	
	public String[] getLENGTH_UNIT_LIST() {
		return LENGTH_UNIT_LIST;
	}

	public double[] getCONVERSION_RATE_IN_ORDER() {
		return CONVERSION_RATE_IN_ORDER;
	}

	@Override
	public String convertUnits(String fromUnit, String toUnit, String fromUnitValue) throws NumberFormatException {
		
		int indexofFromUnit = Arrays.asList(this.LENGTH_UNIT_LIST).indexOf(fromUnit);
		int indexoftoUnit = Arrays.asList(this.LENGTH_UNIT_LIST).indexOf(toUnit);
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
