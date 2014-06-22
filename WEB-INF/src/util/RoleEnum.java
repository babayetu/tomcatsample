package util;

import java.util.EnumSet;
import java.util.HashMap;

public enum RoleEnum {
	admin("admin","管理员"),
	gambler("gambler","参赛者"),
	watcher("watcher","观众");
	
	private String en;
	private String zh;
	
	private static final HashMap<String, RoleEnum> lookUpMap = new HashMap<String, RoleEnum>();
	
	private RoleEnum(String aEN,String aZH) {
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
        for(RoleEnum nn : EnumSet.allOf(RoleEnum.class)){
        	lookUpMap.put(nn.en, nn);
        }
    }
 
    public static String findZH(String en){
    	RoleEnum value = lookUpMap.get(en);
        if(value == null){
            return watcher.getZH();
        }
        return value.getZH();
    }

}