package de.thedesigncraft.discord.botstuff.essential.setup;

import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.categories.CommandCategory;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.essential.manage.versions.Version;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StartupSetup {

    protected static void setIntents(@NotNull GatewayIntent... intents) {
        StartupValues.gatewayIntents = new ArrayList<>(Arrays.stream(intents).toList());
    }

    protected static void setIntents(@NotNull Collection<GatewayIntent> intents) {
        StartupValues.gatewayIntents = new ArrayList<>(intents);
    }

    protected static void addIntents(@NotNull GatewayIntent... intents) {
        List<GatewayIntent> intents1 = StartupValues.gatewayIntents;
        intents1.addAll(Arrays.stream(intents).toList());
        StartupValues.gatewayIntents = intents1;
    }

    protected static void addIntents(@NotNull Collection<GatewayIntent> intents) {
        List<GatewayIntent> intents1 = StartupValues.gatewayIntents;
        intents1.addAll(intents);
        StartupValues.gatewayIntents = intents1;
    }

    protected static void setCacheFlags(@NotNull CacheFlag... flags) {
        StartupValues.cacheFlags = new ArrayList<>(Arrays.stream(flags).toList());
    }

    protected static void setCacheFlags(@NotNull Collection<CacheFlag> flags) {
        StartupValues.cacheFlags = new ArrayList<>(flags);
    }

    protected static void addCacheFlags(@NotNull CacheFlag... flags) {
        List<CacheFlag> flags1 = StartupValues.cacheFlags;
        flags1.addAll(Arrays.stream(flags).toList());
        StartupValues.cacheFlags = flags1;
    }

    protected static void addCacheFlags(@NotNull Collection<CacheFlag> flags) {
        List<CacheFlag> flags1 = StartupValues.cacheFlags;
        flags1.addAll(flags);
        StartupValues.cacheFlags = flags1;
    }

    protected static void setToken(@NotNull Version.Type type, @NotNull String token) {
        StartupValues.tokens.put(type, token);
    }

    protected static void setVersions(@NotNull List<Version> versions) {
        StartupValues.botVersions = versions;
    }

    protected static void addVersions(@NotNull List<Version> versions) {
        StartupValues.botVersions.addAll(versions);
    }

    protected static void setCommandCategories(@NotNull CommandCategory... categories) {
        StartupValues.commandCategories = new ArrayList<>(Arrays.stream(categories).toList());
    }

    protected static void setCommandCategories(@NotNull Collection<CommandCategory> categories) {
        StartupValues.commandCategories = new ArrayList<>(categories);
    }

    protected static void addCommandCategories(@NotNull CommandCategory... categories) {
        List<CommandCategory> categories1 = StartupValues.commandCategories;
        categories1.addAll(Arrays.stream(categories).toList());
        setCommandCategories(categories1);
    }

    protected static void addCommandCategories(@NotNull Collection<CommandCategory> categories) {
        List<CommandCategory> categories1 = StartupValues.commandCategories;
        categories1.addAll(categories);
        setCommandCategories(categories1);
    }

    protected static void setStandardEmbedColor(int newStandardEmbedColor) {
        StartupValues.standardEmbedColor = newStandardEmbedColor;
    }

    protected static void setStandardEmbedColor(@NotNull String hexCode) {
        StartupValues.standardEmbedColor = Integer.parseInt("0x" + hexCode);
    }

    protected static void setIssueEmbedColor(int newIssueEmbedColor) {
        StartupValues.issueEmbedColor = newIssueEmbedColor;
    }

    protected static void setIssueEmbedColor(@NotNull String hexCode) {
        StartupValues.issueEmbedColor = Integer.parseInt("0x" + hexCode);
    }

    protected static void setStandardEmbedFooterPictureLink(@NotNull String newStandardEmbedFooterPictureLink) {
        StartupValues.standardEmbedFooterPictureLink = newStandardEmbedFooterPictureLink;
    }

    protected static void setStandardEmbedFooterText(@NotNull String newStandardEmbedFooterText) {
        StartupValues.standardEmbedFooterText = newStandardEmbedFooterText;
    }

    protected static void setMemberCachePolicy(@NotNull MemberCachePolicy newMemberCachePolicy) {
        StartupValues.memberCachePolicy = newMemberCachePolicy;
    }

    protected static void setProjectName(@NotNull String newProjectName) {
        StartupValues.projectName = newProjectName;
    }

    protected static void setMainPackage(@NotNull String mainPackage) {
        StartupValues.mainPackage = mainPackage;
    }

    protected static void setActivatedStuffPackages(@NotNull IStuffPackage... packages) {
        StartupValues.activatedStuffPackages = new ArrayList<>(Arrays.stream(packages).toList());
    }

    protected static void setActivatedStuffPackages(@NotNull Collection<IStuffPackage> packages) {
        StartupValues.activatedStuffPackages = new ArrayList<>(packages);
    }

    protected static void addActivatedStuffPackages(@NotNull IStuffPackage... packages) {
        StartupValues.activatedStuffPackages.addAll(Arrays.stream(packages).toList());
    }

    protected static void addActivatedStuffPackages(@NotNull Collection<IStuffPackage> packages) {
        StartupValues.activatedStuffPackages.addAll(packages);
    }

    protected static void setActivityText(@NotNull String activityText) {
        StartupValues.activityText = activityText;
    }

}
