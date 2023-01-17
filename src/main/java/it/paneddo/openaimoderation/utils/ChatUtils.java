package it.paneddo.openaimoderation.utils;

import org.bukkit.ChatColor;

public class ChatUtils {
    private ChatUtils() {
    }

    public static final String KEY_NOT_SET = "Openai Key not set in \"config.yml\". Openai Moderation plugin will not start ";
    private static final String PLUGIN_CHAT_PREFIX = format("&b[&c&lOpenai &bModeration&b] &r");
    public static final String MESSAGE_NOT_SENT = addPrefix("Your message was blocked as it is Violent");

    public static String addPrefix(String message) {
        return PLUGIN_CHAT_PREFIX + message;
    }

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
