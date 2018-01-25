package com.zsoft.wx.domain;

/**
 * 表情图标
 * @author zhangyong
 */
public class FaceiconPo {
	private String filename;	//文件名称
	private String character;	//文字代码
	private String sign;		//符号代码
	private String regex;		//正则匹配用的字段
	
	public FaceiconPo(){}
	
	public FaceiconPo(String filename, String character, String sign, String regex) {
		this.filename = filename;
		this.character = character;
		this.sign = sign;
		this.regex = regex;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
}
