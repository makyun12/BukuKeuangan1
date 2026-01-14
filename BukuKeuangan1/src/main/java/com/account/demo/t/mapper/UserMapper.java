package com.account.demo.t.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.account.demo.t.entity.UserTable;

/**
 * <h1>[ユーザーテーブルのMapper]</h1><br>
 *<br>
 * ユーザーテーブルのSQLをマップする。
 */
@Mapper
public interface UserMapper {
	/** 番号でのSELECT */
	public List<UserTable> selectByUno(long uNo);

	/** INSERT */
	public int insert(UserTable ut);
}