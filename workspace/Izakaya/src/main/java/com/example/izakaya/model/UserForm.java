package com.example.izakaya.model;

import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ユーザForm.
 * @author 双江
 *
 */
@Data 
public class UserForm {
	
	//ユーザID
	@Pattern(regexp = "^[0-9a-zA-Z]+$",message = "{user.name.error}")
    private String userId;
    //ユーザ暗証番号
	@Pattern(regexp = "^[0-9a-zA-Z]+$",message = "{user.password.error}")
    private String userPassword;
	
}
