package fr.yabrich.watchover.utils;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerVanish {

    private static final ArrayList<Player> vanished = new ArrayList<Player>();

    public static void vanishPlayer(Player player) {
        vanished.add(player);

        for(Player p : Bukkit.getOnlinePlayers()){
            p.hidePlayer(Main.getInstance(), player);
        }
    }

    public static void unvanishPlayer(Player player){
        vanished.remove(player);

        for(Player p : Bukkit.getOnlinePlayers()){
            p.showPlayer(Main.getInstance(), player);
        }
    }

    public static boolean isPlayerVanished(Player player){
        return vanished.contains(player);
    }

    public static void hideVanishedPlayers(Player player){
        for(Player pvanished : vanished){
            player.hidePlayer(Main.getInstance(), pvanished);
        }
    }
}
