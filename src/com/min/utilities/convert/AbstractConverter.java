package com.min.utilities.convert;

public abstract class AbstractConverter {
	
	protected String fromUnit;
	protected String toUnit;
	protected double fromUnitValue;
	protected double toUnitValue;
	
	public double getFromUnitValue() {
		return fromUnitValue;
	}

	public void setFromUnitValue(double fromUnitValue) {
		this.fromUnitValue = fromUnitValue;
	}

	public double getToUnitValue() {
		return toUnitValue;
	}

	public void setToUnitValue(double toUnitValue) {
		this.toUnitValue = toUnitValue;
	}

	public abstract double convertUnits(String fromUnit, String toUnit, String fromUnitValue);
}
