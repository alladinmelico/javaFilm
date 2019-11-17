package com.javaFilm;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				FrameAdmin frameAdmin = new FrameAdmin();
				
			}
			
		});
	}

}
