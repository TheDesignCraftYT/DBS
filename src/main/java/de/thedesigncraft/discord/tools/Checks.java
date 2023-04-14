package de.thedesigncraft.discord.tools;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class Checks {

    /**
     * Checks if the given argument is null or empty.
     *
     * @param argument The argument to check.
     * @return True if the argument is null, false otherwise.
     */
    public static boolean isNullOrEmpty(@Nullable String argument) {
        return argument == null || argument.isEmpty() || argument.replace(" ", "").isEmpty();
    }

    /**
     * Checks if the given argument is null or empty.
     *
     * @param argument The argument to check.
     * @return True if the argument is null, false otherwise.
     */
    public static boolean isNullOrEmpty(@Nullable Collection<?> argument) {
        return argument == null || argument.isEmpty();
    }

    /**
     * Checks if the given argument is null.
     *
     * @param argument The argument to check.
     */
    public static void checkNull(@Nullable Object argument, @NotNull String name) {
        if (argument == null)
            throw new IllegalArgumentException(name + " may not be null!");
    }

    /**
     * Checks if the given argument is null or empty.
     *
     * @param argument The argument to check.
     */
    public static void checkNullOrEmpty(@Nullable String argument, @NotNull String name) {
        if (isNullOrEmpty(argument))
            throw new IllegalArgumentException(name + " may not be null or empty!");
    }

    /**
     * Checks if the given argument is null or empty.
     *
     * @param argument The argument to check.
     * @param name     The name of the argument.
     * @param suffix   The suffix to append to the exception message.
     */
    public static void checkNullOrEmpty(@Nullable String argument, @NotNull String name, @NotNull String suffix) {
        if (isNullOrEmpty(argument))
            throw new IllegalArgumentException(name + " may not be null or empty! " + suffix);
    }

    /**
     * Checks if the given argument is null or empty.
     *
     * @param argument The argument to check.
     * @param name     The name of the argument.
     */
    public static void checkNullOrEmpty(@Nullable Collection<?> argument, @NotNull String name) {
        if (isNullOrEmpty(argument))
            throw new IllegalArgumentException(name + " may not be null or empty!");
    }

    /**
     * Checks if the given argument is null or empty.
     *
     * @param argument The argument to check.
     * @param name     The name of the argument.
     * @param suffix   The suffix to append to the exception message.
     */
    public static void checkNullOrEmpty(@Nullable Collection<?> argument, @NotNull String name, @NotNull String suffix) {
        if (isNullOrEmpty(argument))
            throw new IllegalArgumentException(name + " may not be null or empty! " + suffix);
    }

}
