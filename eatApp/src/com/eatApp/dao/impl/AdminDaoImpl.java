package com.eatApp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.eatApp.dao.AdminDao;
import com.eatApp.entity.Admin;
import com.eatApp.utils.ComPoolUtil;

public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin getAdminByadName(Admin admin) {
		Admin radmin = null;
		
		try {
			radmin = ComPoolUtil.getQueryRunner().query(
					"select * from t_admin where adName = ? and adPsw = ?",
					new BeanHandler<Admin>(Admin.class),
					new Object[]{admin.getAdName(),admin.getAdPwd()});
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return radmin;
	}

}
