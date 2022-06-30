package fr.v1ct0r.onepiece_uhc.listeners;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.State;
import fr.v1ct0r.onepiece_uhc.players.Role;
import fr.v1ct0r.onepiece_uhc.timers.DyingTimer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Damage implements Listener {
    // Constructeur
    private final OnePieceUHC op;
    public Damage(OnePieceUHC main) {
        this.op = main;
    }

    //**********************************************************************

    // Mort d'un joueur
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage("");
        if(!op.isState(State.END)) {
            Player player = event.getEntity();
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 50, 20);
            }
            fr.v1ct0r.onepiece_uhc.players.Player p = op.findPlayer(player);
            p.setDead(true);
            if(op.role_selected) {
                // TODO MESSAGE DE MORT SPECIAL
            }
            else
                Bukkit.broadcastMessage("§9§m----------------------------------------------\n"
                        + "§4[LG] §6" + event.getEntity().getName() + " §9est mort ! Il n'avait pas de rôle !\n"
                        + "§9§m----------------------------------------------");

            if(p.getRole() == Role.DEFAULT) {
                op.InGame.remove(p);
                op.updateScoreBoard();
            }
            for(fr.v1ct0r.onepiece_uhc.players.Player player_ : op.InGame) {
                // TODO SI JOUEUR PEU REVIVE
            }
            op.verifWin();
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if(!op.isState(State.END))
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
    }

    //**********************************************************************

    // Dégat d'entity
    @EventHandler
    public void OnDamageByEntity(EntityDamageByEntityEvent event) {
        Entity victim = event.getEntity();
        Entity killer_entity = event.getDamager();
        if(victim instanceof Player player) {

            if(op.isState(State.CONFIG) || op.isState(State.STARTING) || op.isState(State.INVINCIBILITY))
                event.setCancelled(true); // Pas de dégats pris dans la phase de préparation ou de lancement ou d'invincibilité
            else {
                if(killer_entity instanceof Player || (killer_entity instanceof Arrow && ((Arrow) killer_entity).getShooter() instanceof Player)) {
                    if(!op.isPvp())
                        event.setCancelled(true);
                    else {
                        fr.v1ct0r.onepiece_uhc.players.Player p = op.findPlayer(player);
                        if(player.getHealth() <= event.getDamage()) {
                            op.findPlayer(player).setDying(true);
                            op.findPlayer(player).death_location = player.getLocation();
                            DyingTimer task = new DyingTimer(op, op.findPlayer(player));
                            task.runTaskTimer(op, 0, 20);
                            event.setDamage(0);
                            player.setHealth(20);
                            player.setGameMode(GameMode.SPECTATOR);
                            if(killer_entity instanceof Player killer) {
                                op.setKiller(op.InGame, player, killer);
                            }else {
                                Player killer = (Player)((Arrow) killer_entity).getShooter();
                                op.setKiller(op.InGame, player, killer);
                            }
                        }
                    }
                }else if(player.getHealth() <= event.getDamage()) {
                    // Mort par un mob
                    op.findPlayer(player).setDying(true);
                    op.findPlayer(player).death_location = player.getLocation();
                    DyingTimer task = new DyingTimer(op, op.findPlayer(player));
                    task.runTaskTimer(op, 0, 20);
                    event.setDamage(0);
                    player.setHealth(20);
                    player.setGameMode(GameMode.SPECTATOR);
                }
            }
        }
    }

    //**********************************************************************

    // N'importe quel dégat
    @EventHandler
    public void OnDamage(EntityDamageEvent event) {
        Entity victim = event.getEntity();
        EntityDamageEvent.DamageCause cause = event.getCause();

        if(victim instanceof Player player) {
            if(op.isState(State.CONFIG) || op.isState(State.STARTING) || op.isState(State.INVINCIBILITY))
                event.setCancelled(true); // Pas de dégats pris dans la phase de préparation ou de lancement ou d'invincibilité
            else {
                if((((cause.equals(DamageCause.FIRE) || cause.equals(DamageCause.FIRE_TICK) || cause.equals(DamageCause.LAVA)) && op.noFire) || (cause.equals(DamageCause.FALL)) && op.noFall))
                    event.setCancelled(true); // Explicite
                if(!cause.equals(DamageCause.ENTITY_ATTACK) && !cause.equals(DamageCause.PROJECTILE)) {
                    if(player.getHealth() <= event.getDamage()) {
                        // Check si le player est mort
                        if(!cause.equals(DamageCause.VOID)) {
                            op.findPlayer(player).setDying(true);
                            op.findPlayer(player).death_location = player.getLocation();
                            DyingTimer task = new DyingTimer(op, op.findPlayer(player));
                            task.runTaskTimer(op, 0, 20);
                            event.setDamage(0);
                            player.setHealth(20);
                            player.setGameMode(GameMode.SPECTATOR);
                        }
                    }
                }
            }
        }
    }
}
