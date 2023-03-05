package de.thedesigncraft.discord.manage.commands.discord.types.user;

import de.thedesigncraft.discord.manage.ClassManager;
import de.thedesigncraft.discord.manage.DBS;
import de.thedesigncraft.discord.manage.commands.discord.events.DBSUserCommandInteractionEvent;
import de.thedesigncraft.discord.manage.commands.discord.types.IDiscordCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface IUserContextMenu extends IDiscordCommand {

    @NotNull
    UserCommandSetup getSetup();

    void execute(@NotNull DBSUserCommandInteractionEvent event);

    @NotNull
    static List<IUserContextMenu> getUserContextMenus() {
        List<IUserContextMenu> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, IUserContextMenu.class).forEach(o -> returnList.add((IUserContextMenu) o));
        return returnList;
    }

    @Nullable
    static IUserContextMenu getByName(@NotNull String name) {
        List<IUserContextMenu> results = getUserContextMenus().stream().filter(iUserContextMenu -> iUserContextMenu.getSetup().getName().equals(name)).toList();
        return results.size() > 0 ? results.get(0) : null;
    }

}
