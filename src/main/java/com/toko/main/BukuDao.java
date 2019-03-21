package com.toko.main;

import java.util.List;

public interface BukuDao {
	List<Buku> getAllBuku();
	boolean addBuku(Buku buku);
	Buku getDetailBuku(int id);
	boolean buyBuku(int id, int jumlah);
}
