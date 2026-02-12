package fr.yabrich.watchover.listeners;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.commands.CommandVanish;
import fr.yabrich.watchover.commands.CommandWatchOver;
import fr.yabrich.watchover.utils.PlayerVanish;
import fr.yabrich.watchover.utils.WatchOverBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class WatchOverListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();

        if(CommandWatchOver.woActived.contains(player)){
            if(e.getHand() != EquipmentSlot.HAND) return;
            e.setCancelled(true);

            ItemStack item = e.getItem();
            assert item != null;
            assert item.getItemMeta() != null;
            String itemName = item.getItemMeta().getDisplayName();

            // Vanish
            if(itemName.equalsIgnoreCase("§4§lVanish") || itemName.equalsIgnoreCase("§a§lVanish")) {
                player.performCommand("watchover:vanish");
                ItemMeta itmeta = item.getItemMeta();

                if(!PlayerVanish.isPlayerVanished(player)) {
                    itmeta.setDisplayName("§4§lVanish");
                    itmeta.removeEnchantments();
                }
                else {
                    itmeta.setDisplayName("§a§lVanish");
                    itmeta.addEnchant(Enchantment.EFFICIENCY, 1, true);
                }

                item.setItemMeta(itmeta);
                return;
            }

            // RTP
            if(itemName.equalsIgnoreCase("§d§lR-TP")){
                ArrayList<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
                onlinePlayers.remove(player);

                if(onlinePlayers.isEmpty()){
                    player.sendMessage(Main.getErrPrefix()+"Aucun autre joueur connecté !");
                    return;
                }

                Random random = new Random();
                int value = random.nextInt(onlinePlayers.size());

                Player target = onlinePlayers.get(value);
                player.teleport(target);
                player.sendMessage(Main.getPrefix()+"Téléportation sur §b"+target.getDisplayName()+"§3..");
                return;
            }
        }
    }
}
