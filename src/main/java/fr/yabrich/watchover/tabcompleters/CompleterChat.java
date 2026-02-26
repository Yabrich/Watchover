package fr.yabrich.watchover.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompleterChat implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            List<String> tab = new ArrayList<>(List.of("enable","disable","slowmode"));

            tab.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));

            return tab;
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("slowmode")){
            List<String> tab = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                tab.add(String.valueOf(i));
            }

            tab.removeIf(s -> !s.toLowerCase().startsWith(args[1].toLowerCase()));

            return tab;
        }

        return List.of();
    }
}
