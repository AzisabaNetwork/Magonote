package com.github.aburaagearou.magonote.magonote.hands;

import com.destroystokyo.paper.event.player.PlayerStartSpectatingEntityEvent;
import com.github.aburaagearou.magonote.magonote.Magonote;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * ゲームモードを簡単に切り替える
 * @author AburaAgeTarou
 */
public class SwapGamemode implements Listener {

	/**
	 * スニーク中にアイテム入れ替えをするとゲームモードを切り替える
	 * @param event PlayerSwapHandItemsEvent
	 */
	@EventHandler(ignoreCancelled = true)
	public void onSwapHandItem(PlayerSwapHandItemsEvent event) {
		Player player = event.getPlayer();
		if(!Magonote.isEnabled(player)) return;

		// ゲームモード切り替え
		if(player.isSneaking()) {
			player.setGameMode(player.getGameMode() == GameMode.CREATIVE ? GameMode.SURVIVAL : GameMode.CREATIVE);
			event.setCancelled(true);
		}
	}

	/**
	 * スニーク中に左クリックするとゲームモードを切り替える
	 * @param event PlayerInteractEvent
	 */
	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(!Magonote.isEnabled(player)) return;

		// ゲームモード切り替え
		if(player.isSneaking() && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
			player.setGameMode(GameMode.SPECTATOR);
			event.setCancelled(true);
		}
	}

	/**
	 * 他人にワープする時にゲームモードを切り替える
	 * @param event PlayerStartSpectatingEntityEvent
	 */
	@EventHandler(ignoreCancelled = true)
	public void onTeleport(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		if(!Magonote.isEnabled(player)) return;

		// ゲームモード切り替え
		if(event.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
			player.setGameMode(GameMode.CREATIVE);
			event.setCancelled(true);
		}
	}
}
