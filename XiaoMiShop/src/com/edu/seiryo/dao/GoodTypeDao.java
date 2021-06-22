package com.edu.seiryo.dao;
import java.util.List;

import com.edu.seiryo.entity.GoodType;

public interface GoodTypeDao {
	/**
	 * 获取所有商品类型列表
	 */
	public List<GoodType> getGoodTypeList() ;
}
