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
import java.util.UUID;

import static fr.yabrich.watchover.commands.CommandSpy.spyActived;

public class CommandWatchOver implements CommandExecutor {

    static HashMap<UUID, ItemStack[]> inventories = new HashMap<>();
    public static ArrayList<UUID> woActived = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if (!woActived.contains(player.getUniqueId())) {
                ItemStack[] playerInv = player.getInventory().getContents();

                inventories.put(player.getUniqueId(), playerInv);

                player.getInventory().clear();

                boolean sv = false;

                if(args.length > 0){
                    if(args[0].equalsIgnoreCase("staff")){
                        if (player.hasPermission("wo.watchoverstaff")) {
                            sv = true;
                        }
                        else{
                            player.sendMessage(Main.getErrPrefix()+"Vous n'avez pas la permission d'executer cette commande !");
                            return false;
                        }
                    }
                }

                // Construction de l'inventaire
                Inventory WOInv = WatchOverBuilder.createWOInventory(sv);

                player.getInventory().setContents(WOInv.getContents());
                PlayerVanish.vanishPlayer(player, sv);
                woActived.add(player.getUniqueId());

                if (!spyActived.contains(player)) {
                    player.performCommand("watchover:spycmd");
                }

                player.sendMessage(Main.getPrefix()+"Menu modération §aactivé §3!");
                if(sv){
                    player.sendMessage(Main.getPrefix()+"§3StaffVanish §aactivé§3 !");
                }else{
                    player.sendMessage(Main.getPrefix()+"Vanish §aactivé§3 !");
                }

            }else {
                player.getInventory().clear();
                player.getInventory().setContents(inventories.get(player.getUniqueId()));
                boolean sv = PlayerVanish.isPlayerStaffVanished(player);
                PlayerVanish.unvanishPlayer(player);
                woActived.remove(player.getUniqueId());

                player.sendMessage(Main.getPrefix()+"Menu modération §4désactivé §3!");
                if(sv){
                    player.sendMessage(Main.getPrefix()+"§3StaffVanish §4désactivé§3 !");
                }else{
                    player.sendMessage(Main.getPrefix()+"Vanish §4désactivé§3 !");
                }
            }
            return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
