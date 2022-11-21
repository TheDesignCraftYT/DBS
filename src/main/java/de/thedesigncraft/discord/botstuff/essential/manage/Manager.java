package de.thedesigncraft.discord.botstuff.essential.manage;

import de.thedesigncraft.discord.botstuff.devCommands.manage.IDevCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.console.IConsoleCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IDiscordCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IMessageContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IUserContextMenu;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.CommandManager;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.loop.ILoopExecution;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;
import de.thedesigncraft.discord.botstuff.interactionErrorDetection.SelectInteractionErrorListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private static final String libraryPackageName = "de.thedesigncraft.discord.botstuff";

    @NotNull
    public static List<Class<?>> getClassesThatExtendAClass(@NotNull String packageName, @NotNull Class<?> clazz) {

        return new ArrayList<>(new Reflections(packageName, new SubTypesScanner()).getSubTypesOf(clazz));

    }

    @NotNull
    public static List<Class<?>> getClassesThatExtendAClass(@NotNull String packageName, Class<?> clazz, @NotNull List<Class<?>> excludedClasses) {

        if (excludedClasses.isEmpty())
            return getClassesThatExtendAClass(packageName, clazz);

        List<Class<?>> returnList = new ArrayList<>();

        new Reflections(packageName, new SubTypesScanner())
                .getSubTypesOf(clazz).forEach(aClass -> {

                    if (!excludedClasses.contains(aClass)) {

                        returnList.add(aClass);

                    }

                });

        return returnList;

    }

    @NotNull
    public static List<Object> getInstantiatedClassesThatExtendAClass(@NotNull String packageName, @NotNull Class<?> clazz) {

        List<Object> returnList = new ArrayList<>();

        getClassesThatExtendAClass(packageName, clazz).forEach(aClass -> {

            try {

                returnList.add(aClass.newInstance());

            } catch (Exception ignored) {
            }

        });

        return returnList;

    }

    @NotNull
    public static List<Object> getInstantiatedClassesThatExtendAClass(@NotNull String packageName, @NotNull Class<?> clazz, @NotNull List<Class<?>> excludedClasses) {

        List<Object> returnList = new ArrayList<>();

        getClassesThatExtendAClass(packageName, clazz, excludedClasses).forEach(aClass -> {

            try {

                returnList.add(aClass.newInstance());

            } catch (Exception ignored) {
            }

        });

        return returnList;

    }

    @NotNull
    private static List<Class<?>> getClassesThatImplementAClass(@NotNull String packageName, @NotNull Class<?> clazz) {

        return new ArrayList<>(new Reflections(packageName).getSubTypesOf(clazz));

    }

    @NotNull
    public static List<Class<?>> getClassesThatImplementAClass(@NotNull String packageName, Class<?> clazz, @NotNull List<Class<?>> excludedClasses) {

        if (excludedClasses.isEmpty())
            return getClassesThatImplementAClass(packageName, clazz);

        List<Class<?>> returnList = new ArrayList<>();

        new Reflections(packageName, new SubTypesScanner())
                .getSubTypesOf(clazz).forEach(aClass -> {

                    if (!excludedClasses.contains(aClass)) {

                        returnList.add(aClass);

                    }

                });

        return returnList;

    }

    @NotNull
    public static List<Object> getInstantiatedClassesThatImplementAClass(@NotNull String packageName, @NotNull Class<?> clazz) {

        List<Object> returnList = new ArrayList<>();

        getClassesThatImplementAClass(packageName, clazz).forEach(aClass -> {
            try {
                returnList.add(aClass.newInstance());
            } catch (Exception ignored) {
            }
        });

        return returnList;

    }

    @NotNull
    public static List<Object> getInstantiatedClassesThatImplementAClass(@NotNull String packageName, @NotNull Class<?> clazz, @NotNull List<Class<?>> excludedClasses) {

        List<Object> returnList = new ArrayList<>();

        getClassesThatImplementAClass(packageName, clazz, excludedClasses).forEach(aClass -> {

            try {

                returnList.add(aClass.newInstance());

            } catch (Exception ignored) {
            }

        });

        return returnList;

    }

    @NotNull
    public static Object[] getEventListeners() {

        List<Object> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        getInstantiatedClassesThatExtendAClass(StartupValues.mainPackage, ListenerAdapter.class, List.of(CommandManager.class, SelectInteractionErrorListener.class)).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add(o);

            }

        });

        getInstantiatedClassesThatExtendAClass(libraryPackageName, ListenerAdapter.class, List.of(CommandManager.class, SelectInteractionErrorListener.class)).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add(o);

            }

        });

        return returnList.toArray();

    }

    @NotNull
    public static List<IDiscordCommand> getDiscordCommands() {

        List<IDiscordCommand> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        getInstantiatedClassesThatImplementAClass(StartupValues.mainPackage, IDiscordCommand.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IDiscordCommand) o);

            }

        });

        getInstantiatedClassesThatImplementAClass(libraryPackageName, IDiscordCommand.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IDiscordCommand) o);

            }

        });

        return returnList;

    }

    @NotNull
    public static List<ISlashCommand> getSlashCommands() {

        List<ISlashCommand> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        getInstantiatedClassesThatImplementAClass(StartupValues.mainPackage, ISlashCommand.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((ISlashCommand) o);

            }

        });

        getInstantiatedClassesThatImplementAClass(libraryPackageName, ISlashCommand.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((ISlashCommand) o);

            }

        });

        return returnList;

    }

    @NotNull
    public static List<IUserContextMenu> getUserContextMenus() {

        List<IUserContextMenu> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        getInstantiatedClassesThatImplementAClass(StartupValues.mainPackage, IUserContextMenu.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IUserContextMenu) o);

            }

        });

        getInstantiatedClassesThatImplementAClass(libraryPackageName, IUserContextMenu.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IUserContextMenu) o);

            }

        });

        return returnList;

    }

    @NotNull
    public static List<IMessageContextMenu> getMessageContextMenus() {

        List<IMessageContextMenu> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        getInstantiatedClassesThatImplementAClass(StartupValues.mainPackage, IMessageContextMenu.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IMessageContextMenu) o);

            }

        });

        getInstantiatedClassesThatImplementAClass(libraryPackageName, IMessageContextMenu.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IMessageContextMenu) o);

            }

        });

        return returnList;

    }

    @NotNull
    public static List<IConsoleCommand> getConsoleCommands() {

        List<IConsoleCommand> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        getInstantiatedClassesThatImplementAClass(StartupValues.mainPackage, IConsoleCommand.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IConsoleCommand) o);

            }

        });

        getInstantiatedClassesThatImplementAClass(libraryPackageName, IConsoleCommand.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IConsoleCommand) o);

            }

        });

        return returnList;

    }

    @NotNull
    public static List<ILoopExecution> getLoopExecutions() {

        List<ILoopExecution> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        getInstantiatedClassesThatImplementAClass(StartupValues.mainPackage, ILoopExecution.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((ILoopExecution) o);

            }

        });

        getInstantiatedClassesThatImplementAClass(libraryPackageName, ILoopExecution.class).forEach(o -> {


            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((ILoopExecution) o);

            }

        });

        return returnList;

    }

    @NotNull
    public static List<IStuffPackage> getStuffPackages() {

        List<IStuffPackage> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        getInstantiatedClassesThatImplementAClass(libraryPackageName, IStuffPackage.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IStuffPackage) o);

            }

        });

        return returnList;

    }

    @NotNull
    public static List<IDevCommand> getDevCommands() {

        List<IDevCommand> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();

        getInstantiatedClassesThatImplementAClass(StartupValues.mainPackage, IDevCommand.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IDevCommand) o);

            }

        });

        getInstantiatedClassesThatImplementAClass(libraryPackageName, IDevCommand.class).forEach(o -> {

            if (!classes.contains(o.getClass())) {

                classes.add(o.getClass());
                returnList.add((IDevCommand) o);

            }

        });

        return returnList;

    }

}
