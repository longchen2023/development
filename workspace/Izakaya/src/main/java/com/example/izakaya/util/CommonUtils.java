package com.example.izakaya.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Common方法.
 * @author 双江
 *
 */
public class CommonUtils {
	
	/**
	 * 現在時間を取得する.
	 * @param formatter
	 * @return フォーマットした時間の文字列
	 */
	public static String getNowDateTime(DateTimeFormatter formatter) {
	
		//ローカル時間を取得する
		if(formatter == null) {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		}
	     String localDateTime = LocalDateTime.now().format(formatter);
	     return localDateTime;
	}

}
