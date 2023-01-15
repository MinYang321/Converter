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
	
	/*private int elementQtyofList = LENGTH_UNIT_LIST.length;
	private int keyPairQty = elementQtyofList - 1;
	private String[] ratePairKey = new String[keyPairQty];
	private Map<String, Double> CONVERSION_RATE_MAP = new HashMap<String, Double>();

	public LengthConverter() {
		super();
		initRatePairKey();
		fillConversionRateMap();
	}

	public void initRatePairKey() {
		for (int i = 0; i < keyPairQty; i++) {
			this.ratePairKey[keyPairQty] = this.LENGTH_UNIT_LIST[i] + "-" + this.LENGTH_UNIT_LIST[i + 1];
		}
	}

	public void fillConversionRateMap() {
		double value = 0;
		for(int i = 0; i < keyPairQty; i++) {
			switch(this.ratePairKey[keyPairQty]) {
				case "Nanometers-Microns": value = 0.001; break;
				case "Microns-Millimeters": value = 0.001; break;
				case "Millimeters-Centimeters": value = 0.1; break;
				case "Centimeters-Meters": value = 0.01; break;
				case "Meters-kilometers": value = 0.001; break;
				case "kilometers-Inches": value = 1/2.54 * 1e5; break;
				case "Inches-Feet": value = 1/12; break;
				case "Feet-Yards": value = 1/3; break;
				case "Yards-Miles": value = 1/1760; break;
				case "Miles-Nautical miles": value = 1/1.15078; break;
				default: break;
			}
			this.CONVERSION_RATE_MAP.put(this.ratePairKey[keyPairQty], value);
		}
	}*/

	@Override
	public double convertUnits(String fromUnit, String toUnit, String fromUnitValue) {
		
		int indexofFromUnit = Arrays.asList(this.LENGTH_UNIT_LIST).indexOf(fromUnit);
		int indexoftoUnit = Arrays.asList(this.LENGTH_UNIT_LIST).indexOf(toUnit);
		double finalRate = 1.0;
		double dblValue;
		try {
			dblValue = Double.parseDouble(fromUnitValue);
		} catch (NumberFormatException e) {
			return 0;
		}
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
		return toUnitValue = dblValue * finalRate;
	}


}
