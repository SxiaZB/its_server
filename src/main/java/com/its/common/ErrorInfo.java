package com.its.common;

public enum ErrorInfo {
	FAILURE_ERROR("操作失败，请联系开发者！",1010003),//系统操作失败
	DEFAULT_ERROR("系统服务异常",1010001),//系统服务异常
	REMOTE_SERVICE_ERROR("调用远程服务失败",1010002),//调用远程服务失败
	SUBCATE_ERROR("该品类下有子分类，不可删除！",1010004),//删除品类，有子分类不可删除
	PROCATE_ERROR("该品类下有商品，不可删除！",1010005),// 删除品类，有商品不可删除
	EFFECTIVE_ERROR("生效时间重复！",1010006),// 生效时间重复！
	DELIMG_ERROR("该图片被使用，不可删除！",1010007),//删除图片，被使用不可删除
	IMGNAME_ERROR("图片名称已存在或者含有非法字符！",1010008),//图片名称重复
	NUMBER_FORMAT_ERROR("数字格式化错误",1010009);//数字格式化异常

	private ErrorInfo(String msg,int value){
		this.msg = msg;
		this.value = value;
	}
	
	String msg;
	int value;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
