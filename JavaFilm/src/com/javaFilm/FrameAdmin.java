package com.javaFilm;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;

public class FrameAdmin  extends JFrame {
	DBConnect dbConnect;
	private JTable tblProduct;
	private static DefaultTableModel modelCust,modelProd;
	private JTable tblCust;
	private ResultSet result;
	private static int rowCount = 0;
	private static int rowSelected = 0;
	private FormEventListener formEventListener;
	private static boolean toInsert = false;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtProvince;
	private JTextField txtZip;
	private JTextField txtUsername;
	private JTextArea txtArea;
	private String searchInput;
	private JTextField txtDesc;
	private JTextField txtDate;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	JTextArea textAreaProd;
	
	public FrameAdmin(DBConnect dbConnect) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
		});
		
		this.dbConnect  = dbConnect;
		setResizable(false);
		setSize(new Dimension(900, 600));
		setTitle("ADMIN: Pine Valley Furniture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setSize(new Dimension(50, 50));
		panel.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDashboard.setBounds(10, 11, 153, 28);
		panel.add(lblDashboard);
		
		
		
		JButton btnLogOut_1 = new JButton("Log out");
		btnLogOut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormEvent formEvent = new FormEvent(this, true);
				
				if (formEventListener != null) {
					formEventListener.formEventOccured(formEvent);
				}
				setVisible(false);
			}
		});
		btnLogOut_1.setBackground(SystemColor.windowBorder);
		btnLogOut_1.setBounds(795, 20, 89, 23);
		panel.add(btnLogOut_1);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlCustomer = new JPanel();
		tabbedPane.addTab("Customer", null, pnlCustomer, null);
		pnlCustomer.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(355, 11, 524, 471);
		pnlCustomer.add(scrollPane);
		
		 txtArea = new JTextArea();
		scrollPane.setViewportView(txtArea);
		
		txtName = new JTextField();
		txtName.setBounds(123, 64, 139, 20);
		pnlCustomer.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(33, 65, 78, 14);
		pnlCustomer.add(lblName);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(123, 95, 139, 20);
		pnlCustomer.add(txtAddress);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(33, 96, 78, 14);
		pnlCustomer.add(lblAddress);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(123, 126, 139, 20);
		pnlCustomer.add(txtCity);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCity.setBounds(33, 127, 78, 14);
		pnlCustomer.add(lblCity);
		
		txtProvince = new JTextField();
		txtProvince.setColumns(10);
		txtProvince.setBounds(123, 157, 139, 20);
		pnlCustomer.add(txtProvince);
		
		JLabel lblProvince = new JLabel("Province");
		lblProvince.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProvince.setBounds(33, 158, 78, 14);
		pnlCustomer.add(lblProvince);
		
		txtZip = new JTextField();
		txtZip.setColumns(10);
		txtZip.setBounds(123, 188, 139, 20);
		pnlCustomer.add(txtZip);
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblZip.setBounds(33, 189, 78, 14);
		pnlCustomer.add(lblZip);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(123, 219, 139, 20);
		pnlCustomer.add(txtUsername);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(33, 220, 78, 14);
		pnlCustomer.add(lblUsername);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strCustName = txtName.getText();
				String strAddress = txtAddress.getText();
				String strCity = txtCity.getText();
				String strProvince = txtProvince.getText();
				int intZip = Integer.parseInt(txtZip.getText());
				String strUserName = txtUsername.getText();
				JFrame frame = new JFrame();
				String strPasscode = JOptionPane.showInputDialog(frame, "Password");
				
				try {
					dbConnect.custInsert(strCustName, strAddress, strCity, strProvince, intZip, strUserName, strPasscode);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				clearInputs();
				showData();
			}
		});
		btnNewButton.setBounds(33, 313, 89, 23);
		pnlCustomer.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strCustName = txtName.getText();
				String strAddress = txtAddress.getText();
				String strCity = txtCity.getText();
				String strProvince = txtProvince.getText();
				int intZip = Integer.parseInt(txtZip.getText());
				String strUserName = txtUsername.getText();
				JFrame frame = new JFrame();
				String strPasscode = JOptionPane.showInputDialog(frame, "Password");
				
				try {
					dbConnect.custUpdate(Integer.parseInt(searchInput), strCustName, 
							strAddress, strCity, strProvince, intZip, strUserName, strPasscode);
					} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				clearInputs();
				showData();
			}
		});
		btnNewButton_1.setBounds(140, 313, 89, 23);
		pnlCustomer.add(btnNewButton_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dbConnect.custDelete(Integer.parseInt(searchInput));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				clearInputs();
				showData();
			}
		});
		btnDelete.setBounds(256, 313, 89, 23);
		pnlCustomer.add(btnDelete);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				searchInput = JOptionPane.showInputDialog(frame, "Input Customer ID");
				
				ResultSet searchResult = dbConnect.selectQuery("SELECT * FROM tblCustomer WHERE idCust = " + searchInput);
				
				try {
					while (searchResult.next()) {
						txtName.setText(searchResult.getString("strCustName"));
						txtAddress.setText(searchResult.getString("strAddress"));
						txtCity.setText(searchResult.getString("strCity"));
						txtProvince.setText(searchResult.getString("strProvince"));
						txtZip.setText(Integer.toString(searchResult.getInt("intZip")));
						txtUsername.setText(searchResult.getString("strUserName"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnSearch.setBounds(272, 63, 73, 23);
		pnlCustomer.add(btnSearch);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});
		btnView.setBounds(256, 13, 89, 23);
		pnlCustomer.add(btnView);
		
		JPanel pnlProduct = new JPanel();
		tabbedPane.addTab("Product", null, pnlProduct, null);
		pnlProduct.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(355, 11, 524, 471);
		pnlProduct.add(scrollPane_1);
		
		textAreaProd = new JTextArea();
		scrollPane_1.setViewportView(textAreaProd);
		
		JButton button = new JButton("View");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDataProd();
				clearInputs();
			}
		});
		button.setBounds(256, 13, 89, 23);
		pnlProduct.add(button);
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				
				searchInput = JOptionPane.showInputDialog(frame, "Input Product ID");
				
				ResultSet searchResult = dbConnect.selectQuery("SELECT * FROM tblProduct WHERE idProd = " + searchInput);
				
				try {
					while (searchResult.next()) {
						txtDesc.setText(searchResult.getString("strDesc"));
						txtDate.setText(searchResult.getString("dtmFinish"));
						txtPrice.setText(String.valueOf(searchResult.getInt("intPrice")));
						txtQuantity.setText(String.valueOf(searchResult.getInt("intOnHand")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_1.setBounds(272, 63, 73, 23);
		pnlProduct.add(button_1);
		
		JLabel label = new JLabel("Name");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(33, 65, 78, 14);
		pnlProduct.add(label);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(123, 64, 139, 20);
		pnlProduct.add(txtDesc);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(33, 96, 63, 14);
		pnlProduct.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(123, 95, 139, 20);
		pnlProduct.add(txtDate);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrice.setBounds(33, 127, 78, 14);
		pnlProduct.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(123, 126, 139, 20);
		pnlProduct.add(txtPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuantity.setBounds(33, 158, 78, 14);
		pnlProduct.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(123, 157, 139, 20);
		pnlProduct.add(txtQuantity);
		
		JButton button_2 = new JButton("Create");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strDesc = txtDesc.getText();
				String dtmFinish = txtDate.getText();
				int intPrice = Integer.parseInt(txtPrice.getText());
				int intOnHand = Integer.parseInt(txtQuantity.getText());
				
				try {
					dbConnect.prodInsert(strDesc, dtmFinish, intPrice, intOnHand);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				showDataProd();
				clearInputs();
			}
		});
		button_2.setBounds(33, 313, 89, 23);
		pnlProduct.add(button_2);
		
		JButton button_3 = new JButton("Update");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strDesc = txtDesc.getText();
				String dtmFinish = txtDate.getText();
				int intPrice = Integer.parseInt(txtPrice.getText());
				int intOnHand = Integer.parseInt(txtQuantity.getText());
				
				try {
					dbConnect.prodUpdate(Integer.parseInt(searchInput), strDesc, dtmFinish, intPrice, intOnHand);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				showDataProd();
				clearInputs();
			}
		});
		button_3.setBounds(140, 313, 89, 23);
		pnlProduct.add(button_3);
		
		JButton button_4 = new JButton("Delete");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dbConnect.prodDelete(Integer.parseInt(searchInput));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				showDataProd();
				clearInputs();
			}
		});
		button_4.setBounds(256, 313, 89, 23);
		pnlProduct.add(button_4);
		
		JLabel lblYearmonthday = new JLabel("year-month-day");
		lblYearmonthday.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblYearmonthday.setBounds(272, 99, 63, 14);
		pnlProduct.add(lblYearmonthday);
		
		showData();
		showDataProd();
	}
	
	public void clearInputs() {
		txtName.setText("");
		txtAddress.setText("");
		txtCity.setText("");
		txtProvince.setText("");
		txtZip.setText("");
		txtUsername.setText("");
		txtDesc.setText("");
		txtDate.setText("");
		txtPrice.setText("");
		txtQuantity.setText("");
	}
		
	public void showData() {
		txtArea.setText("");
		DBConnect dbcon = new DBConnect();
		ResultSet resultSet = dbcon.selectQuery("SELECT * FROM tblCustomer");
		
		try {
			while(resultSet.next()) {
				txtArea.append("\n\nID:\t" + resultSet.getString("idCust"));
				txtArea.append("\nName:\t" + resultSet.getString("strCustName"));
				txtArea.append("\nAddress:\t" + resultSet.getString("strAddress"));
				txtArea.append("\nCity:\t" + resultSet.getString("strCity"));
				txtArea.append("\nstrProvince:\t" + resultSet.getString("strProvince"));
				txtArea.append("\nZip:\t" + resultSet.getString("intZip"));
				txtArea.append("\nUsername:\t" + resultSet.getString("strCustName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showDataProd() {
		textAreaProd.setText("");
		DBConnect dbcon = new DBConnect();
		ResultSet resultSet = dbcon.selectQuery("SELECT * FROM tblProduct");
		
		try {
			while(resultSet.next()) {
				textAreaProd.append("\n\nID:\t" + resultSet.getString("idProd"));
				textAreaProd.append("\nName:\t" + resultSet.getString("strDesc"));
				textAreaProd.append("\nDate:\t" + resultSet.getString("dtmFinish"));
				textAreaProd.append("\nPrice:\t" + resultSet.getString("intPrice"));
				textAreaProd.append("\nQuantity:\t" + resultSet.getString("intOnHand"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setFormEventListener(FormEventListener formEventListener) {
		this.formEventListener = formEventListener;
	}
}
