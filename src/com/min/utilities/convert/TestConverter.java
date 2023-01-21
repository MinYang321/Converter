package com.min.utilities.convert;

import javax.swing.*;
/**
 * Test class to launch the GUI, verify the data models and operation logics
 * @author Min Yang
 * @version 1.0
 * @since 11.0.15
 */
public class TestConverter {

	public static void main(String[] args) {
		
		/**
		 * Use static async method to launch the GUI class
		 */
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FactoryConverter myFC = new FactoryConverter();
				LengthConverter myLC = (LengthConverter) myFC.createConverter("Length");
				new GUIConverter(myLC.getLENGTH_UNIT_LIST());
			}
		});

	}
}
