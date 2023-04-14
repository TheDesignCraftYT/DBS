package de.thedesigncraft.discord.core.setup;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DBSSetupBuilder {

    private String token;
    private Activity activity;
    private OnlineStatus status;
    private String mainPackage;
    private List<Object> eventListeners;
    private List<GatewayIntent> gatewayIntents;
    private List<CacheFlag> cacheFlags;
    private MemberCachePolicy memberCachePolicy;
    private List<Long> botDevelopers = new ArrayList<>();
    private long botLogChannel;

    public DBSSetupBuilder() {
    }

    /**
     * Creates a new {@link DBSSetupBuilder} from an existing {@link DBSSetupBuilder}
     * @param builder
     */
    public DBSSetupBuilder(@NotNull DBSSetupBuilder builder) {
        this.token = builder.token;
        this.activity = builder.activity;
        this.status = builder.status;
        this.mainPackage = builder.mainPackage;
        this.eventListeners = builder.eventListeners;
        this.gatewayIntents = builder.gatewayIntents;
        this.cacheFlags = builder.cacheFlags;
        this.memberCachePolicy = builder.memberCachePolicy;
        this.botDevelopers = builder.botDevelopers;
        this.botLogChannel = builder.botLogChannel;
    }

    /**
     * Sets the token for the bot
     * @param token The token
     */
    public DBSSetupBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Sets the activity for the bot
     * @param activity The activity
     */
    public DBSSetupBuilder setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    /**
     * Sets the {@link OnlineStatus} for the bot
     * @param status The status
     */
    public DBSSetupBuilder setStatus(OnlineStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Sets the main package name of the bot
     * @param mainPackage The main package
     */
    public DBSSetupBuilder setMainPackage(String mainPackage) {
        this.mainPackage = mainPackage;
        return this;
    }

    /**
     * Adds event listeners to the bot
     * @param eventListeners The event listeners
     */
    public DBSSetupBuilder addEventListeners(Object... eventListeners) {
        this.eventListeners.addAll(List.of(eventListeners));
        return this;
    }

    /**
     * Adds event listeners to the bot
     * @param eventListeners The event listeners
     */
    public DBSSetupBuilder addEventListeners(List<Object> eventListeners) {
        this.eventListeners.addAll(eventListeners);
        return this;
    }

    /**
     * Adds gateway intents to the bot
     * @param gatewayIntents The gateway intents
     */
    public DBSSetupBuilder addGatewayIntents(GatewayIntent... gatewayIntents) {
        if (this.gatewayIntents == null) {
            this.gatewayIntents = List.of(gatewayIntents);
        } else {
            this.gatewayIntents.addAll(List.of(gatewayIntents));
        }
        return this;
    }

    /**
     * Adds cache flags to the bot
     * @param cacheFlags The cache flags
     */
    public DBSSetupBuilder addCacheFlags(CacheFlag... cacheFlags) {
        this.cacheFlags.addAll(List.of(cacheFlags));
        return this;
    }

    /**
     * Sets the {@link MemberCachePolicy} for the bot
     * @param memberCachePolicy The member cache policy
     */
    public DBSSetupBuilder setMemberCachePolicy(MemberCachePolicy memberCachePolicy) {
        this.memberCachePolicy = memberCachePolicy;
        return this;
    }

    /**
     * Adds bot developers to the bot
     * @param userIds The ids of the bot developers
     */
    public DBSSetupBuilder addBotDevelopersById(Long... userIds) {
        this.botDevelopers.addAll(List.of(userIds));
        return this;
    }

    /**
     * Sets and overrides the bot developers of the bot
     * @param userIds The ids of the bot developers
     */
    public DBSSetupBuilder setBotDevelopersByIds(List<Long> userIds) {
        this.botDevelopers = new ArrayList<>(userIds);
        return this;
    }

    /**
     * Sets the bot log channel of the bot
     * @param botLogChannelId The id of the bot log channel
     */
    public DBSSetupBuilder setBotLogChannelById(Long botLogChannelId) {
        this.botLogChannel = botLogChannelId;
        return this;
    }

    /**
     * Builds the {@link DBSSetup}
     * @return The {@link DBSSetup}
     */
    public DBSSetup build() {
        return new DBSSetup(this.token, this.activity, this.status, this.mainPackage, this.eventListeners, this.gatewayIntents, this.cacheFlags, this.memberCachePolicy, this.botDevelopers, this.botLogChannel);
    }

}
