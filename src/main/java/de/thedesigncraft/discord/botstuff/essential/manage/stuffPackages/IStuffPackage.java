package de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages;

import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupSetup;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public interface IStuffPackage {

    @NotNull
    String name();

    @NotNull
    List<GatewayIntent> neededIntents();

    @NotNull
    List<CacheFlag> neededCacheFlags();

    void checkValues();

    static void checkStuffPackageIntents() {

        List<GatewayIntent> neededIntents = new ArrayList<>();

        Manager.getStuffPackages().forEach(iStuffPackage -> {

            if (!Main.jda.getGatewayIntents().containsAll(iStuffPackage.neededIntents())) {

                iStuffPackage.neededIntents().forEach(gatewayIntent -> {

                    if (!Main.jda.getGatewayIntents().contains(gatewayIntent))
                        neededIntents.add(gatewayIntent);

                });

            }

        });

        neededIntents.forEach(gatewayIntent -> {

            List<String> packageNames = new ArrayList<>();

            Manager.getStuffPackages().stream().filter(iStuffPackage -> iStuffPackage.neededIntents().contains(gatewayIntent)).toList().forEach(iStuffPackage -> packageNames.add(iStuffPackage.name()));

            LoggerFactory.getLogger(StartupSetup.class).warn("Needed Intent: " + gatewayIntent + " " + packageNames);

        });

    }

    static void checkStuffPackageCacheFlags() {

        List<CacheFlag> neededCacheFlags = new ArrayList<>();

        Manager.getStuffPackages().stream().filter(iStuffPackage -> !Main.jda.getCacheFlags().containsAll(iStuffPackage.neededCacheFlags())).toList().forEach(iStuffPackage -> neededCacheFlags.addAll(iStuffPackage.neededCacheFlags().stream().filter(cacheFlag -> !Main.jda.getCacheFlags().contains(cacheFlag)).toList()));

        neededCacheFlags.forEach(cacheFlag -> {

            List<String> packageNames = new ArrayList<>();

            Manager.getStuffPackages().stream().filter(iStuffPackage -> iStuffPackage.neededCacheFlags().contains(cacheFlag)).toList().forEach(iStuffPackage -> packageNames.add(iStuffPackage.name()));

            LoggerFactory.getLogger(StartupSetup.class).warn("Needed CacheFlag: " + cacheFlag + " " + packageNames);

        });

    }

    static List<Class<? extends IStuffPackage>> activatedStuffPackageClasses() {

        List<Class<? extends IStuffPackage>> returnList = new ArrayList<>();

        StartupValues.activatedStuffPackages.forEach(iStuffPackage -> returnList.add(iStuffPackage.getClass()));

        return returnList;

    }

}
