package com.github.aburaagearou.magonote.magonote.hands;

import com.github.aburaagearou.magonote.magonote.Magonote;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * 岩盤クリックによるエンティティ消滅
 * @author AburaAgeTarou
 */
public class DestroyerBedrock implements Listener {

	/**
	 * 岩盤右クリックによるエンティティ消滅
	 * @param event PlayerInteractEvent
	 */
	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.getInventory().getItemInMainHand().getType() != Material.BEDROCK) return;
		if(!Magonote.isEnabled(player)) return;

		// 右クリック：範囲消滅
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			player.getWorld().getNearbyLivingEntities(player.getLocation(), 2, 2, 2).stream().filter(entity -> !(entity instanceof HumanEntity)).forEach(Entity::remove);
			event.setCancelled(true);
		}
	}

	/**
	 * 岩盤左クリックによるエンティティ消滅
	 * @param event EntityDamageByEntityEvent
	 */
	@EventHandler(ignoreCancelled = true)
	public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player)) return;
		Player player = (Player) event.getDamager();
		if(player.getInventory().getItemInMainHand().getType() != Material.BEDROCK) return;
		if(!Magonote.isEnabled(player)) return;

		// 左クリック：エンティティ消滅
		event.getEntity().remove();
		event.setCancelled(true);
	}
}
