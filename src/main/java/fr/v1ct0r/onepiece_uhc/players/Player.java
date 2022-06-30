package fr.v1ct0r.onepiece_uhc.players;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import org.bukkit.Location;

public class Player {

    private org.bukkit.entity.Player player;
    private OnePieceUHC op;
    public String name;
    public org.bukkit.entity.Player killer;
    public Role role;
    public boolean is_marine;
    public boolean dying;
    public boolean dead;
    public boolean claimed;
    public int diamonds_mined;
    public boolean can_vote;
    public int nb_vote;

    public Player(org.bukkit.entity.Player player, OnePieceUHC op){
        this.op = op;
        this.player = player;
        name = player.getName();
        killer = null;
        role = Role.NONE;
        is_marine = false;
        dying = false;
        dead = false;
        claimed = false;
        // TODO QUAND ON AURA LES ROLES
        diamonds_mined = 0;
        can_vote = false;
        nb_vote = 0;
    }

    public org.bukkit.entity.Player getPlayer() {
        return player;
    }

    public void setPlayer(org.bukkit.entity.Player player) {
        this.player = player;
    }

    public org.bukkit.entity.Player getKiller() {
        return killer;
    }

    public void setKiller(org.bukkit.entity.Player killer) {
        this.killer = killer;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isMarine() {
        return is_marine;
    }

    public void setMarine(boolean is_marine) {
        this.is_marine = is_marine;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    public int getDiamondsMined() {
        return diamonds_mined;
    }

    public void setDiamondsMined(int diamonds_mined) {
        this.diamonds_mined = diamonds_mined;
    }

    public boolean canVote() {
        return can_vote;
    }

    public void setCanVote(boolean can_vote) {
        this.can_vote = can_vote;
    }

    public int getVote() {
        return nb_vote;
    }

    public void setVote(int nb_vote) {
        this.nb_vote = nb_vote;
    }

    public String getName() {
        return name;
    }

    public Location death_location;
}
