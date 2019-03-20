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
		bukuDao.updateStok(2, 4);

		System.out.println("\nSemua Buku");
		printAllBuku(bukuDao.getAllBuku());
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
