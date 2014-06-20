package util;

import java.util.EnumSet;
import java.util.HashMap;

public enum NationName {
	brazil("brazil","巴西"),
	argentina("argentina","阿根廷"),
	belgium("belgium","比利时"),
	cameroon("cameroon","喀麦隆"),
	chile("chile","智利"),
	columbia("columbia","哥伦比亚"),
	cotedlvoir("cotedlvoir","科特迪瓦"),
	england("england","英格兰"),
	france("france","法国"),
	germany("germany","德国"),
	ghana("ghana","加纳"),
	iran("iran","伊朗"),
	japan("japan","日本"),
	mexico("mexico","墨西哥"),
	russia("russia","俄罗斯"),
	spain("spain","西班牙"),
	swiss("swiss","瑞士"),
	uruguay("uruguay","乌拉圭"),
	algeria("algeria","阿尔及利亚"),
	australia("australia","澳大利亚"),
	bosniahercegovina("bosniahercegovina","波黑"),
	costarica("costarica","哥斯达黎加"),
	croatia("croatia","克罗地亚"),
	ecuador("ecuador","厄瓜多尔"),
	greece("greece","希腊"),
	holand("holand","荷兰"),
	honduras("honduras","洪都拉斯"),
	italy("italy","意大利"),
	korea("korea","韩国"),
	nigeria("nigeria","尼日利亚"),
	portugal("portugal","葡萄牙"),
	usa("usa","美国");
	
	private String en;
	private String zh;
	
	private static final HashMap<String, NationName> lookUpMap = new HashMap<String, NationName>();
	
	private NationName(String aEN,String aZH) {
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
        for(NationName nn : EnumSet.allOf(NationName.class)){
        	lookUpMap.put(nn.en, nn);
        }
    }
 
    public static String findZH(String en){
    	NationName value = lookUpMap.get(en);
        if(value == null){
            return brazil.getZH();
        }
        return value.getZH();
    }

}