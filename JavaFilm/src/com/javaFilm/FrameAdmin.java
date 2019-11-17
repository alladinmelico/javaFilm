package com.javaFilm;

import javax.swing.JFrame;
import java.awt.Dimension;

public class FrameAdmin  extends JFrame {
	public FrameAdmin() {
		setResizable(false);
		setSize(new Dimension(1024, 768));
		
		setVisible(true);
		setTitle("Pine Valley Furniture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
