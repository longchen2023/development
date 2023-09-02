package com.example.izakaya.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.izakaya.model.IzakayaSession;
import com.example.izakaya.model.UserForm;
import com.example.izakaya.util.Constants;

/**
 * 
 * トップページ.
 * @author 双江
 *
 */
@Controller
@RequestMapping(value = Constants.URL_INDEX)
public class IzakayaIndexController {
	
	/**
	 *  トップ画面の初期表示.
	 * @return トップ画面
	 */
	@RequestMapping(value="",method= RequestMethod.GET)
	public String index() {
		return Constants.VIEW_INDEX;
	}
	
	/**
	 *  head部の初期表示.
	 * @return ヘッダー画面
	 */
	@RequestMapping(value=Constants.URL_HEAD,method= RequestMethod.GET)
	public String showHead() {
		return Constants.VIEW_HEAD;
	}
	
	/**
	 *  トップメニュー部の初期表示.
	 * @return トップメニューメニュー部画面
	 */
	@RequestMapping(value=Constants.URL_MENU_TOP,method= RequestMethod.GET)
	public String showMenuofTop() {
		return Constants.VIEW_MENU_TOP;
	}
	
	/**
	 *  ログイン部の初期表示.
	 * @param httpSession   httpSession
	 * @return ログイン部画面
	 */
	@RequestMapping(value=Constants.URL_MENU_LOGIN,method= RequestMethod.GET)
	public String showMenuOfLogin(Model model, HttpSession httpSession) {
		
		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		//ログインしていない場合、ログイン画面へ
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			model.addAttribute(Constants.USER_FORM,new UserForm());
			return Constants.VIEW_MENU_LOGIN;
		}
		//ログインした場合、管理画面へ
		return Constants.VIEW_MENU_MANAGE;
	}
	
	/**
	 *  メーン部の初期表示.
	 * @param model   model
	 * @param httpSession   httpSession
	 * @return メーン部画面
	 */
	@RequestMapping(value=Constants.URL_MAIN,method= RequestMethod.GET)
	public String shouMain(Model model, HttpSession httpSession) {
		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			return Constants.URL_REDIRECT + Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_SEARCH;
		}
		return Constants.VIEW_MAIN;
	}
	
	/**
	 *  blank画面の初期表示.
	 * @return blank画面
	 */
	@RequestMapping(value=Constants.URL_BLANK,method= RequestMethod.GET)
	public String shouBlank() {
		return Constants.VIEW_BLANK;
	}
	
	/**
	 * ログイン情報を取得する.
	 * 
	 * @param httpSession session
	 * @return ログイン情報
	 */
	private IzakayaSession getLoginInfo(HttpSession httpSession) {
		final IzakayaSession izakayaSession = (IzakayaSession) httpSession.getAttribute(Constants.IZAKAYA_SESSION);
		return izakayaSession;
	}

}
