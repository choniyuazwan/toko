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
	public void addBuku() {
		dbUtil.addBuku();	
	}

	@Override
	public Buku getDetail(int id) {
		return dbUtil.getDetail(id);
	}

	@Override
	public void updateStok(int id, int jumlah) {
		dbUtil.updateStok(id, jumlah);
	}
}
