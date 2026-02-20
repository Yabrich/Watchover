package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.utils.PlayerVanish;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandFakeLeave implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(!PlayerVanish.isPlayerVanished(player)){
                PlayerVanish.vanishPlayer(player);
            }
            String fakeQuitMessage = ChatColor.YELLOW + player.getName() + " left the game";
            Bukkit.broadcastMessage(fakeQuitMessage);
            return true;
        }
        return false;
    }
}
