package fr.yabrich.watchover.utils;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerVanish {

    private static final ArrayList<Player> vanished = new ArrayList<Player>();

    public static void vanishPlayer(Player player) {
        vanished.add(player);

        Location loc = player.getLocation().add(0, 1, 0);

        player.getWorld().spawnParticle(
                Particle.SMALL_FLAME,
                loc,
                25,
                0.4, 0.8, 0.4,
                0.02
        );

        for(Player p : Bukkit.getOnlinePlayers()){
            p.hidePlayer(Main.getInstance(), player);
        }
    }

    public static void unvanishPlayer(Player player){
        vanished.remove(player);

        Location loc = player.getLocation().add(0, 1, 0);

        player.getWorld().spawnParticle(
                Particle.END_ROD,
                loc,
                25,
                0.4, 0.8, 0.4,
                0.02
        );

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
