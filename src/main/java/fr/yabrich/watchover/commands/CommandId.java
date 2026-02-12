package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandId implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            ItemStack item = player.getInventory().getItemInMainHand();
            Material material = item.getType();

            if(material == Material.AIR){
                player.sendMessage(Main.getPrefix()+"§3Votre main est vide !");
                return false;
            }

            player.sendMessage(Main.getPrefix()+"§3Votre item : "+material);
            return true;
        }

        return false;
    }
}
