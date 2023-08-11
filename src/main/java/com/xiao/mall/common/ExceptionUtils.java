package com.xiao.mall.common;

import java.io.PrintWriter;
import java.io.StringWriter;
 
public class ExceptionUtils {
	public static String getStackTrace(Exception e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer, true));
		return writer.toString();
	}
}