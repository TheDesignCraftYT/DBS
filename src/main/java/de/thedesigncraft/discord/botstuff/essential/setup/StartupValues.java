package de.thedesigncraft.discord.botstuff.essential.setup;

import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.categories.CommandCategory;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.essential.manage.versions.Version;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StartupValues {

    @Nullable
    public static String mainPackage;

    @NotNull
    public static List<GatewayIntent> gatewayIntents = new ArrayList<>();
    @NotNull
    public static List<CacheFlag> cacheFlags = new ArrayList<>();
    @Nullable
    public static MemberCachePolicy memberCachePolicy;

    @Nullable
    public static String currentToken;
    @NotNull
    public static Map<Version.Type, String> tokens = new ConcurrentHashMap<>();

    @NotNull
    public static List<Version> botVersions = new ArrayList<>();
    @Nullable
    public static String currentVersion;
    @Nullable
    public static Version.Type currentVersionType;

    @NotNull
    public static List<CommandCategory> commandCategories = new ArrayList<>();

    public static int standardEmbedColor = 0xffffff;
    public static int issueEmbedColor = 0xff5555;
    @Nullable
    public static String standardEmbedFooterPictureLink;
    @Nullable
    public static String standardEmbedFooterText;

    @Nullable
    public static String projectName;

    @NotNull
    public static List<IStuffPackage> activatedStuffPackages = new ArrayList<>();

    @Nullable
    public static String activityText;
}
