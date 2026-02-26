package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandStaffChat implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 0){
                player.sendMessage(Main.getErrPrefix()+"Message manquant.");
                return false;
            }

            StringBuilder builder = new StringBuilder();
            for(String msg : args){
                builder.append(msg).append(" ");
            }
            builder.deleteCharAt(builder.length()-1);
            String message = builder.toString();


            for (Player p : Bukkit.getOnlinePlayers()){
                if(p.hasPermission("wo.staffchat")){
                    p.sendMessage("§4[§6StaffChat§4] §9"+player.getName()+" §7: §3"+message);
                }
            }

            return true;
        }

        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
