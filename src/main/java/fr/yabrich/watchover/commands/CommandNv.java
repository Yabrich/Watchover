package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class CommandNv implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player)commandSender;

            PotionEffectType nv = PotionEffectType.NIGHT_VISION;

            if(player.getPotionEffect(nv) == null) {
                player.addPotionEffect(new PotionEffect(nv, Integer.MAX_VALUE, 0));
                player.sendMessage(Main.getPrefix()+"§3Vision Nocturne §2activée §3!");
            }
            else {
                player.removePotionEffect(nv);
                player.sendMessage(Main.getPrefix()+"§3Vision Nocturne §4désactivée §3!");
            }

        }
        return true;
    }
}
