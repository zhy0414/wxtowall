package com.zsoft.wx.thread;


import com.zsoft.wx.dao.TowallUserMapper;
import com.zsoft.wx.dao.WxUserInfoMapper;
import com.zsoft.wx.model.TowallUser;
import com.zsoft.wx.model.WxUserInfo;
import com.zsoft.wx.util.GetUserBaseInfoUtil;

/**
 * 更新用户基本信息
 * @author zhangyong
 *
 */
public class UpdateUserInfoThread implements Runnable{
	//private static Logger log = LoggerFactory.getLogger(UpdateUserInfoThread.class);
	
	private TowallUserMapper towallUserMapper;
	private WxUserInfoMapper wxUserInfoMapper;
	private TowallUser towallUser;
	
	public UpdateUserInfoThread(TowallUserMapper towallUserMapper, WxUserInfoMapper wxUserInfoMapper, TowallUser towallUser){
		this.towallUserMapper = towallUserMapper;
		this.wxUserInfoMapper = wxUserInfoMapper;
		this.towallUser = towallUser;
	}
	
	public void run() {
		GetUserBaseInfoUtil getUserBaseInfoUtil = new GetUserBaseInfoUtil(towallUser.getOpenid());
		WxUserInfo wxUserInfo = getUserBaseInfoUtil.getInfo();
		
		//成功获取到用户信息
		if(null != wxUserInfo){
			towallUser.setHeadimgurl(wxUserInfo.getHeadimgurl());
			towallUserMapper.updateByPrimaryKey(towallUser);
			
			WxUserInfo wxUserInfoOld = wxUserInfoMapper.selectByOpenid(towallUser.getOpenid());
			if(null == wxUserInfoOld){
				wxUserInfoMapper.insert(wxUserInfo);
			} else {
				wxUserInfo.setId(wxUserInfoOld.getId());
				wxUserInfoMapper.updateByPrimaryKey(wxUserInfo);
			}
			
		}
	}
	
	
}
