package de.thedesigncraft.discord.botstuff.essential;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

public class Checks {

    public static void notNull(@Nullable Object argument, @NotNull String name) {
        if (argument == null) {
            throw new IllegalArgumentException(name + " may not be null");
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> argument, @NotNull String name) {
        notNull(argument, name);
        if (argument.isEmpty()) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
    }

    public static void notEmpty(@Nullable Collection<?> argument, @NotNull String name) {
        notNull(argument, name);
        if (argument.isEmpty()) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
    }

    public static void notEmpty(@Nullable String argument, @NotNull String name) {
        notNull(argument, name);
        if (argument.isEmpty() || argument.replace(" ", "").isEmpty()) {
            throw new IllegalArgumentException(name + " may not be empty");
        }
    }

    public static boolean isEmptyOrNull(@Nullable String argument) {
        return argument == null || argument.isEmpty() || argument.replace(" ", "").isEmpty();
    }

}
