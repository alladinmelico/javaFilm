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
import java.awt.Canvas;

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
	private String searchInput;
	private JTextField txtDesc;
	private JTextField txtDate;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTable tblCustomer;
	private static DefaultTableModel modelCustomer,modelProduct,modelSup,modelOrder;
	private JTable tblProductNew;
	private JTextField txtSupName;
	private JTextField txtSupAddress;
	private JTextField txtContactNum;
	private JTextField txtSup;
	private JTextField txtSupProd;
	private JTable tblSupOrder;
	private JTable tblSup;
	private JTextField txtSupOrderQnty;
	private JTable tblProdView;
	
	
	public FrameAdmin(DBConnect dbConnect) {
		setBackground(new Color(0, 139, 139));
		getContentPane().setBackground(new Color(0, 139, 139));
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
		panel.setBackground(new Color(0, 139, 139));
		panel.setSize(new Dimension(50, 50));
		panel.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setForeground(new Color(255, 255, 255));
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDashboard.setBounds(10, 11, 153, 28);
		panel.add(lblDashboard);
		
		
		
		JButton btnLogOut_1 = new JButton("Log out");
		btnLogOut_1.setForeground(Color.WHITE);
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
		tabbedPane.setBackground(new Color(175, 238, 238));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlCustomer = new JPanel();
		pnlCustomer.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("Customer", null, pnlCustomer, null);
		pnlCustomer.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(135, 206, 250));
		scrollPane.setBounds(355, 11, 524, 471);
		pnlCustomer.add(scrollPane);
		
		Object[] columnCustomer={"ID","Name","Address","City","Province","Zip","Username"};
		Object[] columnProduct={"ID","strDesc","dtmFilm","intPrice","intOnHand"};
		Object[] columnSup={"ID","Name","Address","Contact Number"};
		Object[] columnOrder={"Order ID","Supplier","Product ID","Description"};
		tblCustomer = new JTable();
		tblCustomer.setBackground(new Color(135, 206, 250));
		scrollPane.setViewportView(tblCustomer);
		
		modelCustomer = new DefaultTableModel();
		modelCustomer.setColumnIdentifiers(columnCustomer);
		tblCustomer.setModel(modelCustomer);
		
		
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
		btnNewButton.setBackground(new Color(34, 139, 34));
		btnNewButton.setForeground(new Color(255, 255, 255));
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
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 215, 0));
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
		btnDelete.setBackground(new Color(165, 42, 42));
		btnDelete.setForeground(new Color(255, 255, 255));
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
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(34, 139, 34));
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
		
		JPanel pnlProduct = new JPanel();
		pnlProduct.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("Product", null, pnlProduct, null);
		pnlProduct.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(new Color(135, 206, 250));
		scrollPane_1.setBounds(355, 11, 524, 471);
		pnlProduct.add(scrollPane_1);
		
		tblProductNew = new JTable();
		tblProductNew.setBackground(new Color(135, 206, 250));
		scrollPane_1.setViewportView(tblProductNew);
		
		modelProduct = new DefaultTableModel();
		modelProduct.setColumnIdentifiers(columnProduct);
		tblProductNew.setModel(modelProduct);
		
		JButton button_1 = new JButton("Search");
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBackground(new Color(50, 205, 50));
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
		button_2.setBackground(new Color(34, 139, 34));
		button_2.setForeground(new Color(255, 255, 255));
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
		button_3.setForeground(new Color(255, 255, 255));
		button_3.setBackground(new Color(255, 215, 0));
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
		button_4.setBackground(new Color(165, 42, 42));
		button_4.setForeground(new Color(255, 255, 255));
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("Supplier", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(33, 65, 78, 14);
		panel_1.add(label_1);
		
		txtSupName = new JTextField();
		txtSupName.setColumns(10);
		txtSupName.setBounds(123, 64, 139, 20);
		panel_1.add(txtSupName);
		
		JButton button = new JButton("Search");
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(34, 139, 34));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				
				searchInput = JOptionPane.showInputDialog(frame, "Input Supplier ID");
				
				ResultSet searchResult = dbConnect.selectQuery("SELECT * FROM tblSupplier WHERE idSup = " + searchInput);
				
				try {
					while (searchResult.next()) {
						txtSupName.setText(searchResult.getString("strName"));
						txtSupAddress.setText(searchResult.getString("strAddress"));
						txtContactNum.setText(String.valueOf(searchResult.getInt("strContactNum")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button.setBounds(272, 63, 73, 23);
		panel_1.add(button);
		
		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress_1.setBounds(33, 96, 63, 14);
		panel_1.add(lblAddress_1);
		
		txtSupAddress = new JTextField();
		txtSupAddress.setColumns(10);
		txtSupAddress.setBounds(123, 95, 139, 20);
		panel_1.add(txtSupAddress);
		
		JLabel lblcontact = new JLabel("Contact #");
		lblcontact.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcontact.setBounds(33, 127, 78, 14);
		panel_1.add(lblcontact);
		
		txtContactNum = new JTextField();
		txtContactNum.setColumns(10);
		txtContactNum.setBounds(123, 126, 139, 20);
		panel_1.add(txtContactNum);
		
		JButton button_5 = new JButton("Create");
		button_5.setForeground(new Color(255, 255, 255));
		button_5.setBackground(new Color(50, 205, 50));
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strName = txtSupName.getText();
				String strAddress = txtSupAddress.getText();
				String strContactNum = txtContactNum.getText();
				
				try {
					dbConnect.supInsert(strName, strAddress, strContactNum);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				showDataSup();
			}
		});
		button_5.setBounds(33, 171, 89, 23);
		panel_1.add(button_5);
		
		JButton button_6 = new JButton("Update");
		button_6.setForeground(new Color(255, 255, 255));
		button_6.setBackground(new Color(255, 215, 0));
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strName = txtSupName.getText();
				String strAddress = txtSupAddress.getText();
				String strContactNum = txtContactNum.getText();
				
				try {
					dbConnect.supUpdate(Integer.parseInt(searchInput),strName, strAddress, strContactNum);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				showDataSup();
			}
		});
		button_6.setBounds(140, 171, 89, 23);
		panel_1.add(button_6);
		
		JButton button_7 = new JButton("Delete");
		button_7.setForeground(new Color(255, 255, 255));
		button_7.setBackground(new Color(165, 42, 42));
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dbConnect.supDelete(Integer.parseInt(searchInput));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				showDataSup();
			}
		});
		button_7.setBounds(256, 171, 89, 23);
		panel_1.add(button_7);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBackground(new Color(135, 206, 250));
		scrollPane_2.setBounds(355, 11, 524, 193);
		panel_1.add(scrollPane_2);
		
		tblSup = new JTable();
		tblSup.setBackground(new Color(135, 206, 250));
		scrollPane_2.setViewportView(tblSup);
		
		JLabel lblSupplierId = new JLabel("Supplier ID");
		lblSupplierId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSupplierId.setBounds(33, 330, 78, 14);
		panel_1.add(lblSupplierId);
		
		txtSup = new JTextField();
		txtSup.setColumns(10);
		txtSup.setBounds(123, 329, 139, 20);
		panel_1.add(txtSup);
		
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProductId.setBounds(33, 361, 63, 14);
		panel_1.add(lblProductId);
		
		txtSupProd = new JTextField();
		txtSupProd.setColumns(10);
		txtSupProd.setBounds(123, 360, 139, 20);
		panel_1.add(txtSupProd);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBackground(new Color(50, 205, 50));
		btnOrder.setForeground(new Color(255, 255, 255));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idSup = Integer.parseInt(txtSup.getText());
				int intQuantity = Integer.parseInt(txtSupOrderQnty.getText());
				int idProd = Integer.parseInt(txtSupProd.getText());
				
				try {
					dbConnect.orderSupTransaction(idSup, intQuantity, idProd);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				showDataProd();
				showDataSupOrder();
			}
		});
		btnOrder.setBounds(256, 436, 89, 23);
		panel_1.add(btnOrder);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBackground(new Color(135, 206, 250));
		scrollPane_3.setBounds(602, 276, 277, 193);
		panel_1.add(scrollPane_3);
		
		tblSupOrder = new JTable();
		tblSupOrder.setBackground(new Color(135, 206, 250));
		scrollPane_3.setViewportView(tblSupOrder);
		
		JLabel lblQuantity_1 = new JLabel("Quantity");
		lblQuantity_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantity_1.setBounds(33, 406, 63, 14);
		panel_1.add(lblQuantity_1);
		
		txtSupOrderQnty = new JTextField();
		txtSupOrderQnty.setColumns(10);
		txtSupOrderQnty.setBounds(123, 405, 139, 20);
		panel_1.add(txtSupOrderQnty);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBackground(new Color(135, 206, 250));
		scrollPane_4.setBounds(355, 276, 237, 193);
		panel_1.add(scrollPane_4);
		
		tblProdView = new JTable();
		tblProdView.setBackground(new Color(135, 206, 250));
		scrollPane_4.setViewportView(tblProdView);
		tblProdView.setModel(modelProduct);
		
		modelSup = new DefaultTableModel();
		modelSup.setColumnIdentifiers(columnSup);
		tblSup.setModel(modelSup);
		
		modelOrder = new DefaultTableModel();
		modelOrder.setColumnIdentifiers(columnOrder);
		tblSupOrder.setModel(modelOrder);
		
		
		
		
		showData();
		showDataProd();
		showDataSup();
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
		DBConnect dbcon = new DBConnect();
		ResultSet resultSet = dbcon.selectQuery("SELECT * FROM tblCustomer");
		
		int numOfRow = 0;
		int rowIterate =0;
		
		try {
			while(resultSet.next()) {
				numOfRow++;
			}
			
			resultSet.beforeFirst();
		
			Object[][] data = new Object[numOfRow][7];
		while(resultSet.next()) {
			data[rowIterate][0]= resultSet.getInt("idCust");
			data[rowIterate][1]= resultSet.getString("strCustName");
			data[rowIterate][2]= resultSet.getString("strAddress");
			data[rowIterate][3]= resultSet.getString("strCity");
			data[rowIterate][4]= resultSet.getString("strProvince");
			data[rowIterate][5]= resultSet.getInt("intZip");
			data[rowIterate][6]= resultSet.getString("strUsername");
			rowIterate++;
		}
		modelCustomer.setRowCount(0);
			for(int i=0; i<numOfRow; i++) {
				modelCustomer.addRow(data[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showDataProd() {
		DBConnect dbcon = new DBConnect();
		ResultSet resultSet = dbcon.selectQuery("SELECT * FROM tblProduct");
		
		int numOfRow = 0;
		int rowIterate =0;
		
		try {
			while(resultSet.next()) {
				numOfRow++;
			}
			
			resultSet.beforeFirst();
		
			Object[][] data = new Object[numOfRow][5];
		while(resultSet.next()) {
			data[rowIterate][0]= resultSet.getInt("idProd");
			data[rowIterate][1]= resultSet.getString("strDesc");
			data[rowIterate][2]= resultSet.getString("dtmFinish");
			data[rowIterate][3]= resultSet.getString("intPrice");
			data[rowIterate][4]= resultSet.getString("intOnHand");
			rowIterate++;
		}
		modelProduct.setRowCount(0);
			for(int i=0; i<numOfRow; i++) {
				modelProduct.addRow(data[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showDataSup() {
		DBConnect dbcon = new DBConnect();
		ResultSet resultSet = dbcon.selectQuery("SELECT * FROM tblSupplier");
		
		int numOfRow = 0;
		int rowIterate =0;
		
		try {
			while(resultSet.next()) {
				numOfRow++;
			}
			
			resultSet.beforeFirst();
		
			Object[][] data = new Object[numOfRow][4];
		while(resultSet.next()) {
			data[rowIterate][0]= resultSet.getInt("idSup");
			data[rowIterate][1]= resultSet.getString("strName");
			data[rowIterate][2]= resultSet.getString("strAddress");
			data[rowIterate][3]= resultSet.getString("strContactNum");
			rowIterate++;
		}
		modelSup.setRowCount(0);
			for(int i=0; i<numOfRow; i++) {
				modelSup.addRow(data[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showDataSupOrder() {
		DBConnect dbcon = new DBConnect();
		ResultSet resultSet = dbcon.selectQuery("SELECT * FROM tblsupplier sup"
				+ " INNER JOIN tblsupplierorder supOrd ON supOrd.tblSupplier_idSup = sup.idSup"
				+ " INNER JOIN tblproduct prod ON prod.idProd = supOrd.tblProduct_idProd");
		
		int numOfRow = 0;
		int rowIterate =0;
		
		try {
			while(resultSet.next()) {
				numOfRow++;
			}
			
			resultSet.beforeFirst();
		
			Object[][] data = new Object[numOfRow][4];
		while(resultSet.next()) {
			data[rowIterate][0]= resultSet.getInt("idSup");
			data[rowIterate][1]= resultSet.getString("strName");
			data[rowIterate][2]= resultSet.getString("idOrder");
			data[rowIterate][3]= resultSet.getString("strDesc");
			rowIterate++;
		}
		modelOrder.setRowCount(0);
			for(int i=0; i<numOfRow; i++) {
				modelOrder.addRow(data[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setFormEventListener(FormEventListener formEventListener) {
		this.formEventListener = formEventListener;
	}
}
