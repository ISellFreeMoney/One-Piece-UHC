package fr.v1ct0r.onepiece_uhc.items;

import java.util.Arrays;
import java.util.List;

import fr.v1ct0r.onepiece_uhc.OnePieceUHC;
import fr.v1ct0r.onepiece_uhc.State;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class ItemsInv {

    // Constructeur
    OnePieceUHC op;
    public ItemsInv(OnePieceUHC op) {
        this.op = op;
        InitializeItems();
    }

    // Global
    public ItemStack config = new ItemStack(Material.NETHER_STAR);
    public ItemStack contours = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
    public ItemStack contours_role = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
    public ItemStack scenarios = new ItemStack(Material.BOOK);
    public ItemStack uhc = new ItemStack(Material.GOLDEN_APPLE);
    public ItemStack roles = new ItemStack(Material.BOOK_AND_QUILL);
    public ItemStack valider = new ItemStack(Material.SLIME_BALL);
    public ItemStack plus = new ItemStack(Material.EMERALD_BLOCK);
    public ItemStack moins = new ItemStack(Material.REDSTONE_BLOCK);
    public ItemStack plus1 = new ItemStack(Material.EMERALD);
    public ItemStack moins1 = new ItemStack(Material.REDSTONE);
    public ItemStack plus5 = new ItemStack(Material.EMERALD_ORE);
    public ItemStack moins5 = new ItemStack(Material.REDSTONE_ORE);
    public ItemStack plus10 = new ItemStack(Material.EMERALD_BLOCK);
    public ItemStack moins10 = new ItemStack(Material.REDSTONE_BLOCK);
    public ItemStack moins10s = new ItemStack(Material.REDSTONE);
    public ItemStack plus10s = new ItemStack(Material.EMERALD);
    public ItemStack moins50s = new ItemStack(Material.REDSTONE_ORE);
    public ItemStack plus50s = new ItemStack(Material.EMERALD_ORE);
    public ItemStack moins100s = new ItemStack(Material.REDSTONE_BLOCK);
    public ItemStack plus100s = new ItemStack(Material.EMERALD_BLOCK);

    // Démarrer/Stopper
    public ItemStack start = new ItemStack(Material.STAINED_GLASS, 1, (short) 5);
    public ItemStack starting = new ItemStack(Material.STAINED_GLASS, 1, (short) 1);

    // Scénarios
    public ItemStack noFood = new ItemStack(Material.COOKED_BEEF);
    public ItemStack noFoodOff = new ItemStack(Material.COOKED_BEEF);
    public ItemStack noFall = new ItemStack(Material.FEATHER);
    public ItemStack noFallOff = new ItemStack(Material.FEATHER);
    public ItemStack noFire = new ItemStack(Material.BLAZE_POWDER);
    public ItemStack noFireOff = new ItemStack(Material.BLAZE_POWDER);
    public ItemStack diamondLimit = new ItemStack(Material.DIAMOND);
    public ItemStack noDiamondLimit = new ItemStack(Material.DIAMOND);
    public ItemStack diamond = new ItemStack(Material.DIAMOND);

    // UHC
    public ItemStack pvp = new ItemStack(Material.IRON_SWORD);
    public ItemStack border = new ItemStack(Material.BARRIER);
    public ItemStack borderSize = new ItemStack(Material.EMPTY_MAP);
    public ItemStack border_speed = new ItemStack(Material.SUGAR);
    public ItemStack max_player = new ItemStack(Material.SKULL_ITEM);
    public ItemStack day_night = new ItemStack(Material.WATCH);
    public ItemStack role_time = new ItemStack(Material.BOOK_AND_QUILL);

    // Rôles


    // Claim


    //**********************************************************************

    public void InitializeItems() {

        //Item de config
        ItemMeta configM = config.getItemMeta();
        configM.setDisplayName("§6§oConfiguration");
        config.setItemMeta(configM);

        //Vitre de contours d'inventaire
        ItemMeta contoursM = contours.getItemMeta();
        contoursM.setDisplayName("§e§k42");
        contours.setItemMeta(contoursM);
        ItemMeta contours_roleM = contours_role.getItemMeta();
        contours_roleM.setDisplayName("§e§k24");
        contours_role.setItemMeta(contours_roleM);

        //Scénarios
        ItemMeta scenariosM = scenarios.getItemMeta();
        scenariosM.setDisplayName("§9Scénarios");
        scenarios.setItemMeta(scenariosM);

        //UHC
        ItemMeta uhcM = uhc.getItemMeta();
        uhcM.setDisplayName("§2UHC");
        uhc.setItemMeta(uhcM);

        //Rôles
        ItemMeta rolesM = roles.getItemMeta();
        rolesM.setDisplayName("§4Rôles");
        roles.setItemMeta(rolesM);

        //Vitre de start
        ItemMeta startM = start.getItemMeta();
        startM.setDisplayName("§aDémarrer");
        startM.addEnchant(Enchantment.DURABILITY, 1, true);
        startM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        start.setItemMeta(startM);
        //Vitre de stop
        ItemMeta startingM = starting.getItemMeta();
        startingM.setDisplayName("§6Démarrage en cours");
        startingM.addEnchant(Enchantment.DURABILITY, 1, true);
        startingM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        starting.setItemMeta(startingM);

        // Valider
        ItemMeta validerM = valider.getItemMeta();
        validerM.setDisplayName("§2Valider");
        validerM.addEnchant(Enchantment.DURABILITY, 1, true);
        validerM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        valider.setItemMeta(validerM);

        // Plus
        ItemMeta plusM = plus.getItemMeta();
        plusM.setDisplayName("§a+");
        plus.setItemMeta(plusM);
        // Moins
        ItemMeta moinsM = moins.getItemMeta();
        moinsM.setDisplayName("§c-");
        moins.setItemMeta(moinsM);
        // Plus
        ItemMeta plus1M = plus1.getItemMeta();
        plus1M.setDisplayName("§a+1");
        plus1.setItemMeta(plus1M);
        // Moins
        ItemMeta moins1M = moins1.getItemMeta();
        moins1M.setDisplayName("§c-1");
        moins1.setItemMeta(moins1M);
        // Plus
        ItemMeta plus5M = plus5.getItemMeta();
        plus5M.setDisplayName("§a+5");
        plus5.setItemMeta(plus5M);
        // Moins
        ItemMeta moins5M = moins5.getItemMeta();
        moins5M.setDisplayName("§c-5");
        moins5.setItemMeta(moins5M);
        // Plus
        ItemMeta plus10M = plus10.getItemMeta();
        plus10M.setDisplayName("§a+10");
        plus10.setItemMeta(plus10M);
        // Moins
        ItemMeta moins10M = moins10.getItemMeta();
        moins10M.setDisplayName("§c-10");
        moins10.setItemMeta(moins10M);
        // Plus
        ItemMeta plus10sM = plus10s.getItemMeta();
        plus10sM.setDisplayName("§a+10");
        plus10s.setItemMeta(plus10sM);
        // Moins
        ItemMeta moins10sM = moins10s.getItemMeta();
        moins10sM.setDisplayName("§c-10");
        moins10s.setItemMeta(moins10sM);
        // Plus
        ItemMeta plus50sM = plus50s.getItemMeta();
        plus50sM.setDisplayName("§a+50");
        plus50s.setItemMeta(plus50sM);
        // Moins
        ItemMeta moins50sM = moins50s.getItemMeta();
        moins50sM.setDisplayName("§c-50");
        moins50s.setItemMeta(moins50sM);
        // Plus
        ItemMeta plus100sM = plus100s.getItemMeta();
        plus100sM.setDisplayName("§a+100");
        plus100s.setItemMeta(plus100sM);
        // Moins
        ItemMeta moins100sM = moins100s.getItemMeta();
        moins100sM.setDisplayName("§c-100");
        moins100s.setItemMeta(moins100sM);

        //**********************************************************************
        // Scénarios

        // NoFood
        ItemMeta noFoodM = noFood.getItemMeta();
        noFoodM.setDisplayName("§9No food");
        noFoodM.setLore(List.of("§aActivé", "(Les joueurs n'auront pas", "besoin de nourriture)"));
        noFoodM.addEnchant(Enchantment.DURABILITY, 1, true);
        noFoodM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        noFood.setItemMeta(noFoodM);
        // NoFood off
        ItemMeta noFoodOffM = noFoodOff.getItemMeta();
        noFoodOffM.setDisplayName("§9No food");
        noFoodOffM.setLore(List.of("§cDésactivé"));
        noFoodOff.setItemMeta(noFoodOffM);

        // NoFall
        ItemMeta noFallM = noFall.getItemMeta();
        noFallM.setDisplayName("§9No fall");
        noFallM.setLore(List.of("§aActivé", "(Les joueurs ne prendront pas", "de dégats de chute)"));
        noFallM.addEnchant(Enchantment.DURABILITY, 1, true);
        noFallM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        noFall.setItemMeta(noFallM);
        // NoFall off
        ItemMeta noFallOffM = noFallOff.getItemMeta();
        noFallOffM.setDisplayName("§9No fall");
        noFallOffM.setLore(List.of("§cDésactivé"));
        noFallOff.setItemMeta(noFallOffM);

        // NoFire
        ItemMeta noFireM = noFire.getItemMeta();
        noFireM.setDisplayName("§9No fire");
        noFireM.setLore(List.of("§aActivé", "(Les joueurs ne prendront pas", "de dégats de feu)"));
        noFireM.addEnchant(Enchantment.DURABILITY, 1, true);
        noFireM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        noFire.setItemMeta(noFireM);
        // NoFire off
        ItemMeta noFireOffM = noFireOff.getItemMeta();
        noFireOffM.setDisplayName("§9No fire");
        noFireOffM.setLore(List.of("§cDésactivé"));
        noFireOff.setItemMeta(noFireOffM);

        // DiamondLimit
        ItemMeta diamondLimitM = diamondLimit.getItemMeta();
        diamondLimitM.setDisplayName("§9Diamond Limit");
        diamondLimitM.setLore(List.of("§aActivé", "(Les joueurs ne pourront miner", "que " + op.diamondMax + " diamants)"));
        diamondLimitM.addEnchant(Enchantment.DURABILITY, 1, true);
        diamondLimitM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        diamondLimit.setItemMeta(diamondLimitM);
        // NoDiamondLimit
        ItemMeta noDiamondLimitM = noDiamondLimit.getItemMeta();
        noDiamondLimitM.setDisplayName("§9Diamond Limit");
        noDiamondLimitM.setLore(List.of("§cDésactivé"));
        noDiamondLimit.setItemMeta(noDiamondLimitM);
        // Diamond
        ItemMeta diamondM = diamond.getItemMeta();
        diamondM.setDisplayName("§9Diamond Limit");
        diamondM.setLore(List.of("§aActivé : " + op.diamondMax));
        diamond.setItemMeta(diamondM);

        //**********************************************************************
        // UHC
        // PVP
        ItemMeta pvpM = pvp.getItemMeta();
        pvpM.setDisplayName("§4PVP");
        pvpM.setLore(List.of("§9Activé à " + op.pvp_time + " minute(s)"));
        pvp.setItemMeta(pvpM);
        // Bordure
        ItemMeta borderM = border.getItemMeta();
        borderM.setDisplayName("§8Bordure");
        borderM.setLore(List.of("§9Activée à " + op.border_time + " minute(s)"));
        border.setItemMeta(borderM);
        // Taille de la bordure
        ItemMeta borderSizeM = borderSize.getItemMeta();
        borderSizeM.setDisplayName("§9Taille de la bordure");
        borderSizeM.setLore(List.of("§9Actuellement : " + op.border_size + "/" + op.border_size));
        borderSize.setItemMeta(borderSizeM);
        // Vitesse de la bordure
        ItemMeta border_speedM = border_speed.getItemMeta();
        border_speedM.setDisplayName("§9Vitesse de la bordure");
        border_speedM.setLore(List.of("§9" + op.border_speed + " bloc(s) par seconde"));
        border_speed.setItemMeta(border_speedM);
        // Nombre de joueur max
        ItemMeta max_playerM = max_player.getItemMeta();
        max_playerM.setDisplayName("§eNombre de Joueurs Maximum");
        max_playerM.setLore(List.of("§9Actuellement " + op.maxPlayer + " joueur(s) maximum"));
        max_player.setItemMeta(max_playerM);
        // Cycle Jour/Nuit
        ItemMeta day_nightM = day_night.getItemMeta();
        day_nightM.setDisplayName("§aCycle Jour/Nuit");
        day_nightM.setLore(List.of("§9Actuellement : " + op.cycle_jn + " minutes", "§9pour un jour ou une nuit"));
        day_night.setItemMeta(day_nightM);
        // Rôles
        ItemMeta role_timeM = role_time.getItemMeta();
        role_timeM.setDisplayName("§4Rôles");
        role_timeM.setLore(List.of("§9Assignation à " + op.role_time + " minute(s)"));
        role_time.setItemMeta(role_timeM);

        //**********************************************************************
        // Rôles


        //**********************************************************************
        // Claim


    }

    //**********************************************************************

    public Inventory InvConfig(Player player) {
        int size = 45;
        Inventory inv_config = Bukkit.createInventory(player, size, "§6§oConfiguration");
        //************************************
        inv_config.setItem((size+1)/2-3, scenarios);
        inv_config.setItem((size+1)/2-1, uhc);
        inv_config.setItem((size+1)/2+1, roles);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_config.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_config.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_config.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_config.setItem(i, contours);
        //************************************
        if(op.isState(State.STARTING))
            inv_config.setItem(size-5, starting);
        else
            inv_config.setItem(size-5, start);
        return inv_config;
    }

    //**********************************************************************

    public Inventory InvScenarios(Player player) {
        int size = 45;
        Inventory inv_scenarios = Bukkit.createInventory(player, size, "§9§oScénarios");

        if(op.noFood) inv_scenarios.setItem(10, noFood); 				else inv_scenarios.setItem(10, noFoodOff);
        if(op.noFall) inv_scenarios.setItem(11, noFall);		 		else inv_scenarios.setItem(11, noFallOff);
        if(op.noFire) inv_scenarios.setItem(12, noFire); 				else inv_scenarios.setItem(12, noFireOff);
        if(op.diamondLimit) inv_scenarios.setItem(13, diamondLimit); 	else inv_scenarios.setItem(13, noDiamondLimit);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_scenarios.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_scenarios.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_scenarios.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_scenarios.setItem(i, contours);
        //************************************
        inv_scenarios.setItem(size-5, valider);
        return inv_scenarios;
    }

    //**********************************************************************

    public Inventory InvDiamondLimit(Player player) {
        int size = 45;
        Inventory inv_diamond_limit = Bukkit.createInventory(player, size, "§9§oDiamond Limit");

        inv_diamond_limit.setItem((size+1)/2-3, moins);
        inv_diamond_limit.setItem((size+1)/2-1, diamond);
        inv_diamond_limit.setItem((size+1)/2+1, plus);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_diamond_limit.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_diamond_limit.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_diamond_limit.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_diamond_limit.setItem(i, contours);
        //************************************
        inv_diamond_limit.setItem(size-5, valider);
        return inv_diamond_limit;
    }

    public ItemStack ActualizeDiamondLimit(int i) {
        if(op.diamondMax + i >= 0)
            op.diamondMax += i;
        ItemMeta diamondM = diamond.getItemMeta();
        diamondM.setDisplayName("§9Diamond Limit");
        diamondM.setLore(List.of("§aActivé : " + op.diamondMax));
        diamond.setItemMeta(diamondM);
        ItemMeta diamondLimitM = diamondLimit.getItemMeta();
        diamondLimitM.setLore(List.of("§aActivé", "(Les joueurs ne pourront miner", "que " + op.diamondMax + " diamants)"));
        diamondLimit.setItemMeta(diamondLimitM);
        return diamond;
    }

    //**********************************************************************

    public Inventory InvUHC(Player player) {
        int size = 45;
        Inventory inv_uhc = Bukkit.createInventory(player, size, "§2§oUHC");
        inv_uhc.setItem((size+1)/2-3, pvp);
        inv_uhc.setItem((size+1)/2-2, border);
        inv_uhc.setItem((size+1)/2-1, max_player);
        inv_uhc.setItem((size+1)/2, day_night);
        inv_uhc.setItem((size+1)/2+1, role_time);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_uhc.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_uhc.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_uhc.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_uhc.setItem(i, contours);
        //************************************
        inv_uhc.setItem(size-5, valider);
        return inv_uhc;
    }

    //**********************************************************************

    public Inventory InvPVP(Player player) {
        int size = 45;
        Inventory inv_uhc = Bukkit.createInventory(player, size, "§4§oPVP");
        inv_uhc.setItem((size+1)/2-4, moins10);
        inv_uhc.setItem((size+1)/2-3, moins5);
        inv_uhc.setItem((size+1)/2-2, moins1);
        inv_uhc.setItem((size+1)/2-1, pvp);
        inv_uhc.setItem((size+1)/2, plus1);
        inv_uhc.setItem((size+1)/2+1, plus5);
        inv_uhc.setItem((size+1)/2+2, plus10);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_uhc.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_uhc.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_uhc.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_uhc.setItem(i, contours);
        //************************************
        inv_uhc.setItem(size-5, valider);
        return inv_uhc;
    }

    public ItemStack ActualizePVPTime(int i) {
        if(op.pvp_time + i >= 1)
            op.pvp_time += i;
        op.pvp_time_s = op.pvp_time*60;
        ItemMeta pvpM = pvp.getItemMeta();
        pvpM.setLore(List.of("§9Activé à " + op.pvp_time + " minute(s)"));
        pvp.setItemMeta(pvpM);
        return pvp;
    }

    //**********************************************************************

    public Inventory InvBorder(Player player) {
        int size = 45;
        Inventory inv_border = Bukkit.createInventory(player, size, "§8§oBordure");
        inv_border.setItem((size+1)/2-13, moins10);
        inv_border.setItem((size+1)/2-12, moins5);
        inv_border.setItem((size+1)/2-11, moins1);
        inv_border.setItem((size+1)/2-10, border);
        inv_border.setItem((size+1)/2-9, plus1);
        inv_border.setItem((size+1)/2-8, plus5);
        inv_border.setItem((size+1)/2-7, plus10);
        inv_border.setItem((size+1)/2-4, moins100s);
        inv_border.setItem((size+1)/2-3, moins50s);
        inv_border.setItem((size+1)/2-2, moins10s);
        inv_border.setItem((size+1)/2-1, borderSize);
        inv_border.setItem((size+1)/2, plus10s);
        inv_border.setItem((size+1)/2+1, plus50s);
        inv_border.setItem((size+1)/2+2, plus100s);
        inv_border.setItem((size+1)/2+6, moins);
        inv_border.setItem((size+1)/2+8, border_speed);
        inv_border.setItem((size+1)/2+10, plus);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_border.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_border.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_border.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_border.setItem(i, contours);
        //************************************
        inv_border.setItem(size-5, valider);
        return inv_border;
    }

    public ItemStack ActualizeBorderTime(int i) {
        if(op.border_time + i >= 1)
            op.border_time += i;
        op.border_time_s = op.border_time*60;
        ItemMeta borderM = border.getItemMeta();
        borderM.setLore(List.of("§9Activée à " + op.border_time + " minute(s)"));
        border.setItemMeta(borderM);
        return border;
    }

    public ItemStack ActualizeBorderSize(int i) {
        if(op.border_size + i >= 300)
            op.border_size += i;
        ItemMeta borderSizeM = borderSize.getItemMeta();
        borderSizeM.setLore(List.of("§9Actuellement : " + op.border_size + "/" + op.border_size));
        borderSize.setItemMeta(borderSizeM);
        return borderSize;
    }

    public ItemStack ActualizeBorderSpeed(double i) {
        if(op.border_speed + i >= 0.5)
            op.border_speed += i;
        ItemMeta border_speedM = border_speed.getItemMeta();
        border_speedM.setLore(List.of("§9" + op.border_speed + " bloc(s) par seconde"));
        border_speed.setItemMeta(border_speedM);
        return border_speed;
    }

    //**********************************************************************

    public Inventory InvMaxPlayer(Player player) {
        int size = 45;
        Inventory inv_max_player = Bukkit.createInventory(player, size, "§e§oJoueurs Maximum");
        inv_max_player.setItem((size+1)/2-3, moins);
        inv_max_player.setItem((size+1)/2-1, max_player);
        inv_max_player.setItem((size+1)/2+1, plus);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_max_player.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_max_player.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_max_player.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_max_player.setItem(i, contours);
        //************************************
        inv_max_player.setItem(size-5, valider);
        return inv_max_player;
    }

    public ItemStack ActualizeMaxPlayer(int i) {
        if(op.maxPlayer + i >= 5)
            op.maxPlayer += i;
        ItemMeta max_playerM = max_player.getItemMeta();
        max_playerM.setLore(List.of("§9Actuellement " + op.maxPlayer + " joueur(s) maximum"));
        max_player.setItemMeta(max_playerM);
        return max_player;
    }

    //**********************************************************************

    public Inventory InvDayNight(Player player) {
        int size = 45;
        Inventory inv_day_night = Bukkit.createInventory(player, size, "§a§oCycle Jour/Nuit");
        inv_day_night.setItem((size+1)/2-3, moins);
        inv_day_night.setItem((size+1)/2-1, day_night);
        inv_day_night.setItem((size+1)/2+1, plus);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_day_night.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_day_night.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_day_night.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_day_night.setItem(i, contours);
        //************************************
        inv_day_night.setItem(size-5, valider);
        return inv_day_night;
    }

    public ItemStack ActualizeDayNight(int i) {
        if(op.cycle_jn + i >= 3)
            op.cycle_jn += i;
        ItemMeta day_nightM = day_night.getItemMeta();
        day_nightM.setLore(List.of("§9Actuellement : " + op.cycle_jn + " minutes", "§9pour un jour ou une nuit"));
        day_night.setItemMeta(day_nightM);
        return day_night;
    }

    //**********************************************************************

    public Inventory InvRolesTime(Player player) {
        int size = 45;
        Inventory inv_uhc = Bukkit.createInventory(player, size, "§4§oTemps des rôles");
        inv_uhc.setItem((size+1)/2-4, moins10);
        inv_uhc.setItem((size+1)/2-3, moins5);
        inv_uhc.setItem((size+1)/2-2, moins1);
        inv_uhc.setItem((size+1)/2-1, role_time);
        inv_uhc.setItem((size+1)/2, plus1);
        inv_uhc.setItem((size+1)/2+1, plus5);
        inv_uhc.setItem((size+1)/2+2, plus10);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_uhc.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_uhc.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_uhc.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_uhc.setItem(i, contours);
        //************************************
        inv_uhc.setItem(size-5, valider);
        return inv_uhc;
    }

    public ItemStack ActualizeRolesTime(int i) {
        if(op.role_time + i >= 1)
            op.role_time += i;
        op.role_time_s = op.role_time*60;
        ItemMeta role_timeM = role_time.getItemMeta();
        role_timeM.setLore(List.of("§9Assignation à " + op.role_time + " minute(s)"));
        role_time.setItemMeta(role_timeM);
        return role_time;
    }

    //**********************************************************************

    public Inventory InvAllRoles(Player player) {
        reloadRoles("");
        int size = 45;
        Inventory inv_roles = Bukkit.createInventory(player, size, "§4§oRôles");
        //************************************

        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_roles.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_roles.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_roles.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_roles.setItem(i, contours);
        //************************************
        inv_roles.setItem(size-5, valider);
        return inv_roles;
    }

    //**********************************************************************

    public Inventory InvRole(Player player, ItemStack item) {
        int size = 45;
        Inventory inv_roles = Bukkit.createInventory(player, size, item.getItemMeta().getDisplayName());
        inv_roles.setItem((size+1)/2-3, moins);
        inv_roles.setItem((size+1)/2-1, item);
        inv_roles.setItem((size+1)/2+1, plus);
        //************************************
        //Contours
        for(int i = 0; i < 9; i ++)
            inv_roles.setItem(i, contours);
        for(int i = 8; i < size; i+=9)
            inv_roles.setItem(i, contours);
        for(int i = 9; i < size; i+=9)
            inv_roles.setItem(i, contours);
        for(int i = size-9; i < size; i ++)
            inv_roles.setItem(i, contours);
        //************************************
        inv_roles.setItem(size-5, valider);
        inv_roles.setItem(4, contours_role);
        return inv_roles;
    }

    //**********************************************************************

    public ItemStack reloadRoles(String role) {

        return null;
    }

    //**********************************************************************

    public void clearArmor(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    //**********************************************************************

    public ItemStack addBookEnchantment(ItemStack item, Enchantment enchant, int level){

        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchant, level, true);
        item.setItemMeta(meta);
        return item;
    }

}
