package cn.bjfu.fesdmp.utils;  

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;

/** 
 * ClassName:FileUploadUtils <br/> 
 * Function: FileUploadUtils. <br/> 
 * Reason:   FileUploadUtils. <br/> 
 * Date:     2014年3月4日 上午10:52:12 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7
 * @see       
 */
public class FileOperationUtils {

	private static String fileRootPath;
	
	static {
		fileRootPath = IndexPropertyUtil.getKeyValueByName("docmentPath");
	}
	
	private FileOperationUtils() {}
	
	public static void download(String filePath, String fileName, String contentType, 
					HttpServletResponse response) throws Exception {
		BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	    
	    File file = new File(fileRootPath + filePath);
	    long fileLength = file.length();
	    // 获取文件的MIME 类型
	    contentType = new MimetypesFileTypeMap().getContentType(file)+";charset=UTF-8";
	    
	    response.setContentType(contentType);
	    response.setHeader("Content-disposition", "attachment; filename="
                + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));
        bis = new BufferedInputStream(new FileInputStream(file));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        
        bis.close();
        bos.close();
	}
	
	/**
	 * 
	 * getFileExt:<br />
	 * 获取文件的扩展名
	 *
	 * @author zhangzhaoyu
	 * @param filaName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		int position = fileName.lastIndexOf(".");
		return fileName.substring(position +1);
	}
	
	public static void main(String []args) {
		System.out.println(getFileExt("xxxxx.pdf"));
	}
}
 