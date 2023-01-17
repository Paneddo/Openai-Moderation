package it.paneddo.openaimoderation.listeners;


import com.theokanning.openai.moderation.Moderation;
import com.theokanning.openai.moderation.ModerationRequest;
import it.paneddo.openaimoderation.OpenaiModeration;
import it.paneddo.openaimoderation.utils.ChatUtils;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@AllArgsConstructor
public class ChatListener implements Listener {

    private final OpenaiModeration plugin;

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        ModerationRequest r = ModerationRequest.builder().input(message).model("text-moderation-" + plugin.getConfigManager().getModelType()).build();
        Moderation result = plugin.getService().createModeration(r).getResults().get(0);
        if (!result.flagged) return;

        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatUtils.MESSAGE_NOT_SENT);
    }
}
