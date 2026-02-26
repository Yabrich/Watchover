package fr.yabrich.watchover.listeners;

import fr.yabrich.watchover.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ChatListener implements Listener {
    public static boolean chatenable = true;
    public static int chatslowmode = 0;
    private Map<Player,LocalTime> cooldown = new HashMap<>();

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if(!chatenable && !(player.hasPermission("wo.chat.bypass"))) {
            event.setCancelled(true);
            player.sendMessage(Main.getErrPrefix()+"Impossible : Le chat est désactivé.");
        }

        if(chatslowmode != 0 && !(player.hasPermission("wo.chat.bypass"))) {
            LocalTime now = LocalTime.now();

            LocalTime cd_joueur = cooldown.get(player);
            LocalTime cd_requis;

            if(cd_joueur == null) {
                cd_requis = now.minusSeconds(1);
            }
            else {
                cd_requis = cd_joueur.plusSeconds(chatslowmode);
            }

            if(!now.isAfter(cd_requis)) {
                event.setCancelled(true);

                Duration cd_restant = Duration.between(now,cd_requis);
                long secondes = cd_restant.toSeconds();

                player.sendMessage(Main.getErrPrefix()+"Le chat est en slowmode, merci d'attendre §4"+secondes+" §csecondes avant d'envoyer votre message.");
            }
            else {
                cooldown.put(player, now);
            }
        }
    }
}
