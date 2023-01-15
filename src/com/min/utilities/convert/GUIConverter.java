package com.min.utilities.convert;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GUIConverter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> typeList = new JComboBox<String>(new String[] { "Length", "Temperature" });
	private FactoryConverter fC = new FactoryConverter();
	private AbstractConverter aC;
	JButton[] buttonArray = new JButton[13];
	JLabel placeHolder1 = new JLabel();
	JLabel placeHolder2 = new JLabel();

	public GUIConverter(String[] unitList) {

		initConverterInstance(typeList.getSelectedItem().toString());

		setTitle("Min's Calculator");
		setSize(600, 600);
		setResizable(false);

		JPanel contentPanel = new JPanel();
		JPanel headerPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel footerPanel = new JPanel();

		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		headerPanel.setPreferredSize(new Dimension(600, 50));
		displayPanel.setPreferredSize(new Dimension(250, 500));
		footerPanel.setPreferredSize(new Dimension(600, 50));

		add(contentPanel);
		contentPanel.setLayout(new BorderLayout(10, 5));
		contentPanel.add(headerPanel, BorderLayout.NORTH);
		contentPanel.add(displayPanel, BorderLayout.WEST);
		contentPanel.add(buttonPanel, BorderLayout.CENTER);
		contentPanel.add(footerPanel, BorderLayout.SOUTH);
		typeList.setPreferredSize(new Dimension(120, 30));
		JLabel headerLabel = new JLabel("Converter");
		headerLabel.setFont(new Font("San-seif", Font.BOLD, 20));
		headerPanel.add(typeList);
		headerPanel.add(headerLabel);

		JComboBox<String> jcbFromList = new JComboBox<String>();
		jcbFromList.setModel(new DefaultComboBoxModel<String>(unitList));
		jcbFromList.setSize(150, 20);
		JLabel fromUnitValue = new JLabel("0", SwingConstants.LEFT);
		fromUnitValue.setFont(new Font("San-seif", Font.BOLD, 50));
		fromUnitValue.setPreferredSize(new Dimension(150, 200));

		JComboBox<String> jcbToList = new JComboBox<String>();
		jcbToList.setModel(new DefaultComboBoxModel<String>(unitList));
		jcbToList.setPreferredSize(new Dimension(150, 20));
		JLabel toUnitValue = new JLabel("0", SwingConstants.LEFT);
		toUnitValue.setFont(new Font("San-seif", Font.BOLD, 50));
		toUnitValue.setPreferredSize(new Dimension(150, 200));

		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		displayPanel.add(fromUnitValue);
		displayPanel.add(jcbFromList);
		displayPanel.add(toUnitValue);
		displayPanel.add(jcbToList);

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
			aC = fC.createConverter(typeList.getSelectedItem().toString());
			switch (typeList.getSelectedItem().toString()) {
			case "Length":
				aC = (LengthConverter) fC.createConverter(typeList.getSelectedItem().toString());
				break;
			}
		});

		fromUnitValue.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Double result = aC.convertUnits(jcbFromList.getSelectedItem().toString(),
						jcbToList.getSelectedItem().toString(), fromUnitValue.getText());
				toUnitValue.setText(result.toString());

			}
		});
	}

	/*
	 * fromUnitValue.getDocument().addDocumentListener(new DocumentListener() {
	 * 
	 * @Override public void insertUpdate(DocumentEvent e) { evaluateValue();
	 * System.out.println(fromUnitValue.getText()); }
	 * 
	 * @Override public void removeUpdate(DocumentEvent e) { evaluateValue();
	 * //System.out.println(fromValue);
	 * 
	 * }
	 * 
	 * @Override public void changedUpdate(DocumentEvent e) { evaluateValue();
	 * //System.out.println(fromValue); }
	 * 
	 * public void evaluateValue() { Double result =
	 * aC.convertUnits(jcbFromList.getSelectedItem().toString(),
	 * jcbToList.getSelectedItem().toString(), fromUnitValue.getText());
	 * toUnitValue.setText(result.toString()); }
	 * 
	 * });
	 */

	/**
	 * 
	 * @param type
	 */
	public void initConverterInstance(String type) {
		switch (type) {
		case "Length":
			aC = fC.createConverter(typeList.getSelectedItem().toString());
		}
	}
}
