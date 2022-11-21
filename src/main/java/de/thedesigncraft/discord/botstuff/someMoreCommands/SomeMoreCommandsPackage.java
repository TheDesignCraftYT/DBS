package de.thedesigncraft.discord.botstuff.someMoreCommands;

import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SomeMoreCommandsPackage implements IStuffPackage {

    @Override
    public @NotNull String name() {
        return "SomeMoreCommands";
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

    }
}
