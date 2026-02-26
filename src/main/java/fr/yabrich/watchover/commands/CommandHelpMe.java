package fr.yabrich.watchover.commands;

import fr.yabrich.watchover.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class CommandHelpMe implements CommandExecutor {
    private Map<Player,LocalTime> cooldown = new HashMap<>();


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 0){
                player.sendMessage(Main.getErrPrefix()+"Veuillez préciser votre demande..");
                player.sendMessage(Main.getErrPrefix()+"Usage : /helpme [question]");
                return false;
            }

            String nickname = player.getName();

            LocalTime now = LocalTime.now();

            LocalTime cd_joueur = cooldown.get(player);
            LocalTime cd_requis;

            if(cd_joueur == null) {
                cd_requis = now.minusSeconds(1);
            }
            else {
                cd_requis = cd_joueur.plusSeconds(30);
            }

            if(now.isAfter(cd_requis)) {
                StringBuilder question = new StringBuilder();
                for(String part : args) {
                    question.append(part).append(" ");
                }

                player.sendMessage(Main.getPrefix()+"Votre demande à bien été prise en compte et envoyée aux staffs !");
                cooldown.put(player, now);

                for(Player staff : Bukkit.getOnlinePlayers()) {
                    if(staff.hasPermission("wo.helpme.ans")) {
                        staff.sendMessage("§2[§aHelpMe§2] §8§o(/ans "+nickname+" pour répondre) §e"+nickname+" §7: §6"+question);
                    }
                }
            }
            else {
                Duration cd_restant = Duration.between(now,cd_requis);
                long secondes = cd_restant.toSeconds();

                player.sendMessage(Main.getErrPrefix()+"Merci d'attendre §4"+secondes+" secondes §cavant d'envoyer votre requête !");
            }
            return true;
        }
        sender.sendMessage(Main.getErrPrefix()+"Vous devez être un joueur pour faire ça..");
        return false;
    }
}
