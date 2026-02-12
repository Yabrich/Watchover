package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            World world = player.getWorld();
            Location spawn = world.getSpawnLocation();

            player.teleport(spawn);
            player.sendMessage(Main.getPrefix()+"§3Téléportation en cours..");

            return true;
        }
        return false;
    }
}
