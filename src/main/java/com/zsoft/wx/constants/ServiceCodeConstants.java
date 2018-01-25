package com.zsoft.wx.constants;

public class ServiceCodeConstants {
	
	/************************************************************code码定义start************************************************************/
	public static final Integer CODE_PARAM_ILLEGAL = -1;
	//异地登录
	public static final Integer OTHER_PLACE_LOGIN = 1001;
	
	//token过期
	public static final Integer TOKEN_EXPIRE = 1002;
	
	//userId为空非法
	public static final Integer REQUEST_USERID_NULL_ILLEGAL = 1003;
	
	//token非法
	public static final Integer TOKEN_ILLEGAL = 1004;
	
	//成功请求返回码
	public static final Integer SUCCESS_RTN = 0;
	
	//失败请求返回码
	public static final Integer FAIL_RTN = -1;
	
	//登录用户不存在或密码错误 
	public static final Integer USER_NOT_EXISTS_PASSWORD_ILLEGAL = 1005;

	// 用户已冻结
	public static final Integer USER_FREEZED = 1006;
	
	//手机号非法
	public static final Integer MOBILE_ILLEGAL = 1007;
	
	//验证码不对
	public static final Integer AUTH_CODE_ILLEAGL = 1008;
	
	//重复注册
	public static final Integer MULTIPLE_REGISTER = 1009;
	
	// 手机号未注册
	public static final Integer MOBILE_UNREGISTER = 1010;
	
	// 生产新用户失败
	public static final Integer USER_CREATE_FAILED = 1011;
	
	
	//必填字段为空
	public static final Integer REQUIRED_PARAM_NULL_ILLEGAL = 1013;
	
	//图片验证码不对
	public static final Integer GRAPHIC_CODE_ILLEGAL = 1014;
	
	//用户不存在
	public static final Integer USER_IS_NOT_EXISTS = 1015;
	

	/************************************************************code码定义end************************************************************/
	
	
}
