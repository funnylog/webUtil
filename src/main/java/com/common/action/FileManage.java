package com.common.action;

@SuppressWarnings("serial")
public class FileManage extends BaseAction {

	private String type;
	private String name;
	private String description;
	private Long   parentId;
	private String path;
	@Override
	public String execute(){
		if("uploadVideo".equals(action)){
			
		}
		return ERROR;
	}
}
