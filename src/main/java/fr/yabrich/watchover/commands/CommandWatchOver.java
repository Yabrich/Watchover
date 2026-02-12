package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.utils.PlayerVanish;
import fr.yabrich.watchover.utils.WatchOverBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandWatchOver implements CommandExecutor {

    static HashMap<Player, ItemStack[]> inventories = new HashMap<>();
    public static ArrayList<Player> woActived = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if (!woActived.contains(player)) {
                ItemStack[] playerInv = player.getInventory().getContents();

                inventories.put(player, playerInv);

                player.getInventory().clear();

                // Construction de l'inventaire
                Inventory WOInv = WatchOverBuilder.createWOInventory();

                player.getInventory().setContents(WOInv.getContents());
                PlayerVanish.vanishPlayer(player);
                woActived.add(player);

                player.sendMessage(Main.getPrefix()+"Menu modération §aactivé §3!");
                player.sendMessage(Main.getPrefix()+"Vanish §aactivé§3 !");

            }else {
                player.getInventory().clear();
                player.getInventory().setContents(inventories.get(player));
                PlayerVanish.unvanishPlayer(player);
                woActived.remove(player);


                player.sendMessage(Main.getPrefix()+"Menu modération §4déactivé §3!");
                player.sendMessage(Main.getPrefix()+"Vanish §4désactivé§3 !");
            }
        }
        return false;
    }
}
