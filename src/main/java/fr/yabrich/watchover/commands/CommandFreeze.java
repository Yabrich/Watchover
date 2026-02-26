package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.utils.FreezeManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandFreeze implements CommandExecutor {
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

            if(targetPlayer.hasPermission("wo.freeze")){
                player.sendMessage(Main.getErrPrefix()+"Ce joueur est immunisé..");
                return false;
            }

            if(!FreezeManager.isPlayerFreezed(targetPlayer)){
                FreezeManager.freezePlayer(targetPlayer);
                player.sendMessage(Main.getPrefix()+"§b"+targetPlayer.getDisplayName()+" §3à bien été freeze.");
            }else{
                FreezeManager.unFreezePlayer(targetPlayer);
                player.sendMessage(Main.getPrefix()+"§b"+targetPlayer.getDisplayName()+" §3à bien été unfreeze.");
            }

            return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
