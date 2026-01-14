package com.account.demo.t.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.account.demo.t.entity.UserTable;
import com.account.demo.t.mapper.UserMapper;
import com.account.demo.t.model.User;

/**
 * <h1>[入力情報確定のコントローラー]</h1><br>
 *<br>
 * DBへの登録処理を持つ。
 */
@Controller
public class FinishController {
	/** ユーザーテーブルのMapper */
	@Autowired
	private UserMapper userMapper;

	/**
	 * <h2>[コミット処理]</h2><br>
	 *<br>
	 * 完了ページに出す情報は適当にSELECTしているので、入力情報と合いません。
	 * @param user  引き継いだユーザー情報
	 * @param model モデル
	 * @return 遷移先
	 * @throws ParseException 誕生日の変換エラー時
	 */
	@PostMapping(value = "/commit", params = "commit")
	public String commit(@ModelAttribute User user, Model model) throws ParseException {
		// --------------------------------------------------------------------
		// パラメータに引き渡すオブジェクト組み立て
		// --------------------------------------------------------------------
		UserTable ut = new UserTable();
		ut.setUNo(user.getUNo());
		ut.setUName(user.getUName());
		ut.setAge(user.getAge());
		ut.setBDate(new java.sql.Date(new SimpleDateFormat("yyyy/MM/dd").parse(user.getBDate()).getTime()));

		// --------------------------------------------------------------------
		// INSERT
		// --------------------------------------------------------------------
		userMapper.insert(ut);

		// --------------------------------------------------------------------
		// INSERTしたレコードをSELECT
		// 個人番号をSELECTで付与している都合上、1を使う。
		// --------------------------------------------------------------------
		List<UserTable> record = userMapper.selectByUno(1);

		// --------------------------------------------------------------------
		// 検索結果を次ページに引き渡し
		// --------------------------------------------------------------------
		model.addAttribute("userTable", record.get(0));

		return "finish";
	}

	/**
	 * <h2>[戻る処理]</h2><br>
	 *<br>
	 * 戻るボタンを押したときに入力画面に戻る。<br>
	 * PostMappingの引数にparamsを指定すると、押したsubmitのname属性が判定に追加できる。<br>
	 * その場合、マップするURLにはvalue=を指定すること。<br>
	 * formにコントローラーのクラスは1つしか紐づけられないので、仕方なしにリダイレクトという手段を取る。<br>
	 * ただし、RedirectAttributesに情報を持たせるこのやり方で実装すると、引き継ぐ情報が丸見えになるので、セキュリティー的に見えると困る情報があるときは、セッションに持たせて引き継ぐなど別の方法で実装しましょう。<br>
	 * サンプルでは、情報の少なかったこのやり方で実装する。
	 * @param user               引き継いだユーザー情報
	 * @param redirectAttributes リダイレクトに情報を引き継ぐためのオブジェクト
	 * @return リダイレクト先
	 */
	@PostMapping(value = "/commit", params = "back")
	public String back(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		// リダイレクト先に引き継ぐ値を詰める
		redirectAttributes.addFlashAttribute("user", user);

		return "redirect:/form";
	}
}
