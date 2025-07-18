package com.chatstyler;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ChatStylerPluginTest
{
        public static void main(String[] args) throws Exception
        {
                ExternalPluginManager.loadBuiltin(ChatStylerPlugin.class);
                RuneLite.main(args);
        }
}