package vn.iotstar.dao.impl;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectSQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnectSQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT * FROM Users ";
		List<UserModel> list = new ArrayList<UserModel>();
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserModel(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("images"),
						rs.getString("fullname"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getInt("roleid"),
						rs.getDate("createDate")));
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public UserModel findById(int id) {
		String sql = "SELECT * FROM Users WHERE id = ? ";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleId(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO Users (id, username, password, images, fullname, email, phone,  roleid, createDate) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			conn = new DBConnectSQL().getConnection();// ket noi database
			ps = conn.prepareStatement(sql);// nem cau sql cho thuc thi

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getImages());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPhone());
			ps.setInt(7, user.getRoleId());
			ps.setDate(8, user.getCreateDate());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM Users WHERE username = ? ";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleId(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static void main(String[] args) {
		try {
			IUserDao userDao = new UserDaoImpl();
			System.out.println(userDao.findByUserName("tandanh"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "SELECT * FROM users WHERE email = ?";
		try {
			conn = super.getDBConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;

	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "SELECT * FROM users WHERE username = ?";
		try {
			conn = super.getDBConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;

	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "SELECT * FROM users WHERE phone = ?";
		try {
			conn = super.getDBConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	public int editProfile(UserModel user) {
		int ketQua = 0;
		String query = "UPDATE users SET email=? , fullname = ?, images = ?, phone = ? WHERE id=?";
		try {
			conn = super.getDBConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getFullName());
			ps.setString(3, user.getImages());
			ps.setString(4, user.getPhone());
			ps.setInt(5, user.getId());
			ketQua = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

}
