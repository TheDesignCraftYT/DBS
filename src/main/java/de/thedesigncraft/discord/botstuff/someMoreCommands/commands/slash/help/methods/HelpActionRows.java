package de.thedesigncraft.discord.botstuff.someMoreCommands.commands.slash.help.methods;

import de.thedesigncraft.discord.botstuff.essential.ActionRows;
import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IMessageContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IUserContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.util.ArrayList;
import java.util.List;

public interface HelpActionRows {

    static List<ActionRow> category(String category, long userId, Guild guild) {
        List<Button> buttons = new ArrayList<>();

        List<ISlashCommand> categoryISlashCommands = new ArrayList<>();
        List<IUserContextMenu> categoryUserCommands = new ArrayList<>();
        List<IMessageContextMenu> categoryMessageCommands = new ArrayList<>();

        Manager.getSlashCommands().forEach(serverCommand -> {

            if (serverCommand.category().equals(category))
                categoryISlashCommands.add(serverCommand);

        });

        Manager.getUserContextMenus().forEach(userContextMenu -> {

            if (userContextMenu.category().equals(category))
                categoryUserCommands.add(userContextMenu);

        });

        Manager.getMessageContextMenus().forEach(messageContextMenu -> {

            if (messageContextMenu.category().equals(category))
                categoryMessageCommands.add(messageContextMenu);

        });

        categoryISlashCommands.forEach(serverCommand -> buttons.add(Button.of(ButtonStyle.SUCCESS, "help.goToCommand&command=/" + serverCommand.name() + "&id=" + userId, "/" + serverCommand.name(), serverCommand.commandEmoji())));

        categoryUserCommands.forEach(userContextMenu -> buttons.add(Button.of(ButtonStyle.SUCCESS, "help.goToCommand&command=USER/" + userContextMenu.name() + "&id=" + userId, "USER/" + userContextMenu.name(), userContextMenu.commandEmoji())));

        categoryMessageCommands.forEach(messageContextMenu -> buttons.add(Button.of(ButtonStyle.SUCCESS, "help.goToCommand&command=MESSAGE/" + messageContextMenu.name() + "&id=" + userId, "MESSAGE/" + messageContextMenu.name(), messageContextMenu.commandEmoji())));

        List<ActionRow> returnList = new ArrayList<>();

        if (buttons.size() > 5) {

            returnList.add(ActionRow.of(buttons.subList(0, 5)));

            if (buttons.size() == 6) {

                returnList.add(ActionRow.of(buttons.get(5)));

            } else if (buttons.size() > 6 && buttons.size() <= 10) {

                returnList.add(ActionRow.of(buttons.subList(6, buttons.size())));

            } else if (buttons.size() == 11) {

                returnList.add(ActionRow.of(buttons.get(10)));

            } else if (buttons.size() > 11 && buttons.size() <= 15) {

                returnList.add(ActionRow.of(buttons.subList(6, 10)));
                returnList.add(ActionRow.of(buttons.subList(11, buttons.size())));

            }

        } else {

            returnList.add(ActionRow.of(buttons));

        }

        returnList.add(ActionRow.of(Button.of(ButtonStyle.SECONDARY, "help.goToMainPage&id=" + userId, "Zurück", Emoji.fromUnicode("U+25c0"))));

        return returnList;

    }

    static Button command(String category, long userId) {

        return Button.of(ButtonStyle.SECONDARY, "help.goToCategory&category=" + category + "&id=" + userId, "Zurück", Emoji.fromUnicode("U+25c0"));

    }

    static SelectMenu allCategories(User user) {

        List<SelectOption> selectOptions = new ArrayList<>();

        StartupValues.commandCategories.forEach(category -> selectOptions.add(SelectOption.of(category.getName(), category.getName())));

        return StringSelectMenu.create("help.goToCategory&id=" + user.getId()).addOptions(selectOptions).setMaxValues(1).setPlaceholder("Welche Kategorie möchtest du sehen?").build();

    }

    static List<ActionRow> mainPage(User user) {

        List<ActionRow> returnList = new ArrayList<>();
        returnList.add(ActionRow.of(allCategories(user)));
        returnList.add(ActionRow.of(ActionRows.cancelButton(user)));

        return returnList;

    }

}
