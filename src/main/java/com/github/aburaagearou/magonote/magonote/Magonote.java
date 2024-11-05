package com.github.aburaagearou.magonote.magonote;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.MessageKeys;
import co.aikar.commands.MessageType;
import co.aikar.commands.PaperCommandManager;
import com.github.aburaagearou.magonote.magonote.commands.MagonoteCommand;
import com.github.aburaagearou.magonote.magonote.hands.DestroyerBedrock;
import com.github.aburaagearou.magonote.magonote.hands.SwapGamemode;
import com.github.aburaagearou.magonote.magonote.listeners.PlayerFilter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Magonote extends JavaPlugin {

    // インスタンス
    private static Magonote instance;

    // コマンドマネージャ
    private PaperCommandManager manager;

    // コマンドリスト
    private static final List<BaseCommand> commands = new ArrayList<>();

    // 機能有効フラグ
    public static Map<Player, Boolean> enabled = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;

        // コマンド登録
        manager = new PaperCommandManager(this);

        // brigadierを有効化しろと言われたので有効化
        manager.enableUnstableAPI("brigadier");

        // helpを有効化
        manager.enableUnstableAPI("help");

        // コマンド登録
        manager.registerCommand(new MagonoteCommand().setExceptionHandler((command, registeredCommand, sender, args, t) -> {
            sender.sendMessage(MessageType.ERROR, MessageKeys.ERROR_GENERIC_LOGGED);
            return true;
        }), true);

        // リスナー登録
        Bukkit.getPluginManager().registerEvents(new DestroyerBedrock(), this);
        Bukkit.getPluginManager().registerEvents(new SwapGamemode(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerFilter(), this);
    }

    @Override
    public void onDisable() {

        // コマンド登録解除
        commands.forEach(manager::unregisterCommand);
        manager.unregisterCommands();
    }

    /**
     * プラグインインスタンスの取得
     * @return Magonote instance
     */
    public static Magonote getInstance() {
        return instance;
    }

    /**
     * コマンド追加
     * @param command BaseCommand
     */
    public void addCommand(BaseCommand command) {
        commands.add(command);
    }

    /**
     * 機能有効フラグの設定
     */
    public static void setEnabled(Player player, boolean flag) {
        enabled.put(player, flag);
    }

    /**
     * 機能有効フラグの取得
     */
    public static boolean isEnabled(Player player) {
        return enabled.getOrDefault(player, false);
    }
}
