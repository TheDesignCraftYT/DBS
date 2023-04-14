package de.thedesigncraft.discord.core.commands.discord.types.user;

import de.thedesigncraft.discord.core.ClassManager;
import de.thedesigncraft.discord.core.DBS;
import de.thedesigncraft.discord.core.commands.discord.events.DBSUserCommandInteractionEvent;
import de.thedesigncraft.discord.core.commands.discord.types.IDiscordCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface IUserContextMenu extends IDiscordCommand {

    /**
     * @return The setup of the command
     */
    @NotNull
    UserCommandSetup getSetup();

    /**
     * The code that should be executed when the command is called
     * @param event The command event
     */
    void execute(@NotNull DBSUserCommandInteractionEvent event);

    /**
     * @return All registered user context menus
     */
    @NotNull
    static List<IUserContextMenu> getUserContextMenus() {
        List<IUserContextMenu> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, IUserContextMenu.class).forEach(o -> returnList.add((IUserContextMenu) o));
        return returnList;
    }

    /**
     * @param name The name of the command
     * @return The command with the given name or null if no command with the given name was found
     */
    @Nullable
    static IUserContextMenu getByName(@NotNull String name) {
        List<IUserContextMenu> results = getUserContextMenus().stream().filter(iUserContextMenu -> iUserContextMenu.getSetup().getName().equals(name)).toList();
        return results.size() > 0 ? results.get(0) : null;
    }

}
