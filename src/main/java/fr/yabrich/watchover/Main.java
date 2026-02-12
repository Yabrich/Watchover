package fr.yabrich.watchover;

import fr.yabrich.watchover.commands.*;
import fr.yabrich.watchover.listeners.VanishListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    static Main instance;
    static String prefix;
    static String errPrefix;

    @Override
    public void onEnable() {
        setInstance(this);
        setPrefix("§4[§6WatchOver§4] §3");
        setErrPrefix("§4[§cWO Error§4] §c");
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new CommandVanish());
        Objects.requireNonNull(getCommand("nv")).setExecutor(new CommandNv());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new CommandSpawn());
        Objects.requireNonNull(getCommand("id")).setExecutor(new CommandId());
        Objects.requireNonNull(getCommand("s")).setExecutor(new CommandTPHere());

        getServer().getPluginManager().registerEvents(new VanishListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

    public static void setInstance(Main main) {
        instance = main;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        Main.prefix = prefix;
    }

    public static String getErrPrefix() {
        return errPrefix;
    }

    public static void setErrPrefix(String errPrefix) {
        Main.errPrefix = errPrefix;
    }
}
