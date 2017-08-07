package com.order.utils;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ComPoolUtil {

	static ComboPooledDataSource dataSource=null;
	
	//初始化对象
	static{
		dataSource=new ComboPooledDataSource();
	}
	
	//返回DButil的核心控制器
	public static QueryRunner getQueryRunner(){
		//核心控制器
		QueryRunner runner=new QueryRunner(dataSource);
		
		return runner;
	}
	public static void main(String[] args) {
//		QueryRunner runner = new QueryRunner(dataSource);
		try {
			Connection conn = dataSource.getConnection();
			
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
