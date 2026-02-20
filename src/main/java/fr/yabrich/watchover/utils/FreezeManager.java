package fr.yabrich.watchover.utils;

import fr.yabrich.watchover.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class FreezeManager {
    static ArrayList<Player> freezed = new ArrayList<>();
    static HashMap<Player, ItemStack> helmet = new HashMap<>();

    public static void freezePlayer(Player player){
        freezed.add(player);

        if(player.getInventory().getHelmet() != null){
            helmet.put(player,player.getInventory().getHelmet());
        }

        player.getInventory().setHelmet(new ItemStack(Material.PACKED_ICE));
        player.sendMessage(Main.getPrefix()+"Vous avez été freeze par un membre du staff.");
    }

    public static void unFreezePlayer(Player player){
        freezed.remove(player);
        player.getInventory().setHelmet(null);

        if(helmet.containsKey(player)){
            player.getInventory().setHelmet(helmet.get(player));
        }

        player.sendMessage(Main.getPrefix()+"Vous avez été unfreeze..");
    }

    public static boolean isPlayerFreezed(Player player){
        return freezed.contains(player);
    }
}
