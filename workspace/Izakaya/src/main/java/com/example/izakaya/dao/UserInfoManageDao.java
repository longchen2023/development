package com.example.izakaya.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.izakaya.entity.UserInfo;

/**
 * ユーザ情報管理Dao.
 * @author 双江
 *
 */
@Mapper
public interface  UserInfoManageDao {

	/**
	 * id、passwordより、ユーザ情報を取得する.
	 * @param id
	 * @param password
	 * @return
	 */
	 @Select("select * from user_info where id = #{id} and password = #{password}")
	 UserInfo findByIdAndPassword(@Param("id")String id,@Param("password")String password);
	 
	 /**
	  * idより、ユーザ情報を取得する.
	  * @param id
	  * @return
	  */
	 @Select("select * from user_info where id = #{id}")
	 UserInfo findById(@Param("id")String id);
	 
	 /**
	  * idより、ユーザ情報を更新する.
	  * @param id
	  * @param password
	  * @return
	  */
	 @Update({ "update user_info set "
				+ "password = #{password,jdbcType=VARCHAR} "
				+ "where id = #{id,jdbcType=NUMERIC}"})
	 int updateManageInfo(@Param("id")String id,@Param("password")String password);
}
