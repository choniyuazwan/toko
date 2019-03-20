package com.toko.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class MainApp {
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    
	public static void main(String[] args) throws Exception {
		BukuDao bukuDao = new BukuDaoImpl();
		Buku buku = new Buku();
		
		// add buku
		bukuDao.addBuku();
		
		System.out.println("\nSemua Buku");
		printAllBuku(bukuDao.getAllBuku());
		
		System.out.println("\nDetail Buku");
		System.out.println("Id: ");
		buku = bukuDao.getDetailBuku(Integer.parseInt(input.readLine()));
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
