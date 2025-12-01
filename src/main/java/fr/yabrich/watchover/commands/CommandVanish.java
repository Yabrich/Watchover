package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CommandVanish implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player)commandSender;

            Collection<? extends Player> playerlist = Bukkit.getOnlinePlayers();

            for(Player p : playerlist){
                p.hidePlayer(Main.getInstance(),player);
            }

            player.sendMessage(Main.getPrefix()+"§3Vanish §aactivé§3 !");
        }
        return false;
    }
}
