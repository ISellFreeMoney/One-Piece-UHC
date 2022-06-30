package fr.v1ct0r.onepiece_uhc.timers;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.State;
import fr.v1ct0r.onepiece_uhc.effects.Effects;
import fr.v1ct0r.onepiece_uhc.players.Player;
import fr.v1ct0r.onepiece_uhc.players.Role;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class GlobalTimer extends BukkitRunnable {

    // Timer
    private int timer = 0;
    private final int day_time;
    private final int role_time;
    private final int pvp_time;
    private final int border_time;

    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    // Jour
    private int nb_day = 1;
    // Nuit
    private int nb_night = 1;

    // Constructeur
    OnePieceUHC op;
    Effects effects;
    public GlobalTimer(OnePieceUHC op) {
        this.op = op;
        pvp_time = op.pvp_time*60;
        role_time = op.role_time*60;
        day_time = op.cycle_jn*120;
        border_time = op.border_time*60;
        effects = new Effects(op);
    }

    @Override
    public void run() {

        // Jour
        if(timer%day_time == 0) {
            String command_day = "time set day";
            Bukkit.dispatchCommand(console, command_day);
            Bukkit.broadcastMessage("§a§m----------------------§r§a Jour " + nb_day + " §m----------------------");
            op.setState(State.DAY);
            if(nb_day == 1) {
                Bukkit.broadcastMessage("§a[UHC] §9Vous êtes invincibles pour les 30 prochaines secondes, profitez en !");
                String command2 = "gamerule naturalRegeneration false";
                Bukkit.dispatchCommand(console, command2);
                op.setState(State.INVINCIBILITY);
            }
            Location parano = null;
            Player paranop = null;

            // TODO EVENEMENT DES ROLES

            nb_day++;
        }

        //**********************************************************************

        // Fin des commandes
        if(timer%(day_time+60) == 0 && nb_day > 2) {
            // TODO EVENEMENT EN FONCTION DES ROLES
        }

        //**********************************************************************

        // Vote
        int vote_time = 90;
        if(timer%(day_time+ vote_time) == 0 && nb_day > 2) {
            Bukkit.broadcastMessage("§4[LG] §9Le jour est levé ! C'est le moment de voter contre le joueur que vous voulez grâce à la commande §6/vote (pseudo) §9!");
            for(Player player: op.InGame) {
                player.setCanVote(true);
                player.setVote(0);
            }
        }
        if(timer%(day_time+ vote_time +60) == 0&& nb_day > 2) {
            Bukkit.broadcastMessage("§4[LG] §9La période de vote est terminée !");
            int more_vote = 0;
            Player vote = null;
            boolean draw = false;
            for(Player player: op.InGame) {
                if(player.getVote() == more_vote)
                    draw = true;
                else if(player.getVote() > more_vote) {
                    more_vote = player.getVote();
                    vote = player;
                    draw = false;
                }
                player.setCanVote(false);
            }
            if(draw || op.InGame.size() == 0) {
                Bukkit.broadcastMessage("§4[LG] §9Le village ne s'est pas mis d'accord, une §légalité §r§9a eu lieu !");
                op.player_vote = null;
            }else {
                assert vote != null;
                Bukkit.broadcastMessage("§4[LG] §9Le joueur ayant pris le plus de vote est §6" + vote.getName() + "§9 avec §l" + more_vote + " vote(s) §r§9!");
                vote.getPlayer().setMaxHealth(vote.getPlayer().getMaxHealth()/2);
                op.player_vote = vote;
            }

        }


        //**********************************************************************

        // Nuit
        if((timer+(day_time/2))%day_time == 0) {
            String command_night = "time set night";
            Bukkit.dispatchCommand(console, command_night);
            Bukkit.broadcastMessage("§c§m----------------------§r§c Nuit " + nb_night + " §m----------------------");
            op.setState(State.NIGHT);
            Location pf = null;
            Player pfp = null;
            // TODO EVENEMENT DE NUIT ROLE
            nb_night++;
        }

        //**********************************************************************

        // Assignation des rôles
        if(timer >= role_time-5 && timer <= role_time) {
            if(timer != role_time)
                Bukkit.broadcastMessage("§4[LG] §9Assignation des rôles dans " + (role_time-timer) + " seconde(s).");
            else {
                Bukkit.broadcastMessage("§4[LG] §9Assignation des rôles !");
                op.assignRole();
                op.role_selected = true;
            }
        }

        //**********************************************************************

        // Fin de l'invincibilité
        int invicibility_time = 30;
        if(timer >= invicibility_time -5 && timer <= invicibility_time) {
            if(timer != invicibility_time)
                Bukkit.broadcastMessage("§a[UHC] §9Fin de l'invincibilité dans "+ (invicibility_time -timer) + " seconde(s).");
            else {
                Bukkit.broadcastMessage("§a[UHC] §9Fin de l'invincibilité !");
                op.setState(State.DAY);
            }
        }

        //**********************************************************************

        // Début du PVP
        if(timer >= pvp_time-5 && timer <= pvp_time) {
            if(timer != pvp_time)
                Bukkit.broadcastMessage("§a[UHC] §9PVP activé dans "+ (pvp_time-timer) + " seconde(s).");
            else {
                Bukkit.broadcastMessage("§a[UHC] §9PVP activé !");
                op.setPvp(true);
            }
        }
        op.updateScoreBoard();

        //**********************************************************************

        // Début de la réduction de la bordure
        if(timer >= border_time-5 && timer <= border_time) {
            if(timer != border_time)
                Bukkit.broadcastMessage("§a[UHC] §9Réduction de la bordure dans "+ (border_time-timer) + " seconde(s).");
            else {
                Bukkit.broadcastMessage("§a[UHC] §9Réduction de la bordure !");
                Bukkit.getScheduler().runTaskTimer(op, () -> {
                    if(op.border.getSize() > 200)
                        op.border.setSize(op.border.getSize()-((op.border_speed/20.0)*2.0));
                },0, 1);
            }
        }
        op.updateScoreBoard();

        //**********************************************************************
        if(timer > day_time*30 || op.isState(State.END))
            this.cancel();
        //**********************************************************************
        timer++;
        op.global_timer = timer;
        op.role_time_s--;
        op.pvp_time_s--;
        op.border_time_s--;
    }
}
