package com.quick.portal.security.authority.metric;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * 
 * @author 
 */
public class MetricPrivilegeUtils {
    
    private static SerializeConfig mapping = new SerializeConfig();
    
    static{
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * javaBean、list、map convert to json string
     */
    public static String obj2json(Object obj){
        return JSON.toJSONString(obj,mapping);
    }
    
    /**
     * json string convert to javaBean、map
     */
    public static <T> T json2obj(String jsonStr,Class<T> clazz){
        return JSON.parseObject(jsonStr,clazz);
    }
    
    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr,Class<T> clazz){
        return JSON.parseArray(jsonArrayStr, clazz);
    }
    
    /**
     * json string convert to map
     */
    public static <T> Map<String,Object> json2map(String jsonStr){
        return json2obj(jsonStr, Map.class);
    }
    
    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String,T> json2map(String jsonStr,Class<T> clazz){
        Map<String,T> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, T>>() {});
        for (Entry<String, T> entry : map.entrySet()) {
            JSONObject obj = (JSONObject) entry.getValue();
            map.put(entry.getKey(), JSONObject.toJavaObject(obj, clazz));
        }
        return map;
    }
    
    
	private static Map<String,Object> timekey = new HashMap<String,Object>();//time主属性 用于存放 需要保存的字段
	private static Map<String,Long> keytime = new HashMap<String,Long>();//time主属性 用于存放 需要保存的字段
	private static final long EXPIRATIONTIME=1000*60*120;//1个半小时
//	private static final long EXPIRATIONTIME=1000*60*2;//测试用120秒
	public static void put(String key,Object vale){
		timekey.put(key, vale);
		keytime.put(key, new Date().getTime());
	}
	
	public static Object get(String key){
		return timekey.get(key);
	}
	
	/*
	 * action :true
	 * no ation :flase
	 * 
	 */
	public static boolean isExpriationTime(String roleID){
		boolean bool = false;
		boolean isRoleID = false;
		long nd = new Date().getTime();//获取系统时间
		Iterator<Entry<String, Long>> entries = keytime.entrySet().iterator(); 
		if(entries.hasNext()){
			while (entries.hasNext()) {  
				Map.Entry<String,Object> entry = (Map.Entry) entries.next();   
				String key = (String)entry.getKey(); //获取key  
				long value = (Long)entry.getValue(); //获取value
				long rt = nd - value;//获取当前时间跟存入时间的差值
				if(key != null && roleID.equals(key)){
					isRoleID = true;
					if( rt>EXPIRATIONTIME){
						//overtime
						bool = true;
						return bool;
					}else{
						bool = false;
						return bool;
					}
				}
			}
			//用户不存在MAP中
			if(!isRoleID){
				put(roleID,roleID);
				bool = true;
			}
		}else {
			//no
			put(roleID,roleID);
			bool = true;
		}
		return bool;
	}
	
	private static ResourceBundle indexServicesProperties;
	  
    /** 配置文件myConfig.properties放在classpath下面otherConfigs目录下面   */
    public static final String INDEXSERVICES_PROPERTIES_FILENAME = "properties/config.properties";
    
    
    
    /**
     * 在静态块中通过<code>ResourceBundle<code>类来加载配置的<code>indexServicesProperties.properties</code>.
     */
    static {
        try {
        	indexServicesProperties = ResourceBundle.getBundle(INDEXSERVICES_PROPERTIES_FILENAME);
        } catch (Exception e) {
            System.out.println("加载indexServicesProperties配置文件错误！".concat(e.getMessage()));
 //           System.exit(0);
        }
    }
     
    /**
     * 通过在<code>indexServicesProperties.properties</code>中定义的key来取得对应的value.
     * 
     * @param key <code>indexServicesProperties.properties</code>的key.
     * @return 返回key对应的value，没有则返回"".
     */
    public static String getProValByKey(String key) {
        String str = indexServicesProperties.getString(key);
        return StringUtils.isEmpty(str) ? "" : str;
    }
     
    
}