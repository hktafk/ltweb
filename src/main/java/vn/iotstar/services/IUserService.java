package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserService {
	public UserModel login(String username, String password);

	UserModel FindByUserName(String username);

	boolean checkExistPhone(String phone);

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);

	void insert(UserModel user);

	boolean register(String username, String password, String fullname, String email, String phone);
}
