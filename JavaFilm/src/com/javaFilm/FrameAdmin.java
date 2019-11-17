package com.javaFilm;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.table.DefaultTableModel;

public class FrameAdmin  extends JFrame {
	DBConnect dbConnect;
	private JTable table;
	
	
	public FrameAdmin(DBConnect dbConnect) {
		this.dbConnect  = dbConnect;
		setResizable(false);
		setSize(new Dimension(1024, 768));
		setTitle("Pine Valley Furniture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setSize(new Dimension(50, 50));
		panel.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlCustomer = new JPanel();
		tabbedPane.addTab("Customer", null, pnlCustomer, null);
		
		JPanel pnlProduct = new JPanel();
		pnlProduct.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("Product", null, pnlProduct, null);
		tabbedPane.setBackgroundAt(1, new Color(135, 206, 235));
		pnlProduct.setLayout(new GridLayout(1, 0, 0, 0));
		
		table = new JTable();
		
		pnlProduct.add(table);
	}
}
