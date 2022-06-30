package fr.v1ct0r.onepiece_uhc.commands;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.State;
import fr.v1ct0r.onepiece_uhc.items.ItemsInv;
import fr.v1ct0r.onepiece_uhc.players.Role;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Roles implements CommandExecutor {

    // Constructeur
    private final OnePieceUHC op;

    public Roles(OnePieceUHC op) {
        this.op = op;
        ItemsInv items_inv = new ItemsInv(op);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        // Vérifie que la commande ne vient pas de la console.
        if(sender instanceof Player player) {

            if(cmd.getName().equalsIgnoreCase("roles")) {
                if(op.InGame.get(0).getRole() != Role.NONE) {
                    player.sendMessage("§9§m----------------------------------------------");
                    player.sendMessage("§9§l   Liste des roles :");
                    // TODO AFFICHER LES DIFFERENTS ROLES
                }else
                    player.sendMessage("§4[LG] §cLes rôles n'ont pas encore été distribués !");
            }

            //**********************************************************************

            else if(op.isState(State.DAY) || op.isState(State.NIGHT) || op.isState(State.INVINCIBILITY)) {
                fr.v1ct0r.onepiece_uhc.players.Player p = op.findPlayer(player);

                if(op.InGame.contains(p) || !p.isDying()) {
                    if(p.getRole() != Role.NONE) {

                        //**********************************************************************
                        // /role

                        if(cmd.getName().equalsIgnoreCase("role"))
                            op.printRole(false, p);

                        //**********************************************************************
                        // /vote

                        else if(cmd.getName().equalsIgnoreCase("vote")) {
                            if(args.length == 1) {
                                if(p.canVote()) {
                                    fr.v1ct0r.onepiece_uhc.players.Player vote = op.findPlayerFromUsername(args[0]);
                                    if(op.InGame.contains(vote)) {
                                        if(!player.getName().equalsIgnoreCase(args[0])){
                                            if(op.player_vote == null || (!op.player_vote.getName().equalsIgnoreCase(args[0]))) {
                                                player.sendMessage("§4[LG] §9Votre vote contre §6" + vote.getName() + "§9 a bien été comptabilisé !");
                                                vote.setVote(vote.getVote()+1);
                                                p.setCanVote(false);
                                            }else
                                                player.sendMessage("§4[LG] §cVous ne pouvez pas voter contre quelqu'un qui a pris les votes la veille !");
                                        }else
                                            player.sendMessage("§4[LG] §cVous ne pouvez pas voter contre vous !");
                                    }else
                                        player.sendMessage("§4[LG] §cCe joueur n'est pas dans la partie !");
                                }else
                                    player.sendMessage("§4[LG] §cVous ne pouvez pas/plus voter pour l'instant !");
                            }else
                                player.sendMessage("§c[Erreur] La commande est §6/vote (pseudo) §c!");
                        }

                        //**********************************************************************
                        // /lgt

                        else if(cmd.getName().equalsIgnoreCase("lgt")) {
                            if(p.isMarine()) {
                                if(op.isState(State.NIGHT)) {
                                    if(args.length == 0)
                                        player.sendMessage("§c[Erreur] La commande est §6/lgt (message) §9!");
                                    else {
                                        StringBuilder message = new StringBuilder();
                                        for(String s: args)
                                            message.append(s).append(" ");
                                        for(fr.v1ct0r.onepiece_uhc.players.Player player_: op.InGame) {
                                            if(player_.isMarine())
                                                player_.getPlayer().sendMessage("§4[Loups] §c" + player.getName() + " : " +  message);
                                        }
                                    }
                                }else
                                    player.sendMessage("§4[OP] §cVous ne pouvez parler dans ce chat que la nuit !");
                            }else
                                player.sendMessage("§4[OP] §cVous n'appartenez pas a la marine !");
                        }

                        //**********************************************************************

                    }else
                        player.sendMessage("§4[OP] §cVous n'avez pas encore de rôle !");
                }else
                    player.sendMessage("§c[OP] Vous n'êtes pas/plus dans la partie !");
            }else
                player.sendMessage("§c[OP] La partie n'a pas encore démarré ou est terminée !");

            //**********************************************************************
        }
        return true; //Default
    }
}
