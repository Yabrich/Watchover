package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandChatClear implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            for (int i=0;i<150;i++) {
                Bukkit.broadcastMessage("");
            }
            Bukkit.broadcastMessage(Main.getPrefix()+"Le chat a été clear..");
            for(Player p : Bukkit.getOnlinePlayers()){
                if(p.hasPermission("wo.chat")){
                    p.sendMessage(Main.getPrefix()+"ChatClear par §b"+player.getName());
                }
            }
            return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
