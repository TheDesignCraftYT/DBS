package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage;

import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IDiscordCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IMessageContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IUserContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiscordCommands {

    public static Map<String, IDiscordCommand> registeredDiscordCommands;

    public static Map<String, ISlashCommand> registeredSlashCommands;
    public static Map<String, IUserContextMenu> registeredUserCommands;
    public static Map<String, IMessageContextMenu> registeredMessageCommands;

    public static void setRegisteredCommands() {

        registeredDiscordCommands = new ConcurrentHashMap<>();

        registeredSlashCommands = new ConcurrentHashMap<>();
        registeredUserCommands = new ConcurrentHashMap<>();
        registeredMessageCommands = new ConcurrentHashMap<>();

        Manager.getDiscordCommands().forEach(iDiscordCommand -> registeredDiscordCommands.put(iDiscordCommand.name(), iDiscordCommand));

        Manager.getSlashCommands().forEach(iSlashCommand -> registeredSlashCommands.put(iSlashCommand.name(), iSlashCommand));
        Manager.getUserContextMenus().forEach(iUserContextMenu -> registeredUserCommands.put(iUserContextMenu.name(), iUserContextMenu));
        Manager.getMessageContextMenus().forEach(iMessageContextMenu -> registeredMessageCommands.put(iMessageContextMenu.name(), iMessageContextMenu));

    }

}
