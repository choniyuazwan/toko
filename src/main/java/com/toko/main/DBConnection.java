package com.toko.main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/toko";
    static final String USER = "root";
    static final String PASS = "aaaa";
     
    static Connection conn;
    
    public static Connection getConnection() {
    	try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);
            
            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return conn;
    }
}
