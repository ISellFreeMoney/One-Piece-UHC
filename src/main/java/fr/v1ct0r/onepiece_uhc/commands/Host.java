package fr.v1ct0r.onepiece_uhc.commands;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.State;
import fr.v1ct0r.onepiece_uhc.items.ItemsInv;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Host  implements CommandExecutor {

    private final OnePieceUHC op;
    private final ItemsInv items_inv;

    public Host(OnePieceUHC op) {
        this.op = op;
        items_inv = new ItemsInv(op);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player player) {
            if (player.isOp()) {
                if (cmd.getName().equalsIgnoreCase("host")) {
                    if (op.isState(State.CONFIG)) {
                        if (player.getInventory().contains(items_inv.config))
                            player.sendMessage("§c[OP] Vous avez deja recu l'item de configuration !");
                        else {
                            player.getInventory().setItem(4, items_inv.config);
                            player.sendMessage("§a[OP] Vous avez recu l'item de configuration !");
                        }
                    } else
                        player.sendMessage("§c[OP] Vous ");
                }
                else if(cmd.getName().equalsIgnoreCase("say")) {
                    if(args.length == 0)
                        player.sendMessage("§c[OP] Veuillez donner votre message");
                    else{
                        StringBuilder message = new StringBuilder("§6[OP - HOST] " + player.getName() + " : §9");
                        for(String s: args)
                            message.append(s).append(" ");
                        Bukkit.broadcastMessage("§9§m----------------------------------------------");
                        Bukkit.broadcastMessage(message.toString());
                        Bukkit.broadcastMessage("§9§m----------------------------------------------");
                    }
                }
            }else
                player.sendMessage("§c[OP] Vous n'avez pas les droits suffisants pour effectuer cette commande !");
        }
        return true;
    }
}
