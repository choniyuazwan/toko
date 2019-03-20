package com.toko.main;

public class Buku {
	private int id;
	private String judul, penulis, penerbit;
	private int harga, stok;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJudul() {
		return judul;
	}
	public void setJudul(String judul) {
		this.judul = judul;
	}
	public String getPenulis() {
		return penulis;
	}
	public void setPenulis(String penulis) {
		this.penulis = penulis;
	}
	public String getPenerbit() {
		return penerbit;
	}
	public void setPenerbit(String penerbit) {
		this.penerbit = penerbit;
	}
	public int getHarga() {
		return harga;
	}
	public void setHarga(int harga) {
		this.harga = harga;
	}
	public int getStok() {
		return stok;
	}
	public void setStok(int stok) {
		this.stok = stok;
	}
}
