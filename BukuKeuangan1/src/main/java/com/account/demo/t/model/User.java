package com.account.demo.t.model;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <h1>[ユーザー情報]</h1><br>
 *<br>
 * USERの情報を持つ。<br>
 * LombokのアノテーションでGetとSetは自動生成する。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends ViewCommonData {
	/** 個人番号 */
	private long uNo;

	/** 氏名 */
	private String uName;

	/** 年齢 */
	private long age;

	/** 誕生日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private String bDate;

	/** 住所 */
	private Address address = new Address();
}
