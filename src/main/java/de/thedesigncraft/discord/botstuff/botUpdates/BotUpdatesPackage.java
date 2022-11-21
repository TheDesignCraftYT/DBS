package de.thedesigncraft.discord.botstuff.botUpdates;

import de.thedesigncraft.discord.botstuff.botUpdates.setup.BotUpdateValues;
import de.thedesigncraft.discord.botstuff.essential.Checks;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BotUpdatesPackage implements IStuffPackage {

    @Override
    public @NotNull String name() {
        return "BotUpdates";
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

        Checks.notNull(BotUpdateValues.oAuthToken, "oAuthToken");
        Checks.notEmpty(BotUpdateValues.updateChannels, "updateChannels");
        Checks.notEmpty(BotUpdateValues.ownerIds, "ownerIds");

        if (BotUpdateValues.connection == null) {
            try {
                BotUpdateValues.connection = new GitHubBuilder().withOAuthToken(BotUpdateValues.oAuthToken).build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
