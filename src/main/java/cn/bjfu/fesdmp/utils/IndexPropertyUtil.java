package cn.bjfu.fesdmp.utils;  

import java.io.File;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/** 
 * ClassName:IndexPropertyUtil <br/> 
 * Function: IndexPropertyUtil. <br/> 
 * Reason:   IndexPropertyUtil. <br/> 
 * Date:     2014年2月27日 下午4:02:57 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7
 * @see       
 */
public class IndexPropertyUtil {

	/**
	 * 
	 * getKeyValueByName:<br />
	 * 根据key 获取值
	 *
	 * @author zhangzhaoyu
	 * @param name
	 * @return
	 */
	public static String getKeyValueByName(String name) {
		String path = null;
		Resource resource = new ClassPathResource("index.properties");
		try {
			
			if (resource.exists()) {
				Properties prop = new Properties();
				prop.load(resource.getInputStream());
				path = prop.getProperty(name);
				
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
			} else {
				path = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
}
 