package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

	/*
	 * 上传文件
	 */
	@SuppressWarnings("unused")
	private static void uploadFile(File src, File dst) { 
		InputStream in = null; 
		OutputStream out = null; 
		try { 
			in = new BufferedInputStream(new FileInputStream(src), 2048); 
			out = new BufferedOutputStream(new FileOutputStream(dst),2048); 
			byte[] buffer = new byte[2048]; 
			int len = 0; 
			while ((len = in.read(buffer)) > 0) { 
				out.write(buffer, 0, len); 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} finally { 
			if (null != in) { 
				try { 
					in.close(); 
				} catch (IOException e) { 
					e.printStackTrace(); 
				} 
			} 
			if (null != out) { 
				try { 
					out.close(); 
				} catch (IOException e) { 
					e.printStackTrace(); 
				} 
			} 
		} 
	} 



}
