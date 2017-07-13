package com.common.action;

import com.common.bo.CommonBo;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	protected CommonBo commonBo;
	protected String action = "";
	protected String mes = "";

	public CommonBo getCommonBo() {
		return commonBo;
	}
	public void setCommonBo(CommonBo commonBo) {
		this.commonBo = commonBo;
	}
	public String getMes() {
		return mes;
	}
	public void setAction(String action) {
		this.action = action;
	}

	
}
