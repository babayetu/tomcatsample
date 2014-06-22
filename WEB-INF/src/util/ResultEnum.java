package util;

import java.util.EnumSet;
import java.util.HashMap;

public enum ResultEnum {
	host_win("host_win","主胜"),
	guest_win("guest_win","客胜"),
	deuce("deuce","平局");
	
	private String en;
	private String zh;
	
	private static final HashMap<String, ResultEnum> lookUpMap = new HashMap<String, ResultEnum>();
	
	private ResultEnum(String aEN,String aZH) {
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
        for(ResultEnum nn : EnumSet.allOf(ResultEnum.class)){
        	lookUpMap.put(nn.en, nn);
        }
    }
 
    public static String findZH(String en){
    	ResultEnum value = lookUpMap.get(en);
        if(value == null){
            return host_win.getZH();
        }
        return value.getZH();
    }

}