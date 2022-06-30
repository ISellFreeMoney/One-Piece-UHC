package fr.v1ct0r.onepiece_uhc.timers;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.State;
import fr.v1ct0r.onepiece_uhc.effects.Effects;
import fr.v1ct0r.onepiece_uhc.players.Player;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class StartingTimer extends BukkitRunnable {
    static int count = 0;

    private int timer = 11;

    // Counstructeur
    private final OnePieceUHC op;
    private final String message;
    static Effects effects;
    public StartingTimer(OnePieceUHC op, String message) {
        this.op = op;
        this.message = message;
        effects = new Effects(op);
    }

    @Override
    public void run() {

        if(!op.isState(State.STARTING))
            this.cancel();
        else if(timer==0) {
            Bukkit.broadcastMessage("§a[UHC] §9" + message + " commence !");
            // Son de démarrage
            for(Player player : op.InGame) {
                player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.LEVEL_UP, 50, 20);
            }
            if(Objects.equals(message, "La téléportation des joueurs")) {
                // Setter de Ingame
                op.InGame.addAll(op.Connected);
                for(Player player : op.InGame) {
                    player.getPlayer().closeInventory();
                    player.getPlayer().getInventory().clear();
                    player.getPlayer().getEquipment().clear();
                    player.getPlayer().setExp(0);
                    player.getPlayer().setLevel(0);
                    player.setKiller(player.getPlayer());
                }
                op.updateScoreBoard();
                this.cancel();
                Teleportations(op);
            }
            else if(Objects.equals(message, "La partie")) {

                op.role_time_s = op.role_time*60;
                op.pvp_time_s = op.pvp_time*60;
                op.border_time_s = op.border_time*60;
                op.game_started = true;
                TeleportationsEnded(op);
                for(Player player : op.InGame) {
                    org.bukkit.entity.Player p = player.getPlayer();
                    p.setGameMode(GameMode.SURVIVAL);
                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    p.removePotionEffect(PotionEffectType.SPEED);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                    p.removePotionEffect(PotionEffectType.WEAKNESS);
                    p.removePotionEffect(PotionEffectType.SLOW);
                    p.removePotionEffect(PotionEffectType.JUMP);
                    // Starter
                    p.getInventory().addItem(new ItemStack(Material.BOOK));
                    p.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
                    if(!op.noFood)
                        p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF,64));
                }
                GlobalTimer task = new GlobalTimer(op);
                task.runTaskTimer(op, 0, 20);
            }
        }else {
            if (timer == 10 && Objects.equals(message, "La téléportation des joueurs")) {
                for(Player player : op.Connected)
                    player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.SUCCESSFUL_HIT, 50, 20);
            }
            if((timer<=5 || timer==10)) {
                for(Player player : op.InGame)
                    player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.SUCCESSFUL_HIT, 50, 20);
                if(timer == 1)
                    Bukkit.broadcastMessage("§a[UHC] §9" + message + " commence dans 1 seconde.");
                else
                    Bukkit.broadcastMessage("§a[UHC] §9" + message + " commence dans " + timer + " secondes.");
            }
        }
        timer--;
    }

    //*****************************************************************************

    static void Teleportations(OnePieceUHC op) {
        int current = 0;
        int time = 30; //temps (en tics) entre chaque tp
        int distance = op.border_size/4; //distance entre chaque joueurs
        int x = op.border_size - op.border_size/5;
        int z = x;
        for(Player p: op.InGame) {
            org.bukkit.entity.Player player = p.getPlayer();
            createPlateforms(current, op, player, x, 200, z);
            current+=time;
            if(x-distance>=distance-op.border_size)
                x-=distance;
            else {
                x = op.border_size - op.border_size/5;
                z-=distance*2;
            }
        }
    }

    static void createPlateforms(int current, OnePieceUHC op, org.bukkit.entity.Player player, int x, int y, int z) {
        Bukkit.getScheduler().runTaskLater(op, () -> {
            for(int i = x-3; i <= x+3; i++){
                for(int j = z-3; j <= z+3; j++)
                    player.getWorld().getBlockAt(i, 200, j).setType(Material.BARRIER);
            }
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            Bukkit.broadcastMessage("§a[UHC] §9Téléportation de §6" + player.getName() + "§9.");
            player.teleport(new Location(op.world, x, 205, z));
            player.addPotionEffect(effects.slowness);
            player.addPotionEffect(effects.jump_boost);
            count++;
            if(count == op.InGame.size()) {
                // Lancement du deuxième startingTimer
                StartingTimer task = new StartingTimer(op, "La partie");
                task.runTaskTimer(op, 0, 20);
            }
        },current);
    }

    //*****************************************************************************

    static void TeleportationsEnded(OnePieceUHC op) {
        int distance = op.border_size/5; //distance entre chaque joueurs
        int x = op.border_size - op.border_size/5;
        int z = x;
        for(Player player: op.InGame) {
            DeletePlateforms(op, player.getPlayer(), x, 200, z);
            x-=distance;
        }
    }

    static void DeletePlateforms(OnePieceUHC op, org.bukkit.entity.Player player, int x, int y, int z) {
        for(int i = x-3; i <= x+3; i++){
            for(int j = z-3; j <= z+3; j++)
                player.getWorld().getBlockAt(i, 200, j).setType(Material.AIR);
        }
    }
}
