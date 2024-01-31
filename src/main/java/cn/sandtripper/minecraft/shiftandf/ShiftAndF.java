package cn.sandtripper.minecraft.shiftandf;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.List;

public final class ShiftAndF extends JavaPlugin {
    boolean isRunning;
    boolean hasPapi;
    List<String> commands;
    private static final String[] enableTexts = {
            "\033[36m   _____ _     _  __ _                      _ ______ \033[0m",
            "\033[36m  / ____| |   (_)/ _| |     /\\             | |  ____|\033[0m",
            "\033[36m | (___ | |__  _| |_| |_   /  \\   _ __   __| | |__   \033[0m",
            "\033[36m  \\___ \\| '_ \\| |  _| __| / /\\ \\ | '_ \\ / _` |  __|  \033[0m",
            "\033[36m  ____) | | | | | | | |_ / ____ \\| | | | (_| | |     \033[0m",
            "\033[36m |_____/|_| |_|_|_|  \\__/_/    \\_\\_| |_|\\__,_|_|     \033[0m",
            "\033[36m                                                     \033[0m",
            "\033[36mshift+f监听 --by 沙酱紫漏\033[0m",
    };

    @Override
    public void onEnable() {
        for (int i = 0; i < enableTexts.length; i++) {
            getLogger().info(enableTexts[i]);
        }

        this.saveDefaultConfig();
        isRunning = true;

        initData();

        getCommand("ShiftAndF").setExecutor(new CommandHandler(this));
        getCommand("ShiftAndF").setTabCompleter(new MyTabCompleter(this));

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);


        int pluginId = 20863;
        Metrics metrics = new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {
        isRunning = false;
    }

    void initData() {
        commands = new LinkedList<>();
        List<String> tmpLst = getConfig().getStringList("commands");
        for (String command : tmpLst) {
            if (command == null || command.length() < 2 || command.charAt(0) != 'c' && command.charAt(0) != 'p' || command.charAt(1) != ':') {
                getLogger().warning("§e已忽略错误的格式：" + command);
            } else {
                commands.add(command);
            }
        }
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            hasPapi = true;
            getLogger().info("§9检测到 PlaceholderAPI，启用插件 PlaceholderAPI 功能");
        } else {
            hasPapi = false;
            getLogger().info("§9未检测到 PlaceholderAPI，禁用插件 PlaceholderAPI 功能");
        }
    }

    public void hasTrigger(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking() && isRunning) {
            for (String command : commands) {
                if (command.charAt(0) == 'c') {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), formatCommand(command.substring(2), player));
                } else {
                    player.performCommand(formatCommand(command.substring(2), player));
                }

            }
            event.setCancelled(true);
        }
    }

    public void listenOn(CommandSender commandSender) {
        if (!isRunning) {
            isRunning = true;
            commandSender.sendMessage("§7[§bShiftAndF§7] §a监听开启成功");
        } else {
            commandSender.sendMessage("§7[§bShiftAndF§7] §e监听已经处于开启状态");
        }
    }

    public void listenOff(CommandSender commandSender) {
        if (isRunning) {
            isRunning = false;
            commandSender.sendMessage("§7[§bShiftAndF§7] §a监听关闭成功");
        } else {
            commandSender.sendMessage("§7[§bShiftAndF§7] §e监听已经处于关闭状态");
        }
    }

    public void reload(CommandSender commandSender) {
        reloadConfig();
        initData();
        commandSender.sendMessage("§7[§bShiftAndF§7] §a插件重载成功!");
    }

    private String formatCommand(String command, Player player) {
        if (player != null && hasPapi) {
            command = PlaceholderAPI.setPlaceholders(player, command);
        }
        return command;
    }
}
