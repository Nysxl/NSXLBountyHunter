package com.internal.Nysxl.NSXLBountyHunter.LanguageManager;

import com.internal.Nysxl.NSXLBountyHunter.main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LanguageManager {

    private final Map<Language, String> messages = new HashMap<>();

    public static String parsePlaceHolder(String string, String placeholder, String replaceWith){
            if(!string.toLowerCase().contains(placeholder.toLowerCase())) return string;
            return string.replace(placeholder, replaceWith);
    }

    public void loadLanguage() {
        FileConfiguration config = main.getCfgM().getConfig("language.yml");
        Set<String> keys = config.getKeys(false);

        for (String key : keys) {
            try {
                Language languageKey = Language.valueOf(key);
                String message = config.getString(key);
                if (message != null) {
                    String coloredMessage = ChatColor.translateAlternateColorCodes('&', message);
                    messages.put(languageKey, coloredMessage);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Warning: Language key " + key + " is not defined in the Language enum.");
            }
        }
    }

    public String getMessage(Language key) {
        return messages.getOrDefault(key, "Message not defined for key: " + key);
    }
}
