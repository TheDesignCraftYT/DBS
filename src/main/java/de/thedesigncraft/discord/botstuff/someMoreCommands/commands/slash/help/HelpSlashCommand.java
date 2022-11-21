package de.thedesigncraft.discord.botstuff.someMoreCommands.commands.slash.help;

import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IMessageContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IUserContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.DiscordCommands;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;
import de.thedesigncraft.discord.botstuff.someMoreCommands.SomeMoreCommandsPackage;
import de.thedesigncraft.discord.botstuff.someMoreCommands.commands.slash.help.methods.HelpActionRows;
import de.thedesigncraft.discord.botstuff.someMoreCommands.commands.slash.help.methods.HelpEmbeds;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HelpSlashCommand implements ISlashCommand {

    @Override
    public @NotNull String version() {
        return "v1.0.0-alpha.1";
    }

    @Override
    public @NotNull String description() {
        return "Zeigt das Help-Menü des Bots an.";
    }

    @Override
    public @NotNull String category() {
        return "Nützliches";
    }

    @Override
    public boolean globalCommand() {
        return IStuffPackage.activatedStuffPackageClasses().contains(SomeMoreCommandsPackage.class);
    }

    @Override
    public List<Guild> guilds() {
        return null;
    }

    @Override
    public boolean guildOnly() {
        return false;
    }

    @Override
    public List<OptionData> options() {
        List<OptionData> options = new ArrayList<>();
        OptionData command = new OptionData(OptionType.STRING, "command", "Welchen Command möchtest du öffnen?", false);

        Manager.getSlashCommands().forEach(slashCommand -> command.addChoice("/" + slashCommand.name(), "/" + slashCommand.name()));
        Manager.getUserContextMenus().forEach(userContextMenu -> command.addChoice("USER/" + userContextMenu.name(), "USER/" + userContextMenu.name()));
        Manager.getMessageContextMenus().forEach(messageContextMenu -> command.addChoice("MESSAGE/" + messageContextMenu.name(), "MESSAGE/" + messageContextMenu.name()));

        options.add(command);
        OptionData category = new OptionData(OptionType.STRING, "category", "Welche Kategorie möchtest du öffnen?", false);
        StartupValues.commandCategories.forEach(category1 -> category.addChoice(category1.getName(), category1.getName()));
        options.add(category);
        return options;
    }

    @Override
    public @NotNull Emoji commandEmoji() {
        return Emoji.fromUnicode("U+2139");
    }

    @Override
    public List<Permission> requiredPermissions() {
        return null;
    }

    @Override
    public void performSlashCommand(@NotNull SlashCommandInteractionEvent event) {

        if (event.getOption("category") == null && event.getOption("command") == null) {

            event.replyEmbeds(HelpEmbeds.mainPage()).addComponents(HelpActionRows.mainPage(event.getUser())).queue();

        } else if (event.getOption("category") != null && event.getOption("command") != null) {

            event.replyEmbeds(EmbedTemplates.issueEmbed("Du kannst **entweder** einen Command **oder** eine Kategorie angeben.", false)).setEphemeral(true).queue();

        } else if (event.getOption("category") != null && event.getOption("command") == null) {

            String arg2 = event.getOption("category").getAsString();

            event.replyEmbeds(HelpEmbeds.category(arg2)).addComponents(HelpActionRows.category(arg2, event.getMember().getIdLong(), event.getGuild())).queue();

        } else if (event.getOption("category") == null && event.getOption("command") != null) {

            String[] commandArgs = event.getOption("command").getAsString().split("/");

            ISlashCommand iSlashCommand = DiscordCommands.registeredSlashCommands.get(commandArgs[1]);

            IUserContextMenu iUserContextMenu = DiscordCommands.registeredUserCommands.get(commandArgs[1]);

            IMessageContextMenu iMessageContextMenu = DiscordCommands.registeredMessageCommands.get(commandArgs[1]);

            if (commandArgs.length == 2 && commandArgs[0].equals("")) {

                event.replyEmbeds(HelpEmbeds.slashCommand(iSlashCommand, event.getChannel())).addActionRow(HelpActionRows.command(iSlashCommand.category(), event.getUser().getIdLong())).queue();

            } else if (commandArgs.length == 2 && commandArgs[0].equals("USER")) {

                event.replyEmbeds(HelpEmbeds.userCommand(iUserContextMenu)).addActionRow(HelpActionRows.command(iUserContextMenu.category(), event.getUser().getIdLong())).queue();

            } else if (commandArgs.length == 2 && commandArgs[0].equals("MESSAGE")) {

                event.replyEmbeds(HelpEmbeds.messageCommand(iMessageContextMenu)).addActionRow(HelpActionRows.command(iMessageContextMenu.category(), event.getUser().getIdLong())).queue();

            } else {

                event.replyEmbeds(EmbedTemplates.issueEmbed("Ein unerwarteter Fehler ist aufgetreten.\n\nWenn dies passiert, melde es bitte umgehend dem Entwickler des Bots.", false)).queue();

            }

        } else {

            event.replyEmbeds(EmbedTemplates.issueEmbed("Das ist kein gültiger Wert.\n\nDu kannst Command-Namen und Kategorie-Namen angeben.", false)).setEphemeral(true).queue();

        }

    }
}
