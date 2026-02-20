package fr.yabrich.watchover.listeners;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.utils.FreezeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        if(FreezeManager.isPlayerFreezed(player)){
            e.setCancelled(true);
            player.sendMessage(Main.getErrPrefix()+"Vous êtes actuellement freeze !");
        }
    }
}
