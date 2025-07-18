package com.chatstyler;

import com.google.inject.Provides;
import java.awt.Color;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.MessageNode;
import net.runelite.api.Player;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.util.Text;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.ColorUtil;

@Slf4j
@PluginDescriptor(
    name = "Chat Styler",
    description = "Adds custom styling to your own chat messages"
)
public class ChatStylerPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private ChatStylerConfig config;

    @Provides
    ChatStylerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ChatStylerConfig.class);
    }

    @Subscribe
    public void onChatMessage(ChatMessage chatMessage)
    {
        switch (chatMessage.getType())
        {
            case PUBLICCHAT:
            case MODCHAT:
            case FRIENDSCHAT:
            case CLAN_CHAT:
            case CLAN_GUEST_CHAT:
            case CLAN_GIM_CHAT:
            case PRIVATECHATOUT:
            case MODPRIVATECHAT:
                styleOwnMessage(chatMessage);
                break;
            default:
                break;
        }
    }

    private void styleOwnMessage(ChatMessage chatMessage)
    {
        Player local = client.getLocalPlayer();
        if (local == null)
        {
            return;
        }

        String localName = Text.removeTags(local.getName());
        String sender = Text.removeTags(chatMessage.getName());

        if (!localName.equalsIgnoreCase(sender))
        {
            return;
        }

        MessageNode node = chatMessage.getMessageNode();
        String original = node.getRuneLiteFormatMessage() != null ? node.getRuneLiteFormatMessage() : node.getValue();
        Color color = config.chatColor();
        String styled = ColorUtil.wrapWithColorTag(original, color);
        node.setRuneLiteFormatMessage(styled);
        client.refreshChat();
    }
}
