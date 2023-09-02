package com.example.izakaya.model;

import lombok.Data;

/**
 * ユーザ情報管理.
 * 
 * @author 双江
 *
 */
@Data
public class IzakayaSession {

    //ユーザID
	private String userId;

    //ユーザ名前
	private String username;

    //ユーザ暗証番号
	private String password;
	
	//権限リスト
	private String[] authorities;
	
	//ログイン状態
	private boolean isLogin;

}
