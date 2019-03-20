package com.toko.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    List<Buku> listBuku = new ArrayList<Buku>();
    
    public DBUtil(Connection connect) {
    	connection = connect;
    }
    
    public List<Buku> getAllBuku() throws Exception {
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
    
    public void addBuku() {
    	Buku buku = new Buku();
    	buku.setJudul("b");
    	buku.setPenulis("b");
    	buku.setPenerbit("b");
    	buku.setHarga(100000);
    	buku.setStok(10);
    	
    	try {
        	statement = connection.createStatement();
        	String sql = "INSERT INTO buku (judul, penulis, penerbit, harga, stok) VALUE('%s', '%s', '%s', '%d', '%d')";
    		sql = String.format(sql, buku.getJudul(), buku.getPenulis(), buku.getPenerbit(), buku.getHarga(), buku.getStok());
        	statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Buku getDetail(int id) {
    	Buku hasil = null;
		for(Buku buku : listBuku) {
			if(buku.getId() == id) {
				hasil = buku;
			}
		}
		return hasil;
    }
    
    public void updateStok(int id, int jumlah) {
    	for (Buku buku : listBuku) {
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
    	}	
    }
}
