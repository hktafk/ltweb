package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import vn.iotstar.configs.DBConnectSQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl implements IUserDao {
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
				 ps = conn.prepareStatement(sql);//nem cau sql cho thuc thi
				 
				 ps.setString(1, user.getUsername());
				 ps.setString(2, user.getPassword());
				 ps.setString(3, user.getImages());
				 ps.setString(4, user.getFullname());
				 ps.setString(5, user.getEmail());
				 ps.setString(6,user.getPhone());
				 ps.setInt(7,user.getRoleId());
				 ps.setDate(8, user.getCreateDate());
				 ps.executeUpdate();
				 } catch (Exception e) {e.printStackTrace();} }

	

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
			//System.out.println(userDao.findById(1));
			//System.out.println(userDao.findByUserName("yenkhang"));
			System.out.println(userDao.findAll());
			// userDao.insert(new UserModel(3, "abc", "abc@gmail.com", "Khang", "123", ""));

			/*
			 * List<UserModel> list = userDao.findAll();
			 * 
			 * for (UserModel user : list) { System.out.println(user); }
			 */

			// System.out.println(userDao.findAll());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
