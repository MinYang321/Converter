package com.min.utilities.convert;

import javax.swing.*;

public class TestConverter {

	public static void main(String[] args) {

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
