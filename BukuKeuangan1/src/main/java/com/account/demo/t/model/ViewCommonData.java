package com.account.demo.t.model;

import lombok.Data;

/**
 * <h1>[共通データ]</h1><br>
 *<br>
 * 各画面で共通で使用するデータを保持する。<br>
 * 画面で使用するモデルは必ずこのクラスを継承する。<br>
 * コントローラーで使用するModelは、ModelAttributeを使用すると、そのクラスに保持しているデータ以外を受け渡せなくなる。<br>
 * 例えば、エラーメッセージを出し分けたい場合、変数を使うが、エラーメッセージをユーザー情報などに直接持たせるのはダサいので、こういう方法で対処する。<br>
 * 他にかっこいいやり方があれば、是非コメントください。
 */
@Data
public class ViewCommonData {
	/** エラーメッセージ */
	protected String errMsg;
}
