package fr.yabrich.watchover;

import fr.yabrich.watchover.commands.*;
import fr.yabrich.watchover.listeners.*;
import fr.yabrich.watchover.tabcompleters.*;
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
        Objects.requireNonNull(getCommand("staffvanish")).setExecutor(new CommandStaffVanish());
        Objects.requireNonNull(getCommand("nv")).setExecutor(new CommandNv());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new CommandSpawn());
        Objects.requireNonNull(getCommand("id")).setExecutor(new CommandId());
        Objects.requireNonNull(getCommand("s")).setExecutor(new CommandTPHere());
        Objects.requireNonNull(getCommand("wo")).setExecutor(new CommandWatchOver());
        Objects.requireNonNull(getCommand("alert")).setExecutor(new CommandAlert());
        Objects.requireNonNull(getCommand("freeze")).setExecutor(new CommandFreeze());
        Objects.requireNonNull(getCommand("spycmd")).setExecutor(new CommandSpy());
        Objects.requireNonNull(getCommand("track")).setExecutor(new CommandTrack());
        Objects.requireNonNull(getCommand("playerxyz")).setExecutor(new CommandXYZ());
        Objects.requireNonNull(getCommand("chatclear")).setExecutor(new CommandChatClear());
        Objects.requireNonNull(getCommand("chat")).setExecutor(new CommandChat());
        Objects.requireNonNull(getCommand("helpme")).setExecutor(new CommandHelpMe());
        Objects.requireNonNull(getCommand("ans")).setExecutor(new CommandAns());
        Objects.requireNonNull(getCommand("report")).setExecutor(new CommandReport());
        Objects.requireNonNull(getCommand("staffchat")).setExecutor(new CommandStaffChat());



        Objects.requireNonNull(getCommand("track")).setTabCompleter(new CompleterTrack());
        Objects.requireNonNull(getCommand("wo")).setTabCompleter(new CompleterWO());
        Objects.requireNonNull(getCommand("chat")).setTabCompleter(new CompleterChat());
        Objects.requireNonNull(getCommand("helpme")).setTabCompleter(new CompleterHelpMe());
        Objects.requireNonNull(getCommand("ans")).setTabCompleter(new CompleterHelpMe());
        Objects.requireNonNull(getCommand("report")).setTabCompleter(new CompleterHelpMe());
        Objects.requireNonNull(getCommand("staffchat")).setTabCompleter(new CompleterStaffChat());

        getServer().getPluginManager().registerEvents(new VanishListener(), this);
        getServer().getPluginManager().registerEvents(new WatchOverListener(), this);
        getServer().getPluginManager().registerEvents(new WatchOverExceptionListener(), this);
        getServer().getPluginManager().registerEvents(new FreezeListener(), this);
        getServer().getPluginManager().registerEvents(new SpyListener(), this);
        getServer().getPluginManager().registerEvents(new TrackListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);

        Bukkit.getConsoleSender().sendMessage("""
                
                 _    _       _       _     _____               \s
                | |  | |     | |     | |   |  _  |              \s
                | |  | | __ _| |_ ___| |__ | | | |_   _____ _ __\s
                | |/\\| |/ _` | __/ __| '_ \\| | | \\ \\ / / _ \\ '__|
                \\  /\\  / (_| | || (__| | | \\ \\_/ /\\ V /  __/ |  \s
                 \\/  \\/ \\__,_|\\__\\___|_| |_|\\___/  \\_/ \\___|_|  \s
                                                                \s
                                                                \s
                              Developped by Yabrich""");

    }

    @Override
    public void onDisable() {
        // azd
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
