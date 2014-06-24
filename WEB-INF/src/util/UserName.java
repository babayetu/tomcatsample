package util;

import java.util.EnumSet;
import java.util.HashMap;

public enum UserName {
	gluo("gluo","Luo Guoping"),
	jianshuang("jianshuang","Huang Jianshi"),
	jingjliu("jingjliu","Liu Karl"),
	jyao1("jyao1","Yao Jin"),
	jwan("jwan","Wan Jian"),
	jzhang14("jzhang14","Zhang Jie"),
	kezheng("kezheng","Zheng Ke"),
	lai("lai","Susan Ai"),
	likawang("likawang","Wang Likai"),
	linhu("linhu","Irena Hu"),
	linmeng("linmeng","Meng Linggong"),
	lulli("lulli","Li Luliang"),
	shdong("shdong","Evan Dong"),
	tingszhang("tingszhang","Zhang Emerson"),
	wguo("wguo","Guo Weiji"),
	wsun1("wsun1","Sun Austin"),
	xianguo("xianguo","Guo Jacky"),
	xiaofu("xiaofu","Fu Xiaoyi"),
	yanlwang("yanlwang","Wang Yanling"),
	yingding("yingding","Ding Yingqi"),
	yxwang("yxwang","Novia Wang");
	
	private String en;
	private String zh;
	
	private static final HashMap<String, UserName> lookUpMap = new HashMap<String, UserName>();
	
	private UserName(String aEN,String aZH) {
		this.zh = aZH;
		this.en = aEN;
	}
	
	public String getZH() {
		return this.zh;
	}
	
	public String getEN() {
		return this.en;
	}
	
	static {
        for(UserName nn : EnumSet.allOf(UserName.class)){
        	lookUpMap.put(nn.en, nn);
        }
    }
 
    public static String findZH(String en){
    	UserName value = lookUpMap.get(en);
        if(value == null){
            return tingszhang.getZH();
        }
        return value.getZH();
    }

}