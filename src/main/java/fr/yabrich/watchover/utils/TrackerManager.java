package fr.yabrich.watchover.utils;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class TrackerManager {
    public static Map<UUID, Set<UUID>> trackedPlayers = new HashMap<>();
    static int task_id = 0;

    public static void addTarget(Player tracker, Player target) {
        if(trackedPlayers.isEmpty()){
            TrackerManager.TrackerInit();
        }

        trackedPlayers
                .computeIfAbsent(tracker.getUniqueId(), k -> new HashSet<>())
                .add(target.getUniqueId());
    }

    public static void removeTarget(Player tracker, Player target) {
        Set<UUID> targets = trackedPlayers.get(tracker.getUniqueId());
        if (targets == null) return;

        targets.remove(target.getUniqueId());

        if (targets.isEmpty()) {
            trackedPlayers.remove(tracker.getUniqueId());

            if(trackedPlayers.isEmpty()){
                cancelTrackerTask();
            }
        }
    }

    public static void clearTargets(Player tracker) {
        trackedPlayers.remove(tracker.getUniqueId());

        if(trackedPlayers.isEmpty()){
            cancelTrackerTask();
        }
    }

    public static boolean isTracking(Player tracker){
        return trackedPlayers.containsKey(tracker.getUniqueId());
    }

    public static boolean isTracking(Player tracker, Player target) {

        Set<UUID> targets = trackedPlayers.get(tracker.getUniqueId());
        if (targets == null) return false;

        return targets.contains(target.getUniqueId());
    }

    public static void cancelTrackerTask(){
        if(task_id == 0){
            return;
        }
        Bukkit.getScheduler().cancelTask(task_id);
    }

    public static void TrackerInit(){
        task_id = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {

            for (UUID trackerId : trackedPlayers.keySet()) {

                Player tracker = Bukkit.getPlayer(trackerId);
                if (tracker == null || !tracker.isOnline()) continue;

                Set<UUID> targets = trackedPlayers.get(trackerId);
                if (targets == null || targets.isEmpty()) continue;

                StringBuilder actionBar = new StringBuilder();

                for (UUID targetId : targets) {

                    Player target = Bukkit.getPlayer(targetId);
                    if (target == null || !target.isOnline()) continue;
                    if (!tracker.getWorld().equals(target.getWorld())) continue;

                    int distance = (int) Math.round(
                            tracker.getLocation().distance(target.getLocation())
                    );

                    String arrow = getDirectionArrow8(tracker, target);

                    actionBar.append("§b")
                            .append(target.getName())
                            .append(" §6(§f")
                            .append(arrow)
                            .append("§6)§3 ")
                            .append(distance)
                            .append(" §7| ");
                }

                if (!actionBar.isEmpty()) {
                    actionBar.setLength(actionBar.length() - 3); // retire dernier " | "
                    tracker.spigot().sendMessage(
                            net.md_5.bungee.api.ChatMessageType.ACTION_BAR,
                            new net.md_5.bungee.api.chat.TextComponent(actionBar.toString())
                    );
                }
            }

        }, 0L, 10L).getTaskId();
    }

    public static String getDirectionArrow8(Player player, Player target) {

        double angle = getAngle(player, target);

        // 8 directions = 360 / 8 = 45°
        if (angle >= -22.5 && angle < 22.5) return "↑";
        if (angle >= 22.5 && angle < 67.5) return "↗";
        if (angle >= 67.5 && angle < 112.5) return "→";
        if (angle >= 112.5 && angle < 157.5) return "↘";
        if (angle >= -67.5 && angle < -22.5) return "↖";
        if (angle >= -112.5 && angle < -67.5) return "←";
        if (angle >= -157.5 && angle < -112.5) return "↙";

        return "↓";
    }

    private static double getAngle(Player player, Player target) {
        Location from = player.getLocation();
        Location to = target.getLocation();

        // Différence horizontale uniquement
        double dx = to.getX() - from.getX();
        double dz = to.getZ() - from.getZ();

        // Angle vers la cible (en degrés)
        double targetAngle = Math.toDegrees(Math.atan2(-dx, dz));

        // Yaw du joueur
        double playerYaw = from.getYaw();

        // Angle relatif
        double angle = targetAngle - playerYaw;

        // Normalisation entre -180 et 180
        angle = (angle + 540) % 360 - 180;
        return angle;
    }
}
