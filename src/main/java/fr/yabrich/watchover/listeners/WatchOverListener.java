package fr.yabrich.watchover.listeners;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.commands.CommandWatchOver;
import fr.yabrich.watchover.utils.FreezeManager;
import fr.yabrich.watchover.utils.PlayerVanish;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class WatchOverListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();

        if(CommandWatchOver.woActived.contains(player)){
            if(e.getHand() != EquipmentSlot.HAND) return;

            ItemStack item = e.getItem();
            if(item == null) return;
            String itemName = Objects.requireNonNull(item.getItemMeta()).getDisplayName();

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

            // NV
            if(itemName.equalsIgnoreCase("§1§lNight Vision")){
                e.setCancelled(true);
                player.performCommand("watchover:nv");
                return;
            }

            // Execption pose de bloc
            if(itemName.equalsIgnoreCase("§9§lFreeze")){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();

        if(CommandWatchOver.woActived.contains(player)){
            if(e.getHand() != EquipmentSlot.HAND) return;
            if(!(entity instanceof Player targetPlayer)) return;

            ItemStack item = player.getInventory().getItemInMainHand();
            String itemName = Objects.requireNonNull(item.getItemMeta()).getDisplayName();

            // Invsee
            if(itemName.equalsIgnoreCase("§e§lInvsee")){
                player.openInventory(targetPlayer.getInventory());
                player.sendMessage(Main.getPrefix()+"Inventaire de §b"+targetPlayer.getDisplayName());
                return;
            }

            // Alert
            if(itemName.equalsIgnoreCase("§c§lAlert")){
                player.performCommand("watchover:alert "+targetPlayer.getDisplayName());
                return;
            }

            // Freeze
            if(itemName.equalsIgnoreCase("§9§lFreeze")){
                e.setCancelled(true);
                player.performCommand("watchover:freeze "+targetPlayer.getDisplayName());
            }
        }
    }
}
