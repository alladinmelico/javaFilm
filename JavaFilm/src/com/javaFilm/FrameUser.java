package com.javaFilm;

import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;

public class FrameUser extends JFrame {

	DBConnect dbConnect;
	private HashMap<String, Integer> map;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTable tblShop;
	private JTable tblCart;
	public FrameUser(DBConnect dbConnect) {
		map = new HashMap<>();
		
		
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
		btnLogOut.setBounds(919, 20, 89, 23);
		panel.add(btnLogOut);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Shop", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 447, 573);
		panel_1.add(scrollPane);
		
		tblShop = new JTable();
		scrollPane.setViewportView(tblShop);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(556, 77, 447, 573);
		panel_1.add(scrollPane_1);
		
		tblCart = new JTable();
		scrollPane_1.setViewportView(tblCart);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 26, 46, 20);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantity.setBounds(106, 26, 86, 20);
		panel_1.add(lblQuantity);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(106, 46, 86, 20);
		panel_1.add(textField_1);
		
		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(368, 43, 89, 23);
		panel_1.add(btnAddToCart);
		
		JLabel label = new JLabel("ID");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(556, 26, 46, 20);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Quantity");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(652, 26, 86, 20);
		panel_1.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(652, 46, 86, 20);
		panel_1.add(textField_3);
		
		JButton btnCheckOut = new JButton("Check out");
		btnCheckOut.setBounds(914, 26, 89, 40);
		panel_1.add(btnCheckOut);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(748, 43, 57, 23);
		panel_1.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(815, 43, 86, 23);
		panel_1.add(btnDelete);
		
		JLabel label_2 = new JLabel("ID");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setBounds(556, 43, 46, 20);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("ID");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_3.setBounds(10, 46, 46, 20);
		panel_1.add(label_3);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Purchase History", null, panel_2, null);
	}
}
