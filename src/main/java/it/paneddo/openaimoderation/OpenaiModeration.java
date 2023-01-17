package it.paneddo.openaimoderation;

import com.theokanning.openai.OpenAiService;
import it.paneddo.openaimoderation.config.ConfigManager;
import it.paneddo.openaimoderation.listeners.ChatListener;
import it.paneddo.openaimoderation.utils.ChatUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class OpenaiModeration extends JavaPlugin {

    @Getter
    private ConfigManager configManager;
    @Getter
    private OpenAiService service;

    @Override
    public synchronized void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        configManager = new ConfigManager(this);

        if (configManager.getOpenaiKey().equals("")) {
            this.getLogger().warning(ChatUtils.KEY_NOT_SET);
            this.setEnabled(false);
            return;
        }

        this.service = new OpenAiService(configManager.getOpenaiKey());
        this.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }
}