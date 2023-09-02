package com.example.izakaya.util;
/**
 * constants.
 * @author 双江
 *
 */
public class Constants {
	
	
	//画面：トップページ
	public static final String VIEW_INDEX = "view_index"; 
	
	//画面：head部
	public static final String VIEW_HEAD = "view_head"; 
	
	//画面：トップメニュー
	public static final String VIEW_MENU_TOP = "view_menu_top"; 
	
	//画面：blank画面
	public static final String VIEW_BLANK = "/view_blank"; 
	
	//画面：ログイン
	public static final String VIEW_MENU_LOGIN = "view_menu_login"; 
	
	//画面：パスワード変更画面
	public static final String VIEW_MANAGER_PASSWORD_UPDATE= "view_manager_password_update"; 
	
	//画面：パスワード変更結果画面
	public static final String VIEW_MANAGER_PASSWORD_UPDATE_RESULT= "view_manager_password_update_result"; 
	
	//画面：メーン
	public static final String VIEW_MAIN = "view_main"; 
	
	//画面：居酒屋管理
	public static final String VIEW_MENU_MANAGE = "view_menu_manage"; 
	
	//画面：居酒屋登録リスト
	public static final String VIEW_IZAKAYA_LIST = "/view_izakaya_list";
	
	//画面：居酒屋登録
	public static final String VIEW_IZAKAYA_REGISTER = "/view_izakaya_register";
	
	//画面：居酒屋登録結果
	public static final String VIEW_IZAKAYA_REGISTER_RESULT = "/view_izakaya_register_result";
	
	//画面：居酒屋修正
	public static final String VIEW_IZAKAYA_UPDATE = "/view_izakaya_update";
	
	//画面：居酒屋修正結果
	public static final String VIEW_IZAKAYA_UPDATE_RESULT = "/view_izakaya_update_result";
	
	//画面：居酒屋検索（conditions）
	public static final String VIEW_IZAKAYA_SEARCH = "/view_izakaya_search";
	
	//画面：居酒屋検索（all）
	public static final String VIEW_IZAKAYA_SEARCH_ALL = "/view_izakaya_search_all";
	
	//画面：居酒屋詳細
	public static final String VIEW_IZAKAYA_DETAIL = "/view_izakaya_detail";
	
	//画面：居酒屋コメント削除対象
	public static final String VIEW_IZAKAYA_COMMENT_TARGET = "/view_izakaya_comment_target";
	
	//画面：居酒屋コメント削除
	public static final String VIEW_IZAKAYA_COMMENT = "/view_izakaya_comment";
	
	
	
		

	
	
	//URL：redirect
	public static final String URL_REDIRECT = "redirect:"; 
	//URL：トップページコントロール
	public static final String URL_INDEX = "/index"; 
	
	//URL：manageコントロール
	public static final String URL_MANAGE = "/manage";
	
	//URL：searchコントロール
	public static final String URL_SEARCH = "/search";

	//URL：head部へ
	public static final String URL_HEAD = "/head"; 
	
	//URL：トップメニューへ
	public static final String URL_MENU_TOP = "/menu_top"; 
	
	//URL：ログインへ
	public static final String URL_MENU_LOGIN = "/menu_login"; 
	
	//URL：メーンへ
	public static final String URL_MAIN = "/main"; 
	
	//URL：blankへ
	public static final String URL_BLANK = "/blank"; 
	
	//URL：ログイン情報をチェックして、マネジメント画面へ
	public static final String URL_LOGIN_IN = "/login_in";
	
	//URL：ログイン情報を削除して、ログイン画面へ
	public static final String URL_LOGIN_OUT = "/login_out";
	
	//URL：パスワード変更画面へ
	public static final String URL_TO_PASSWORD_UPDATE = "/to_password_update";
	
	//URL：パスワード変更処理
	public static final String URL_PASSWORD_UPDATE = "/password_update";
	
	//URL：居酒屋一覧画面へ
	public static final String URL_TO_IZAKAYA_LIST= "/to_izakaya_list";
	
	//URL：居酒屋登録画面へ
	public static final String URL_TO_IZAKAYA_REGISTER = "/to_izakaya_register";
	
	//URL：居酒屋情報を登録
	public static final String URL_IZAKAYA_REGISTER = "/izakaya_register";
	
	//URL：居酒屋修正画面へ
	public static final String URL_TO_IZAKAYA_UPDATE = "/to_izakaya_update";
	
	//URL：居酒屋情報を修正
	public static final String URL_IZAKAYA_UPDATE = "/izakaya_update";
	
	//URL：居酒屋情報を削除
	public static final String URL_IZAKAYA_DELETE = "/izakaya_delete";
	
	//URL：居酒屋検索画面へ
	public static final String URL_TO_IZAKAYA_SEARCH= "/to_izakaya_search";
	
	//URL：検索パラメータを取得して、検索
	public static final String URL_IZAKAYA_SEARCH = "/izakaya_search";
	
	//URL：居酒屋情報を検索
	public static final String URL_IZAKAYA_SEARCH_ALL = "/izakaya_search_all";
	
	//URL：居酒屋詳細画面へ
	public static final String URL_TO_IZAKAYA_DETAIL= "/to_izakaya_detail";
	
	//URL：居酒屋へのコメント
	public static final String URL_IZAKAYA_COMMENT= "/to_izakaya_comment";
	
	//URL：居酒屋のコメントの削除対象画面へ
	public static final String URL_TO_IZAKAYA_COMMENT_TARGET= "/to_izakaya_comment_target";	
	
	//URL：居酒屋のコメントの削除画面へ
	public static final String URL_TO_IZAKAYA_COMMENT_DELETE= "/to_izakaya_comment_delete";	

	//URL：居酒屋のコメントの削除
	public static final String URL_IZAKAYA_COMMENT_DELETE= "/izakaya_comment_delete";	
	
	
	
	
	
	
	
	
	
	
	//セッション管理
	public static final String IZAKAYA_SESSION = "izakaya_session";
	//userForm
	public static final String USER_FORM = "userForm";
	//izakayaForm
	public static final String IZAKAYA_FORM = "izakayaForm";
	//izakayaListForm
	public static final String IZAKAYA_LIST_FORM = "izakayaListForm";
	//passwordForm
	public static final String PASSWORD_FORM = "passwordForm";
	//passwordForm
	public static final String IZAKAYA_SEARCH_FORM = "izakayaSearchForm";
	//izakayaCommentListForm
	public static final String IZAKAYA_COMMENT_LIST_FORM = "izakayaCommentListForm";
	//izakayaCommentForm
	public static final String IZAKAYA_COMMENT_FORM = "izakayaCommentForm";
	//izakayaDeleteCommentForm
	public static final String IZAKAYA_DELETE_COMMENT_FORM = "izakayaDeleteCommentForm";
	
	
	
	
	
	//INFO
	//ログイン情報が存在していないエラー
	public static final String LOGIN_NOT_EXIST_ERROR = "loginNotExistError";
	//居酒屋情報が成功に登録した場合、false 以外はtrue
	public static final String IZAKAYA_REGISTER_ERROR = "izakayaRegisterError";
	//居酒屋情報が成功に更新した場合、false 以外はtrue
	public static final String IZAKAYA_UPDATE_ERROR = "izakayaUpdateError";
	//パスワード情報が成功に更新した場合、false 以外はtrue
	public static final String PASSWORD_UPDATE_ERROR = "passwordUpdateError";
	
	//parameter 
	
	//居酒屋Id
	public static final String ATTR_IZAKAYA_ID = "izakayaId";
	
	
}
