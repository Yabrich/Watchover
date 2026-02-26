package fr.yabrich.watchover.listeners;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.commands.CommandWatchOver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WatchOverExceptionListener implements Listener {
    private ArrayList<String> woItems = new ArrayList<>(List.of(
            "§a§lStaffVanish",
            "§a§lVanish",
            "§c§lAlert",
            "§d§lR-TP",
            "§e§lInvsee",
            "§9§lFreeze",
            "§1§lNight Vision",
            "§4§lStaffVanish",
            "§4§lVanish"));

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        if(item == null){ return;}
        if(item.getItemMeta() == null){return;}

        for (String name : woItems) {
            if(item.getItemMeta().getDisplayName().equalsIgnoreCase(name)){
                if(!CommandWatchOver.woActived.contains(player.getUniqueId())){
                    player.getInventory().remove(item);
                    player.sendMessage(Main.getErrPrefix()+"Vous devez être en /wo pour utiliser cet item ! (Item supprimé)");
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack();
        if(item.getItemMeta() == null){return;}

        for (String name : woItems) {
            if(item.getItemMeta().getDisplayName().equalsIgnoreCase(name)){
                e.setCancelled(true);
                player.sendMessage(Main.getErrPrefix()+"Vous ne pouvez pas jeter cet item !");
                return;
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getWhoClicked() instanceof Player player){
            if(CommandWatchOver.woActived.contains(player.getUniqueId())) {return;}

            ItemStack item = e.getCurrentItem();
            if(item == null) {return;}
            if(item.getItemMeta() == null) {return;}

            for (String name : woItems) {
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase(name)){
                    e.setCancelled(true);
                    e.setCurrentItem(null);
                    player.sendMessage(Main.getErrPrefix()+"Vous devez être en /wo pour utiliser cet item ! (Item supprimé)");
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e){
        if(e.getEntity() instanceof Player player){
            if(CommandWatchOver.woActived.contains(player.getUniqueId())){
                e.setCancelled(true);
            }
        }
    }
}
