package fr.v1ct0r.onepiece_uhc.listeners;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.State;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Global implements Listener {
    // Constructeur
    private final OnePieceUHC op;
    public Global(OnePieceUHC op) {
        this.op = op;
    }

    //**********************************************************************

    // Désactiver le chat
    @EventHandler
    public void onChat(PlayerChatEvent event) {
        if(op.isState(State.DAY) || op.isState(State.NIGHT) || op.isState(State.INVINCIBILITY) || op.isState(State.STARTING)) {
            event.getPlayer().sendMessage("§4[LG] §cLe chat est désactivé pendant la partie !");
            event.setCancelled(true);
        }
    }

    //**********************************************************************

    // Empêcher la nourriture de descendre
    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        if(op.isState(State.CONFIG) || op.isState(State.STARTING) || op.isState(State.END) || op.noFood)
            event.setCancelled(true); // Pas de nourriture perdue dans la phase de préparation ou de lancement ou de fin ou si le scénario NoFood est actif.
    }

    //**********************************************************************

    // Diamond Limit
    @EventHandler
    public void onMine(BlockBreakEvent event) {
        Player player = event.getPlayer();
        fr.v1ct0r.onepiece_uhc.players.Player p = op.findPlayer(player);
        if(op.diamondLimit) {
            if(event.getBlock().getType().equals(Material.DIAMOND_ORE)) {
                if(p.getDiamondsMined() >= op.diamondMax) {
                    event.setCancelled(true);
                    player.sendMessage("§4[LG] §cVous avez atteint votre diamond limit (" + op.diamondMax + ") !");
                }
                else
                    p.setDiamondsMined(p.getDiamondsMined()+1);
            }
        }
    }

    //**********************************************************************

    // Empêcher la pluie
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if(event.toWeatherState())
            event.setCancelled(true);
    }
}
