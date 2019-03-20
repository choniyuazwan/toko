package com.toko.main;

import java.util.List;

public interface BukuDao {
	List<Buku> getAllBuku();
	void addBuku();
	Buku getDetail(int id);
	void updateStok(int id, int jumlah);
}
