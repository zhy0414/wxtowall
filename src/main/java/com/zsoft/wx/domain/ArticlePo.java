package com.zsoft.wx.domain;

import java.util.Date;

public class ArticlePo {
	private int id;
	private int typeId = 0;
	private String title = "";
	private String content = "";
	private String overview = "";
	private int hitNum = 0;
	private String titleImg = "";
	private Date createTime;
	private String createUser = "";
	private Date repareTime;
	private String repareUser = "";

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOverview() {
		return this.overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public int getHitNum() {
		return this.hitNum;
	}

	public void setHitNum(int hitNum) {
		this.hitNum = hitNum;
	}

	public String getTitleImg() {
		return this.titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getRepareTime() {
		return this.repareTime;
	}

	public void setRepareTime(Date repareTime) {
		this.repareTime = repareTime;
	}

	public String getRepareUser() {
		return this.repareUser;
	}

	public void setRepareUser(String repareUser) {
		this.repareUser = repareUser;
	}
}