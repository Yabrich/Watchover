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
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player player) {
            PotionEffectType nv = PotionEffectType.NIGHT_VISION;

            if(player.getPotionEffect(nv) == null) {
                player.addPotionEffect(new PotionEffect(nv, Integer.MAX_VALUE, 0));
                player.sendMessage(Main.getPrefix()+"§3Vision Nocturne §aactivée §3!");
            }
            else {
                player.removePotionEffect(nv);
                player.sendMessage(Main.getPrefix()+"§3Vision Nocturne §4désactivée §3!");
            }
            return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
