package de.thedesigncraft.discord.tools;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ActionRows {

    @NotNull
    static Button cancelButton(User user) {
        return Button.of(ButtonStyle.SECONDARY, "cancel", "Cancel");
    }

    @NotNull
    static List<ActionRow> createFromButtons(@NotNull Collection<Button> buttons) {
        if (buttons.size() > 25)
            throw new IllegalArgumentException("The maximum amount of buttons is 25! (You tried to add " + buttons.size() + " buttons!)");
        List<ActionRow> returnList = new ArrayList<>();
        List<Button> buttonList = new ArrayList<>(buttons);
        int i = 0;
        while (i < buttons.size()) {
            returnList.add(ActionRow.of(buttonList.subList(i, Math.min(i + 5, buttonList.size()))));
            i += 5;
        }
        return returnList;
    }

}
