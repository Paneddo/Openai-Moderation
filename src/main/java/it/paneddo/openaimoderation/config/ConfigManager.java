package it.paneddo.openaimoderation.config;

import it.paneddo.openaimoderation.OpenaiModeration;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.UnaryOperator;

@AllArgsConstructor
public class ConfigManager {

    private final OpenaiModeration plugin;

    public String getOpenaiKey() {
        return this.plugin.getConfig().getString("openai.key", "");
    }

    public String getModelType() {
        return this.plugin.getConfig().getString("openai.model", "stable");
    }

    public double getModerationThreshold(String category) {
        return this.plugin.getConfig().getDouble("moderation.category-thresholds." + category, 0.99);
    }

    public List<String> getModerationActions(String playerName, String categories, String certainties) {
        List<String> commandActions = this.plugin.getConfig().getStringList("moderation.command-actions");

        if (!commandActions.isEmpty()) {
            UnaryOperator<String> stringUnaryOperator = str -> str
                    .replaceAll("%player%", playerName)
                    .replaceAll("%category%", categories)
                    .replaceAll("%certainty%", certainties);

            commandActions.replaceAll(stringUnaryOperator);
        }

        return commandActions;
    }

}
