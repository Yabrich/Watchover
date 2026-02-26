package fr.yabrich.watchover.utils;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class PlayerVanish {

    private static final ArrayList<UUID> vanished = new ArrayList<>();
    private static final ArrayList<UUID> staffvanished = new ArrayList<>();

    public static void vanishPlayer(Player player, boolean sv) {
        if(sv){
            staffvanished.add(player.getUniqueId());
        }
        else {
            vanished.add(player.getUniqueId());
        }

        Location loc = player.getLocation().add(0, 1, 0);

        player.getWorld().spawnParticle(
                Particle.SMALL_FLAME,
                loc,
                25,
                0.4, 0.8, 0.4,
                0.02
        );

        for(Player p : Bukkit.getOnlinePlayers()){
            if(sv){
                if(!p.hasPermission("wo.staffvanish.seeothers")){
                    hideStaffVanishedPlayers(p);
                }
            }
            else{
                if(!p.hasPermission("wo.vanish.seeothers")){
                    hideVanishedPlayers(p);
                }
            }
        }
    }

    public static void unvanishPlayer(Player player){
        staffvanished.remove(player.getUniqueId());
        vanished.remove(player.getUniqueId());

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
        return vanished.contains(player.getUniqueId());
    }

    public static boolean isPlayerStaffVanished(Player player){
        return staffvanished.contains(player.getUniqueId());
    }

    public static void hideVanishedPlayers(Player player){

        for(UUID idPVanished : vanished){
            player.hidePlayer(Main.getInstance(), Objects.requireNonNull(Bukkit.getPlayer(idPVanished)));
        }
    }

    public static void hideStaffVanishedPlayers(Player player){
        for(UUID idPVanished : staffvanished){
            player.hidePlayer(Main.getInstance(), Objects.requireNonNull(Bukkit.getPlayer(idPVanished)));
        }
    }
}
