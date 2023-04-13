package de.thedesigncraft.discord.manage;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;

public class GlobalLogger {

    public static void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    public static void error(Class<?> clazz, String message) {
        getLogger(clazz).error(message);
    }

    public static void exceptionError(Exception e) {
        exceptionError(Arrays.stream(e.getStackTrace()).findFirst().get().getClassName(), e.getMessage(), e);
    }

    public static void exceptionError(String clazz, String message, @NotNull Throwable throwable) {
        getLogger(clazz).error(message, throwable.getMessage());
        throw new RuntimeException(throwable);
    }

    public static void exceptionError(Class<?> clazz, String message, @NotNull Throwable throwable) {
        getLogger(clazz).error(message, throwable.getMessage());
        throw new RuntimeException(throwable);
    }

}
