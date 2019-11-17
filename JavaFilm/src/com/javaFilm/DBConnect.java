package com.javaFilm;

import java.sql.*;

public class DBConnect {
	private Connection conn;
	 
	
	public DBConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/db_pineValley?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet selectQuery(String query) {
		try {
			ResultSet result;
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void custUpdate(int idCust, String strCustName, String strAddress, String strCity, String strProvince,
			int intZip, String strUserName, String strPasscode) throws SQLException {
		
		PreparedStatement prepStmt = null;
		
		String query = "UPDATE tblCustomer SET strCustName = ?, strAddress = ?, strCity = ?, strProvince = ?,"
				+ " intZip = ?, strUserName = ?, strPasscode = PASSWORD(?) WHERE idCust = ?";
		
		try {
			conn.setAutoCommit(false);
			
			prepStmt = conn.prepareStatement(query);
			
			prepStmt.setString(1, strCustName);
			prepStmt.setString(2, strAddress);
			prepStmt.setString(3, strCity);
			prepStmt.setString(4, strProvince);
			prepStmt.setInt(5, intZip);
			prepStmt.setString(6, strUserName);
			prepStmt.setString(7, strPasscode);
			prepStmt.setInt(8, idCust);
			
			
			prepStmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
				if (conn != null) {
					try {
						System.out.println("Transaction is being rolled back");
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		} finally {
			if (prepStmt != null) {
				prepStmt.close();
			}
			
			conn.setAutoCommit(true);
		}
		
		
	}
	
	
	public void custInsert(String strCustName, String strAddress, String strCity, String strProvince, int intZip, String strUserName, String strPasscode) throws SQLException {
		
		PreparedStatement prepStmt = null;
		
		String query = "INSERT INTO tblCustomer(strCustName,strAddress,strCity,strProvince,intZip,strUserName,strPasscode) VALUES(?,?,?,?,?,PASSWORD(?))";
		
		try {
			conn.setAutoCommit(false);
			prepStmt = conn.prepareStatement(query);
			prepStmt.setString(1, strCustName);
			prepStmt.setString(2, strAddress);
			prepStmt.setString(3, strCity);
			prepStmt.setString(4, strProvince);
			prepStmt.setInt(5, intZip);
			prepStmt.setString(6, strUserName);
			prepStmt.setString(7, strPasscode);

			prepStmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
				if (conn != null) {
					try {
						System.out.println("Transaction is being rolled back");
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		} finally {
			if (prepStmt != null) {
				prepStmt.close();
			}
			
			conn.setAutoCommit(true);
		}
	}
	
	
	public void custDelete(int idCust) throws SQLException {
		PreparedStatement prepStmt = null;
		
		String query = "DELETE FROM tblCustomer WHERE idCust = ?";
		
		try {
			conn.setAutoCommit(false);
			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, idCust);

			prepStmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
				if (conn != null) {
					try {
						System.out.println("Transaction is being rolled back");
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		} finally {
			if (prepStmt != null) {
				prepStmt.close();
			}
			
			conn.setAutoCommit(true);
		}
	}
	
	
	public void prodUpdate(int idProd, String strDesc, String dtmFinish, int intPrice, int intOnHand) throws SQLException {
		
		PreparedStatement prepStmt = null;
		
		String query = "UPDATE tblProduct SET strDesc = ?, dtmFinish = ?, intPrice = ?, intOnHand = ? WHERE idProd = ?";
		
		try {
			conn.setAutoCommit(false);
			
			prepStmt = conn.prepareStatement(query);
			
			prepStmt.setString(1, strDesc);
			prepStmt.setString(2, dtmFinish);
			prepStmt.setInt(3, intPrice);
			prepStmt.setInt(4, intOnHand);
			prepStmt.setInt(5, idProd);
			
			prepStmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
				if (conn != null) {
					try {
						System.out.println("Transaction is being rolled back");
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		} finally {
			if (prepStmt != null) {
				prepStmt.close();
			}
			
			conn.setAutoCommit(true);
		}
		
		
	}
	
	
	public void prodInsert(String strDesc, String dtmFinish, int intPrice, int intOnHand) throws SQLException {
		
		PreparedStatement prepStmt = null;
		
		String query = "INSERT INTO tblProduct(strDesc,dtmFinish,intPrice,intOnHand) VALUE(?,?,?,?)";
		
		try {
			conn.setAutoCommit(false);
			
			prepStmt = conn.prepareStatement(query);
			
			prepStmt.setString(1, strDesc);
			prepStmt.setString(2, dtmFinish);
			prepStmt.setInt(3, intPrice);
			prepStmt.setInt(4, intOnHand);
			
			prepStmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
				if (conn != null) {
					try {
						System.out.println("Transaction is being rolled back");
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		} finally {
			if (prepStmt != null) {
				prepStmt.close();
			}
			
			conn.setAutoCommit(true);
		}
		
		
	}
	
	
	public void prodDelete(int idProd) throws SQLException {
		
		PreparedStatement prepStmt = null;
		
		String query = "DELETE FROM tblProduct WHERE idProd = ?";
		
		try {
			conn.setAutoCommit(false);
			
			prepStmt = conn.prepareStatement(query);

			prepStmt.setInt(1, idProd);
			
			prepStmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
				if (conn != null) {
					try {
						System.out.println("Transaction is being rolled back");
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		} finally {
			if (prepStmt != null) {
				prepStmt.close();
			}
			
			conn.setAutoCommit(true);
		}
		
		
	}
}
