package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandChat implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage(Main.getErrPrefix() + "Arguments manquant(s)..");
                player.sendMessage(Main.getErrPrefix() + "Usage : /chat [enable/disable/slowmode]");
                return false;
            }

            if (args[0].equalsIgnoreCase("disable")) {
                if(!ChatListener.chatenable){
                    player.sendMessage(Main.getErrPrefix()+"Le chat est déjà désactivé..");
                    return false;
                }
                ChatListener.chatenable = false;
                Bukkit.broadcastMessage(Main.getPrefix()+"Le chat a été §4désactivé.");
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.hasPermission("wo.chat")){
                        p.sendMessage(Main.getPrefix()+"Action effectuée par §b"+player.getName());
                    }
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("enable")) {
                if(ChatListener.chatenable){
                    player.sendMessage(Main.getErrPrefix()+"Le chat est déjà activé..");
                    return false;
                }
                ChatListener.chatenable = true;
                Bukkit.broadcastMessage(Main.getPrefix()+"Le chat a été §aactivé.");
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.hasPermission("wo.chat")){
                        p.sendMessage(Main.getPrefix()+"Action effectuée par §b"+player.getName());
                    }
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("slowmode")) {
                if(args.length < 2){
                    player.sendMessage(Main.getErrPrefix()+"Veuillez préciser une durée de slowmode.");
                    return false;
                }
                String timeRaw = args[1];
                try{
                    int time = Integer.parseInt(timeRaw);

                    if(time == ChatListener.chatslowmode){
                        player.sendMessage(Main.getErrPrefix()+"Le slowmode est déjà activé à ce temps.");
                        return false;
                    }

                    ChatListener.chatslowmode = time;

                    if(time == 0){
                        Bukkit.broadcastMessage(Main.getPrefix()+"Le slowmode du chat a été désactivé.");
                    }else{
                        Bukkit.broadcastMessage(Main.getPrefix()+"Le slowmode du chat a été activé à §b"+timeRaw+" §3seconde(s)");
                    }

                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.hasPermission("wo.chat")){
                            p.sendMessage(Main.getPrefix()+"Action effectuée par §b"+player.getName());
                        }
                    }
                }catch (NumberFormatException e){
                    player.sendMessage(Main.getErrPrefix()+"Nombre incorrect..");
                    return false;
                }
                return true;
            }
            player.sendMessage(Main.getErrPrefix() + "Arguments incorrect(s)..");
            player.sendMessage(Main.getErrPrefix() + "Usage : /chat [enable/disable/slowmode]");
            return false;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
