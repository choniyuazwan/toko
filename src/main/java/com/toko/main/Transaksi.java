package com.toko.main;

public class Transaksi {
	private Buku buku;
	private int jumlah;
	private int harga;
	
	public Buku getBuku() {
		return buku;
	}
	public void setBuku(Buku buku) {
		this.buku = buku;
	}
	public int getJumlah() {
		return jumlah;
	}
	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}
	public int getHarga() {
		return harga;
	}
	public void setHarga(int harga) {
		this.harga = harga;
	}
}
