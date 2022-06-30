package fr.v1ct0r.onepiece_uhc.timers;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.effects.Effects;
import fr.v1ct0r.onepiece_uhc.players.Player;
import fr.v1ct0r.onepiece_uhc.players.Role;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class DyingTimer extends BukkitRunnable {

    // Timer
    private int timer = 0;
    private int death_time = 16;

    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    // Constructeur
    OnePieceUHC op;
    Player player;
    Effects effects;
    public DyingTimer(OnePieceUHC op, Player player) {
        this.op = op;
        this.player = player;
        effects = new Effects(op);
    }

    @Override
    public void run() {

        //**********************************************************************

        // Message de pré mort
        if(timer ==  0)
            player.getPlayer().sendMessage("§4[LG] §9Vous êtes mort mais ne partez pas ! Vous pouvez encore vous faire réanimer si vous êtes Ancien ou "
                    + "si l'Infect Père Des Loups ou la Sorcière le décide !");

        //**********************************************************************

        // TODO ROLE EMPECHANT LA MORT

        // Mort
        if(timer == death_time && player.isDying()) {
            //Kill le joueur
            //TODO Gérer le cas où le joueur s'est déconnecté
            player.getPlayer().teleport(player.death_location);
            Bukkit.dispatchCommand(console, "kill " + player.getName());
            player.setDying(false);
        }

        //**********************************************************************

        if(!player.isDying() || player.isDead()) {
            // Respawn le joueur
            this.cancel();
        }

        //**********************************************************************
        if(timer > death_time)
            this.cancel();
        //**********************************************************************
        timer++;
    }
}
