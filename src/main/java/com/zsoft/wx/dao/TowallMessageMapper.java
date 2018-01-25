package com.zsoft.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zsoft.wx.model.TowallMessage;

public interface TowallMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TowallMessage record);

    int insertSelective(TowallMessage record);

    TowallMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TowallMessage record);

    int updateByPrimaryKey(TowallMessage record);
    
    
    //得到新的消息，即在上次调取时间之后的消息
    List<TowallMessage> selectNewMsg();
    //修改已发送消息状态
    int updateNewMsgStatus(@Param("messages")List<TowallMessage> messages);
}