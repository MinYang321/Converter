/*
 * Author: Min Yang
 * Course:CST8284-OOP
 * Date: 21 Jan,2023
 * Project: Converter
 */
package com.min.utilities.convert;

/**
 * An abstract class representing a general converter contains the common data 
 * and methods which are going to be inherited and implemented by concrete child classes
 * The concrete class includes but not limited to Length Unit Converter, Temperature Unit
 * Converter, Weight Unit Converter. The core work methods of the class is convertUnits()
 * function which is to be overridden in concrete class to fulfill the polymorphism
 * @author Min Yang
 * @version 1.0
 * @since 11.0.15
 */
public abstract class AbstractConverter {
	
	//fields declaration and the fields will be inherited by all sub concrete classes.
	protected String fromUnit;
	protected String toUnit;
	protected double fromUnitValue;
	protected double toUnitValue;
	
	/**
	 * Getter of field fromUnit
	 * @return fromUnitValue
	 */
	public double getFromUnitValue() {
		return fromUnitValue;
	}

	/**
	 * Setter of field fromUnit
	 * @param fromUnitValue intakes a double typed value of from unit
	 */
	public void setFromUnitValue(double fromUnitValue) {
		this.fromUnitValue = fromUnitValue;
	}

	/**
	 * Getter of field toUnit
	 * @return toUnitValue
	 */
	public double getToUnitValue() {
		return toUnitValue;
	}

	/**
	 * Setter of field toUnit
	 * @param toUnitValue intakes a double typed value of to unit
	 */
	public void setToUnitValue(double toUnitValue) {
		this.toUnitValue = toUnitValue;
	}

	/**
	 * Abstract worker method to be overridden and implemented by the concrete sub classes
	 * The core method to realize the polymorphism
	 * @param fromUnit take in a string of from unit
	 * @param toUnit take in a string of to unit
	 * @param fromUnitValue take in a string of from unit value
	 * @return To be implemented
	 */
	public abstract String convertUnits(String fromUnit, String toUnit, String fromUnitValue);
}
