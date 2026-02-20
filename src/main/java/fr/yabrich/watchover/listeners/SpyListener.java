package fr.yabrich.watchover.listeners;

import fr.yabrich.watchover.commands.CommandSpy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpyListener implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e){
        Player player = e.getPlayer();
        String cmd = e.getMessage();

        for(Player p : CommandSpy.spyActived){
            if(!p.equals(player)){
                p.sendMessage("§8[§7SPY§8] §7"+player.getName()+"§8 : "+cmd);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        CommandSpy.spyActived.remove(player);
    }
}
