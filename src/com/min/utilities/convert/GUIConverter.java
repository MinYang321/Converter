/*
 * Author: Min Yang
 * Course:CST8284-OOP
 * Date: 21 Jan,2023
 * Project: Converter
 */
package com.min.utilities.convert;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

/**
 * Build a concrete class inheriting the Swing JFrame.
 * The layout of the components reference to Windows 10 intrinsic converter application
 * Four types of Swing layouts were used to fulfill the layout intension.
 * BorderLayout is used to set the general layout of four JPanels
 * FlowLayout is used to set the header JPanel
 * BoxLayout is used to set the display JPanel
 * GridLayout is used to set the button JPanel
 * Worker method createEventHandlers() is the core of the class, where events of all
 * JComponents (click, change of property) were handled with appropriate actions as intended.
 * @author Min Yang
 * @version 1.0
 * @since 11.0.1
 * @see java.beans.PropertyChangeEvent
 * @see java.beans.PropertyChangeListener
 */
public class GUIConverter extends JFrame {
	
	//GUI Swing components(JPanel, JComboBox, JLabeL, etc.) declaration & initialization
	private static final long serialVersionUID = 1L;
	private JComboBox<String> typeList = new JComboBox<String>(new String[] { "Length", "Temperature" ,"Weight" });
	private FactoryConverter fC = new FactoryConverter(); // create a converter factory reference and set to null
	private AbstractConverter aC = null; // create a abstract factory reference and set to null
	private LengthConverter lC = null; // create a length factory reference and set to null
	private TemperatureConverter tC = null; // create a temperature factory reference and set to null
	private WeightConverter wC = null; // create a weight factory reference and set to null
	JButton[] buttonArray = null;
	JLabel placeHolder1 = null;
	JLabel placeHolder2 = null;
	JLabel headerLabel = null;
	JLabel footerLabel = null;
	JLabel fromUnitValue = null;
	JLabel toUnitValue = null;
	JPanel contentPanel = null;
	JPanel headerPanel = null;
	JPanel displayPanel = null;
	JPanel buttonPanel = null;
	JPanel footerPanel = null;
	JComboBox<String> jcbFromList = null;
	JComboBox<String> jcbToList = null;

	/**
	 * Constructor to load all needed GUI components
	 * @param unitList intakes a unit list string array
	 */
	public GUIConverter(String[] unitList) {

		aC = fC.createConverter(typeList.getSelectedItem().toString());
		initJComponents(); //call below defined function initJComponents() to load and initialize JComponents
		
		setTitle("Min's Converter"); //set title of the GUI window
		setSize(700, 600); //set size of the GUI window
		
		//Using FlowLayout to set layout of header and footer
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		headerPanel.setPreferredSize(new Dimension(600, 50));
		buttonPanel.setPreferredSize(new Dimension(350, 500));
		footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		footerPanel.setPreferredSize(new Dimension(600, 50));

		//Add four sub JPanel (header, display, button, footer) to main content Panel using BorderLayout
		add(contentPanel);
		contentPanel.setLayout(new BorderLayout(10, 5));
		contentPanel.add(headerPanel, BorderLayout.NORTH); //header panel is located in North/Top
		contentPanel.add(displayPanel, BorderLayout.CENTER); // display panel is located in Center (center-left)
		contentPanel.add(buttonPanel, BorderLayout.EAST); //button panel is located in East (center-right)
		contentPanel.add(footerPanel, BorderLayout.SOUTH); //footer panel is located in South/Bottom
		typeList.setPreferredSize(new Dimension(120, 30));
		
		//set the style and content of the header label and add it to header panel
		headerLabel.setFont(new Font("San-seif", Font.BOLD, 20));
		headerPanel.add(typeList);
		headerPanel.add(headerLabel);
		
		//set the style and content of the footer label and add it to footer panel
		footerLabel.setFont(new Font("San-seif", Font.PLAIN, 15));
		footerPanel.add(footerLabel);
		
		//set the style and content of 'from' label and 'from' drop-down unit list
		jcbFromList.setModel(new DefaultComboBoxModel<String>(unitList));
		jcbFromList.setSize(150, 20);
		fromUnitValue.setFont(new Font("San-seif", Font.BOLD, 50));
		fromUnitValue.setPreferredSize(new Dimension(150, 200));

		//set the style and content of 'to' label and 'to' drop-down unit list
		jcbToList.setModel(new DefaultComboBoxModel<String>(unitList));
		jcbToList.setPreferredSize(new Dimension(150, 20));
		toUnitValue.setFont(new Font("San-seif", Font.BOLD, 50));
		toUnitValue.setPreferredSize(new Dimension(150, 200));

		//add 'from' drop-down list & label to display panel
		//add 'to' drop-down list & label to displya panel
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		displayPanel.add(fromUnitValue);
		displayPanel.add(jcbFromList);
		displayPanel.add(toUnitValue);
		displayPanel.add(jcbToList);
		setDefaultFromToUnitPairOnLoad();//call below defined function to set the default from & to unit pair upon loading
		
		//set style and layout of each individual JButton
		//add each JButton to Button Panel
		for (JButton ea : buttonArray) {
			ea.setFont(new Font("San-seif", Font.PLAIN, 20));
		}
		buttonPanel.setLayout(new GridLayout(5, 3, 1, 1));
		buttonPanel.add(placeHolder1);
		for (int i = 0; i <= 10; i++) {
			buttonPanel.add(buttonArray[i]);
		}
		buttonPanel.add(placeHolder2);
		buttonPanel.add(buttonArray[11]);
		buttonPanel.add(buttonArray[12]);

		//set main GUI frame visible to user
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//call below defined event handler function to handle the events occurred on each component
		createEventHandlers();
	}
	
	/**
	 * Declare  and initialize all required JComponents
	 */
	public void initJComponents() {
		buttonArray = new JButton[13];
		placeHolder1 = new JLabel();
		placeHolder2 = new JLabel();
		contentPanel = new JPanel();
		headerPanel = new JPanel();
		displayPanel = new JPanel();
		buttonPanel = new JPanel();
		footerPanel = new JPanel();
		
		headerLabel = new JLabel("Converter");
		footerLabel = new JLabel("\u00a9" + Calendar.getInstance().get(Calendar.YEAR) + " Min Yang" );
		
		jcbFromList = new JComboBox<String>();
		fromUnitValue = new JLabel("0", SwingConstants.LEFT);
		
		jcbToList = new JComboBox<String>();
		toUnitValue = new JLabel("0", SwingConstants.LEFT);
		
		//buttons initialization showing the appropriate literals and digits
		buttonArray[0] = new JButton("CE");
		buttonArray[1] = new JButton("BS");
		buttonArray[2] = new JButton("7");
		buttonArray[3] = new JButton("8");
		buttonArray[4] = new JButton("9");
		buttonArray[5] = new JButton("4");
		buttonArray[6] = new JButton("5");
		buttonArray[7] = new JButton("6");
		buttonArray[8] = new JButton("1");
		buttonArray[9] = new JButton("2");
		buttonArray[10] = new JButton("3");
		buttonArray[11] = new JButton("0");
		buttonArray[12] = new JButton(".");
	}
	
	//set the default from & to unit pair upon the loading of GUI
	public void setDefaultFromToUnitPairOnLoad() {
		String currentInstanceName = aC.getClass().getSimpleName();//get the current converter type
		switch(currentInstanceName) {
		case "LengthConverter":
			jcbFromList.setSelectedIndex(6); //set to Inches unit
			jcbToList.setSelectedIndex(3); //set to Centimeters unit
		    break;
		case "TemperatureConverter":
			jcbFromList.setSelectedIndex(0); //set to Celcius
			jcbToList.setSelectedIndex(1); //set to Fahrenheit
		    break;
		case "WeightConverter":
			jcbFromList.setSelectedIndex(7); //set to Kilograms unit
			jcbToList.setSelectedIndex(10); //set to Pounds unit
		    break;
		}
	}
	
	/**
	 * Core worker method to the whole application
	 * For each JComponent, corresponding event handler is bound to them,
	 * by calling the proper event listener
	 */
	public void createEventHandlers() {
		
		/**
		 * Event handlers for all JButton components
		 */
		for (int i = 2; i <= 11; i++) { //for all number (0-9) buttons
			JButton element = buttonArray[i];
			element.addActionListener(e -> {
				String fromStr = fromUnitValue.getText();
				if (fromStr.equals("0")) {
					fromStr = element.getText();
				} else {
					fromStr += element.getText();
				}
				fromUnitValue.setText(fromStr);
			});
		}
		buttonArray[0].addActionListener(e -> { //for CE (clear) button
			fromUnitValue.setText("0");
			toUnitValue.setText("0");
		});
		buttonArray[1].addActionListener(e -> { //for BS (Backspace) button
			String fromStr = fromUnitValue.getText();
			int originalLength = fromStr.length();
			if (originalLength > 1)
				fromUnitValue.setText(fromStr.substring(0, originalLength - 1));
			if (originalLength == 1)
				fromUnitValue.setText("0");
		});
		buttonArray[12].addActionListener(e -> { //for decimal point (.) button
			String fromStr = fromUnitValue.getText();
			fromStr += buttonArray[12].getText();
			fromUnitValue.setText(fromStr);
		});

		/**
		 * Event handler for choosing converter type from the drop-down type list
		 */
		typeList.addItemListener(e -> {
			
			//switch case block to change the 'from' and 'to' unit list according to selected converter type
			switch (typeList.getSelectedItem().toString()) {
			case "Length":
				lC = (LengthConverter) fC.createConverter(typeList.getSelectedItem().toString());
				jcbFromList.setModel(new DefaultComboBoxModel<String>(lC.getLENGTH_UNIT_LIST()));
				jcbToList.setModel(new DefaultComboBoxModel<String>(lC.getLENGTH_UNIT_LIST()));
				break;
			case "Temperature":
				tC = (TemperatureConverter) fC.createConverter(typeList.getSelectedItem().toString());
				jcbFromList.setModel(new DefaultComboBoxModel<String>(tC.getTEMPERATURE_UNIT_LIST()));
				jcbToList.setModel(new DefaultComboBoxModel<String>(tC.getTEMPERATURE_UNIT_LIST()));
				break;
			case "Weight":
				wC = (WeightConverter) fC.createConverter(typeList.getSelectedItem().toString());
				jcbFromList.setModel(new DefaultComboBoxModel<String>(wC.getWEIGHT_UNIT_LIST()));
				jcbToList.setModel(new DefaultComboBoxModel<String>(wC.getWEIGHT_UNIT_LIST()));
				break;
			default:
				throw new IllegalArgumentException("No such converter exists");
			}
			
			/**
			 * Call the converter factory class method to
			 * instantiate the concrete converter according to selected converter type
			 */
			aC = fC.createConverter(typeList.getSelectedItem().toString()); 

			setDefaultFromToUnitPairOnLoad(); //call the function to set the default 'from' and 'to' unit upon loading
		});
		
		/**
		 * Event handler for the 'from' unit list
		 */
		jcbFromList.addItemListener(e -> {
			evaluate();
		});
		
		/**
		 * Event handler for the 'to' unit list
		 */
		jcbToList.addItemListener(e -> {
			evaluate();
		});

		/**
		 * Event handler for the change of values in 'from' label
		 * The value change is caused and triggered by clicking the buttons in the JButton Panel
		 */
		fromUnitValue.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				evaluate();
			}
		});
	}
	
	//
	/**
	 * The ultimate function to compute the value of 'to' unit,
	 * by calling the overridden worker methods convertUnits()
	 * The implementation of convertUnits() method depends on the type of current(selected)
	 * class (Length, Temperature or Weight...).
	 * This is the core method of realizing polymorphism
	 */
	public void evaluate() {
		try {
			//
			
			//reflecting the nature of polymorphism
			/**
			 * A superclass reference dynamically bound to its concrete sub class, so
			 * as to execute the different version of implementation of convertUnits() method.
			 * Here embodies the essence of polymorphism
			 */
			String result = aC.convertUnits(jcbFromList.getSelectedItem().toString(),
					jcbToList.getSelectedItem().toString(), fromUnitValue.getText());
			toUnitValue.setText(result);
		} catch (Exception e) {
			toUnitValue.setText("Error");
		}
	}
}
