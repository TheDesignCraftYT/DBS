package de.thedesigncraft.discord.botstuff.devCommands.setup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class DevCommandsSetup {

    public static void setDeveloperIds(@NotNull Long... userIds) {

        DevCommandsValues.developerIds = Arrays.stream(userIds).toList();

    }

    public static void setDeveloperIds(@NotNull Collection<Long> userIds) {

        DevCommandsValues.developerIds = new ArrayList<>(userIds);

    }

    public static void addDeveloperIds(@NotNull Long... userIds) {

        DevCommandsValues.developerIds.addAll(Arrays.stream(userIds).toList());

    }

    public static void addDeveloperIds(@NotNull Collection<Long> userIds) {

        DevCommandsValues.developerIds.addAll(userIds);

    }

}
