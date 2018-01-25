package com.zsoft.wx.dao;

import java.util.List;

import com.zsoft.wx.model.WxUserInfo;

public interface WxUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxUserInfo record);

    int insertSelective(WxUserInfo record);

    WxUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUserInfo record);

    int updateByPrimaryKey(WxUserInfo record);
    
    /**
     * 根据 openid 查找
     * @param openid
     * @return
     */
    WxUserInfo selectByOpenid(String openid);
    
    /**
     * 查找所有
     */
    List<WxUserInfo> selectAll();
    
    /**
     * 根据 openid 更新用户信息
     * @return
     */
    int updateByOpenid(WxUserInfo info);
}