package de.thedesigncraft.discord.core.commands.discord.types.message;

import de.thedesigncraft.discord.core.ClassManager;
import de.thedesigncraft.discord.core.DBS;
import de.thedesigncraft.discord.core.commands.discord.events.DBSMessageCommandInteractionEvent;
import de.thedesigncraft.discord.core.commands.discord.types.IDiscordCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface IMessageContextMenu extends IDiscordCommand {

    @NotNull
    MessageCommandSetup getSetup();

    void execute(@NotNull DBSMessageCommandInteractionEvent event);

    @NotNull
    static List<IMessageContextMenu> getMessageContextMenus() {
        List<IMessageContextMenu> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, IMessageContextMenu.class).forEach(o -> returnList.add((IMessageContextMenu) o));
        return returnList;
    }

    @Nullable
    static IMessageContextMenu getByName(@NotNull String name) {
        List<IMessageContextMenu> results = getMessageContextMenus().stream().filter(iMessageContextMenu -> iMessageContextMenu.getSetup().getName().equals(name)).toList();
        return results.size() > 0 ? results.get(0) : null;
    }

}
