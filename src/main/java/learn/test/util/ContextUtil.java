package learn.test.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

public class ContextUtil {

	private static String rootDir =null;
	
	private static String resourceDir =null;
	
	public static void main(String[] args) {
		
		System.out.println(getResourceDir());
		
	}
	public static String getRootDir() {
		if(null != rootDir){
			return rootDir;
		}
		
		URL url =FileUtil.class.getClassLoader().getResource("learn/test/util/ContextUtil.class");
	    String deployPath = "";
		try {
			System.out.println(url.getFile());
			deployPath = URLDecoder.decode(url.getPath(),"UTF-8");
			System.out.println(deployPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    int index = deployPath.indexOf("bin");
	    int startIndex=0;
	    startIndex =deployPath.indexOf("file:/");
	    if(-1 == startIndex){
	    	startIndex=0;
	    }else{
	    	startIndex =startIndex+5;
	    }
	    if(index !=-1){
	    	
	    	rootDir =deployPath.substring(startIndex,index);
	    }else{
	    	index = deployPath.indexOf("target");
	    	rootDir =deployPath.substring(startIndex,index);
	    }
		System.out.println(deployPath);
		System.out.println(rootDir);
		return rootDir;
	}
	
	public static String getResourceDir(){
		
		if(null != resourceDir){
			return resourceDir;
		}
		
		resourceDir = getRootDir()+ "resource/";
		
		resourceDir=resourceDir.replaceAll("\\\\", "/");
		return resourceDir ;
	}
	
}
