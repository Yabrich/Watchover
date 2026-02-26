package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAns implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length < 2) {
                player.sendMessage(Main.getErrPrefix()+"Argument(s) manquant(s)");
                player.sendMessage(Main.getErrPrefix()+"Usage : /ans [joueur] [réponse]");
                return false;
            }

            if(Bukkit.getPlayer(args[0]) == null) {
                player.sendMessage(Main.getErrPrefix()+"Ce joueur n'est pas connecté..");

                return false;
            }

            if(args[0].equalsIgnoreCase(player.getName())) {
                player.sendMessage(Main.getErrPrefix()+"Vous ne pouvez pas vous répondre à vous même !");

                return false;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);

            StringBuilder answer = new StringBuilder();
            for(String part : args) {
                if(!part.equalsIgnoreCase(args[0])) {
                    answer.append(part).append(" ");
                }
            }

            for(Player staff : Bukkit.getOnlinePlayers()) {
                if(staff.hasPermission("wo.helpme.ans")) {
                    staff.sendMessage("§2[§aHelpMe§2] §2Réponse de §a"+player.getName()+" §2à §a"+targetPlayer.getName()+" §7: §6"+answer);
                }
            }
            targetPlayer.sendMessage("§2[§aHelpMe§2] §2Réponse de §a"+player.getName()+" §7: §6"+answer);
            return true;
        }

        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
