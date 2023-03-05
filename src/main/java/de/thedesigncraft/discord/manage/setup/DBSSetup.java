package de.thedesigncraft.discord.manage.setup;

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

    public DBSSetup(String token, Activity activity, OnlineStatus status, String mainPackage, List<Object> eventListeners, List<GatewayIntent> gatewayIntents, List<CacheFlag> cacheFlags, MemberCachePolicy memberCachePolicy) {
        this.token = token;
        this.activity = activity;
        this.status = status;
        this.mainPackage = mainPackage;
        this.eventListeners = eventListeners;
        this.gatewayIntents = gatewayIntents;
        this.cacheFlags = cacheFlags;
        this.memberCachePolicy = memberCachePolicy;
    }

    public String getToken() {
        return this.token;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public OnlineStatus getStatus() {
        return this.status;
    }

    public String getMainPackage() {
        return this.mainPackage;
    }

    public Object[] getEventListeners() {
        if (this.eventListeners == null)
            return null;
        return this.eventListeners.toArray();
    }

    public List<GatewayIntent> getGatewayIntents() {
        return this.gatewayIntents;
    }

    public List<CacheFlag> getCacheFlags() {
        return this.cacheFlags;
    }

    public MemberCachePolicy getMemberCachePolicy() {
        return this.memberCachePolicy;
    }

    public boolean isValid() {
        return !Checks.isNullOrEmpty(this.token) &&
                !Checks.isNullOrEmpty(this.mainPackage) && this.mainPackage.contains(".");
    }

}
