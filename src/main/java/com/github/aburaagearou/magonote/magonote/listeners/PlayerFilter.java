package com.github.aburaagearou.magonote.magonote.listeners;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerFilter implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(player.getName().startsWith(".")) {
			player.kick(LegacyComponentSerializer.legacyAmpersand().deserialize("&cこのサーバーは統合版では参加できません。"));
		}
	}
}
