package de.thedesigncraft.discord.botstuff.someMoreCommands.commands.slash.help;

import de.thedesigncraft.discord.botstuff.essential.ActionRows;
import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IMessageContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IUserContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.DiscordCommands;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;
import de.thedesigncraft.discord.botstuff.someMoreCommands.commands.slash.help.methods.HelpActionRows;
import de.thedesigncraft.discord.botstuff.someMoreCommands.commands.slash.help.methods.HelpEmbeds;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class HelpActionRowListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        User user = event.getUser();

        if (ActionRows.proof(event, "help.goToCommand", ActionRows.Check.ALL)) {

            String[] commandArgs = event.getButton().getId().split("&")[1].replace("command=", "").split("/");

            ISlashCommand iSlashCommand = DiscordCommands.registeredSlashCommands.get(commandArgs[1]);

            IUserContextMenu userContextMenu = DiscordCommands.registeredUserCommands.get(commandArgs[1]);

            IMessageContextMenu messageContextMenu = DiscordCommands.registeredMessageCommands.get(commandArgs[1]);

            if (commandArgs.length == 2 && commandArgs[0].equals("")) {

                event.editMessageEmbeds(HelpEmbeds.slashCommand(iSlashCommand, event.getChannel())).setActionRow(HelpActionRows.command(iSlashCommand.category(), event.getUser().getIdLong())).queue();

            } else if (commandArgs.length == 2 && commandArgs[0].equals("USER")) {

                event.editMessageEmbeds(HelpEmbeds.userCommand(userContextMenu)).setActionRow(HelpActionRows.command(userContextMenu.category(), event.getMember().getIdLong())).queue();

            } else if (commandArgs.length == 2 && commandArgs[0].equals("MESSAGE")) {

                event.editMessageEmbeds(HelpEmbeds.messageCommand(messageContextMenu)).setActionRow(HelpActionRows.command(messageContextMenu.category(), event.getMember().getIdLong())).queue();

            } else {

                event.editMessageEmbeds(EmbedTemplates.issueEmbed("Ein unerwarteter Fehler ist aufgetreten.\n\nWenn dies passiert, melde es bitte umgehend dem Entwickler des Bots.", false)).queue();

            }

        } else if (ActionRows.proof(event, "help.goToCategory", ActionRows.Check.ALL)) {

            String arg = event.getButton().getId().split("&")[1].replace("category=", "");

            event.editMessageEmbeds(HelpEmbeds.category(arg)).setComponents(HelpActionRows.category(arg, user.getIdLong(), event.getGuild())).queue();

        } else if (ActionRows.proof(event, "help.goToMainPage", ActionRows.Check.ALL)) {

            event.editMessageEmbeds(HelpEmbeds.mainPage()).setComponents(HelpActionRows.mainPage(user)).queue();

        }

    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {

        if (ActionRows.proof(event, "help.goToCategory", ActionRows.Check.ALL)) {

            String arg = event.getSelectedOptions().get(0).getLabel();

            event.editMessageEmbeds(HelpEmbeds.category(arg)).setComponents(HelpActionRows.category(arg, event.getUser().getIdLong(), event.getGuild())).queue();

        }

    }
}
