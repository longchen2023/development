package com.example.izakaya.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * 居酒屋Form.
 * @author 双江
 *
 */
@Data 
public class IzakayaSearchForm {
	
	//地域名
	private String area;
	//ジャンル
	private String genre;
	//予約人数
	@Pattern(regexp = "^[0-9]+$",message = "{izakayaSearchForm.reservationNums.error}")
	private String reservationNums;
	//料金範囲（最小）
	@Pattern(regexp = "^[0-9]+$",message = "{izakayaSearchForm.minAmount.error}")
	private String minAmount;
	//料金範囲（最大）
	@Pattern(regexp = "^[0-9]+$",message = "{izakayaSearchForm.maxAmount.error}")
	private String maxAmount;
	

}
