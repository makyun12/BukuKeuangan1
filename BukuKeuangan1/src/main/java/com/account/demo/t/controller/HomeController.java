package com.account.demo.t.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.account.demo.t.model.User;

/**
 * <h1>[HOMEのコントローラー]</h1><br>
 *<br>
 * 業務処理を持つ。
 */
@Controller
public class HomeController {
	/**
	 * <h2>[Get処理]</h2><br>
	 *<br>
	 * 何の変哲もない、Getリクエストを受け付けて、入力画面を返すだけの処理。<br>
	 * Spring Bootでは、URLとリクエストメソッドはアノテーションを使ってマッピングする。<br>
	 * この処理は、/formというURLに、Getリクエストを受信したときに起動する。<br>
	 * 画面に保持するModelを、特定のオブジェクトにロックオンして紐づける場合は、ModelAttributeとうアノテーションを付与しておけば、Thymeleafからは簡単に参照できる。
	 * @param user ユーザー情報
	 * @return 遷移先
	 */
	@GetMapping("/form")
	private String readForm(@ModelAttribute User user) {
		return "form";
	}

	/**
	 * <h2>[Post処理]</h2><br>
	 *<br>
	 * ModelAttributeで紐づけるオブジェクトに加えて、Modelそのものをもう一つの引数として受け取ることもできる。<br>
	 * サンプルでは、入力チェックとエラー画面への遷移をわかりやすくするように、業務処理内に実装する。<br>
	 * エラーメッセージは、共通オブジェクトに持たせるものと、画面スコープのModelに別出しして持たせるものと、2種類のやり方で返してみる。
	 * @param user  ユーザー情報
	 * @param model モデル
	 * @return 遷移先
	 */
	@PostMapping("/form")
	private String confirm(@ModelAttribute User user, Model model) {
		// エラーの場合は遷移先を分けてみる
		if (user.getAge() < 0) {
			user.setErrMsg("年齢の値が不正です。");

			// Modelにもエラーメッセージを追加してみる
			model.addAttribute("message", "エラーです。");

			// 遷移先はtemplatesフォルダーからのパスをファイル名で指定する
			return "folder/irregular";
		}

		return "confirm";
	}
}
