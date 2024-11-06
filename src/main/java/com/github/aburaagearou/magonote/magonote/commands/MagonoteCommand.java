package com.github.aburaagearou.magonote.magonote.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.github.aburaagearou.magonote.magonote.Magonote;
import org.bukkit.entity.Player;

@CommandAlias("magonote|kayui")
@Description("孫の手")
public class MagonoteCommand extends BaseCommand {

	/**
	 * コンストラクタ
	 */
	public MagonoteCommand() {
		// コマンド登録
		Magonote.addCommand(this);
	}

	@Default
	@Subcommand("toggle")
	@Description("孫の手の有効/無効を切り替えます")
	@CommandPermission("magonote.toggle")
	public void onToggle(Player player) {
		Magonote.setEnabled(player, !Magonote.isEnabled(player));
		player.sendMessage("孫の手を" + (Magonote.isEnabled(player) ? "有効" : "無効") + "にしました");
	}
}
