package com.dass.pawning.util;

public enum ParameterEnum {
	MININTERESTDAYS(1),
	MAXPAWNADDFORNONCUSTOMER(2),
	MAXPAWNADDFORCUSTOMER(3),
	MININTEREST(4),
	SECONDINTEREST(5),
	REMINDERCOST(6);
	

	private int code;

	ParameterEnum(int code){
		this.code = code;
	}
	public int getCode() {
		return code;
	}

	public static ParameterEnum getEnumByCode(int code) {
		Class clazz = ParameterEnum.class;
		Object[] cons = clazz.getEnumConstants();
		for(Object o:cons) {
			if(((ParameterEnum)o).getCode()==code)
				return (ParameterEnum)o;
		}
		return null;
	}

}
