/**
 * 
 * @author       
 * @date         
 * @project_name 
 * @package_name 
 * @file_name    
 * @classname    
 * @version      
 */
package com.edu.seiryo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author       PATRICK
 * @date         2021年5月19日 下午1:21:09
 * @project_name Bank
 * @package_name com.edu.seiryo.util
 * @file_name    DButil.java
 * @classname    
 * @version      
 */
public class DButil {
	// SQL Server JDBC 连接驱动名称
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// SQL Server 数据库 URL 地址
	private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;DataBaseName=XiaoMiMail";
	private static final String ADMIN = "sa";
	private static final String PWD = "123";
	static Connection con = null;
	static PreparedStatement pst = null;
	static ResultSet rst = null;
	
	private static void preparedStatement(String sql,Object...objects) throws Exception {	
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, ADMIN, PWD);
		pst = con.prepareStatement(sql);
		for (int i = 0; i < objects.length; i++) {
			pst.setObject(i + 1, objects[i]);
		}
	}
	
	/**
	 *	 增删改方法
	 * @param sql 增删改语句
	 * @param objects 设置参数
	 * @return
	 * @throws Exception
	 */
	public static boolean updateDb(String sql,Object...objects) throws Exception{
		boolean bo = false;
		preparedStatement(sql, objects);
		bo = pst.executeUpdate() > 0 ?true:false;
		dbclose(rst, pst, con);
		return bo;
	}
	/**
	 * 	登录方法
	 * @param sql 查询语句
	 * @param objects 设置参数
	 * @return 
	 * @throws Exception
	 */
	public static boolean queryLogin(String sql,Object...objects) throws Exception {		
		boolean bo = false;
		preparedStatement(sql, objects);
		rst =  pst.executeQuery();
		bo = rst.next();
		dbclose();
		return bo;
	}
	/**
	 * 	查询方法
	 * @param sql 查询语句
	 * @param objects 设置参数
	 * @return 
	 * @throws Exception
	 */
	public static ResultSet queryDB(String sql,Object...objects) throws Exception{		
		preparedStatement(sql, objects);
		rst =  pst.executeQuery();
		return rst;
	}
	/**
	 * 	关闭方法
	 * @param rst
	 * @param pst
	 * @param con
	 * @throws Exception
	 */
	public static void dbclose(ResultSet rst,PreparedStatement pst,Connection con) throws Exception {
		if (rst != null) {
			rst.close();
			rst = null;
		}
		if (pst != null) {
			pst.close();
			pst = null;
		}
		if (con != null) {
			con.close();
			con = null;
		}
	}
	/**
	 * 	关闭方法
	 * @throws Exception
	 */
	public static void dbclose() throws Exception {
		if (rst != null) {
			rst.close();
			rst = null;
		}
		if (pst != null) {
			pst.close();
			pst = null;
		}
		if (con != null) {
			con.close();
			con = null;
		}
	}
}
