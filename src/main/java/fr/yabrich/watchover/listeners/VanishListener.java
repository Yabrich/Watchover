package fr.yabrich.watchover.listeners;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.utils.PlayerVanish;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static fr.yabrich.watchover.utils.PlayerVanish.hideStaffVanishedPlayers;
import static fr.yabrich.watchover.utils.PlayerVanish.hideVanishedPlayers;

public class VanishListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(!player.hasPermission("wo.staffvanish.seeothers")){
            hideStaffVanishedPlayers(player);
        }
        if(!player.hasPermission("wo.vanish.seeothers")){
            hideVanishedPlayers(player);
        }

        if(PlayerVanish.isPlayerVanished(player)){
            for(Player p : Bukkit.getOnlinePlayers()){
                if(!p.hasPermission("wo.vanish.seeothers")){
                    hideVanishedPlayers(p);
                }
            }
        }

        if(PlayerVanish.isPlayerStaffVanished(player)){
            for(Player p : Bukkit.getOnlinePlayers()){
                if(!p.hasPermission("wo.staffvanish.seeothers")){
                    hideStaffVanishedPlayers(p);
                }
            }
        }
    }
}
