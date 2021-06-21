package com.edu.seiryo.service.imp;

import java.util.List;

import com.edu.seiryo.dao.GoodTypeDao;
import com.edu.seiryo.dao.imp.GoodTypeDaoImp;
import com.edu.seiryo.entity.GoodType;
import com.edu.seiryo.service.GoodTypeService;

public class GoodTypeServiceImp implements GoodTypeService {
	GoodTypeDao dao = new GoodTypeDaoImp();
	@Override
	public List<GoodType> getGoodTypeList() {
		// TODO Auto-generated method stub
		return dao.getGoodTypeList();
	}

}
