package de.thedesigncraft.discord.botstuff.interactionErrorDetection.setup;

import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class InteractionErrorDetectionSetup {

    public static void setUsers(@NotNull User... users) {

        InteractionErrorDetectionValues.users = List.of(users);

    }

    public static void setUsers(@NotNull Collection<User> users) {

        InteractionErrorDetectionValues.users = (List<User>) users;

    }

    public static void addUsers(@NotNull User... users) {

        InteractionErrorDetectionValues.users.addAll(List.of(users));

    }

    public static void addUsers(@NotNull Collection<User> users) {

        InteractionErrorDetectionValues.users.addAll(users);

    }

}
