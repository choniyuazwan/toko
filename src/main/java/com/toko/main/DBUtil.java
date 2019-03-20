package com.toko.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    Buku buku = new Buku();
	Transaksi transaksi = new Transaksi();
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    public DBUtil(Connection connect) {
    	connection = connect;
    }
    
    public List<Buku> getAllBuku() {
        List<Buku> listBuku = new ArrayList<Buku>();
    	try {
        	statement = connection.createStatement();
        	String sql = "SELECT * FROM buku";
    		resultSet = statement.executeQuery(sql);
    		while (resultSet.next()) {
    			Buku buku = new Buku();
    			buku.setId(resultSet.getInt("id"));
                buku.setJudul(resultSet.getString("judul"));
                buku.setPenulis(resultSet.getString("penulis"));
                buku.setPenerbit(resultSet.getString("penerbit"));
                buku.setHarga(resultSet.getInt("harga"));
                buku.setStok(resultSet.getInt("stok"));
                listBuku.add(buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return listBuku;
    }
     
    public void addBuku(Buku buku) {
    	try {
    		Buku buku1 = buku;
        	statement = connection.createStatement();
        	String sql = "INSERT INTO buku (judul, penulis, penerbit, harga, stok) VALUE('%s', '%s', '%s', '%d', '%d')";
    		sql = String.format(sql, buku1.getJudul(), buku1.getPenulis(), buku1.getPenerbit(), buku1.getHarga(), buku1.getStok());
        	statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Buku getDetail(int id) {
		Buku hasil = null;
    	try {
        	statement = connection.createStatement();
        	String sql = "select * from buku where id=%d";
    		sql = String.format(sql, id);
    		resultSet = statement.executeQuery(sql);
    		while (resultSet.next()) {
    			Buku buku = new Buku();
    			buku.setId(resultSet.getInt("id"));
                buku.setJudul(resultSet.getString("judul"));
                buku.setPenulis(resultSet.getString("penulis"));
                buku.setPenerbit(resultSet.getString("penerbit"));
                buku.setHarga(resultSet.getInt("harga"));
                buku.setStok(resultSet.getInt("stok"));
                hasil = buku;
            }
		} catch (Exception e) {
			e.printStackTrace();	
		}
    	return hasil;
    }
    
    public void buyBuku(int id, int jumlah) {
		buku = getDetail(id);
		transaksi.setBuku(buku);
		transaksi.setJumlah(jumlah);

		int totalHarga = buku.getHarga()*transaksi.getJumlah();
		transaksi.setHarga(totalHarga);
		
		try {
        	statement = connection.createStatement();
        	String sql = "INSERT INTO transaksi (idBuku, judul, jumlah, harga) VALUE('%d', '%s', '%d', '%d')";
    		sql = String.format(sql, transaksi.getBuku().getId(), transaksi.getBuku().getJudul(), transaksi.getJumlah(), transaksi.getHarga());
        	statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

		try {	    	
			buku = getDetail(id);
    		if (buku.getId() == id){
    			int stokLama = buku.getStok();
    			int stokBaru = stokLama - jumlah;
    			buku.setStok(stokBaru);
    			
    			try {
    	        	statement = connection.createStatement();
    	        	String sql = "update buku set stok=%d where id=%d";
    	    		sql = String.format(sql, buku.getStok(), id);
    	        	statement.execute(sql);
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    		}
	    	
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
}
