package com.example.izakaya.model;

import java.util.List;

import com.example.izakaya.entity.CommentInfo;

import lombok.Data;

/**
 * 居酒屋コメントForm.
 * @author 双江
 *
 */
@Data 
public class IzakayaCommentListForm {

	//居酒屋のリスト
	private List<CommentInfo> commentList;
	
}
