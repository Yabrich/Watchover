package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import fr.yabrich.watchover.utils.TrackerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandXYZ implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if(args.length == 0){
                player.sendMessage(Main.getErrPrefix()+"Veuillez préciser une cible..");
                return false;
            }

            if(args[0].equalsIgnoreCase("cancel")){
                if(!TrackerManager.isTracking(player)){
                    player.sendMessage(Main.getErrPrefix()+"Vous ne tracker personne.");
                    return false;
                }

                TrackerManager.clearTargets(player);
                player.sendMessage(Main.getPrefix()+"Votre tracker a été clear.");
                return true;
            }

            String username = args[0];
            Player targetPlayer = Bukkit.getPlayer(username);

            if(targetPlayer == null){
                player.sendMessage(Main.getErrPrefix()+"Ce joueur n'est pas connecté..");
                return false;
            }

            Location location = targetPlayer.getLocation();
            player.sendMessage(Main.getPrefix()+"Coordonnées de §b"+targetPlayer.getName()+"§3 :\n" +
                    "§3- Monde : §b" + Objects.requireNonNull(location.getWorld()).getName()+"\n"+
                    "§3- X : §b" + location.getBlockX()+"\n"+
                    "§3- Y : §b" + location.getBlockY()+"\n"+
                    "§3- Z : §b"+location.getBlockY());
            return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
