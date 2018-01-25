package com.zsoft.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zsoft.wx.model.TowallUser;

public interface TowallUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TowallUser record);

    int insertSelective(TowallUser record);

    TowallUser selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(TowallUser record);

    int updateByPrimaryKey(TowallUser record);
    
    //根据 openid 查找用户
    TowallUser selectByOpenid(String openid);
    
    //根据用户名和手机号查找用户
    TowallUser selectByUsernameAndMobile(@Param("username")String username, @Param("mobile")String mobile);
    
    //查找可接收信息用户
    List<TowallUser> selectActiveUser();
    
    List<TowallUser> selectAllUser();
    
    //更新用户状态、openid和注册时间、最后登录时间
    int updateStatusAndOpenid(@Param("id")int id, @Param("status")int status, @Param("openid")String openid);
    
    //更新最后登录时间
    int updateLastmsgtimeAndMesnum(Integer id);
    
    //计算总数
    int countall();
    
    //增加 msgnum 数量
    int updateMsgNum(int userid);
}