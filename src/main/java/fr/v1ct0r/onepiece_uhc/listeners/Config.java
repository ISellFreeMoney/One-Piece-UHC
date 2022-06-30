package fr.v1ct0r.onepiece_uhc.listeners;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.State;
import fr.v1ct0r.onepiece_uhc.items.ItemsInv;
import fr.v1ct0r.onepiece_uhc.timers.StartingTimer;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Config implements Listener {

    private final OnePieceUHC op;
    private final ItemsInv items_inv;
    public Config(OnePieceUHC op) {
        this.op = op;
        items_inv = new ItemsInv(op);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(player.getItemInHand().isSimilar(items_inv.config))
                player.openInventory(items_inv.InvConfig(player));
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getInventory().getName().equalsIgnoreCase("§6§oConfiguration")){
            if(e.getCurrentItem().isSimilar(items_inv.start)){
                if(op.countRoles() == op.Connected.size()){
                    op.setState(State.STARTING);
                    player.closeInventory();
                    player.openInventory(items_inv.InvConfig(player));
                    StartingTimer task = new StartingTimer(op, "La teleportion de joueurs");
                    task.runTaskTimer(op,0,20);
                }else {
                    player.sendMessage("§c[OP] Le nombre de rôles ne correspond pas au nombre de joueurs !");
                }
            }else if(e.getCurrentItem().isSimilar(items_inv.starting)){
                op.setState(State.CONFIG);
                //Arréter de démarrer la partie
                player.closeInventory();
                player.openInventory(items_inv.InvConfig(player));
            }else if(e.getCurrentItem().isSimilar(items_inv.scenarios)) {
                player.closeInventory();
                player.openInventory(items_inv.InvScenarios(player));
            }else if(e.getCurrentItem().isSimilar(items_inv.uhc)) {
                player.closeInventory();
                player.openInventory(items_inv.InvUHC(player));
            }else if(e.getCurrentItem().isSimilar(items_inv.roles)) {
                player.closeInventory();
                player.openInventory(items_inv.InvAllRoles(player));
            }
            e.setCancelled(true);
        }else if(e.getInventory().getName().equalsIgnoreCase("§9§oScénarios")){

            //**********************************************************************
            // Scénarios
            if(e.getCurrentItem().isSimilar(items_inv.noFood)) {
                e.getInventory().setItem(10, items_inv.noFoodOff);
                op.noFood = false;
            }else if(e.getCurrentItem().isSimilar(items_inv.noFoodOff)) {
                e.getInventory().setItem(10, items_inv.noFood);
                op.noFood = true;
            }else if(e.getCurrentItem().isSimilar(items_inv.noFall)) {
                e.getInventory().setItem(11, items_inv.noFallOff);
                op.noFall = false;
            }else if(e.getCurrentItem().isSimilar(items_inv.noFallOff)) {
                e.getInventory().setItem(11, items_inv.noFall);
                op.noFall = true;
            }else if(e.getCurrentItem().isSimilar(items_inv.noFire)) {
                e.getInventory().setItem(12, items_inv.noFireOff);
                op.noFire = false;
            }else if(e.getCurrentItem().isSimilar(items_inv.noFireOff)) {
                e.getInventory().setItem(12, items_inv.noFire);
                op.noFire = true;
            }else if(e.getCurrentItem().isSimilar(items_inv.diamondLimit)) {
                e.getInventory().setItem(13, items_inv.noDiamondLimit);
                op.diamondLimit = false;
            }else if(e.getCurrentItem().isSimilar(items_inv.noDiamondLimit)) {
                player.closeInventory();
                op.diamondLimit = true;
                player.openInventory(items_inv.InvDiamondLimit(player));
            }
            //**********************************************************************
            if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvConfig(player));
            }
            e.setCancelled(true);
            //**********************************************************************
        }else if(e.getInventory().getName().equalsIgnoreCase("§9§oDiamond Limit")){
            //**********************************************************************
            // Diamond Limit
            if(e.getCurrentItem().isSimilar(items_inv.plus))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeDiamondLimit(1));
            else if(e.getCurrentItem().isSimilar(items_inv.moins))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeDiamondLimit(-1));
                //**********************************************************************
            else if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvScenarios(player));
            }
            e.setCancelled(true);
        }else if(e.getInventory().getName().equalsIgnoreCase("§2§oUHC")){
            //**********************************************************************
            // UHC
            if(e.getCurrentItem().isSimilar(items_inv.pvp)) {
                player.closeInventory();
                player.openInventory(items_inv.InvPVP(player));
            }else if(e.getCurrentItem().isSimilar(items_inv.border)) {
                player.closeInventory();
                player.openInventory(items_inv.InvBorder(player));
            }else if(e.getCurrentItem().isSimilar(items_inv.max_player)) {
                player.closeInventory();
                player.openInventory(items_inv.InvMaxPlayer(player));
            }else if(e.getCurrentItem().isSimilar(items_inv.day_night)) {
                player.closeInventory();
                player.openInventory(items_inv.InvDayNight(player));
            }else if(e.getCurrentItem().isSimilar(items_inv.role_time)) {
                player.closeInventory();
                player.openInventory(items_inv.InvRolesTime(player));
            }
            //**********************************************************************
            if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvConfig(player));
            }
            e.setCancelled(true);
            //**********************************************************************
        }else if(e.getInventory().getName().equalsIgnoreCase("§4§oPVP")){
            //**********************************************************************
            // PVP
            if(e.getCurrentItem().isSimilar(items_inv.plus1))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(1));
            else if(e.getCurrentItem().isSimilar(items_inv.moins1))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(-1));
            else if(e.getCurrentItem().isSimilar(items_inv.plus5))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(5));
            else if(e.getCurrentItem().isSimilar(items_inv.moins5))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(-5));
            else if(e.getCurrentItem().isSimilar(items_inv.plus10))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(10));
            else if(e.getCurrentItem().isSimilar(items_inv.moins10))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(-10));
            //**********************************************************************
            if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvUHC(player));
            }
            op.updateScoreBoard();
            e.setCancelled(true);
            //**********************************************************************
        }else if(e.getInventory().getName().equalsIgnoreCase("§8§oBordure")){
            //**********************************************************************
            // Bordure
            if(e.getCurrentItem().isSimilar(items_inv.plus1))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(1));
            else if(e.getCurrentItem().isSimilar(items_inv.moins1))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(-1));
            else if(e.getCurrentItem().isSimilar(items_inv.plus5))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(5));
            else if(e.getCurrentItem().isSimilar(items_inv.moins5))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(-5));
            else if(e.getCurrentItem().isSimilar(items_inv.plus10))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(10));
            else if(e.getCurrentItem().isSimilar(items_inv.moins10))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(-10));
            else if(e.getCurrentItem().isSimilar(items_inv.plus10s))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(10));
            else if(e.getCurrentItem().isSimilar(items_inv.moins10s))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(-10));
            else if(e.getCurrentItem().isSimilar(items_inv.plus50s))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(50));
            else if(e.getCurrentItem().isSimilar(items_inv.moins50s))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(-50));
            else if(e.getCurrentItem().isSimilar(items_inv.plus100s))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(100));
            else if(e.getCurrentItem().isSimilar(items_inv.moins100s))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(-100));
            else if(e.getCurrentItem().isSimilar(items_inv.plus))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2+8, items_inv.ActualizeBorderSpeed(0.5));
            else if(e.getCurrentItem().isSimilar(items_inv.moins))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2+8, items_inv.ActualizeBorderSpeed(-0.5));
            //**********************************************************************
            if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvUHC(player));
            }
            op.border.setSize(op.border_size*2);
            op.updateScoreBoard();
            e.setCancelled(true);
            //**********************************************************************
        }else if(e.getInventory().getName().equalsIgnoreCase("§e§oJoueurs Maximum")){

            if(e.getCurrentItem().isSimilar(items_inv.plus))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeMaxPlayer(1));
            else if(e.getCurrentItem().isSimilar(items_inv.moins))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeMaxPlayer(-1));
            //**********************************************************************
            if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvUHC(player));
            }
            op.updateScoreBoard();
            e.setCancelled(true);
            //**********************************************************************
        }else if(e.getInventory().getName().equalsIgnoreCase("§a§oCycle Jour/Nuit")){

            if(e.getCurrentItem().isSimilar(items_inv.plus))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeDayNight(1));
            else if(e.getCurrentItem().isSimilar(items_inv.moins))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeDayNight(-1));
            //**********************************************************************
            if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvUHC(player));
            }
            e.setCancelled(true);
            //**********************************************************************
        }else if(e.getInventory().getName().equalsIgnoreCase("§4§oTemps des rôles")){
            //**********************************************************************
            // Temps des rôles
            if(e.getCurrentItem().isSimilar(items_inv.plus1))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(1));
            else if(e.getCurrentItem().isSimilar(items_inv.moins1))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(-1));
            else if(e.getCurrentItem().isSimilar(items_inv.plus5))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(5));
            else if(e.getCurrentItem().isSimilar(items_inv.moins5))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(-5));
            else if(e.getCurrentItem().isSimilar(items_inv.plus10))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(10));
            else if(e.getCurrentItem().isSimilar(items_inv.moins10))
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(-10));
            //**********************************************************************
            if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvUHC(player));
            }
            op.updateScoreBoard();
            e.setCancelled(true);
            //**********************************************************************
        }else if(e.getInventory().getName().equalsIgnoreCase("§4§oRôles")){

            if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvConfig(player));
            }else if(!e.getCurrentItem().isSimilar(items_inv.contours)) {
                player.closeInventory();
                player.openInventory(items_inv.InvRole(player, e.getCurrentItem()));
            }
            e.setCancelled(true);
            //**********************************************************************
        }else if(e.getInventory().getItem(4) != null && e.getInventory().getItem(4).isSimilar(items_inv.contours_role)) {

            if(e.getCurrentItem().isSimilar(items_inv.valider)) {
                player.closeInventory();
                player.openInventory(items_inv.InvAllRoles(player));
            }else if(e.getCurrentItem().isSimilar(items_inv.plus)) {
                if(op.countRoles() < op.maxPlayer)
                    op.plusMinusRoles(1, e.getInventory().getName());
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.reloadRoles(e.getInventory().getName()));
            }else if(e.getCurrentItem().isSimilar(items_inv.moins)) {
                op.plusMinusRoles(-1, e.getInventory().getName());
                e.getInventory().setItem((e.getInventory().getSize()+1)/2-1, items_inv.reloadRoles(e.getInventory().getName()));
            }
            e.setCancelled(true);
            //**********************************************************************
        }
    }

    //**********************************************************************

    // Empécher le host de drop son item de configuration
    @EventHandler
    public void onItemDrop (PlayerDropItemEvent e) {
        if(e.getItemDrop().getItemStack().isSimilar(items_inv.config))
            e.setCancelled(true);
    }
}