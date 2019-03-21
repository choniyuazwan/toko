package com.toko.main;

import java.util.ArrayList;
import java.util.List;

public class BukuDaoImpl implements BukuDao{
	private DBUtil dbUtil;
    List<Buku> listBuku = new ArrayList<Buku>();
	
	public BukuDaoImpl() {
		dbUtil = new DBUtil(DBConnection.getConnection());
	}

	@Override
	public List<Buku> getAllBuku() {
		try {
        	listBuku = dbUtil.getAllBuku();
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return listBuku;
	}
 
	@Override
	public boolean addBuku(Buku buku) {
		return dbUtil.addBuku(buku);	
	}

	@Override
	public Buku getDetailBuku(int id) {
		return dbUtil.getDetail(id);
	}

	@Override
	public boolean buyBuku(int id, int jumlah) {
		return dbUtil.buyBuku(id, jumlah);
	}
}
