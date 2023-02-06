package it.paneddo.openaimoderation;

import com.theokanning.openai.OpenAiService;
import it.paneddo.openaimoderation.config.ConfigManager;
import it.paneddo.openaimoderation.listeners.ChatListener;
import it.paneddo.openaimoderation.utils.MessageKey;
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
        this.configManager = new ConfigManager(this);

        if (!this.configManager.getOpenaiKey().startsWith("sk-")) {
            this.getLogger().warning(MessageKey.KEY_NOT_SET);
            this.setEnabled(false);
            return;
        }

        this.service = new OpenAiService(this.configManager.getOpenaiKey());
        this.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }
}