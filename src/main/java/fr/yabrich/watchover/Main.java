package fr.yabrich.watchover;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("ça marche super!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
