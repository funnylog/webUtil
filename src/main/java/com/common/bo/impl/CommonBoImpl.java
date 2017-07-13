package com.common.bo.impl;

import com.common.bo.CommonBo;
import com.common.dao.CommonDao;

public class CommonBoImpl implements CommonBo {
	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
}
