package cn.bjfu.fesdmp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** 
 * ClassName:FileUtils <br/> 
 * Function: FileUtils. <br/> 
 * Reason:   FileUtils. <br/> 
 * Date:     2013年10月23日 下午4:28:05 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class FileUtils {

	/**
	 * 
	 * readFile:<br />
	 * 读取文件的内容
	 *
	 * @author zhangzhaoyu
	 * @param fileName
	 * 包含文件的路径和名字
	 * @return
	 */
	public static String readFile(String fileName) {
		
		String content = "";
		File file = new File(fileName);
		BufferedReader input = null;
		
		if (file.exists() && file.isFile()) {
			 try{  
                 input = new BufferedReader (new FileReader(file));  
                 StringBuffer buffer = new StringBuffer();  
                 String text;  
                      
                 while((text = input.readLine()) != null)  
                     buffer.append(text);  
                      
                 content = buffer.toString();                      
             }  catch(IOException e){  
            	 e.printStackTrace();
             }  finally {
            	 try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
             }
		}
		
		return content;
	}
	
	public static String readFile(File file) {
		
		String content = "";
		 BufferedReader input = null;
		 
		if (file.exists() && file.isFile()) {
			 try{  
                 input = new BufferedReader (new FileReader(file));  
                 StringBuffer buffer = new StringBuffer();  
                 String text;  
                      
                 while((text = input.readLine()) != null)  
                     buffer.append(text);  
                      
                 content = buffer.toString();                      
             }  catch(IOException e){  
            	 e.printStackTrace();
             }  finally {
            	 try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
             }
		}
		
		return content;
	}
	
	/**
	 * 
	 * deleteFile:<br />
	 * TODO 删除文件
	 *
	 * @author huoshansir
	 * @param path  删除文件的路径
	 * @return
	 */
	
	public static boolean deleteFile(String path){
		File file  = new File(path);
		if(file.exists()){
			return file.delete();
		}else{
			return false;
		}
	}
	/**
	 * 
	 * getRealFileName:<br />
	 * 通过Apache FileUpLoad 组建获取的上传文件名，在有的机器上会自带路径，此函数
	 * 功能是用来仅仅获取文件名。
	 *
	 * @author zhangzhaoyu
	 * @param fileName
	 * @return
	 */
	public static String getRealFileName(String fileName) {
		if (fileName != null) {
			// 获取系统分割符
			String separator = System.getProperty("file.separator");
			int posi = fileName.lastIndexOf(separator);
			fileName = fileName.substring(posi+1);
		}
		return fileName;
	}
}
 