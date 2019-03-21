package com.toko.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	static Connection connection;
	static Statement statement;
	static ResultSet resultSet;
	static PreparedStatement preparedStatement;
	Buku buku = new Buku();
	Transaksi transaksi = new Transaksi();
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader input = new BufferedReader(inputStreamReader);

	public DBUtil(Connection connect) {
		connection = connect;
	}

	public List<Buku> getAllBuku() {
		List<Buku> listBuku = new ArrayList<Buku>();
		try {
			String sql = "SELECT * FROM buku";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Buku buku = new Buku();
				buku.setId(resultSet.getInt("id"));
				buku.setJudul(resultSet.getString("judul"));
				buku.setPenulis(resultSet.getString("penulis"));
				buku.setPenerbit(resultSet.getString("penerbit"));
				buku.setHarga(resultSet.getInt("harga"));
				buku.setStok(resultSet.getInt("stok"));
				listBuku.add(buku);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBuku;
	}

	public boolean addBuku(Buku buku) {
		try {
			String sql = "INSERT INTO buku (judul, penulis, penerbit, harga, stok) VALUE(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, buku.getJudul());
			preparedStatement.setString(2, buku.getPenulis());
			preparedStatement.setString(3, buku.getPenerbit());
			preparedStatement.setInt(4, buku.getHarga());
			preparedStatement.setInt(5, buku.getStok());
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Buku getDetail(int id) {
		Buku hasil = null;
		try {
			String sql = "select * from buku where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Buku buku = new Buku();
				buku.setId(resultSet.getInt("id"));
				buku.setJudul(resultSet.getString("judul"));
				buku.setPenulis(resultSet.getString("penulis"));
				buku.setPenerbit(resultSet.getString("penerbit"));
				buku.setHarga(resultSet.getInt("harga"));
				buku.setStok(resultSet.getInt("stok"));
				hasil = buku;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasil;
	}

	public boolean buyBuku(int id, int jumlah) {
		try {
			buku = getDetail(id);
			transaksi.setBuku(buku);
			transaksi.setJumlah(jumlah);

			int totalHarga = buku.getHarga() * transaksi.getJumlah();
			transaksi.setHarga(totalHarga);

			String sql = "INSERT INTO transaksi (idBuku, judul, jumlah, harga) VALUES (?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, transaksi.getBuku().getId());
			preparedStatement.setString(2, transaksi.getBuku().getJudul());
			preparedStatement.setInt(3, transaksi.getJumlah());
			preparedStatement.setInt(4, transaksi.getHarga());
			preparedStatement.execute();

			int stokLama = buku.getStok();
			int stokBaru = stokLama - jumlah;
			buku.setStok(stokBaru);

			try {
				statement = connection.createStatement();
				sql = "update buku set stok=%d where id=%d";
				sql = String.format(sql, buku.getStok(), id);
				statement.execute(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
