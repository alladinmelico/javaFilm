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
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			result = stmt.executeQuery(query);
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void orderTransaction(int idCust,int intQuantity, int idProd) throws SQLException {
		PreparedStatement prepStmt = null;
		PreparedStatement prepStmt2 = null;
		PreparedStatement prepStmt3 = null;
		String createOrderQuery = "INSERT INTO tblOrder(tblCustomer_idCust) VALUE(?)";
		String createOrderline = "INSERT INTO tblorderline(intQuantity,tblOrder_idOrder,tblProduct_idProd) "
				+ "VALUE(?,?,?);";
		String onHandDeduction = "UPDATE tblProduct(intOnHand) VALUE((SELECT intOnHand tblProduct WHERE idProd = ?) - ?) WHERE idProd = ?";
		
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			
			prepStmt = conn.prepareStatement(createOrderQuery, Statement.RETURN_GENERATED_KEYS);
			prepStmt.setInt(1, idCust);
			int rowAffected = prepStmt.executeUpdate();

			rs = prepStmt.getGeneratedKeys();
			int ordID = 0;
			if(rs.next())
                ordID = rs.getInt(1);
			
			if(rowAffected == 1)
            {
			prepStmt2 = conn.prepareStatement(createOrderline);
			prepStmt2.setInt(1, intQuantity);
			prepStmt2.setInt(2, ordID);
			prepStmt2.setInt(3, idProd);
			
			prepStmt2.executeUpdate();
            }
			
			prepStmt3 = conn.prepareStatement(onHandDeduction);
			prepStmt3.setInt(1, idProd);
			prepStmt3.setInt(2, intQuantity);
			prepStmt3.setInt(3, idProd);
			prepStmt3.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.setAutoCommit(true);
	}
	
	public void orderSupTransaction(int idSup,int intQuantity, int idProd) throws SQLException {
		PreparedStatement prepStmt = null;
		PreparedStatement prepStmt2 = null;
		
		String createOrderQuery = "INSERT INTO tblSupplierOrder(tblSupplier_idSup,tblProduct_idProd,intQnty) VALUES(?,?,?)";
	
		String onHandDeduction = "UPDATE tblProduct SET intOnHand= ((SELECT intOnHand tblProduct WHERE idProd = ?) + ?) WHERE idProd = ?";
		
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			
			prepStmt = conn.prepareStatement(createOrderQuery, Statement.RETURN_GENERATED_KEYS);
			prepStmt.setInt(1, idSup);
			prepStmt.setInt(2, idProd);
			prepStmt.setInt(3, intQuantity);
			int rowAffected = prepStmt.executeUpdate();

			rs = prepStmt.getGeneratedKeys();
			int ordID = 0;
			if(rs.next())
                ordID = rs.getInt(1);
			
			if(rowAffected == 1)
            {
			prepStmt2 = conn.prepareStatement(onHandDeduction);
			prepStmt2.setInt(1, idProd);
			prepStmt2.setInt(2, intQuantity);
			prepStmt2.setInt(3, idProd);
			
			prepStmt2.executeUpdate();
            }

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.setAutoCommit(true);
	}
	
	public void custUpdate(int idCust, String strCustName, String strAddress, String strCity, String strProvince,
			int intZip, String strUserName, String strPasscode) throws SQLException {
		
		PreparedStatement prepStmt = null;
		
		String query = "UPDATE tblCustomer SET strCustName = ?, strAddress = ?, strCity = ?, strProvince = ?,"
				+ " intZip = ?, strUserName = ? WHERE idCust = ?";
		
		try {
			conn.setAutoCommit(false);
			
			prepStmt = conn.prepareStatement(query);
			
			prepStmt.setString(1, strCustName);
			prepStmt.setString(2, strAddress);
			prepStmt.setString(3, strCity);
			prepStmt.setString(4, strProvince);
			prepStmt.setInt(5, intZip);
			prepStmt.setString(6, strUserName);
//			prepStmt.setString(7, strPasscode);
			prepStmt.setInt(7, idCust);
			
			
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
		
		String query = "INSERT INTO tblCustomer(strCustName,strAddress,strCity,strProvince,intZip,strUserName,strPasscode) VALUES(?,?,?,?,?,?,PASSWORD(?))";
		
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
	
	
	public void supInsert(String strName, String strAddress, String contactNum) throws SQLException {
		
		PreparedStatement prepStmt = null;
		
		String query = "INSERT INTO tblSupplier(strName,strAddress,strContactNum) VALUES(?,?,?)";
		
		try {
			conn.setAutoCommit(false);
			prepStmt = conn.prepareStatement(query);
			prepStmt.setString(1, strName);
			prepStmt.setString(2, strAddress);
			prepStmt.setString(3, contactNum);

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
	
	public void supUpdate(int idSup,String strCustName, String strAddress, String contactNum) throws SQLException {
		
		PreparedStatement prepStmt = null;
		
		String query = "UPDATE tblSupplier SET strName = ?,strAddress = ?,strContactNum = ? WHERE idSup = ?";
		
		try {
			conn.setAutoCommit(false);
			prepStmt = conn.prepareStatement(query);
			prepStmt.setString(1, strCustName);
			prepStmt.setString(2, strAddress);
			prepStmt.setString(3, contactNum);
			prepStmt.setInt(4, idSup);

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
	
	public void supDelete(int idSup) throws SQLException {
		
		PreparedStatement prepStmt = null;
		
		String query = "DELETE FROM tblSupplier WHERE idSup = ?";
		
		try {
			conn.setAutoCommit(false);
			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, idSup);

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
