package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandTPHere implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 0){
                player.sendMessage(Main.getErrPrefix()+"Veuillez préciser une cible..");
                return false;
            }

            String username = args[0];
            Player targetPlayer = Bukkit.getPlayer(username);

            if(targetPlayer == null){
                player.sendMessage(Main.getErrPrefix()+"Ce joueur n'est pas connecté..");
                return false;
            }

            targetPlayer.teleport(player.getLocation());
            player.sendMessage(Main.getPrefix()+"Vous avez téléporté §b"+targetPlayer.getDisplayName()+" §3à vous.");
            targetPlayer.sendMessage(Main.getPrefix()+"Vous avez été téléporté.");
            return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
