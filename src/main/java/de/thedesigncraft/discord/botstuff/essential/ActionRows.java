package de.thedesigncraft.discord.botstuff.essential;

import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.GenericComponentInteractionCreateEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ActionRows {

    @NotNull
    static Button cancelButton(User user) {
        return Button.of(ButtonStyle.SECONDARY, "cancel&id=" + user.getId(), "Cancel");
    }

    static boolean proof(@NotNull GenericComponentInteractionCreateEvent event, @NotNull Check... checks) {
        return proofFinal(event, null, checks);
    }

    static boolean proof(@NotNull GenericComponentInteractionCreateEvent event, @Nullable String expectedId, @NotNull Check... checks) {
        return proofFinal(event, expectedId, checks);
    }

    private static boolean proofFinal(@NotNull GenericComponentInteractionCreateEvent event, @Nullable String expectedId, @NotNull Check... checks) {

        List<Check> checkList = new java.util.ArrayList<>(List.of(checks));
        if (checkList.contains(Check.ALL)) {
            checkList.remove(Check.ALL);
            checkList.add(Check.ID);
            checkList.add(Check.USER);
        }
        Checks.notEmpty(checkList, "checks");

        Checks.notEmpty(event.getComponentId(), "componentId");
        String[] idTiles = event.getComponentId().split("&id=");
        if (idTiles.length > 2)
            throw new IllegalArgumentException("Cannot check a component with an id that contains '&id=' more than once.");

        if (checkList.contains(Check.USER)) {
            User expectedUser = Main.jda.retrieveUserById(idTiles[1]).complete();
            if (!event.getUser().equals(expectedUser)) {
                event.replyEmbeds(EmbedTemplates.issueEmbed("Only " + expectedUser.getAsMention() + " has access to this '" + event.getComponentType().name() + "'.", false)).setEphemeral(true).queue();
                return false;
            }
        }

        if (checkList.contains(Check.ID)) {
            Checks.notEmpty(expectedId, "expectedId");
            return idTiles[0].equals(expectedId);
        }

        return true;

    }

    enum Check {

        ID,
        USER,
        ALL

    }

}
