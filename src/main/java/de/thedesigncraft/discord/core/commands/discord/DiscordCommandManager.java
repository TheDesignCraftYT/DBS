package de.thedesigncraft.discord.core.commands.discord;

import de.thedesigncraft.discord.manage.DBS;
import de.thedesigncraft.discord.manage.commands.discord.events.DBSMessageCommandInteractionEvent;
import de.thedesigncraft.discord.manage.commands.discord.events.DBSSlashAndUserCommandInteractionEvent;
import de.thedesigncraft.discord.manage.commands.discord.events.DBSSlashCommandInteractionEvent;
import de.thedesigncraft.discord.manage.commands.discord.events.DBSUserCommandInteractionEvent;
import de.thedesigncraft.discord.manage.commands.discord.types.IDiscordCommand;
import de.thedesigncraft.discord.manage.commands.discord.types.message.IMessageContextMenu;
import de.thedesigncraft.discord.manage.commands.discord.types.slash.ISlashCommand;
import de.thedesigncraft.discord.manage.commands.discord.types.slashanduser.ISlashAndUserCommand;
import de.thedesigncraft.discord.manage.commands.discord.types.user.IUserContextMenu;
import de.thedesigncraft.discord.tools.Checks;
import de.thedesigncraft.discord.tools.EmbedTemplates;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiscordCommandManager extends ListenerAdapter {

    public DiscordCommandManager() {

        List<IDiscordCommand> globalCommands = new ArrayList<>();
        Map<Guild, List<IDiscordCommand>> localCommands = new ConcurrentHashMap<>();

        IDiscordCommand.getDiscordCommands().forEach(iDiscordCommand -> {
            if (iDiscordCommand.getSetup().isGlobal()) {
                globalCommands.add(iDiscordCommand);
            } else if (!Checks.isNullOrEmpty(iDiscordCommand.getSetup().getGuilds())) {
                iDiscordCommand.getSetup().getGuilds().forEach(guild -> {
                    List<IDiscordCommand> commands = localCommands.get(guild);
                    commands.add(iDiscordCommand);
                    localCommands.put(guild, commands);
                });
            }
        });

        CommandListUpdateAction globalUpdateAction = DBS.getJDA().updateCommands();
        globalCommands.forEach(iDiscordCommand -> registerCommand(iDiscordCommand, globalUpdateAction));
        globalUpdateAction.queue();

        DBS.getJDA().getGuilds().stream().filter(guild -> !Checks.isNullOrEmpty(localCommands.get(guild))).forEach(guild -> {
            CommandListUpdateAction localUpdateAction = guild.updateCommands();
            localCommands.get(guild).forEach(command -> registerCommand(command, localUpdateAction));
            localUpdateAction.queue();
        });

        LoggerFactory.getLogger(DiscordCommandManager.class).info("Registered " + IDiscordCommand.getDiscordCommands().size() + " commands.");

    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        IDiscordCommand command;
        if ((command = ISlashCommand.getByName(event.getName())) != null) {
            ISlashCommand slashCommand = (ISlashCommand) command;
            if (Checks.isNullOrEmpty(slashCommand.getSetup().getRequiredPermissions()) || !event.isFromGuild()) {
                slashCommand.execute(new DBSSlashCommandInteractionEvent(slashCommand, event));
                return;
            }
            if (event.getMember().hasPermission(slashCommand.getSetup().getRequiredPermissions())) {
                slashCommand.execute(new DBSSlashCommandInteractionEvent(slashCommand, event));
                return;
            }
            event.replyEmbeds(missingPermissionsEmbed(slashCommand, event.getMember())).setEphemeral(true).queue();
        } else if ((command = ISlashAndUserCommand.getByName(event.getName())) != null) {
            ISlashAndUserCommand slashAndUserCommand = (ISlashAndUserCommand) command;
            if (Checks.isNullOrEmpty(slashAndUserCommand.getSetup().getRequiredPermissions()) || !event.isFromGuild()) {
                slashAndUserCommand.execute(new DBSSlashAndUserCommandInteractionEvent(slashAndUserCommand, event));
                return;
            }
            if (event.getMember().hasPermission(slashAndUserCommand.getSetup().getRequiredPermissions())) {
                slashAndUserCommand.execute(new DBSSlashAndUserCommandInteractionEvent(slashAndUserCommand, event));
                return;
            }
            event.replyEmbeds(missingPermissionsEmbed(slashAndUserCommand, event.getMember())).setEphemeral(true).queue();
        }
    }

    @Override
    public void onUserContextInteraction(@NotNull UserContextInteractionEvent event) {
        IDiscordCommand command;
        if ((command = IUserContextMenu.getByName(event.getName())) != null) {
            IUserContextMenu userContextMenu = (IUserContextMenu) command;
            if (Checks.isNullOrEmpty(userContextMenu.getSetup().getRequiredPermissions()) || !event.isFromGuild()) {
                userContextMenu.execute(new DBSUserCommandInteractionEvent(userContextMenu, event));
                return;
            }
            if (event.getMember().hasPermission(userContextMenu.getSetup().getRequiredPermissions())) {
                userContextMenu.execute(new DBSUserCommandInteractionEvent(userContextMenu, event));
                return;
            }
            event.replyEmbeds(missingPermissionsEmbed(userContextMenu, event.getMember())).setEphemeral(true).queue();
        } else if ((command = ISlashAndUserCommand.getByName(event.getName())) != null) {
            ISlashAndUserCommand slashAndUserCommand = (ISlashAndUserCommand) command;
            if (Checks.isNullOrEmpty(slashAndUserCommand.getSetup().getRequiredPermissions()) || !event.isFromGuild()) {
                slashAndUserCommand.execute(new DBSSlashAndUserCommandInteractionEvent(slashAndUserCommand, event));
                return;
            }
            if (event.getMember().hasPermission(slashAndUserCommand.getSetup().getRequiredPermissions())) {
                slashAndUserCommand.execute(new DBSSlashAndUserCommandInteractionEvent(slashAndUserCommand, event));
                return;
            }
            event.replyEmbeds(missingPermissionsEmbed(slashAndUserCommand, event.getMember())).setEphemeral(true).queue();
        }
    }

    @Override
    public void onMessageContextInteraction(@NotNull MessageContextInteractionEvent event) {
        IMessageContextMenu command;
        if ((command = IMessageContextMenu.getByName(event.getName())) != null) {
            if (Checks.isNullOrEmpty(command.getSetup().getRequiredPermissions()) || !event.isFromGuild()) {
                command.execute(new DBSMessageCommandInteractionEvent(command, event));
                return;
            }
            if (event.getMember().hasPermission(command.getSetup().getRequiredPermissions())) {
                command.execute(new DBSMessageCommandInteractionEvent(command, event));
                return;
            }
            event.replyEmbeds(missingPermissionsEmbed(command, event.getMember())).setEphemeral(true).queue();
        }
    }

    private static void registerCommand(@NotNull IDiscordCommand command, @NotNull CommandListUpdateAction updateAction) {
        if (command.getSetup().getType().equals(IDiscordCommand.Type.SLASH))
            registerSlashCommand((ISlashCommand) command, updateAction);

        if (command.getSetup().getType().equals(IDiscordCommand.Type.MESSAGE))
            registerMessageCommand((IMessageContextMenu) command, updateAction);

        if (command.getSetup().getType().equals(IDiscordCommand.Type.USER))
            registerUserCommand((IUserContextMenu) command, updateAction);

        if (command.getSetup().getType().equals(IDiscordCommand.Type.SLASH_AND_USER))
            registerSlashAndUserCommand((ISlashAndUserCommand) command, updateAction);
    }

    private static void registerSlashCommand(@NotNull ISlashCommand command, @NotNull CommandListUpdateAction updateAction) {
        SlashCommandData data = Commands.slash(
                        command.getSetup().getName(),
                        command.getSetup().getDescription())
                .setGuildOnly(command.getSetup().isGuildOnly());
        if (!Checks.isNullOrEmpty(command.getSetup().getRequiredPermissions()))
            data.setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.getSetup().getRequiredPermissions()));
        if (!Checks.isNullOrEmpty(command.getSetup().getOptions()))
            data.addOptions(command.getSetup().getOptions());
        updateAction.addCommands(data);
    }

    private static void registerUserCommand(@NotNull IUserContextMenu command, @NotNull CommandListUpdateAction updateAction) {
        CommandData data = Commands.user(
                        command.getSetup().getName())
                .setGuildOnly(command.getSetup().isGuildOnly());
        if (!Checks.isNullOrEmpty(command.getSetup().getRequiredPermissions()))
            data.setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.getSetup().getRequiredPermissions()));
        updateAction.addCommands(data);
    }

    private static void registerMessageCommand(@NotNull IMessageContextMenu command, @NotNull CommandListUpdateAction updateAction) {
        CommandData data = Commands.message(
                        command.getSetup().getName())
                .setGuildOnly(command.getSetup().isGuildOnly());
        if (!Checks.isNullOrEmpty(command.getSetup().getRequiredPermissions()))
            data.setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.getSetup().getRequiredPermissions()));
        updateAction.addCommands(data);
    }

    private static void registerSlashAndUserCommand(@NotNull ISlashAndUserCommand command, @NotNull CommandListUpdateAction updateAction) {
        SlashCommandData slashData = Commands.slash(
                        command.getSetup().getName(),
                        command.getSetup().getDescription())
                .setGuildOnly(command.getSetup().isGuildOnly())
                .addOptions(new OptionData(OptionType.USER, "user", command.getSetup().getSlashCommandOptionDescription(), true));
        if (!Checks.isNullOrEmpty(command.getSetup().getRequiredPermissions()))
            slashData.setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.getSetup().getRequiredPermissions()));
        updateAction.addCommands(slashData);
        CommandData userData = Commands.user(
                        command.getSetup().getName())
                .setGuildOnly(command.getSetup().isGuildOnly());
        if (!Checks.isNullOrEmpty(command.getSetup().getRequiredPermissions()))
            userData.setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.getSetup().getRequiredPermissions()));
        updateAction.addCommands(userData);
    }

    @NotNull
    private static MessageEmbed missingPermissionsEmbed(@NotNull IDiscordCommand command, @NotNull Member member) {
        StringBuilder stringBuilder = new StringBuilder();
        command.getSetup().getRequiredPermissions().stream().filter(permission -> !member.hasPermission(permission)).forEach(permission -> stringBuilder.append("> • ").append(permission.getName()).append("\n"));
        return EmbedTemplates.issueEmbed("You don't have all the necessary permissions to execute this command!\n> **Missing Permissions:**\n" + stringBuilder);
    }

}
