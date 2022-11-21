package de.thedesigncraft.discord.botstuff.essential.setup;

import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.versions.Version;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface StartupMethods {

    @NotNull
    static List<Class<?>> getActivatedStuffPackageClasses() {

        List<Class<?>> returnList = new ArrayList<>();

        StartupValues.activatedStuffPackages.forEach(iStuffPackage -> returnList.add(iStuffPackage.getClass()));

        return returnList;

    }

    @Nullable
    static String getCurrentVersionName() {
        return StartupValues.botVersions.get(StartupValues.botVersions.size() - 1).getName();
    }

    @Nullable
    static Version.Type getCurrentVersionType() {
        return StartupValues.botVersions.get(StartupValues.botVersions.size() - 1).getType();
    }

    @Nullable
    static String getCurrentToken() {

        if (StartupValues.currentVersionType != null)
            return StartupValues.tokens.get(StartupValues.currentVersionType);

        return null;

    }

    static void setDefaultStandardEmbedFooterPictureLink() {

        StartupValues.standardEmbedFooterPictureLink = Main.jda.getSelfUser().getEffectiveAvatarUrl();

    }

}
