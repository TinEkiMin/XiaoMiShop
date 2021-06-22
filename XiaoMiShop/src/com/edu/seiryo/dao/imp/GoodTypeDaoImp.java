package com.edu.seiryo.dao.imp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edu.seiryo.dao.GoodTypeDao;
import com.edu.seiryo.entity.GoodType;
import com.edu.seiryo.util.DButil;


public class GoodTypeDaoImp implements GoodTypeDao {
	ResultSet resultset = null;
	List<GoodType> goodTypes = new ArrayList<GoodType>();
	@Override
	public List<GoodType> getGoodTypeList() {
		goodTypes.clear();
		String sql = "select * from tb_goods_type";
		try {
			resultset = DButil.queryDB(sql);
			while (resultset.next()) {
				int id = resultset.getInt(1);
				String name = resultset.getString(2);
				GoodType goodType = new GoodType(id, name);
				goodTypes.add(goodType);
			}
			return goodTypes;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DButil.dbclose();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
