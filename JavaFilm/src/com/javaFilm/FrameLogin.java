package com.javaFilm;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Arrays;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameLogin extends JFrame{
	DBConnect dbConnect;
	private FrameAdmin frameAdmin;
	private FrameUser frameUser;
	private JPasswordField txtPassword;
	
	
	public FrameLogin(DBConnect dbConnect,FrameAdmin frameAdmin, FrameUser frameUser) {
		this.frameAdmin = frameAdmin;
		this.frameUser = frameUser;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		setTitle("Login");
		setResizable(false);
		setBackground(new Color(106, 90, 205));
		this.dbConnect  = dbConnect;
		
		setSize(new Dimension(300, 400));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(188, 143, 143));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextArea txtUserName = new JTextArea();
		txtUserName.setTabSize(3);
		txtUserName.setSize(new Dimension(160, 20));
		txtUserName.setBounds(67, 191, 162, 22);
		panel.add(txtUserName);
		
		txtPassword = new JPasswordField();
		txtPassword.setSize(new Dimension(160, 20));
		txtPassword.setBounds(67, 256, 163, 20);
		panel.add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(67, 169, 109, 22);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(67, 237, 96, 14);
		panel.add(lblPassword);
		
		JLabel lblPineValley = new JLabel("Pine Valley");
		lblPineValley.setForeground(new Color(255, 255, 255));
		lblPineValley.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPineValley.setBounds(88, 44, 114, 36);
		panel.add(lblPineValley);
		
		JLabel lblFurniture = new JLabel("furniture");
		lblFurniture.setFont(new Font("Tahoma", Font.ITALIC, 19));
		lblFurniture.setForeground(new Color(255, 255, 255));
		lblFurniture.setBounds(108, 70, 78, 36);
		panel.add(lblFurniture);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = txtUserName.getText();
				String userpassword = String.valueOf(txtPassword.getPassword());
				ResultSet result = dbConnect.selectQuery
						("SELECT * FROM tblCustomer WHERE strUserName = '" + userName + "' AND strPasscode = PASSWORD('" + userpassword + "');" );
				try {
					String tempUserName = null;
					while(result.next()) {
						tempUserName = result.getString("strUserName");
					}
					
					if (tempUserName != null) {
						if (tempUserName.equals("admin")) {
							frameAdmin.setVisible(true);
							setVisible(false);
						} else {
						frameUser.setVisible(true);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		frameAdmin.setFormEventListener(new FormEventListener() {

			@Override
			public void formEventOccured(FormEvent ev) {
				if (ev.isActive()) {
					setVisible(true);
				}
			}
			
		});
		
		
		btnNewButton.setBackground(new Color(139, 69, 19));
		btnNewButton.setBounds(108, 305, 89, 23);
		panel.add(btnNewButton);
	}
}
