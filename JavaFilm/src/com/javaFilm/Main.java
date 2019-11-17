package com.javaFilm;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				DBConnect dbConnect = new DBConnect();
				FrameAdmin frameAdmin = new FrameAdmin(dbConnect);
				FrameUser frameUser = new FrameUser(dbConnect);
				FrameLogin frameLogin = new FrameLogin(dbConnect,frameAdmin,frameUser);
			}
		});
	}

}
