package com.javaFilm;

import java.awt.Dimension;

import javax.swing.JFrame;

public class FrameUser extends JFrame {

	DBConnect dbConnect;
	public FrameUser(DBConnect dbConnect) {
		this.dbConnect  = dbConnect;
		setResizable(false);
		setSize(new Dimension(1024, 768));
		setTitle("Pine Valley Furniture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
