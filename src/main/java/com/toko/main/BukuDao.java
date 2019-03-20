package com.toko.main;

import java.util.List;

public interface BukuDao {
	List<Buku> getAllBuku();
	void addBuku(Buku buku);
	Buku getDetailBuku(int id);
	void buyBuku(int id, int jumlah);
}
