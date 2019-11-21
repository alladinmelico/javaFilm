package com.javaFilm;

import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;



public class FrameUser extends JFrame {

	DBConnect dbConnect;
	private HashMap<String, Integer> map;
	private JTextField txtShopQnty;
	private JTextField txtCartQnty;
	private JTable tblShop;
	private JTable tblCart;
	private static DefaultTableModel modelShop,modelCart,modelOrder;
	private Product product;
	private int shopSelected;
	private Customer customer;
	private JTable tblOrder;

	private FormEventListener formEventListener;
	
	public FrameUser(DBConnect dbConnect) {
		product = new Product();
		
		
		this.dbConnect  = dbConnect;
		setResizable(false);
		setSize(new Dimension(1024, 768));
		setTitle("Pine Valley Furniture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(50, 50));
		panel.setSize(new Dimension(50, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 11, 220, 28);
		panel.add(lblNewLabel);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormEvent formEvent = new FormEvent(this, true);
				
				if (formEventListener != null) {
					formEventListener.formEventOccured(formEvent);
				}
				setVisible(false);
			}
		});
		btnLogOut.setBounds(919, 20, 89, 23);
		panel.add(btnLogOut);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showOrder();
			}
		});
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Shop", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 447, 573);
		panel_1.add(scrollPane);
		
		Object[] columnShop={"ID","Description","Date Finish","Price","Quantity"};
		Object[] columnCart={"ID","Description","Date Finish","Price","Quantity","Total"}; 
		
		tblShop = new JTable();
		
		tblShop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblShop.setCellSelectionEnabled(true);
		scrollPane.setViewportView(tblShop);
		
		modelShop = new DefaultTableModel();
		modelCart = new DefaultTableModel();
		modelOrder = new DefaultTableModel();
		
		modelShop.setColumnIdentifiers(columnShop);
		modelCart.setColumnIdentifiers(columnCart);
		modelOrder.setColumnIdentifiers(columnShop);
		
		
		tblShop.setModel(modelShop);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(556, 77, 447, 573);
		panel_1.add(scrollPane_1);
		
		tblCart = new JTable();
		
		scrollPane_1.setViewportView(tblCart);
		tblCart.setModel(modelCart);
		
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 26, 46, 20);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantity.setBounds(106, 26, 86, 20);
		panel_1.add(lblQuantity);
		
		txtShopQnty = new JTextField();
		txtShopQnty.setColumns(10);
		txtShopQnty.setBounds(106, 46, 86, 20);
		panel_1.add(txtShopQnty);
		
		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(txtShopQnty.getText().equals("0") || txtShopQnty.getText().equals(""))) {
					
					int id = (int) tblShop.getModel().getValueAt(shopSelected, 0);
					String desc = tblShop.getModel().getValueAt(shopSelected, 1).toString();
					String finish = tblShop.getModel().getValueAt(shopSelected, 2).toString();
					int price = (int) tblShop.getModel().getValueAt(shopSelected, 3);
					int onHand = (int) tblShop.getModel().getValueAt(shopSelected, 4);
					int qnty = Integer.parseInt(txtShopQnty.getText());
					product.addToCart(new Product(id,desc,finish,price,onHand,qnty));
					showOnCart();
				}
			}
		});
		btnAddToCart.setBounds(328, 43, 129, 23);
		panel_1.add(btnAddToCart);
		
		JLabel label = new JLabel("ID");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(556, 26, 46, 20);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Quantity");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(612, 26, 86, 20);
		panel_1.add(label_1);
		
		txtCartQnty = new JTextField();
		txtCartQnty.setColumns(10);
		txtCartQnty.setBounds(612, 46, 86, 20);
		panel_1.add(txtCartQnty);
		
		JButton btnCheckOut = new JButton("Check out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Product prod: product.getCart()) {
					try {
						dbConnect.orderTransaction(customer.getIdCust(), prod.getQnty(), prod.getId());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnCheckOut.setBounds(914, 26, 89, 40);
		panel_1.add(btnCheckOut);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(708, 43, 81, 23);
		panel_1.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		
		btnDelete.setBounds(799, 43, 102, 23);
		panel_1.add(btnDelete);
		
		JLabel lblCartDesc = new JLabel("ID");
		lblCartDesc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCartDesc.setBounds(556, 43, 46, 20);
		panel_1.add(lblCartDesc);
		
		JLabel lblDesc = new JLabel("ID");
		lblDesc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDesc.setBounds(10, 46, 46, 20);
		panel_1.add(lblDesc);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Purchase History", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 296, 639);
		panel_2.add(scrollPane_2);
		
		tblOrder = new JTable();
		
		scrollPane_2.setViewportView(tblOrder);
		
		tblOrder.setModel(modelOrder);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(327, 11, 676, 639);
		panel_2.add(scrollPane_3);
		
		JTextArea textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int toDel = 0;
				ArrayList<Product> prod = product.getCart();
				
				for (int i=0 ; i<prod.size(); i++) {
					if (prod.get(i).getId() == Integer.parseInt(tblCart.getModel().getValueAt(i, 0).toString())) {
						toDel = prod.get(i).getId();
						break;
					}
				}
				
				product.deleteFromCart(toDel);
				showOnCart();
			}
		});
		
		tblShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			      int rowIndex = tblShop.getSelectedRow();
			      int colIndex = tblShop.getSelectedColumn();
			      shopSelected = rowIndex;
			      String value = tblShop.getModel().getValueAt(rowIndex, 0).toString();
			      lblDesc.setText(String.valueOf(value));
			}
		});
		
		tblCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tblCart.getSelectedRow();
		      int colIndex = tblCart.getSelectedColumn();
		      shopSelected = rowIndex;
		      String value = tblCart.getModel().getValueAt(rowIndex, 0).toString();
		      lblCartDesc.setText(String.valueOf(value));
		      txtCartQnty.setText(tblCart.getModel().getValueAt(rowIndex, 4).toString());
		      showOnCart();
			}
		});
		
	
		tblOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tblOrder.getSelectedRow();
			      int colIndex = tblOrder.getSelectedColumn();
			      shopSelected = rowIndex;
			      String value = tblOrder.getModel().getValueAt(rowIndex, 0).toString();

			      textArea.append("\t\tCUSTOMER INFO:");
			      textArea.append("\nID:\t" + customer.getIdCust());
			      textArea.append("\nUsername:\t" + customer.getUsername());
			      textArea.append("\nName:\t" + customer.getName());
			      textArea.append("\nAddress:\t" + customer.getAddress());
			      textArea.append("\nCity:\t" + customer.getCity());
			      textArea.append("\nProvince:\t" + customer.getProvince());
			      textArea.append("\nZip:\t" + customer.getZip());
			      
			      textArea.append("\n\n\t\tORDER INFO:");
			      
			      ResultSet rs = dbConnect.selectQuery("CALL showPurchase(" + String.valueOf(customer.getIdCust() + ");"));
			      
			      try {
					while (rs.next()) {
						textArea.append("\nDescription:\t" + rs.getString("strDesc"));
						textArea.append("\nDate:\t" + rs.getString("dtmOrderDate"));
						textArea.append("\nPrice:\t" + rs.getInt("intPrice"));
						textArea.append("\nQuantity:\t" + rs.getInt("intQuantity"));
						textArea.append("\nTotal:\t" + (rs.getInt("intPrice") * rs.getInt("intQuantity")));
						textArea.append("\n\n\t\t======Thank you======");
						
					  }
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		if(customer != null) {
			showOrder();
		}
		showDataShop();
	}
	
	protected Customer getCustomer() {
		return customer;
	}

	protected void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void showDataShop() {
		ResultSet resultSet = dbConnect.selectQuery("SELECT * FROM tblProduct");
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
			data[rowIterate][3]= resultSet.getInt("intPrice");
			data[rowIterate][4]= resultSet.getInt("intOnHand");
			rowIterate++;
		}
		
			for(int i=0; i<numOfRow; i++) {
				modelShop.addRow(data[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showOnCart() {
		ArrayList<Product> cart = product.getCart();
		int cartSize = cart.size();
		Object[][] data = new Object[cartSize][6];
		
		for(int i = 0; i< cartSize; i++) {
			data[i][0] = cart.get(i).getId();
			data[i][1] = cart.get(i).getDesc();
			data[i][2] = cart.get(i).getFinish();
			data[i][3] = cart.get(i).getPrice();
			data[i][4] = cart.get(i).getQnty();
			data[i][5] = cart.get(i).getTotal();
		}
		modelCart.setRowCount(0);
		for(int i=0; i< data.length; i++) {
			modelCart.addRow(data[i]);
		}
	}
	
	public void showOrder() {
		ResultSet resultSet;
		
			resultSet = dbConnect.selectQuery("CALL showPurchase(" + String.valueOf(customer.getIdCust() + ");"));
		
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
			data[rowIterate][3]= resultSet.getInt("intPrice");
			data[rowIterate][4]= resultSet.getInt("intOnHand");
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
