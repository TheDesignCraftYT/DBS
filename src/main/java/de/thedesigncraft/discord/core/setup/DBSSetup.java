package de.thedesigncraft.discord.core.setup;

import de.thedesigncraft.discord.tools.Checks;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.List;

public class DBSSetup {

    private final String token;
    private final Activity activity;
    private final OnlineStatus status;
    private final String mainPackage;
    private final List<Object> eventListeners;
    private final List<GatewayIntent> gatewayIntents;
    private final List<CacheFlag> cacheFlags;
    private final MemberCachePolicy memberCachePolicy;
    private final List<Long> botDevelopers;
    private final long botLogChannel;

    /**
     * Creates a new DBSSetup
     * @param token
     * @param activity
     * @param status
     * @param mainPackage
     * @param eventListeners
     * @param gatewayIntents
     * @param cacheFlags
     * @param memberCachePolicy
     * @param botDevelopers
     * @param botLogChannel
     */
    public DBSSetup(String token, Activity activity, OnlineStatus status, String mainPackage, List<Object> eventListeners, List<GatewayIntent> gatewayIntents, List<CacheFlag> cacheFlags, MemberCachePolicy memberCachePolicy, List<Long> botDevelopers, long botLogChannel) {
        this.token = token;
        this.activity = activity;
        this.status = status;
        this.mainPackage = mainPackage;
        this.eventListeners = eventListeners;
        this.gatewayIntents = gatewayIntents;
        this.cacheFlags = cacheFlags;
        this.memberCachePolicy = memberCachePolicy;
        this.botDevelopers = botDevelopers;
        this.botLogChannel = botLogChannel;
    }

    /**
     * @return The token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * @return The activity
     */
    public Activity getActivity() {
        return this.activity;
    }

    /**
     * @return The status
     */
    public OnlineStatus getStatus() {
        return this.status;
    }

    /**
     * @return The main package
     */
    public String getMainPackage() {
        return this.mainPackage;
    }

    /**
     * @return The event listeners
     */
    public Object[] getEventListeners() {
        if (this.eventListeners == null)
            return null;
        return this.eventListeners.toArray();
    }

    /**
     * @return The gateway intents
     */
    public List<GatewayIntent> getGatewayIntents() {
        return this.gatewayIntents;
    }

    /**
     * @return The cache flags
     */
    public List<CacheFlag> getCacheFlags() {
        return this.cacheFlags;
    }

    /**
     * @return The member cache policy
     */
    public MemberCachePolicy getMemberCachePolicy() {
        return this.memberCachePolicy;
    }

    /**
     * @return The bot developers
     */
    public List<Long> getBotDevelopers() {
        return this.botDevelopers;
    }

    /**
     * @return The bot log channel
     */
    public long getBotLogChannel() {
        return this.botLogChannel;
    }

    /**
     * Checks if the setup is valid
     * @return If the setup is valid
     */
    public boolean isValid() {
        return !Checks.isNullOrEmpty(this.token) &&
                !Checks.isNullOrEmpty(this.mainPackage) && this.mainPackage.contains(".");
    }

}
