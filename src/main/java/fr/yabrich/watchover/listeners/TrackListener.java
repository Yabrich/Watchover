package fr.yabrich.watchover.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Set;
import java.util.UUID;

import static fr.yabrich.watchover.utils.TrackerManager.trackedPlayers;

public class TrackListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID quitting = event.getPlayer().getUniqueId();
        trackedPlayers.remove(quitting);

        for (Set<UUID> targets : trackedPlayers.values()) {
            targets.remove(quitting);
        }
    }
}
