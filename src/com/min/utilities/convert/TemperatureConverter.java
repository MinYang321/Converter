package com.min.utilities.convert;

public class TemperatureConverter extends AbstractConverter {
	
	private final String[] TEMPERATURE_UNIT_LIST = {"Celsius", "Fahrenheit", "Kevin"};
	
	public String[] getTEMPERATURE_UNIT_LIST() {
		return TEMPERATURE_UNIT_LIST;
	}

	@Override
	public String convertUnits(String fromUnit, String toUnit, String fromUnitValue) throws NumberFormatException {
		double dblValue = Double.parseDouble(fromUnitValue);
		switch(fromUnit + "-" + toUnit) {
		case "Celsius-Fahrenheit":
			toUnitValue = (dblValue * 9.0/5.0) + 32;
			break;
		case "Celsius-Kevin":
			toUnitValue = dblValue + 273.15;
			break;
		case "Fahrenheit-Celsius":
			toUnitValue = (dblValue - 32.0) * 5.0/9.0;
			break;
		case "Fahrenheit-Kevin":
			toUnitValue = (dblValue - 32.0) * 5.0/9.0 + 273.15;
			break;
		case "Kevin-Celsius":
			toUnitValue = dblValue - 273.15;
			break;
		case "Kevin-Fahrenheit":
			toUnitValue = ((dblValue - 273.15) * 9.0/5.0) + 32;
			break;
		}
		
		return String.format("%.6f", toUnitValue);
	}

}
