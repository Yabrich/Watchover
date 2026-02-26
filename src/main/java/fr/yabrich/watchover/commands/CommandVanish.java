package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.utils.PlayerVanish;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class CommandVanish implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player player){
            if(PlayerVanish.isPlayerStaffVanished(player)){
                PlayerVanish.unvanishPlayer(player);
                player.sendMessage(Main.getPrefix()+"§3StaffVanish §4désactivé§3 !");
            }

            if(!PlayerVanish.isPlayerVanished(player)){
                PlayerVanish.vanishPlayer(player,false);
                player.sendMessage(Main.getPrefix()+"§3Vanish §aactivé§3 !");
            }
            else{
                PlayerVanish.unvanishPlayer(player);
                player.sendMessage(Main.getPrefix()+"§3Vanish §4désactivé§3 !");
            }
        return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
