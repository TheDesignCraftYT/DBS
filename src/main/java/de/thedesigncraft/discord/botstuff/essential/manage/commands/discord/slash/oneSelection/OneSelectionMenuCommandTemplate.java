package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.oneSelection;

import de.thedesigncraft.discord.botstuff.essential.ActionRows;
import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.DiscordCommands;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public interface OneSelectionMenuCommandTemplate extends ISlashCommand {

    @NotNull
    MessageEmbed mainPage();

    @NotNull
    Settings settings();

    @NotNull
    ConcurrentHashMap<String, String[]> choices();

    @NotNull
    MessageEmbed choiceEmbed(@NotNull String name, @NotNull String[] args);

    @NotNull
    default List<OptionData> options() {

        List<OptionData> returnList = new ArrayList<>();

        List<Command.Choice> choices = new ArrayList<>();

        choices().forEach((s, strings) -> choices.add(new Command.Choice(s, s)));

        returnList.add(new OptionData(OptionType.STRING, settings().getOptionName(), settings().getOptionDescription()).addChoices(choices));

        return returnList;

    }

    default void performSlashCommand(@NotNull SlashCommandInteractionEvent event) {

        if (event.getOption(settings().getOptionName()) != null) {

            String arg = event.getOption(settings().getOptionName()).getAsString();

            ConcurrentHashMap<String, String[]> targets = targets(choices(), arg);

            String key = targets.keySet().stream().findFirst().get();

            event.replyEmbeds(replyEmbed(arg, targets, settings(), mainPage(), choiceEmbed(key, targets.get(key)))).addComponents(actionRow(event.getUser(), arg, choices(), settings())).queue();

        } else {

            event.replyEmbeds(replyEmbed(null, targets(choices(), null), settings(), mainPage(), null)).addComponents(actionRow(event.getUser(), null, choices(), settings())).queue();

        }

    }

    @NotNull
    static MessageEmbed replyEmbed(@Nullable String arg, @NotNull ConcurrentHashMap<String, String[]> targets, @NotNull Settings settings, @NotNull MessageEmbed mainPage, @Nullable MessageEmbed choiceEmbed) {

        if (targets.size() > 1)
            return EmbedTemplates.issueEmbed("An unexpected error occurred.", false);

        if (targets.size() == 0) {

            if (arg != null) {

                return EmbedTemplates.issueEmbed("This isn't a registered value for option: '" + settings.getOptionName() + "'.", false);

            } else {

                return mainPage;

            }

        }

        assert choiceEmbed != null;
        return choiceEmbed;

    }

    static List<ActionRow> actionRow(@NotNull User user, @Nullable String arg, @NotNull ConcurrentHashMap<String, String[]> choices, @NotNull Settings settings) {

        ConcurrentHashMap<String, String[]> targets = targets(choices, arg);

        if (targets.size() > 1)
            return Collections.singletonList(ActionRow.of(ActionRows.cancelButton(user)));

        if (targets.size() == 0) {

            if (arg != null) {

                return Collections.singletonList(ActionRow.of(ActionRows.cancelButton(user)));

            } else {

                List<ActionRow> returnList = new ArrayList<>();

                List<SelectOption> options = new ArrayList<>();

                choices.forEach((s, strings) -> options.add(SelectOption.of(s, s)));

                returnList.add(ActionRow.of(StringSelectMenu.create(settings.getCommandName() + "Menu&id=" + user.getIdLong()).addOptions(options).build()));

                returnList.add(ActionRow.of(ActionRows.cancelButton(user)));

                return returnList;

            }

        }

        return Collections.singletonList(ActionRow.of(Button.of(ButtonStyle.SECONDARY, settings.getCommandName() + ".goToMainPage&id=" + user.getIdLong(), "Back", Emoji.fromUnicode("U+25c0"))));

    }

    static ConcurrentHashMap<String, String[]> targets(@NotNull ConcurrentHashMap<String, String[]> choices, @Nullable String arg) {

        ConcurrentHashMap<String, String[]> targets = new ConcurrentHashMap<>();

        choices.forEach((s, strings) -> {

            if (s.equalsIgnoreCase(arg)) {

                targets.put(s, strings);

            }

        });

        return targets;

    }

    static void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        ISlashCommand serverCommand = DiscordCommands.registeredSlashCommands.get(event.getButton().getId().replace(".goToMainPage", "").toLowerCase().split("&id=")[0]);

        try {

            OneSelectionMenuCommandTemplate oneSelectionMenu = (OneSelectionMenuCommandTemplate) serverCommand;

            if (oneSelectionMenu != null && ActionRows.proof(event, event.getButton().getId().split("&id=")[0], ActionRows.Check.ALL)) {

                event.editMessageEmbeds(oneSelectionMenu.mainPage()).setComponents(actionRow(event.getUser(), null, oneSelectionMenu.choices(), oneSelectionMenu.settings())).queue();

            }

        } catch (ClassCastException ignored) {

        }

    }

    static void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {

        ISlashCommand iSlashCommand = DiscordCommands.registeredSlashCommands.get(event.getSelectMenu().getId().replace("Menu", "").toLowerCase().split("&id=")[0]);

        OneSelectionMenuCommandTemplate oneSelectionMenu = (OneSelectionMenuCommandTemplate) iSlashCommand;

        if (oneSelectionMenu != null && ActionRows.proof(event, event.getSelectMenu().getId().split("&id=")[0], ActionRows.Check.ALL)) {

            String arg = event.getSelectedOptions().get(0).getValue();

            ConcurrentHashMap<String, String[]> targets = targets(oneSelectionMenu.choices(), arg);

            event.editMessageEmbeds(replyEmbed(arg, targets, oneSelectionMenu.settings(), oneSelectionMenu.mainPage(), oneSelectionMenu.choiceEmbed(targets.keySet().stream().findFirst().get(), targets.get(targets.keySet().stream().findFirst().get())))).setComponents(actionRow(event.getUser(), arg, oneSelectionMenu.choices(), oneSelectionMenu.settings())).queue();

        }

    }

    class Settings {

        protected final String commandName;
        protected final String optionName;
        protected final String optionDescription;

        public Settings(String commandName, String optionName, String optionDescription) {
            this.commandName = commandName;
            this.optionName = optionName;
            this.optionDescription = optionDescription;
        }

        @Nullable
        public String getCommandName() {
            return this.commandName;
        }

        @Nullable
        public String getOptionName() {
            return this.optionName;
        }

        @Nullable
        public String getOptionDescription() {
            return this.optionDescription;
        }

        public boolean isEmpty() {
            return (this.commandName == null || this.commandName.isEmpty()) && (this.optionName == null || this.optionName.isEmpty()) && (this.optionDescription == null || this.optionDescription.isEmpty());
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Settings)) {
                return false;
            } else if (obj == this) {
                return true;
            } else {
                Settings other = (Settings) obj;
                return Objects.equals(this.commandName, other.commandName) && Objects.equals(this.optionName, other.optionName) && Objects.equals(this.optionDescription, other.optionDescription);
            }
        }

    }

}
