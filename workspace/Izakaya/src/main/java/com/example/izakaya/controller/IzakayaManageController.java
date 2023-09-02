package com.example.izakaya.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.izakaya.entity.CommentInfo;
import com.example.izakaya.entity.IzakayaInfo;
import com.example.izakaya.model.IzakayaCommentListForm;
import com.example.izakaya.model.IzakayaDeleteCommentForm;
import com.example.izakaya.model.IzakayaForm;
import com.example.izakaya.model.IzakayaListForm;
import com.example.izakaya.model.IzakayaSession;
import com.example.izakaya.model.PasswordForm;
import com.example.izakaya.model.UserForm;
import com.example.izakaya.service.IzakayaManageService;
import com.example.izakaya.util.Constants;

/**
 * 
 * マネジメント管理.
 * 
 * @author 双江
 *
 */
@Controller
@RequestMapping(value = Constants.URL_MANAGE)
public class IzakayaManageController {

	@Autowired
	IzakayaManageService izakayaManageService;

	/**
	 * ログイン処理.
	 * @param model model
	 * @param userForm form 
	 * @param bindingResult チェック結果
	 * @param httpSession session
	 * @return トップ画面
	 */
	@RequestMapping(value = Constants.URL_LOGIN_IN, method = RequestMethod.POST)
	public String login(Model model, @Valid UserForm userForm, BindingResult bindingResult, HttpSession httpSession) {

		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			return Constants.VIEW_MENU_LOGIN;
		}

		// 入力したユーザ情報をチェックする.
		final boolean checkResult = izakayaManageService.checkLoginInfo(userForm.getUserId(),userForm.getUserPassword());
		if (!checkResult) {
			// ユーザ情報が存在していない場合、エラーを返す
			model.addAttribute(Constants.LOGIN_NOT_EXIST_ERROR, true);
			return Constants.VIEW_MENU_LOGIN;
		}

		// sessionにユーザ情報を設定
		final IzakayaSession izakayaSession = new IzakayaSession();
		izakayaSession.setUserId(userForm.getUserId());
		izakayaSession.setPassword(userForm.getUserPassword());
		izakayaSession.setLogin(true);
		httpSession.setAttribute(Constants.IZAKAYA_SESSION, izakayaSession);
		return Constants.VIEW_MENU_MANAGE;
	}
	
	/**
	 * ログアウト処理.
	 * @param model model
	 * @param userForm form 
	 * @param bindingResult チェック結果
	 * @param httpSession session
	 * @return ログイン画面
	 */
	@RequestMapping(value = Constants.URL_LOGIN_OUT, method = RequestMethod.POST)
	public String loginOut(Model model, @Valid UserForm userForm, BindingResult bindingResult, HttpSession httpSession) {

		
		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession != null) {
			//ログイン情報を削除する
			httpSession.removeAttribute(Constants.IZAKAYA_SESSION);
		}
		return Constants.VIEW_MENU_LOGIN;
	}
	
	/**
	 * パスワード変更画面へ.
	 * @param model model
	 * @param httpSession session
	 * @return パスワード変更画面
	 */
	@RequestMapping(value = Constants.URL_TO_PASSWORD_UPDATE, method = RequestMethod.GET)
	public String showPasswordUpdate(Model model, HttpSession httpSession) {

		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			// TODO エラーページにいく予定
		}

		model.addAttribute(Constants.PASSWORD_FORM, new PasswordForm());
		return Constants.VIEW_MANAGER_PASSWORD_UPDATE;
	}
	
	/**
	 * パスワード変更処理.
	 * @param model model
	 * @param userForm form 
	 * @param bindingResult チェック結果
	 * @param httpSession session
	 * @return パスワード変更管理画面
	 */
	@RequestMapping(value = Constants.URL_PASSWORD_UPDATE, method = RequestMethod.POST)
	public String passwordUpdate(Model model, @Valid PasswordForm passwordForm, BindingResult bindingResult, HttpSession httpSession) {

		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			return Constants.VIEW_MANAGER_PASSWORD_UPDATE;
		}

		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			// TODO エラーページにいく予定
		}

		// パスワード変更処理
		final boolean checkResult = izakayaManageService.checkAndUpdateManageInfo(passwordForm);
		if(!checkResult) {
			model.addAttribute(Constants.PASSWORD_UPDATE_ERROR, true);
		}else {
			model.addAttribute(Constants.PASSWORD_UPDATE_ERROR,false );
		}
		
		return Constants.VIEW_MANAGER_PASSWORD_UPDATE_RESULT;
	}

	/**
	 * 居酒屋一覧画面へ.
	 * @param model model
	 * @param httpSession session
	 * @return 居酒屋一覧画面
	 */
	@RequestMapping(value = Constants.URL_TO_IZAKAYA_LIST, method = RequestMethod.GET)
	public String showIzakayaList(Model model, HttpSession httpSession) {

		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			// TODO エラーページにいく予定
		}

		// form情報を設定
		final IzakayaListForm izakayaListForm = new IzakayaListForm();
		final List<IzakayaInfo> resultList = izakayaManageService.getIzakayaList();
		izakayaListForm.setIzakayaList(resultList);

		model.addAttribute(Constants.IZAKAYA_LIST_FORM, izakayaListForm);
		return Constants.VIEW_IZAKAYA_LIST;
	}

	/**
	 * 居酒屋登録画面へ.
	 * @param model model
	 * @param httpSession session
	 * @return 居酒屋登録画面
	 */
	@RequestMapping(value = Constants.URL_TO_IZAKAYA_REGISTER, method = RequestMethod.GET)
	public String showIzakayaRegister(Model model, HttpSession httpSession) {

		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			// TODO エラーページにいく予定
		}

		model.addAttribute(Constants.IZAKAYA_FORM, new IzakayaForm());
		return Constants.VIEW_IZAKAYA_REGISTER;
	}

	/**
	 * 居酒屋登録処理.
	 * @param model model
	 * @param izakayaForm form 
	 * @param bindingResult チェック結果
	 * @param httpSession session
	 * @return 登録結果画面
	 */
	@RequestMapping(value = Constants.URL_IZAKAYA_REGISTER, method = RequestMethod.POST)
	public String registerIzakaya(Model model, @Valid IzakayaForm izakayaForm, BindingResult bindingResult,
			HttpSession httpSession) {

		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			// TODO エラーページにいく予定
		}

		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			return Constants.VIEW_IZAKAYA_REGISTER;
		}

		// 居酒屋情報をDBに登録する
		final boolean registerIsOk = izakayaManageService.registerIzakaya(izakayaForm, izakayaSession.getUserId());
		if (!registerIsOk) {
			model.addAttribute(Constants.IZAKAYA_REGISTER_ERROR, true);
		} else {
			model.addAttribute(Constants.IZAKAYA_REGISTER_ERROR, false);
		}

		// 登録結果画面へ
		return Constants.VIEW_IZAKAYA_REGISTER_RESULT;
	}

	/**
	 * 居酒屋修正画面へ.
	 * 
	 * @param model       model
	 * @param id          修正対象ID
	 * @param httpSession session
	 * @return 居酒屋修正画面
	 */
	@RequestMapping(value = Constants.URL_TO_IZAKAYA_UPDATE, method = RequestMethod.GET)
	public String showIzakayaUpdate(Model model, @RequestParam("izakayaId") String id, HttpSession httpSession) {

		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			// TODO エラーページにいく予定
		}

		// 取得した居酒屋情報をizakayaFormに設定
		IzakayaForm izakayaForm = new IzakayaForm();
		final IzakayaInfo izakayaInfo = izakayaManageService.getIzakayaById(id);
		izakayaForm.setNo(izakayaInfo.getId());
		izakayaForm.setShopName(izakayaInfo.getName());
		izakayaForm.setArea(izakayaInfo.getArea());
		izakayaForm.setGenre(izakayaInfo.getGenre());
		izakayaForm.setOpenTime(izakayaInfo.getOpening_time());
		izakayaForm.setCloseTime(izakayaInfo.getClosing_time());
		izakayaForm.setHoliday(izakayaInfo.getHoliday());
		izakayaForm.setPrice(izakayaInfo.getPrice());
		izakayaForm.setAddress(izakayaInfo.getAddress());
		izakayaForm.setMaxNumOfPeople(izakayaInfo.getMax_num_of_people());
		izakayaForm.setTele(izakayaInfo.getTele());
		izakayaForm.setUrl(izakayaInfo.getUrl());

		model.addAttribute(Constants.IZAKAYA_FORM, izakayaForm);
		return Constants.VIEW_IZAKAYA_UPDATE;
	}

	/**
	 * 居酒屋修正処理.
	 * 
	 * @param model         model
	 * @param izakayaForm   form
	 * @param bindingResult チェック結果
	 * @param httpSession   session
	 * @return 居酒屋修正結果画面
	 */
	@RequestMapping(value = Constants.URL_IZAKAYA_UPDATE, method = RequestMethod.POST)
	public String updateIzakaya(Model model, @Valid IzakayaForm izakayaForm, BindingResult bindingResult,
			HttpSession httpSession) {

		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			// TODO エラーページにいく予定
		}

		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			return Constants.VIEW_IZAKAYA_UPDATE;
		}

		// 居酒屋情報をDBにアップロードする
		final boolean updateIsOk = izakayaManageService.updateIzakaya(izakayaForm, izakayaSession.getUserId());
		if (!updateIsOk) {
			model.addAttribute(Constants.IZAKAYA_UPDATE_ERROR, true);
		} else {
			model.addAttribute(Constants.IZAKAYA_UPDATE_ERROR, false);
		}

		// 修正結果画面へ
		return Constants.VIEW_IZAKAYA_UPDATE_RESULT;
	}

	/**
	 * 居酒屋削除処理.
	 * 
	 * @param model         model
	 * @param izakayaForm   form
	 * @param httpSession   session
	 * @return 居酒屋一覧画面
	 */
	@RequestMapping(value = Constants.URL_IZAKAYA_DELETE, method = RequestMethod.POST)
	public String deleteIzakaya(Model model,IzakayaForm izakayaForm, HttpSession httpSession) {

		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			// TODO エラーページにいく予定
		}

		// バリデーションチェック
		if(izakayaForm.getNo() == 0) {
			//改ざんチェックがかかってる場合、エラーページに遷移
			//TODO
		}

		// 居酒屋情報をDBにから削除する
		final boolean deleteIsOk = izakayaManageService.deleteIzakaya(String.valueOf(izakayaForm.getNo()));
		if (!deleteIsOk) {
			//TODO　削除エラー
		} 

		// rediect
		return Constants.URL_REDIRECT + Constants.URL_MANAGE + Constants.URL_TO_IZAKAYA_LIST;
	}
	
	
	/**
	 * 居酒屋コメント削除対象画面へ.
	 * @param model model
	 * @param httpSession session
	 * @return 居酒屋コメント削除対象画面
	 */
	@RequestMapping(value = Constants.URL_TO_IZAKAYA_COMMENT_TARGET, method = RequestMethod.GET)
	public String showIzakayaCommentTarget(Model model, HttpSession httpSession) {

		final IzakayaSession izakayaSession = getLoginInfo(httpSession);
		if (izakayaSession == null || !izakayaSession.isLogin()) {
			// TODO エラーページにいく予定
		}

		// form情報を設定
		final IzakayaListForm izakayaListForm = new IzakayaListForm();
		final List<IzakayaInfo> resultList = izakayaManageService.getIzakayaList();
		izakayaListForm.setIzakayaList(resultList);

		model.addAttribute(Constants.IZAKAYA_LIST_FORM, izakayaListForm);
		return Constants.VIEW_IZAKAYA_COMMENT_TARGET;
	}
	
	/**
	 * 居酒屋コメント削除画面へ.
	 * 
	 * @param model model
	 * @param id 居酒屋id
	 * @return 居酒屋コメント削除画面
	 */
	@RequestMapping(value = Constants.URL_TO_IZAKAYA_COMMENT_DELETE, method = RequestMethod.GET)
	public String showIzakayaDetail(Model model,@ModelAttribute("izakayaId") String redirId, @RequestParam(required=false,name="izakayaId") String paramId) {

		//居酒屋id
		String izakayaId = paramId;
		if(paramId == null) {
			izakayaId = redirId;
		}
		
		//取得した居酒屋コメント情報をIzakayaCommentListFormに設定
		final IzakayaCommentListForm izakayaCommentListForm = new IzakayaCommentListForm();
		//居酒屋のリスト
		 List<CommentInfo> commentList = izakayaManageService.getIzakayaCommentById(izakayaId);
		 izakayaCommentListForm.setCommentList(commentList);

		model.addAttribute(Constants.IZAKAYA_COMMENT_LIST_FORM, izakayaCommentListForm);
		model.addAttribute(Constants.ATTR_IZAKAYA_ID, izakayaId);
		return Constants.VIEW_IZAKAYA_COMMENT;
	}
	
	
	/**
	 * 居酒屋コメント削除.
	 * 
	 * @param redirectAttributes redirectAttributes
	 * @param model model
	 * @param izakayaDeleteCommentForm 居酒屋コメントの削除Form
	 * @return 居酒屋コメント削除画面
	 */
	@RequestMapping(value = Constants.URL_IZAKAYA_COMMENT_DELETE, method = RequestMethod.POST)
	public String deleteIzakayaComment(RedirectAttributes redirectAttributes, Model model,IzakayaDeleteCommentForm izakayaDeleteCommentForm) {

		
		// 削除結果
		final boolean deleteIsOk = izakayaManageService.deleteIzakayaCommentById(izakayaDeleteCommentForm.getIds());
		if(!deleteIsOk) {
			//TODOエラーページに遷移
		}

		redirectAttributes.addFlashAttribute(Constants.ATTR_IZAKAYA_ID, izakayaDeleteCommentForm.getIzakayaId());
		// rediect
		return Constants.URL_REDIRECT + Constants.URL_MANAGE + Constants.URL_TO_IZAKAYA_COMMENT_DELETE;
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
