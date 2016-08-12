package com.vince.imageloaderexp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyUtils {

	public static void  close(InputStream in){
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void  close(OutputStream out){
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
