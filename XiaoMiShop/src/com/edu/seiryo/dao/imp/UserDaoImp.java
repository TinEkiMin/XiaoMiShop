package com.edu.seiryo.dao.imp;

import com.edu.seiryo.dao.UserDao;
import com.edu.seiryo.util.BaseDao;

public class UserDaoImp extends BaseDao implements UserDao {

	@Override
	public boolean checkUsername(String username) {
		boolean bo = false;
		String sql = "select * from tb_user where username = ?";
		try {
			rs = executeQuery(sql, username);
			bo = rs.next()?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bo;
	}

}
