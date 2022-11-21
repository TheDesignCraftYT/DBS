package de.thedesigncraft.discord.botstuff.essential;

import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupMethods;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EssentialPackage implements IStuffPackage {

    @Override
    public @NotNull String name() {
        return "Essential";
    }

    @Override
    public @NotNull List<GatewayIntent> neededIntents() {
        return new ArrayList<>();
    }

    @Override
    public @NotNull List<CacheFlag> neededCacheFlags() {
        return new ArrayList<>();
    }

    @Override
    public void checkValues() {

        Checks.notEmpty(StartupValues.mainPackage, "mainPackage");
        Checks.notEmpty(StartupValues.projectName, "projectName");
        Checks.notEmpty(StartupValues.botVersions, "botVersions");
        Checks.notEmpty(StartupValues.commandCategories, "commandCategories");
        Checks.notEmpty(StartupValues.tokens, "tokens");

        StartupValues.currentVersion = StartupMethods.getCurrentVersionName();
        StartupValues.currentVersionType = StartupMethods.getCurrentVersionType();
        StartupValues.currentToken = StartupMethods.getCurrentToken();

    }

}
