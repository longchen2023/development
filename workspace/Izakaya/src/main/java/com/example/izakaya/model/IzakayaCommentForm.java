package com.example.izakaya.model;

import lombok.Data;

/**
 * 居酒屋コメントForm.
 * @author 双江
 *
 */
@Data 
public class IzakayaCommentForm {

	//id
	private String id;
	//コメント内容
	private String content;
	//評価
	private String assessment;
	//有効フラグ
	private String validFlag;
	//居酒屋ID
	private String izakayaId;
	//ユーザID
	private String userId;
	//登録時間
	private String registerTime;
	
}
