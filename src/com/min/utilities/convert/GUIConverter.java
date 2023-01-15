package com.min.utilities.convert;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GUIConverter extends JFrame {
	
	/**
	 * fields declaration
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> typeList = new JComboBox<String>(new String[] { "Length", "Temperature" ,"Weight" });
	private FactoryConverter fC = new FactoryConverter();
	private AbstractConverter aC = null;
	private LengthConverter lC = null;
	private TemperatureConverter tC = null;
	private WeightConverter wC = null;
	JButton[] buttonArray = null;
	JLabel placeHolder1 = null;
	JLabel placeHolder2 = null;
	JLabel headerLabel = null;
	JLabel fromUnitValue = null;
	JLabel toUnitValue = null;
	JPanel contentPanel = null;
	JPanel headerPanel = null;
	JPanel displayPanel = null;
	JPanel buttonPanel = null;
	JPanel footerPanel = null;
	JComboBox<String> jcbFromList = null;
	JComboBox<String> jcbToList = null;

	
	public GUIConverter(String[] unitList) {

		aC = fC.createConverter(typeList.getSelectedItem().toString());
		initJComponents();
		
		setTitle("Min's Converter");
		setSize(700, 600);
		//setResizable(false);

		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		headerPanel.setPreferredSize(new Dimension(600, 50));
		buttonPanel.setPreferredSize(new Dimension(350, 500));
		footerPanel.setPreferredSize(new Dimension(600, 50));

		add(contentPanel);
		contentPanel.setLayout(new BorderLayout(10, 5));
		contentPanel.add(headerPanel, BorderLayout.NORTH);
		contentPanel.add(displayPanel, BorderLayout.CENTER);
		contentPanel.add(buttonPanel, BorderLayout.EAST);
		contentPanel.add(footerPanel, BorderLayout.SOUTH);
		typeList.setPreferredSize(new Dimension(120, 30));
		
		headerLabel.setFont(new Font("San-seif", Font.BOLD, 20));
		headerPanel.add(typeList);
		headerPanel.add(headerLabel);
		
		jcbFromList.setModel(new DefaultComboBoxModel<String>(unitList));
		jcbFromList.setSize(150, 20);
		fromUnitValue.setFont(new Font("San-seif", Font.BOLD, 50));
		fromUnitValue.setPreferredSize(new Dimension(150, 200));

		jcbToList.setModel(new DefaultComboBoxModel<String>(unitList));
		jcbToList.setPreferredSize(new Dimension(150, 20));
		toUnitValue.setFont(new Font("San-seif", Font.BOLD, 50));
		toUnitValue.setPreferredSize(new Dimension(150, 200));

		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		displayPanel.add(fromUnitValue);
		displayPanel.add(jcbFromList);
		displayPanel.add(toUnitValue);
		displayPanel.add(jcbToList);
		setDefaultFromToUnitPairOnLoad();

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

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createEventHandlers();
	}
	
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
		
		jcbFromList = new JComboBox<String>();
		fromUnitValue = new JLabel("0", SwingConstants.LEFT);
		
		jcbToList = new JComboBox<String>();
		toUnitValue = new JLabel("0", SwingConstants.LEFT);
		
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
	
	
	public void setDefaultFromToUnitPairOnLoad() {
		String currentInstanceName = aC.getClass().getSimpleName();
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
	
	
	public void createEventHandlers() {
		for (int i = 2; i <= 11; i++) {
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

		buttonArray[0].addActionListener(e -> {
			fromUnitValue.setText("0");
			toUnitValue.setText("0");
		});

		buttonArray[1].addActionListener(e -> {
			String fromStr = fromUnitValue.getText();
			int originalLength = fromStr.length();
			if (originalLength > 1)
				fromUnitValue.setText(fromStr.substring(0, originalLength - 1));
			if (originalLength == 1)
				fromUnitValue.setText("0");
		});

		buttonArray[12].addActionListener(e -> {
			String fromStr = fromUnitValue.getText();
			fromStr += buttonArray[12].getText();
			fromUnitValue.setText(fromStr);
		});

		typeList.addItemListener(e -> {
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
			aC = fC.createConverter(typeList.getSelectedItem().toString());
			setDefaultFromToUnitPairOnLoad();
		});
		
		jcbFromList.addItemListener(e -> {
			evaluate();
		});
		
		jcbToList.addItemListener(e -> {
			evaluate();
		});

		fromUnitValue.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				evaluate();
			}
		});
	}
	
	public void evaluate() {
		try {
			String result = aC.convertUnits(jcbFromList.getSelectedItem().toString(),
					jcbToList.getSelectedItem().toString(), fromUnitValue.getText());
			toUnitValue.setText(result);
		} catch (Exception e) {
			toUnitValue.setText("Error");
		}
	}
}
