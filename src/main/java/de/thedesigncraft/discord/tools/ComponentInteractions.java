package de.thedesigncraft.discord.tools;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.GenericComponentInteractionCreateEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ComponentInteractions {

    @Nullable
    public static User getUserThatInitialized(@NotNull GenericComponentInteractionCreateEvent event) {
        User user = null;

        if (event.getMessage().getInteraction() != null) {
            user = event.getMessage().getInteraction().getUser();
        } else if (event.getMessage().getMessageReference() != null && event.getMessage().getMessageReference().resolve().complete() != null) {
            user = event.getMessage().getMessageReference().resolve().complete().getAuthor();
        }

        return user;
    }

}
