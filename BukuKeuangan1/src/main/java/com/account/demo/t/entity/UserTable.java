package com.account.demo.t.entity;

import java.sql.Date;

import lombok.Data;

/**
 * <h1>[ユーザーテーブルのエンティティ]</h1><br>
 *<br>
 * USER_TABLEの情報を持つ。
 */
@Data
public class UserTable {
	/** 番号 */
	private long uNo;

	/** 氏名 */
	private String uName;

	/** 年齢 */
	private long age;

	/** 誕生日 */
	private Date bDate;
}