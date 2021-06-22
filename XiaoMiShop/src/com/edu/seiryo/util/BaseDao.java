package com.edu.seiryo.util;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class BaseDao {
	private Connection con = null;
	private PreparedStatement ps = null;
	protected ResultSet rs = null;
	private int i = 0;
	private Result result;
	/**
	 * 创建连接对象
	 * 
	 * @return 返回已经创建好的连接对象
	 */
	private Connection createConnection() throws Exception {		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		this.con = DriverManager.getConnection(
				"jdbc:sqlserver://localhost:1433;DatabaseName=Users", "sa",
				"123");
		System.out.println("success");
		return this.con;
	}
	
	/**
	 * 创建执行SQL语句对象
	 * 
	 * @param sql
	 *            SQL语句
	 * @param objects
	 *            参数列表（为null 或 Object类型的参数列表数组）
	 * @return 返回执行SQL语句对象
	 */
	private PreparedStatement createPrepardStatement(String sql,
			Object... objects) throws Exception {
		this.con = this.createConnection();
		this.ps = this.con.prepareStatement(sql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				this.ps.setObject(i + 1, objects[i]);
			}
		}
		return this.ps;
	}
	
	/**
	 * 创建结果集对象
	 * 
	 * @param sql
	 *            查询SQL语句
	 * @param objects
	 *            参数列表（为null 或 Object类型的参数列表数组）
	 * @return 返回受影响的结果集对象
	 */
	private ResultSet createResultSet(String sql, Object... objects)
			throws Exception {
		this.ps = this.createPrepardStatement(sql, objects);
		this.rs = this.ps.executeQuery();
		return this.rs;
	}
	
	/**
	 * 关闭 连接对象 ，关闭执行 sql语句对象 ，关闭结果集对象
	 * 
	 * @param con
	 * @param stat
	 * @param rset
	 * @throws Exception
	 */
	private void close(Connection con, PreparedStatement ps, ResultSet rs)
			throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (con != null) {
			con.close();
		}
		rs = null;
		ps = null;
		con = null;
	}
	
	/**
	 * 执行更新 （增/删/改）
	 * 
	 * @param sql
	 *            更新SQL语句（增/删/改 SQL语句）
	 * @param objects
	 *            参数列表（为null 或 Object类型的参数列表数组）
	 * @return 返回是否执行更新成功
	 */
	protected boolean executeUpdate(String sql, Object... objects)
			throws Exception {
		this.i=0;
		this.ps = this.createPrepardStatement(sql, objects);
		this.i = this.ps.executeUpdate();
		this.close(con, ps, rs);
		return this.i != 0 ? true : false;
	}
	
	/**
	 * 创建结果集对象
	 * 
	 * @param sql
	 *            查询SQL语句
	 * @param objects
	 *            参数列表（为null 或 Object类型的参数列表数组）
	 * @return 返回受影响的结果集对象
	 */
	protected ResultSet executeQuery(String sql, Object... objects)
			throws Exception {
		this.rs = this.createResultSet(sql, objects);
		return this.rs;
	}
	
	/**
	 * 执行查询结果集对象
	 * 
	 * @param sql
	 *            查询SQL语句
	 * @param objects
	 *            参数列表（为null 或 Object类型的参数列表数组）
	 * @return 返回Result对象
	 */
	protected Result executeResult(String sql, Object... objects)
			throws Exception {
		this.rs = this.createResultSet(sql, objects);
		this.result = ResultSupport.toResult(this.rs);
		this.close(con, ps, rs);
		return this.result;
	}
	protected List executQuery(Object objClass, String sql, Object... objects)
			throws Exception {
		List list = new ArrayList();
		this.rs = createResultSet(sql, objects);
		ResultSetMetaData meta = this.rs.getMetaData();// 获取列信息
		Class classInfo = Class.forName(objClass.getClass().getName());// 取到这个叫objClass对象的类
		Method methods[] = classInfo.getDeclaredMethods();// 获取objClass类中的方法
		while (this.rs.next()) {
			//创建对象
			Object oo = classInfo.newInstance();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				String strCt = meta.getColumnTypeName(i);// 获取数据库中的字段类型
				String columnName = meta.getColumnName(i).toLowerCase();// 得到数据库中该列的名称并转成小写
				for (int j = 0; j < methods.length; j++) {
					if (methods[j].getName().startsWith("set")) {// 判断如果是以set开头的方法就进入下面
						if (methods[j].getName().toLowerCase().endsWith(
								columnName)) {// 如果这个字段和你方法中的set后面的面辍是相同的就执行
							Object convertO = this.rs.getObject(i);
							Object[] o = { convertO };
							methods[j].invoke(oo, o);// 添加数据
							break;
						}
					}
				}
			}
			list.add(oo);
		}
		return list;
	}
}
