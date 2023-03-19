package it.paneddo.openaimoderation.listeners;


import com.theokanning.openai.moderation.Moderation;
import com.theokanning.openai.moderation.ModerationCategoryScores;
import com.theokanning.openai.moderation.ModerationRequest;
import it.paneddo.openaimoderation.OpenaiModeration;
import it.paneddo.openaimoderation.utils.ModerationUtils;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@AllArgsConstructor
public class ChatListener implements Listener {

    private final OpenaiModeration plugin;

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChatMessage(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        ModerationRequest moderationRequest = ModerationRequest.builder().input(message).model("text-moderation-" + this.plugin.getConfigManager().getModelType()).build();

        // We don't want to block chat messages, so it is not necessary for us to hold up this event
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            Moderation result = this.plugin.getService().createModeration(moderationRequest).getResults().get(0); // First one is the only one returned
            ModerationCategoryScores categoryScores = result.getCategoryScores();

            StringBuilder flaggedCategories = new StringBuilder(), certainties = new StringBuilder();

            ModerationUtils.mapCategoryScores(categoryScores)
                    .forEach((category, certainty) -> {
                        if (certainty >= this.plugin.getConfigManager().getModerationThreshold(category)) {
                            if (flaggedCategories.length() > 0) {
                                flaggedCategories.append(", ");
                            }
                            if (certainties.length() > 0) {
                                certainties.append(", ");
                            }

                            flaggedCategories.append(category);
                            certainties.append(certainty * 100); // Scale to 1 -> 100
                        }
                    });

            // Only run commands if there are flagged categories
            if (flaggedCategories.length() > 0) {
                // Commands have to be dispatched synchronously
                Bukkit.getScheduler().runTask(this.plugin, () -> this.plugin.getConfigManager()
                        .getModerationActions(event.getPlayer().getName(), flaggedCategories.toString(), certainties.toString())
                        .forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)));
            }
        });
    }
}
