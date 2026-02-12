package fr.yabrich.watchover.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WatchOverBuilder {
    public static Inventory createWOInventory(){
        Inventory inventory = Bukkit.createInventory(null,36);

        //1. Vanish
        ItemStack vanish = createItem("§a§lVanish", Material.MAGMA_CREAM);
        inventory.setItem(0,vanish);

        //2. Alert
        ItemStack alert = createItem("§c§lAlert", Material.PAPER);
        inventory.setItem(1,alert);

        //3. RTP
        ItemStack rtp = createItem("§d§lR-TP", Material.NETHER_STAR);
        inventory.setItem(3,rtp);

        //4. Invsee
        ItemStack invsee = createItem("§e§lInvsee", Material.BLAZE_ROD);
        inventory.setItem(5, invsee);

        //5. Freeze
        ItemStack freeze = createItem("§9§lFreeze", Material.PACKED_ICE);
        inventory.setItem(7, freeze);

        //6. NightVision
        ItemStack nv = createItem("§1§lNight Vision", Material.GOLDEN_CARROT);
        inventory.setItem(8, nv);

        return inventory;
    }

    public static ItemStack createItem(String name, Material material){
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName(name);
        itemMeta.addEnchant(Enchantment.EFFICIENCY,1,false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);

        return item;
    }
}
