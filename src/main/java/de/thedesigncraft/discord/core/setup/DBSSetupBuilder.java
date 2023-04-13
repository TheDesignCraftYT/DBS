package de.thedesigncraft.discord.core.setup;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

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

    public DBSSetupBuilder() {
    }

    public DBSSetupBuilder(@NotNull DBSSetupBuilder builder) {
        this.token = builder.token;
        this.activity = builder.activity;
        this.status = builder.status;
        this.mainPackage = builder.mainPackage;
        this.eventListeners = builder.eventListeners;
        this.gatewayIntents = builder.gatewayIntents;
        this.cacheFlags = builder.cacheFlags;
        this.memberCachePolicy = builder.memberCachePolicy;
    }

    public DBSSetupBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    public DBSSetupBuilder setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public DBSSetupBuilder setStatus(OnlineStatus status) {
        this.status = status;
        return this;
    }

    public DBSSetupBuilder setMainPackage(String mainPackage) {
        this.mainPackage = mainPackage;
        return this;
    }

    public DBSSetupBuilder addEventListeners(Object... eventListeners) {
        this.eventListeners.addAll(List.of(eventListeners));
        return this;
    }

    public DBSSetupBuilder addEventListeners(List<Object> eventListeners) {
        this.eventListeners.addAll(eventListeners);
        return this;
    }

    public DBSSetupBuilder addGatewayIntents(GatewayIntent... gatewayIntents) {
        if (this.gatewayIntents == null) {
            this.gatewayIntents = List.of(gatewayIntents);
        } else {
            this.gatewayIntents.addAll(List.of(gatewayIntents));
        }
        return this;
    }

    public DBSSetupBuilder addCacheFlags(CacheFlag... cacheFlags) {
        this.cacheFlags.addAll(List.of(cacheFlags));
        return this;
    }

    public DBSSetupBuilder setMemberCachePolicy(MemberCachePolicy memberCachePolicy) {
        this.memberCachePolicy = memberCachePolicy;
        return this;
    }

    public DBSSetup build() {
        return new DBSSetup(this.token, this.activity, this.status, this.mainPackage, this.eventListeners, this.gatewayIntents, this.cacheFlags, this.memberCachePolicy);
    }

}
