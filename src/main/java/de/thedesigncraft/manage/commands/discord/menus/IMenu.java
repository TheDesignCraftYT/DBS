package de.thedesigncraft.manage.commands.discord.menus;

import de.thedesigncraft.manage.ClassManager;
import de.thedesigncraft.manage.DBS;
import de.thedesigncraft.tools.ActionRows;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface IMenu {

    @NotNull String getPath();

    @NotNull MessageEmbed getEmbed();

    default List<ActionRow> getComponents() {
        List<Button> buttons = new ArrayList<>();
        getMenus().forEach(iMenu -> {
            if (iMenu.getPath().startsWith(getPath() + "/") && !iMenu.getPath().equals(getPath()) && !iMenu.getPath().equals(getPath() + "/")) {
                String label = iMenu.getEmbed().getTitle() == null ? iMenu.getEmbed().getDescription() : iMenu.getEmbed().getTitle();
                if (label == null)
                    label = iMenu.getPath().substring(iMenu.getPath().lastIndexOf("/") + 1);
                buttons.add(Button.of(ButtonStyle.PRIMARY, iMenu.getPath(), label));
            }
        });

        if (buttons.size() > 20)
            throw new IllegalStateException("Too many buttons! Max. 20 sub-menus per menu!");

        List<ActionRow> returnList = new ArrayList<>(ActionRows.createFromButtons(buttons));
        String[] pathSplitted = getPath().split("/");
        String path = getPath().replace("/" + pathSplitted[pathSplitted.length - 1], "");
        if (getMenuPaths().contains(path)) {
            returnList.add(ActionRow.of(Button.of(ButtonStyle.SECONDARY, path, "Back", Emoji.fromUnicode("U+25C0"))));
        } else {
            returnList.add(ActionRow.of(Button.of(ButtonStyle.SECONDARY, "cancel", "Cancel")));
        }
        return returnList;
    }

    @NotNull
    static List<IMenu> getMenus() {
        List<IMenu> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, IMenu.class).forEach(o -> returnList.add((IMenu) o));
        return returnList;
    }

    @Nullable
    static IMenu getMenuByPath(@NotNull String path) {
        for (IMenu iMenu : getMenus()) {
            if (iMenu.getPath().equals(path))
                return iMenu;
        }
        return null;
    }

    @NotNull
    static List<String> getMenuPaths() {
        List<String> returnList = new ArrayList<>();
        getMenus().forEach(iMenu -> returnList.add(iMenu.getPath()));
        return returnList;
    }

}
