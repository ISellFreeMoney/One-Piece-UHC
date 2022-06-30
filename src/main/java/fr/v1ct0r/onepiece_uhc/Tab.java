package fr.v1ct0r.onepiece_uhc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class Tab {
    static String head = """
              §c§m------------------------§r \s
              §r§4Loup Garou UHC  §r
            """;
    static String foot = """

            §3§oMade by CS_Sauvaj  §r
            §b§m------------------------§r \s""".indent(2);

    // Cette classe est trouvée sur internet, je comprends comment elle marche mais je l'aurais pas faite par moi même (pareil pour la ligne du catch en dessous).
    //****************************************************************************************************************************************************************
    public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }
    //****************************************************************************************************************************************************************

    public static void sendTablist(Player player, OnePieceUHC plugin, boolean colors) {
        try {
            Object header = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, "{'text': '" + head + "'}");
            Object footer = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, "{'text': '" + foot + "'}");
            Object packet = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[] { getNmsClass("IChatBaseComponent") }).newInstance(header);
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, footer);
            Object nmsp = player.getClass().getMethod("getHandle").invoke(player);
            Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
            pcon.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(pcon, packet);
        }catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException|ClassNotFoundException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
