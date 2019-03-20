package com.toko.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
	static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

	public static void main(String[] args) {
		BukuDao bukuDao = new BukuDaoImpl();
		List<Transaksi> listTransaksi = new ArrayList<Transaksi>();
		Buku buku = new Buku();
		
		// add buku
		bukuDao.addBuku();
		
		System.out.println("\nSemua Buku");
		printAllBuku(bukuDao.getAllBuku());
		
		System.out.println("\nDetail Buku");
		buku = bukuDao.getDetail(2);
		printDetailBuku(buku);

		// update stok
		Transaksi transaksi = new Transaksi();
		buku = bukuDao.getDetail(2);
		transaksi.setBuku(buku);
		transaksi.setJumlah(4);

		int totalHarga = buku.getHarga()*transaksi.getJumlah();
		transaksi.setHarga(totalHarga);
		
		try {
			connection = DBConnection.getConnection();
        	statement = connection.createStatement();
        	String sql = "INSERT INTO transaksi (idBuku, judul, jumlah, harga) VALUE('%d', '%s', '%d', '%d')";
    		sql = String.format(sql, transaksi.getBuku().getId(), transaksi.getBuku().getJudul(), transaksi.getJumlah(), transaksi.getHarga());
        	statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

		bukuDao.updateStok(transaksi.getBuku().getId(), transaksi.getJumlah());

		System.out.println("\nSemua Buku");
		bukuDao = new BukuDaoImpl();
		for (Buku buku1 : bukuDao.getAllBuku()) {
			System.out.println(buku1.getId() +  " - " + buku1.getJudul() + " : " + buku1.getStok());
		}
	}
	
	private static void printAllBuku(List<Buku> listBuku) {
		for (Buku buku : listBuku) {
			System.out.println(buku.getId() +  " - " + buku.getJudul() + " : " + buku.getStok());
		}
	}
	
	private static void printDetailBuku(Buku buku) {
		System.out.println(buku.getId() +  " - " + buku.getJudul() + " : " + buku.getStok());
	}
}
