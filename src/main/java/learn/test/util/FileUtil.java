package learn.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileUtil {

	public static String defaultImage = "";
	public static byte[] getFile(String file){
		
		File fi = new File(file);
		byte[] ret =null;
		int size =0;
		InputStream in =null;
		if((fi.isFile() && fi.exists())){
			 size = (int) fi.length();
		}else{
			file= ContextUtil.getResourceDir()+"Default.jpg";
			fi = new File(file);
			size = (int) fi.length();
			System.out.println(size);
		}
		
		ret =new byte[size];
		try {
			 in= new FileInputStream(fi);
			 in.read(ret);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in !=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ret;
		
	}
	
	public static void main(String[] args) {
		FileUtil.getFile("C:/Users/lll/Downloads/bg(08-28-.jpg");
	}
}
