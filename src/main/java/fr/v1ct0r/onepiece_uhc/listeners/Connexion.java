package fr.v1ct0r.onepiece_uhc.listeners;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.Scoreboard;
import fr.v1ct0r.onepiece_uhc.State;
import fr.v1ct0r.onepiece_uhc.Tab;
import fr.v1ct0r.onepiece_uhc.items.ItemsInv;
import fr.v1ct0r.onepiece_uhc.players.Player;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class Connexion implements Listener {
    // Constructeur :
    private final OnePieceUHC op;
    private final ItemsInv items_inv;
    public Connexion(OnePieceUHC op) {
        this.op = op;
        items_inv = new ItemsInv(op);
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {

        org.bukkit.entity.Player p = event.getPlayer();
        for (org.bukkit.entity.Player joinplayer : Bukkit.getOnlinePlayers()) {
            Tab.sendTablist(joinplayer, op, false);
        }

        if(op.contains(op.InGame, p))
            op.Connected.add(op.findPlayer(p));
        else {
            Player player = new Player(p, op);
            op.Connected.add(player);
        }

        // Check du moment de la connexion
        if(op.isState(State.CONFIG)) {
            event.setJoinMessage("§a[UHC] §b"+ p.getName()+" vient de se connecter ! §a(" + op.Connected.size() + "/" + op.maxPlayer + ")");
            p.setGameMode(GameMode.ADVENTURE);
            p.setMaxHealth(20); // Cas du LGB
            p.setHealth(20); // 20 = full life
            p.setFoodLevel(20); // 20 = full food
            p.setLevel(0); p.setExp(0);
            p.getInventory().clear();
            items_inv.clearArmor(p);
        }else if(op.contains(op.InGame, p))
            event.setJoinMessage("§a[UHC] §b"+ p.getName()+" vient de se reconnecter !");
        else
            event.setJoinMessage("");

        //**********************************************************************
        // Scoreboard

        Scoreboard sb = new Scoreboard(p);
        sb.sendLine();
        sb.set();
        op.updateScoreBoard();

    }

    //***********************************************************************

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        org.bukkit.entity.Player p = event.getPlayer();
        String name = p.getName();
        for(int i = 0; i < op.Connected.size(); i++) {
            if(Objects.equals(op.Connected.get(i).getName(), name))
                op.Connected.remove(i);
        }
        if(!op.game_started)
            event.setQuitMessage("§a[UHC] §b"+ p.getName()+" vient de se déconnecter ! §a(" + op.Connected.size() + "/" + op.maxPlayer + ")");
        else if(op.contains(op.InGame, p))
            event.setQuitMessage("§a[UHC] §b"+ p.getName()+" vient de se déconnecter ! Il peut encore se reconnecter.");
        else
            event.setQuitMessage("");

        //Scoreboard
        op.updateScoreBoard();
    }
}
