package com.example.izakaya.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.izakaya.entity.IzakayaInfo;
import com.example.izakaya.model.IzakayaCommentForm;
import com.example.izakaya.model.IzakayaSearchForm;
import com.example.izakaya.model.IzakayaSession;
import com.example.izakaya.service.IzakayaSearchService;
import com.example.izakaya.util.CommonUtils;
import com.example.izakaya.util.Constants;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IzakayaSearchControllerTests {

	private MockMvc mockMvc;
	
    @MockBean
    private IzakayaSearchService izakayaSearchServiceMock;
	
	@Autowired
	private IzakayaSearchController target;

	// ユーザID
	public static final String USER_ID_TEST_1 = "test1";
	// ユーザpassword
	public static final String USER_TEST_1_PASSWORD = "zxcv1234";
	// ログイン状態:true
	public static final boolean LOGIN_STATUS_TRUE = true;
	// ログイン状態:false
	public static final boolean LOGIN_STATUS_FALSE = false;
	// 居酒屋ID
	public static final String IZAKAYA_ID = "23";

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	/**
	 * showSearchのテスト.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowSearch() throws Exception {

		mockMvc.perform(get(Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_SEARCH)).andExpect(status().isOk())
				.andExpect(view().name(Constants.VIEW_IZAKAYA_SEARCH));

	}

	/**
	 * searchByConditionsのテスト.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByConditions() throws Exception {

		final IzakayaSearchForm izakayaSearchForm = new IzakayaSearchForm();
		// バリデーションチェックがかかってる場合
		izakayaSearchForm.setReservationNums("さいあ");
		izakayaSearchForm.setArea("さいあ");
		izakayaSearchForm.setMaxAmount("さいあ");
		izakayaSearchForm.setMinAmount("さいあ");
		izakayaSearchForm.setGenre("さいあ");
		when(izakayaSearchServiceMock.getIzakayaListBySearchForm(izakayaSearchForm)).thenReturn(new ArrayList<IzakayaInfo>());
		mockMvc.perform(post(Constants.URL_SEARCH + Constants.URL_IZAKAYA_SEARCH)
				.flashAttr(Constants.IZAKAYA_SEARCH_FORM, izakayaSearchForm)).andExpect(status().isOk())
				.andExpect(view().name(Constants.VIEW_IZAKAYA_SEARCH));
		// バリデーションチェックがかかっていない場合
		izakayaSearchForm.setReservationNums("10");
		izakayaSearchForm.setArea("1");
		izakayaSearchForm.setMaxAmount("1000");
		izakayaSearchForm.setMinAmount("1000");
		izakayaSearchForm.setGenre("1");
		when(izakayaSearchServiceMock.getIzakayaListBySearchForm(izakayaSearchForm)).thenReturn(new ArrayList<IzakayaInfo>());
		mockMvc.perform(post(Constants.URL_SEARCH + Constants.URL_IZAKAYA_SEARCH)
				.flashAttr(Constants.IZAKAYA_SEARCH_FORM, izakayaSearchForm)).andExpect(status().isOk())
				.andExpect(view().name(Constants.VIEW_IZAKAYA_SEARCH));

	}

	/**
	 * searchAllのテスト.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchAll() throws Exception {

		when(izakayaSearchServiceMock.getIzakayaList()).thenReturn(new ArrayList<IzakayaInfo>());
		mockMvc.perform(get(Constants.URL_SEARCH + Constants.URL_IZAKAYA_SEARCH_ALL)).andExpect(status().isOk())
				.andExpect(view().name(Constants.VIEW_IZAKAYA_SEARCH_ALL));

	}

	/**
	 * showIzakayaDetailのテスト.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowIzakayaDetail() throws Exception {

		// paramIdがnullではない、かつ、ログイン未済の場合
		when(izakayaSearchServiceMock.getIzakayaById(IZAKAYA_ID)).thenReturn(new IzakayaInfo());
		mockMvc.perform(get(Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_DETAIL).param(Constants.ATTR_IZAKAYA_ID,
				IZAKAYA_ID)).andExpect(status().isOk()).andExpect(view().name(Constants.VIEW_IZAKAYA_DETAIL));

		// paramIdがnullである、かつ、ログイン未済の場合//TODO
		when(izakayaSearchServiceMock.getIzakayaById(IZAKAYA_ID)).thenReturn(new IzakayaInfo());
		mockMvc.perform(get(Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_DETAIL)
				.flashAttr(Constants.ATTR_IZAKAYA_ID, IZAKAYA_ID)).andExpect(status().isOk())
				.andExpect(view().name(Constants.VIEW_IZAKAYA_DETAIL));

		// paramIdがnullではない、かつ、ログイン済の場合
		when(izakayaSearchServiceMock.getIzakayaById(IZAKAYA_ID)).thenReturn(new IzakayaInfo());
		mockMvc.perform(get(Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_DETAIL)
				.param(Constants.ATTR_IZAKAYA_ID, IZAKAYA_ID).sessionAttr(Constants.IZAKAYA_SESSION,
						setLoginInfo(USER_ID_TEST_1, USER_TEST_1_PASSWORD, LOGIN_STATUS_FALSE)))
				.andExpect(status().isOk()).andExpect(view().name(Constants.VIEW_IZAKAYA_DETAIL));

		// paramIdがnullであるかつ、ログイン済の場合場合
		when(izakayaSearchServiceMock.getIzakayaById(IZAKAYA_ID)).thenReturn(new IzakayaInfo());
		mockMvc.perform(get(Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_DETAIL)
				.flashAttr(Constants.ATTR_IZAKAYA_ID, IZAKAYA_ID).sessionAttr(Constants.IZAKAYA_SESSION,
						setLoginInfo(USER_ID_TEST_1, USER_TEST_1_PASSWORD, LOGIN_STATUS_FALSE)))
				.andExpect(status().isOk()).andExpect(view().name(Constants.VIEW_IZAKAYA_DETAIL));
		
		// paramIdがnullであるかつ、ログイン済の場合
		when(izakayaSearchServiceMock.getIzakayaById(IZAKAYA_ID)).thenReturn(new IzakayaInfo());
		mockMvc.perform(get(Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_DETAIL)
				.flashAttr(Constants.ATTR_IZAKAYA_ID, IZAKAYA_ID).sessionAttr(Constants.IZAKAYA_SESSION,
						setLoginInfo(USER_ID_TEST_1, USER_TEST_1_PASSWORD, LOGIN_STATUS_FALSE)))
				.andExpect(status().isOk()).andExpect(view().name(Constants.VIEW_IZAKAYA_DETAIL));

	}

	/**
	 * registerIzakayaCommentのテスト.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegisterIzakayaComment() throws Exception {

		
		final IzakayaCommentForm izakayaCommentForm = new IzakayaCommentForm();
		// ログイン未済
		izakayaCommentForm.setId("30000");
		izakayaCommentForm.setIzakayaId("20000");
		izakayaCommentForm.setContent("zzzzz");
		izakayaCommentForm.setAssessment("4");
		izakayaCommentForm.setRegisterTime(CommonUtils.getNowDateTime(null));
		when(izakayaSearchServiceMock.registerIzakayaCommentInfo(izakayaCommentForm)).thenReturn(true);
		mockMvc.perform(post(Constants.URL_SEARCH + Constants.URL_IZAKAYA_COMMENT)
				.flashAttr(Constants.IZAKAYA_COMMENT_FORM, izakayaCommentForm)).andExpect(status().isFound())
				.andExpect(redirectedUrl(Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_DETAIL));
		// ログイン済み
		izakayaCommentForm.setId("30000");
		izakayaCommentForm.setIzakayaId("20000");
		izakayaCommentForm.setContent("zzzzz");
		izakayaCommentForm.setAssessment("4");
		izakayaCommentForm.setRegisterTime(CommonUtils.getNowDateTime(null));
		when(izakayaSearchServiceMock.registerIzakayaCommentInfo(izakayaCommentForm)).thenReturn(true);
		mockMvc.perform(post(Constants.URL_SEARCH + Constants.URL_IZAKAYA_COMMENT)
				.flashAttr(Constants.IZAKAYA_COMMENT_FORM, izakayaCommentForm).sessionAttr(Constants.IZAKAYA_SESSION,
						setLoginInfo(USER_ID_TEST_1, USER_TEST_1_PASSWORD, LOGIN_STATUS_FALSE)))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl(Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_DETAIL));
		
		// ログイン済み
		izakayaCommentForm.setId("30000");
		izakayaCommentForm.setIzakayaId("20000");
		izakayaCommentForm.setContent("zzzzz");
		izakayaCommentForm.setAssessment("4");
		izakayaCommentForm.setRegisterTime(CommonUtils.getNowDateTime(null));
		when(izakayaSearchServiceMock.registerIzakayaCommentInfo(izakayaCommentForm)).thenReturn(true);
		mockMvc.perform(post(Constants.URL_SEARCH + Constants.URL_IZAKAYA_COMMENT)
				.flashAttr(Constants.IZAKAYA_COMMENT_FORM, izakayaCommentForm).sessionAttr(Constants.IZAKAYA_SESSION,
						setLoginInfo(USER_ID_TEST_1, USER_TEST_1_PASSWORD, LOGIN_STATUS_TRUE)))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl(Constants.URL_SEARCH + Constants.URL_TO_IZAKAYA_DETAIL));
	}

	/**
	 * ログイン情報を取得する.
	 * 
	 * @param id          ユーザID
	 * @param password    password
	 * @param loginStatus ログインステータス
	 * @return ログイン情報
	 */
	private IzakayaSession setLoginInfo(String id, String password, boolean loginStatus) {
		final IzakayaSession izakayaSession = new IzakayaSession();
		izakayaSession.setUserId(id);
		izakayaSession.setPassword("zxcv1234");
		izakayaSession.setLogin(loginStatus);
		return izakayaSession;
	}

}
