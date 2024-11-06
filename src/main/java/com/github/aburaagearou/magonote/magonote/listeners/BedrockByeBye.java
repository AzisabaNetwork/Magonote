package com.github.aburaagearou.magonote.magonote.listeners;

import com.github.aburaagearou.magonote.magonote.config.MagonoteConfig;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * 統合版の自動キック
 * @author AburaAgeTarou
 */
public class BedrockByeBye implements Listener {

	/**
	 * プレイヤー接続時
	 * @param event プレイヤー接続イベント
	 */
	@EventHandler
	public void onPlayerJoin(PlayerLoginEvent event) {
		// 設定で無効化されている場合は処理しない
		if(!MagonoteConfig.get(MagonoteConfig.Keys.BEDROCK_BYE_BYE, true)) return;

		// 名前が"."から始まる場合はキック
		Player player = event.getPlayer();
		if(player.getName().startsWith(".")) {
			event.disallow(PlayerLoginEvent.Result.KICK_BANNED, LegacyComponentSerializer.legacyAmpersand().deserialize("&cこのサーバーは統合版では参加できません。"));
		}
	}
}
