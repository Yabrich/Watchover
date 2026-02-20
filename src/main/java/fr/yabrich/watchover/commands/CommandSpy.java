package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandSpy implements CommandExecutor {
    public static ArrayList<Player> spyActived = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("wo")){
                if (!spyActived.contains(player)) {
                    spyActived.add(player);
                    player.sendMessage(Main.getPrefix()+"Vous avez §aactivé §3le spycmd !");
                    return true;
                }
                else{
                    spyActived.remove(player);
                    player.sendMessage(Main.getPrefix()+"Vous avez §4désactivé §3le spycmd !");
                    return true;
                }
            }
            else{
                player.sendMessage(Main.getErrPrefix()+"Vous n'avez pas la permission d'éxécuter cette commande !");
            }
        }
        return false;
    }
}
