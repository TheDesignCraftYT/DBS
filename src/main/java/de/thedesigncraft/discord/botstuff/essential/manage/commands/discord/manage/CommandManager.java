package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage;

import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IDiscordCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IMessageContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IUserContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import javax.annotation.CheckReturnValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager extends ListenerAdapter {

    public CommandManager() {

        List<IDiscordCommand> globalCommands = new ArrayList<>();
        Map<Guild, List<IDiscordCommand>> localCommands = new ConcurrentHashMap<>();

        Manager.getDiscordCommands().forEach(iDiscordCommand -> {

            if (iDiscordCommand.globalCommand()) {

                globalCommands.add(iDiscordCommand);

            } else if (iDiscordCommand.guilds() != null) {

                if (!iDiscordCommand.guilds().isEmpty()) {

                    iDiscordCommand.guilds().forEach(guild -> {

                        List<IDiscordCommand> commands = localCommands.get(guild);
                        commands.add(iDiscordCommand);

                        localCommands.put(guild, commands);

                    });

                }

            }

        });

        CommandListUpdateAction globalUpdateAction = Main.jda.updateCommands();
        globalCommands.forEach(iDiscordCommand -> registerCommand(iDiscordCommand, globalUpdateAction));
        globalUpdateAction.queue();

        Main.jda.getGuilds().forEach(guild -> {

            CommandListUpdateAction localUpdateAction = guild.updateCommands();

            List<IDiscordCommand> localCommandsForThisGuild = new ArrayList<>();

            if (localCommands.get(guild) != null)
                localCommandsForThisGuild.addAll(localCommands.get(guild));

            if (!localCommandsForThisGuild.isEmpty()) {

                localCommandsForThisGuild.forEach(iDiscordCommand -> registerCommand(iDiscordCommand, localUpdateAction));

            }

            localUpdateAction.queue();

        });

        DiscordCommands.setRegisteredCommands();

        LoggerFactory.getLogger(CommandManager.class).info("Commands registered.");

    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        ISlashCommand iSlashCommand;

        if ((iSlashCommand = DiscordCommands.registeredSlashCommands.get(event.getName())) != null) {

            if (iSlashCommand.requiredPermissions() != null && event.isFromGuild()) {

                if (event.getMember().hasPermission(iSlashCommand.requiredPermissions())) {

                    iSlashCommand.performSlashCommand(event);

                } else {

                    event.replyEmbeds(missingPermissionsEmbed(iSlashCommand, event.getMember())).setEphemeral(true).queue();

                }

            } else {

                iSlashCommand.performSlashCommand(event);

            }

        }

    }

    @Override
    public void onUserContextInteraction(@NotNull UserContextInteractionEvent event) {

        IUserContextMenu iUserContextMenu;

        if ((iUserContextMenu = DiscordCommands.registeredUserCommands.get(event.getName())) != null) {

            if (iUserContextMenu.requiredPermissions() != null && event.isFromGuild()) {

                if (event.getMember().hasPermission(iUserContextMenu.requiredPermissions())) {

                    iUserContextMenu.performUserContextMenu(event);

                } else {

                    event.replyEmbeds(missingPermissionsEmbed(iUserContextMenu, event.getMember())).setEphemeral(true).queue();

                }

            } else {

                iUserContextMenu.performUserContextMenu(event);

            }

        }

    }

    @Override
    public void onMessageContextInteraction(@NotNull MessageContextInteractionEvent event) {

        IMessageContextMenu iMessageContextMenu;

        if ((iMessageContextMenu = DiscordCommands.registeredMessageCommands.get(event.getName())) != null) {

            if (iMessageContextMenu.requiredPermissions() != null && event.isFromGuild()) {

                if (event.getMember().hasPermission(iMessageContextMenu.requiredPermissions())) {

                    iMessageContextMenu.performMessageContextMenu(event);

                } else {

                    event.replyEmbeds(missingPermissionsEmbed(iMessageContextMenu, event.getMember())).setEphemeral(true).queue();

                }

            } else {

                iMessageContextMenu.performMessageContextMenu(event);

            }

        }

    }

    @CheckReturnValue
    private static void registerCommand(@NotNull IDiscordCommand iDiscordCommand, @NotNull CommandListUpdateAction updateAction) {

        if (iDiscordCommand instanceof ISlashCommand)
            registerSlashCommand((ISlashCommand) iDiscordCommand, updateAction);

        if (iDiscordCommand instanceof IUserContextMenu)
            registerUserCommand((IUserContextMenu) iDiscordCommand, updateAction);

        if (iDiscordCommand instanceof IMessageContextMenu)
            registerMessageCommand((IMessageContextMenu) iDiscordCommand, updateAction);

    }

    @CheckReturnValue
    private static void registerSlashCommand(@NotNull ISlashCommand ISlashCommand, @NotNull CommandListUpdateAction updateAction) {

        if (ISlashCommand.options() != null) {

            if (ISlashCommand.requiredPermissions() != null) {

                updateAction.addCommands(Commands.slash(
                                ISlashCommand.name(),
                                ISlashCommand.description())
                        .setGuildOnly(ISlashCommand.guildOnly())
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(ISlashCommand.requiredPermissions()))
                        .addOptions(ISlashCommand.options())
                );

            } else {

                updateAction.addCommands(Commands.slash(
                                ISlashCommand.name(),
                                ISlashCommand.description())
                        .setGuildOnly(ISlashCommand.guildOnly())
                        .addOptions(ISlashCommand.options())
                );

            }

        } else {

            if (ISlashCommand.requiredPermissions() != null) {

                updateAction.addCommands(Commands.slash(
                                ISlashCommand.name(),
                                ISlashCommand.description())
                        .setGuildOnly(ISlashCommand.guildOnly())
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(ISlashCommand.requiredPermissions()))
                );

            } else {

                updateAction.addCommands(Commands.slash(
                                ISlashCommand.name(),
                                ISlashCommand.description())
                        .setGuildOnly(ISlashCommand.guildOnly())
                );

            }

        }

    }

    @CheckReturnValue
    private static void registerUserCommand(@NotNull IUserContextMenu IUserContextMenu, @NotNull CommandListUpdateAction updateAction) {

        if (IUserContextMenu.requiredPermissions() != null) {

            updateAction.addCommands(Commands.user(
                            IUserContextMenu.name())
                    .setGuildOnly(IUserContextMenu.guildOnly())
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(IUserContextMenu.requiredPermissions()))
            );

        } else {

            updateAction.addCommands(Commands.user(
                            IUserContextMenu.name())
                    .setGuildOnly(IUserContextMenu.guildOnly())
            );

        }

    }

    @CheckReturnValue
    private static void registerMessageCommand(@NotNull IMessageContextMenu IMessageContextMenu, @NotNull CommandListUpdateAction updateAction) {

        if (IMessageContextMenu.requiredPermissions() != null) {

            updateAction.addCommands(Commands.user(
                            IMessageContextMenu.name())
                    .setGuildOnly(IMessageContextMenu.guildOnly())
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(IMessageContextMenu.requiredPermissions()))
            );

        } else {

            updateAction.addCommands(Commands.user(
                            IMessageContextMenu.name())
                    .setGuildOnly(IMessageContextMenu.guildOnly())
            );

        }

    }

    @NotNull
    @CheckReturnValue
    private static MessageEmbed missingPermissionsEmbed(@NotNull IDiscordCommand iDiscordCommand, @NotNull Member member) {

        List<Permission> missingPermissions = new ArrayList<>();

        iDiscordCommand.requiredPermissions().forEach(permission -> {

            if (!member.hasPermission(permission))
                missingPermissions.add(permission);

        });

        StringBuilder stringBuilder = new StringBuilder();

        missingPermissions.forEach(permission -> stringBuilder
                .append("> • ")
                .append(permission.getName())
                .append("\n"));

        return EmbedTemplates.issueEmbed("You don't have all the necessary permissions for this command.\n> Missing:\n\n" + stringBuilder, false);

    }

}
