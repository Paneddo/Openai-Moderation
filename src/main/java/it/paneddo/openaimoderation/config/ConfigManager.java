package it.paneddo.openaimoderation.config;

import it.paneddo.openaimoderation.OpenaiModeration;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfigManager {

    private final OpenaiModeration plugin;

    public String getOpenaiKey() {
        return plugin.getConfig().getString("openai.key", "");
    }

    public String getModelType() {
        return plugin.getConfig().getString("openai.model", "latest");
    }

}
