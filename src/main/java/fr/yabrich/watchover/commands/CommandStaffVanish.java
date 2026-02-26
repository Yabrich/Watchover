package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.utils.PlayerVanish;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandStaffVanish implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(!PlayerVanish.isPlayerStaffVanished(player)){
                if(PlayerVanish.isPlayerVanished(player)){
                    PlayerVanish.unvanishPlayer(player);
                    player.sendMessage(Main.getPrefix()+"§3Vanish §4désactivé§3 !");
                }
                PlayerVanish.vanishPlayer(player,true);
                player.sendMessage(Main.getPrefix()+"§3StaffVanish §aactivé§3 !");
            }
            else {
                PlayerVanish.unvanishPlayer(player);
                player.sendMessage(Main.getPrefix()+"§3StaffVanish §4désactivé§3 !");
            }
            return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
