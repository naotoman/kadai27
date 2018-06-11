package algorithm;

import java.io.File;

/**
 * <p>課題27を解く手順を規定します。
 * 具体的なソルバーとなるすべてのクラスは、このインターフェースを実装する必要があります。
 * <p>このインターフェースは、
 * <ul>
 * <li>入力となる問題を読み取る
 * <li>問題を解く
 * <li>解いた結果（解）を表示する
 * </ul>
 * ためのメソッドとして、それぞれ{@link #readInput(File)}, {@link #solve()}, {@link #showResult()}
 * を提供しています。これらは正しい順番で実行される必要があります。
 */
public interface Solver27 {

	/**
	 *<p>問題をファイルから読み込んで、{@link #solve()}で解くための準備をします。
	 *入力の形式は、問題文に示されたものに合っている必要があります。
	 * @param input 問題が書かれたファイル
	 */
	void readInput(File input);

	/**
	 *<p>直前の {@link #readInput(File)}で読み込んだ問題を解きます。
	 *そして、{@link #showResult()}で結果を表示するための準備をします。
	 */
	void solve();

	/**
	 * 直前の{@link #solve()}で解いた結果（解）を標準出力に出力します。
	 */
	void showResult();

}