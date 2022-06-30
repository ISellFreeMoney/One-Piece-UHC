package fr.v1ct0r.onepiece_uhc;

import fr.v1ct0r.onepiece_uhc.commands.Host;
import fr.v1ct0r.onepiece_uhc.commands.Roles;
import fr.v1ct0r.onepiece_uhc.effects.Effects;
import fr.v1ct0r.onepiece_uhc.listeners.Config;
import fr.v1ct0r.onepiece_uhc.listeners.Connexion;
import fr.v1ct0r.onepiece_uhc.listeners.Damage;
import fr.v1ct0r.onepiece_uhc.listeners.Global;
import fr.v1ct0r.onepiece_uhc.players.Player;
import fr.v1ct0r.onepiece_uhc.players.Role;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;


public class OnePieceUHC extends JavaPlugin {

    // VAR GLOBALE
    public boolean game_started;
    public boolean role_selected;

    // SCOREBOARD
    public static OnePieceUHC instance;
    public HashMap<org.bukkit.entity.Player, Scoreboard> scoreboard = new HashMap<>();
    public static OnePieceUHC getInstance(){
        return instance;
    }

    // PLAYERS
    public ArrayList<Player> Connected = new ArrayList<>();
    public ArrayList<Player> InGame = new ArrayList<>();
    public int maxPlayer = 25;
    public Player player_vote = null;

    // ROLES
    public Role[] RoleIngame;

    // UHC CONFIG
    public int pvp_time;
    public int pvp_time_s;
    public int border_time;
    public int border_time_s;
    public int border_size;
    public double border_speed;
    public int role_time ;
    public int role_time_s;
    public int cycle_jn;
    public int global_timer = 0;

    public World world;
    public WorldBorder border;


    // DEFAULT COMPO

    // Effects
    Effects effects = new Effects(this);

    // SCENARIO
    public boolean noFood = false;
    public boolean noFall = false;
    public boolean noFire = false;
    public boolean diamondLimit = true;
    public int diamondMax = 17;

    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        pvp_time = this.getConfig().getInt("pvp_time");
        pvp_time_s = this.getConfig().getInt("pvp_time_s");
        border_time = this.getConfig().getInt("border_time");
        border_time_s = this.getConfig().getInt("border_time_s");
        border_size = this.getConfig().getInt("border_size");
        border_speed = this.getConfig().getInt("border_speed");
        role_time = this.getConfig().getInt("role_time");
        role_time_s = this.getConfig().getInt("role_time_s");
        cycle_jn = this.getConfig().getInt("cycle_jn");
        instance = this;
        super.onEnable();
        game_started = false;
        role_selected = false;
        pvp = false;
        setState(State.CONFIG);

        // WORLD

        world = Bukkit.getWorld("world");
        border = world.getWorldBorder();
        border.setCenter(0,0);
        border.setSize(border_size*2);

        // LISTENER

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Global(this), this);
        pm.registerEvents(new Connexion(this), this);
        pm.registerEvents(new Config(this), this);
        pm.registerEvents(new Damage(this), this);

        //COMMANDES

        Host host = new Host(this);
        getCommand("host").setExecutor(host);
        getCommand("say").setExecutor(host);

        Roles roles = new Roles(this);
        getCommand("role").setExecutor(roles);
        getCommand("roles").setExecutor(roles);
        getCommand("claim").setExecutor(roles);
        getCommand("tir").setExecutor(roles);
        getCommand("sauver").setExecutor(roles);
        getCommand("proteger").setExecutor(roles);
        getCommand("voir").setExecutor(roles);
        //AJOUTER EN FONCTION DES ROLES

    }


    public void onDisable(){
        System.out.println("Plugin eteint");
    }

    // GAME STATE

    private State state;

    public void setState(State state){
        this.state = state;
    }
    public boolean isState(State state){
        return this.state == state;
    }

    private boolean pvp;
    public void setPvp(boolean on_off){
        pvp = on_off;
    }
    public boolean isPvp(){
        return pvp;
    }

    // PLAYER IN LIST
    public boolean contains(ArrayList<Player> list, org.bukkit.entity.Player player){
        for(Player p: list){
            if(p.getName().equalsIgnoreCase(player.getName()))
                return true;
        }
        return false;
    }

    // GET KILLER
    public void setKiller(ArrayList<Player> list, org.bukkit.entity.Player player, org.bukkit.entity.Player killer){
        for(Player p: list){
            if(p.getName().equalsIgnoreCase(player.getName()))
                p.setKiller(killer);
        }
    }

    // FIND PLAYER
    public Player findPlayer(org.bukkit.entity.Player player){
        Player pl = null;
        for(Player p: InGame){
            if(p.getName().equalsIgnoreCase(player.getName())) {
                p.setPlayer(player);
                pl = p;
            }
        }
        return pl;
    }

    public Player findPlayerFromUsername(String s){
        Player p = null;
        for(Player player: InGame){
            if(player.getName().equalsIgnoreCase(s))
                p = player;
        }
        return p;
    }

    // GET ROLE
    public Role getRole(ArrayList<Player> list, org.bukkit.entity.Player player) {
        Role role = Role.NONE;
        for(Player p: list){
            if(p.getName().equalsIgnoreCase(player.getName()))
                role = p.getRole();
        }
        return role;
    }

    public String getRoleString(Role role){

        // TODO: A FAIRE QUAND ON AURA DEFINIT LES ROLES

        return "default";
    }

    // ROLE COUNTER
    public void plusMinusRoles(int x, String name){
        // TODO: A FAIRE QUAND ON AURA DEFINIT LES ROLES
    }

    public int countRoles(){
        // TODO
        return 0;
    }

    // ROLE GESTION

    public void InitializeRoleIngame(){
        Arrays.fill(RoleIngame, Role.INIT);
    }

    public void setRoleIngame(){
        // TODO
    }

    // DElETE A RANDOM ROLE

    public void deleteRandomRole(int count){
        int role = RoleIngame.length;
        int ct = count;
        int random;
        while(ct > 0){
            random = (int) (Math.random() * role);
            if(RoleIngame[random] == Role.INIT){
                RoleIngame[random] = Role.NONE;
                ct--;
            }
        }
    }

    // ASSIGN ROLES
    public void assignRole(){
        int count = InGame.size();
        int count_role = countRoles();
        RoleIngame = new Role[count];
        InitializeRoleIngame();
        if(count_role > count)
            deleteRandomRole(count_role-count);
        setRoleIngame();
        int roles = count;
        int random;
        while(count > 0){
            random = (int) (Math.random() * roles);
            if(RoleIngame[random] != Role.NONE){
                InGame.get(count-1).setRole(RoleIngame[random]);
                RoleIngame[random] = Role.NONE;
                count--;
            }
        }
        for(Player player: InGame){
            printRole(true, player);
        }
        for(Player player: InGame){
            if(player.isMarine())
                ListeMarine(player.getPlayer());
        }

    }

    public void printRole(boolean first, Player player){

        org.bukkit.entity.Player p = player.getPlayer();
        // TODO QUAND ON AURA LES ROLES
    }

    public void ListeMarine(org.bukkit.entity.Player player){
        StringBuilder liste = new StringBuilder("§c ");
        for(Player p: InGame){
            if(p.isMarine())
                liste.append(p.getName()).append("   ");
        }
        player.sendMessage(liste.toString());
    }

    public void newMarine(Player player) {
        for(Player p: InGame){
            if(p.isMarine())
                p.getPlayer().sendMessage("§4[OP] §6" + player.getName() + "§9 a rejoint la Marine !");
        }
    }

    public void verifWin(){
        // TODO QUAND ON AURA LES ROLES
    }

    public void respawn(Player player){
        org.bukkit.entity.Player p = player.getPlayer();
        int size = (int) border.getSize()/2-50;
        int y = 200;
        while(p.getWorld().getBlockAt(size, y, size).getType() == Material.AIR)
            y--;
        p.teleport(new Location(p.getWorld(), size, y+5, size));
        p.setGameMode(GameMode.SURVIVAL);
        // TODO SI PARTICULARITE DE ROLE
        p.setHealth(20);
    }

    public void updateScoreBoard(){
        for(Entry<org.bukkit.entity.Player, Scoreboard> s: OnePieceUHC.getInstance().scoreboard.entrySet()){
            Scoreboard sb = s.getValue();
            sb.refresh();
        }
    }
}
