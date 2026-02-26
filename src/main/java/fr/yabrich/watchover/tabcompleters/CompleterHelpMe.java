package fr.yabrich.watchover.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CompleterHelpMe implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if((command.getName().equalsIgnoreCase("ans") || command.getName().equalsIgnoreCase("report")) && args.length == 1){
            List<String> tab = new ArrayList<>();

            for(Player p : Bukkit.getOnlinePlayers()){
                tab.add(p.getName());
            }

            tab.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));

            return tab;
        }
        return List.of();
    }
}
