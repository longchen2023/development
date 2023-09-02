package com.example.izakaya.entity;

import lombok.Data;

/**
 * ユーザentity.
 * @author 双江
 *
 */
@Data
public class UserInfo {

	//ユーザID
    private String id;
    //ユーザ名
    private String name;
    //暗証番号
    private String password;
    //登録時間
    private String register_time;
    //更新時間
    private String update_time;
}
