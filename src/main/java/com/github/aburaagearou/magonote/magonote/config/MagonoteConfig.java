package com.github.aburaagearou.magonote.magonote.config;

import com.github.aburaagearou.magonote.magonote.Magonote;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * 設定管理クラス
 * @author AburaAgeTarou
 */
public class MagonoteConfig {

	/**
	 * コンフィグの読み込み
	 */
	public static void loadConfig() {

		// 初期設定
		Magonote.getInstance().saveDefaultConfig();

		// 読み込み
		Magonote.getInstance().getConfig();
	}

	/**
	 * コンフィグオブジェクトの取得
	 * @return コンフィグオブジェクト
	 */
	public static ConfigurationSection getConfig() {
		return Magonote.getInstance().getConfig();
	}

	/**
	 * 設定の取得
	 * @param key 設定キー
	 * @param def デフォルト値
	 * @return 設定値
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Keys key, T def) {
		return (T) getConfig().get(key.key, def);
	}

	/**
	 * 設定キーの列挙
	 */
	public enum Keys {
		BEDROCK_BYE_BYE("bedrock-bye-bye")
		;

		// 設定キー
		private final String key;

		/**
		 * コンストラクタ
		 * @param key 設定キー
		 */
		Keys(String key) {
			this.key = key;
		}
	}
}
