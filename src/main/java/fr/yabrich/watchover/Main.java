package fr.yabrich.watchover;

import fr.yabrich.watchover.commands.CommandNv;
import fr.yabrich.watchover.commands.CommandVanish;
import fr.yabrich.watchover.listeners.VanishListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    static Main instance;
    static String prefix;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("ça marche super!");
        setInstance(this);
        setPrefix("§4[§6WatchOver§4] §r");
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new CommandVanish());
        Objects.requireNonNull(getCommand("nv")).setExecutor(new CommandNv());

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
}
