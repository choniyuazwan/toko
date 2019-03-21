package com.toko.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class MainApp {
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    static BukuDao bukuDao = new BukuDaoImpl();
	static Buku buku = new Buku();
    
	public static void main(String[] args) {
		menu();
	}
	
	static void menu() {
		System.out.println("\n========= MENU UTAMA =========");
	    System.out.println("1. Lihat Semua Data Buku");
	    System.out.println("2. Lihat Detail Buku");
	    System.out.println("3. Tambah Data Buku");
	    System.out.println("4. Beli Buku");
	    System.out.println("0. Keluar");
	    System.out.println("");
	    System.out.print("Pilih menu: ");
	
	    try {
	        String pilihan = input.readLine().trim();
	
	        if (pilihan.equals("0")) {
	        	System.out.println("Terimakasih");
	        	System.exit(0);
	        } else if (pilihan.equals("1")) {
                getAllBuku();
                menu();
	        } else if (pilihan.equals("2")) {
                getDetailBuku();
                menu();
	        } else if (pilihan.equals("3")) {
                addBuku();
                menu();
	        } else if (pilihan.equals("4")) {
                buyBuku();
                menu();
	        } else {
	        	System.out.println("Pilihan salah");
	        	menu();
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
		}
	}

	static void getAllBuku() {
		System.out.println("\nSemua Data Buku");
		if(bukuDao.getAllBuku() != null || !bukuDao.getAllBuku().isEmpty()) {
			printAllBuku(bukuDao.getAllBuku());
    	} else {
    		notFound();
    	}
	}
	
	static void getDetailBuku( ) {
		try {
			System.out.println("\nDetail Buku");
			System.out.print("Id: ");
			buku = bukuDao.getDetailBuku(Integer.parseInt(input.readLine()));
			
			if(buku != null) {
				printDetailBuku(buku);
	    	} else {
	    		notFound();
	    	}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	static void addBuku() {
		try {
			System.out.println("Tambah Data Buku");
			System.out.print("Judul: ");
			buku.setJudul(input.readLine().trim());
			System.out.print("Penulis: ");
			buku.setPenulis(input.readLine().trim());
			System.out.print("Penerbit: ");
	    	buku.setPenerbit(input.readLine().trim());
			System.out.print("Harga: ");
	    	buku.setHarga(Integer.parseInt(input.readLine()));
			System.out.print("Stok: ");
	    	buku.setStok(Integer.parseInt(input.readLine()));
	    	if(bukuDao.addBuku(buku)) {
	    		success();
	    	} else {
	    		failed();
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static void buyBuku() {
		try {
			System.out.println("\nBeli Buku");
			System.out.print("Id: ");
			int id = Integer.parseInt(input.readLine());
			System.out.print("Jumlah: ");
			int jumlah = Integer.parseInt(input.readLine());
			if(bukuDao.buyBuku(id, jumlah)) {
				success();
			} else {
				failed();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void notFound() {
		System.out.println("Data tidak ditemukan");
	}
	
	static void success() {
		System.out.println("Proses berhasil");
	}
	
	static void failed() {
		System.out.println("Proses gagal");
	}
	
	static void printAllBuku(List<Buku> listBuku) {
		for (Buku buku : listBuku) {
			System.out.println(buku.getId() +  " | " + buku.getJudul() + " | " + buku.getPenulis() + " | " + buku.getPenerbit() + " | " + buku.getHarga() + " | " + buku.getStok());
		}
	}
	
	static void printDetailBuku(Buku buku) {
		System.out.println(buku.getId() +  " | " + buku.getJudul() + " | " + buku.getPenulis() + " | " + buku.getPenerbit() + " | " + buku.getHarga() + " | " + buku.getStok());
	}
}
