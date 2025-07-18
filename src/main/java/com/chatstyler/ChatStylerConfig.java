package com.chatstyler;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("chatstyler")
public interface ChatStylerConfig extends Config
{
    @ConfigItem(
        keyName = "chatColor",
        name = "Chat color",
        description = "Color to apply to your chat messages"
    )
    default Color chatColor()
    {
        return Color.CYAN;
    }
}
