package com.example.izakaya.model;

import java.util.List;

import com.example.izakaya.entity.IzakayaInfo;

import lombok.Data;

/**
 * 居酒屋のリストform.
 * @author 双江
 *
 */
@Data
public class IzakayaListForm {
	
	//居酒屋のリスト
	private List<IzakayaInfo> izakayaList;

}
