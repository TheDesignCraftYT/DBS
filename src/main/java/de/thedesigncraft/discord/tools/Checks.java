package de.thedesigncraft.discord.tools;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class Checks {

    public static boolean isNullOrEmpty(@Nullable String argument) {
        return argument == null || argument.isEmpty() || argument.replace(" ", "").isEmpty();
    }

    public static boolean isNullOrEmpty(@Nullable Collection<?> argument) {
        return argument == null || argument.isEmpty();
    }

    public static void checkNull(@Nullable Object argument, @NotNull String name) {
        if (argument == null)
            throw new IllegalArgumentException(name + " may not be null!");
    }

    public static void checkNullOrEmpty(@Nullable String argument, @NotNull String name) {
        if (isNullOrEmpty(argument))
            throw new IllegalArgumentException(name + " may not be null or empty!");
    }

    public static void checkNullOrEmpty(@Nullable String argument, @NotNull String name, @NotNull String suffix) {
        if (isNullOrEmpty(argument))
            throw new IllegalArgumentException(name + " may not be null or empty! " + suffix);
    }

    public static void checkNullOrEmpty(@Nullable Collection<?> argument, @NotNull String name) {
        if (isNullOrEmpty(argument))
            throw new IllegalArgumentException(name + " may not be null or empty!");
    }

    public static void checkNullOrEmpty(@Nullable Collection<?> argument, @NotNull String name, @NotNull String suffix) {
        if (isNullOrEmpty(argument))
            throw new IllegalArgumentException(name + " may not be null or empty! " + suffix);
    }

}
