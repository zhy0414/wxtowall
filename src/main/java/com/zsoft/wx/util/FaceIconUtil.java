package com.zsoft.wx.util;

import java.util.ArrayList;
import java.util.List;

import com.zsoft.wx.domain.FaceiconPo;

/**
 * 表情图标工具类
 * @author zhangyong
 */
public class FaceIconUtil {
	private List<FaceiconPo> iconList;
	
	private static class FaceIconUtilHandler {
		private static FaceIconUtil instance = new FaceIconUtil();
	}
	
	private FaceIconUtil(){
		iconList = new ArrayList<FaceiconPo>();
		iconList.add(new FaceiconPo("icon_1.png", "/微笑", "/::)", "/::\\)"));
		iconList.add(new FaceiconPo("icon_2.png", "/抓狂", "/::Q", "/::Q"));
		iconList.add(new FaceiconPo("icon_3.png", "/撇嘴", "/::~", "/::~"));
		iconList.add(new FaceiconPo("icon_4.png", "/吐", "/::T", "/::T"));
		iconList.add(new FaceiconPo("icon_5.png", "/色", "/::B", "/::B"));
		iconList.add(new FaceiconPo("icon_6.png", "/偷笑", "/:,@P", "/:,@P"));
		iconList.add(new FaceiconPo("icon_7.png", "/发呆", "/::|", "/::\\|"));
		iconList.add(new FaceiconPo("icon_8.png", "/愉快", "/:,@-D", "/:,@-D"));
		iconList.add(new FaceiconPo("icon_9.png", "/得意", "/:8-)", "/:8-\\)"));
		iconList.add(new FaceiconPo("icon_10.png", "/白眼", "/::d", "/::d"));
		iconList.add(new FaceiconPo("icon_11.png", "/流泪", "/::<", "/::<"));
		iconList.add(new FaceiconPo("icon_12.png", "/傲慢", "/:,@o", "/:,@o"));
		iconList.add(new FaceiconPo("icon_13.png", "/害羞", "/::$", "/::\\$"));
		iconList.add(new FaceiconPo("icon_14.png", "/饥饿", "/::g", "/::g"));
		iconList.add(new FaceiconPo("icon_15.png", "/闭嘴", "/::X", "/::X"));
		iconList.add(new FaceiconPo("icon_16.png", "/困", "/:|-)", "/:\\|-\\)"));
		iconList.add(new FaceiconPo("icon_17.png", "/睡", "/::Z", "/::Z"));
		iconList.add(new FaceiconPo("icon_18.png", "/惊恐", "/::!", "/::!"));
		iconList.add(new FaceiconPo("icon_19.png", "/大哭", "/::'(", "/::'\\("));
		iconList.add(new FaceiconPo("icon_20.png", "/流汗", "/::L", "/::L"));
		iconList.add(new FaceiconPo("icon_21.png", "/尴尬", "/::-|", "/::-\\|"));
		iconList.add(new FaceiconPo("icon_22.png", "/憨笑", "/::>", "/::>"));
		iconList.add(new FaceiconPo("icon_23.png", "/发怒", "/::@", "/::@"));
		iconList.add(new FaceiconPo("icon_24.png", "/悠闲", "/::,@", "/::,@"));
		iconList.add(new FaceiconPo("icon_25.png", "/调皮", "/::P", "/::P"));
		iconList.add(new FaceiconPo("icon_26.png", "/奋斗", "/:,@f", "/:,@f"));
		
		iconList.add(new FaceiconPo("icon_27.png", "/呲牙", "/::D", "/::D"));
		iconList.add(new FaceiconPo("icon_28.png", "/咒骂", "/::-S", "/::-S"));
		iconList.add(new FaceiconPo("icon_29.png", "/惊讶", "/::O", "/::O"));			//
		iconList.add(new FaceiconPo("icon_30.png", "/疑问", "/:?", "/:\\?"));
		iconList.add(new FaceiconPo("icon_31.png", "/难过", "/::(", "/::\\("));
		iconList.add(new FaceiconPo("icon_32.png", "/嘘", "/:,@x", "/:,@x"));
		iconList.add(new FaceiconPo("icon_33.png", "/酷", "/::+", "/::\\+"));
		iconList.add(new FaceiconPo("icon_34.png", "/晕", "/:,@@", "/:,@@"));
		iconList.add(new FaceiconPo("icon_35.png", "/冷汗", "/:--b", "/:--b"));
		iconList.add(new FaceiconPo("icon_36.png", "/疯了", "/::8", "/::8"));
		iconList.add(new FaceiconPo("icon_37.png", "/哀", "/:,@!", "/:,@!"));
		iconList.add(new FaceiconPo("icon_38.png", "/篮球", "/:basketb", "/:basketb"));
		iconList.add(new FaceiconPo("icon_39.png", "/骷髅", "/:!!!", "/:!!!"));
		iconList.add(new FaceiconPo("icon_40.png", "/乒乓", "/:oo", "/:oo"));
		iconList.add(new FaceiconPo("icon_41.png", "/敲打", "/:xx", "/:xx"));
		iconList.add(new FaceiconPo("icon_42.png", "/咖啡", "/:coffee", "/:coffee"));
		iconList.add(new FaceiconPo("icon_43.png", "/再见", "/:bye", "/:bye"));
		iconList.add(new FaceiconPo("icon_44.png", "/饭", "/:eat", "/:eat"));
		iconList.add(new FaceiconPo("icon_45.png", "/擦汗", "/:wipe", "/:wipe"));
		iconList.add(new FaceiconPo("icon_46.png", "/猪头", "/:pig", "/:pig"));
		iconList.add(new FaceiconPo("icon_47.png", "/抠鼻", "/:dig", "/:dig"));
		iconList.add(new FaceiconPo("icon_48.png", "/玫瑰", "/:rose", "/:rose"));
		iconList.add(new FaceiconPo("icon_49.png", "/鼓掌", "/:handclap", "/:handclap"));
		iconList.add(new FaceiconPo("icon_50.png", "/凋谢", "/:fade", "/:fade"));
		iconList.add(new FaceiconPo("icon_51.png", "/糗大了", "/:&-(", "/:&-\\("));
		iconList.add(new FaceiconPo("icon_52.png", "/嘴唇", "/:showlove", "/:showlove"));
		
		iconList.add(new FaceiconPo("icon_53.png", "/坏笔", "/:B-)", "/:B-\\)"));
		iconList.add(new FaceiconPo("icon_54.png", "/爱心", "/:heart", "/:heart"));
		iconList.add(new FaceiconPo("icon_55.png", "/左哼哼", "/:<@", "/:<@"));
		iconList.add(new FaceiconPo("icon_56.png", "/心碎", "/:break", "/:break"));
		iconList.add(new FaceiconPo("icon_57.png", "/右哼哼", "/:@>", "/:@>"));
		iconList.add(new FaceiconPo("icon_58.png", "/蛋糕", "/:cake", "/:cake"));
		iconList.add(new FaceiconPo("icon_59.png", "/哈欠", "/::-O", "/::-O"));		//
		iconList.add(new FaceiconPo("icon_60.png", "/闪电", "/:li", "/:li"));
		iconList.add(new FaceiconPo("icon_61.png", "/鄙视", "/:>-|", "/:>-\\|"));
		iconList.add(new FaceiconPo("icon_62.png", "/炸弹", "/:bome", "/:bome"));
		iconList.add(new FaceiconPo("icon_63.png", "/委屈", "/:P-(", "/:P-\\("));
		iconList.add(new FaceiconPo("icon_64.png", "/刀", "/:kn", "/:kn"));
		iconList.add(new FaceiconPo("icon_65.png", "/快哭了", "/::'|", "/::'\\|"));
		iconList.add(new FaceiconPo("icon_66.png", "/足球", "/:footb", "/:footb"));
		iconList.add(new FaceiconPo("icon_67.png", "/阴险", "/:X-)", "/:X-\\)"));
		iconList.add(new FaceiconPo("icon_68.png", "/瓢虫", "/:ladybug", "/:ladybug"));
		iconList.add(new FaceiconPo("icon_69.png", "/亲亲", "/::*", "/::\\*"));
		iconList.add(new FaceiconPo("icon_70.png", "/便便", "/:shit", "/:shit"));
		iconList.add(new FaceiconPo("icon_71.png", "/吓", "/:@x", "/:@x"));
		iconList.add(new FaceiconPo("icon_72.png", "/月亮", "/:moon", "/:moon"));
		iconList.add(new FaceiconPo("icon_73.png", "/可怜", "/:8*", "/:8\\*"));
		iconList.add(new FaceiconPo("icon_74.png", "/太阳", "/:sun", "/:sun"));
		iconList.add(new FaceiconPo("icon_75.png", "/菜刀", "/:pd", "/:pd"));
		iconList.add(new FaceiconPo("icon_76.png", "/礼物", "/:gift", "/:gift"));
		iconList.add(new FaceiconPo("icon_77.png", "/西瓜", "/:<W>", "/:<W>"));
		iconList.add(new FaceiconPo("icon_78.png", "/拥抱", "/:hug", "/:hug"));
		iconList.add(new FaceiconPo("icon_79.png", "/啤酒", "/:beer", "/:beer"));
		iconList.add(new FaceiconPo("icon_80.png", "/强", "/:strong", "/:strong"));
		iconList.add(new FaceiconPo("icon_81.png", "/弱", "/:weak", "/:weak"));
		iconList.add(new FaceiconPo("icon_82.png", "/发抖", "/:shake", "/:shake"));
		iconList.add(new FaceiconPo("icon_83.png", "/握手", "/:share", "/:share"));
		iconList.add(new FaceiconPo("icon_84.png", "/怄火", "/:<O>", "/:<O>"));
		iconList.add(new FaceiconPo("icon_85.png", "/胜利", "/:v", "/:v"));
		iconList.add(new FaceiconPo("icon_86.png", "/转圈", "/:circle", "/:circle"));
		iconList.add(new FaceiconPo("icon_87.png", "/抱拳", "/:@)", "/:@\\)"));
		iconList.add(new FaceiconPo("icon_88.png", "/磕头", "/:kotow", "/:kotow"));
		iconList.add(new FaceiconPo("icon_89.png", "/勾引", "/:jj", "/:jj"));
		iconList.add(new FaceiconPo("icon_90.png", "/回头", "/:turn", "/:turn"));
		iconList.add(new FaceiconPo("icon_91.png", "/拳头", "/:@@", "/:@@"));
		iconList.add(new FaceiconPo("icon_92.png", "/跳绳", "/:skip", "/:skip"));
		iconList.add(new FaceiconPo("icon_93.png", "/差劲", "/:bad", "/:bad"));
		iconList.add(new FaceiconPo("icon_94.png", "/投降", "/:oY", "/:oY"));
		iconList.add(new FaceiconPo("icon_95.png", "/爱你", "/:lvu", "/:lvu"));
		iconList.add(new FaceiconPo("icon_96.png", "/激动", "/:#-O", "/:#-O"));		//
		iconList.add(new FaceiconPo("icon_97.png", "/NO", "/:no", "/:no"));
		iconList.add(new FaceiconPo("icon_98.png", "/乱舞", "/:hiphot", "/:hiphot"));
		iconList.add(new FaceiconPo("icon_99.png", "/OK", "/:ok", "/:ok"));
		iconList.add(new FaceiconPo("icon_100.png", "/献吻", "/:kiss", "/:kiss"));
		iconList.add(new FaceiconPo("icon_101.png", "/爱情", "/:love", "/:love"));
		iconList.add(new FaceiconPo("icon_102.png", "/左太极", "/:<&", "/:<&"));
		iconList.add(new FaceiconPo("icon_103.png", "/飞吻", "/:<L>", "/:<L>"));
		iconList.add(new FaceiconPo("icon_104.png", "/右太极", "/:&>", "/:&>"));
		iconList.add(new FaceiconPo("icon_105.png", "/跳跳", "/:jump", "/:jump"));
		
	}
	
	public static FaceIconUtil getInstance(){
		return FaceIconUtilHandler.instance;
	}
	
	/**
	 * 将字符串中的表情图标代码替换为图标路径
	 * @param str	待替换的字符串
	 * @param imageurl	图片路径的除去图标文件名的部分
	 * @return	将字符串中的图标字符替换为图标路径后的字符串
	 */
	public String replace(String str, String imageurl){
		//System.out.println("【原str】"+str+", 【imageurl】"+imageurl);
		for(FaceiconPo faceiconPo:iconList){
			if(str.indexOf(faceiconPo.getSign()) > -1){
				str = str.replaceAll(faceiconPo.getRegex(), "<img class='wxfaceicon' src='"+imageurl+"/"+faceiconPo.getFilename()+"' />");
			}
		}
		
		//System.out.println("【str】"+str);
		
		return str;
	}
}
