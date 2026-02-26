package fr.yabrich.watchover.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CompleterWO implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            if (sender.hasPermission("wo.staffvanish")) {
                List<String> tab = new ArrayList<>();
                tab.add("staff");

                tab.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));

                return tab;
            }
        }
        return List.of();
    }
}
