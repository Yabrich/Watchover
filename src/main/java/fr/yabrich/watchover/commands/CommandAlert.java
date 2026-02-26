package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAlert implements CommandExecutor {
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

            targetPlayer.sendTitle("§4/!\\ Alerte Staff /!\\", "§6Vous êtes demandé par un membre du staff !",10,70,20);
            targetPlayer.sendMessage("§6§kHHHH§r§6 -§4§l Alerte Staff§r§6 - §6§kHHHH");
            targetPlayer.sendMessage("§6Vous êtes demandé par un membre du staff !");
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING,1f,1f);

            player.sendMessage("§4[§6WatchOver§4] §3Alerte envoyée à §e"+targetPlayer.getDisplayName());
            return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
