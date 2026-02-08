package fr.yabrich.watchover.listeners;

import fr.yabrich.watchover.utils.PlayerVanish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VanishListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        PlayerVanish.hideVanishedPlayers(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if(PlayerVanish.isPlayerVanished(player)){
            PlayerVanish.unvanishPlayer(player);
        }
    }
}
