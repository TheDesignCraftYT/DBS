package de.thedesigncraft.discord.botstuff.botUpdates.setup;

import de.thedesigncraft.discord.botstuff.essential.Checks;
import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BotUpdateValues {

    @Nullable
    public static GitHub connection;

    @Nullable
    public static String oAuthToken;

    @Nullable
    public static String botGitHubRepoURL;

    @Nullable
    public static List<MessageChannel> updateChannels;

    @Nullable
    public static String updateInformationFooter;

    @NotNull
    public static List<Long> ownerIds = new ArrayList<>();

    @Nullable
    public static String getUserName() {

        if (botGitHubRepoURL == null)
            return null;

        String[] splitted = botGitHubRepoURL.split("/");

        return splitted[splitted.length - 2];

    }

    @Nullable
    public static String getRepoName() {

        if (botGitHubRepoURL == null)
            return null;

        String[] splitted = botGitHubRepoURL.split("/");

        return splitted[splitted.length - 1];

    }

    @Nullable
    public static GHRepository getRepository() {

        Checks.notNull(connection, "connection");

        try {
            return connection.getUser(BotUpdateValues.getUserName()).getRepository(BotUpdateValues.getRepoName());
        } catch (IOException e) {
            LoggerFactory.getLogger(BotUpdateValues.class).error(e.getMessage());
            return null;
        }

    }

    @Nullable
    public static List<GHRelease> getReleases() {

        try {
            return getRepository().getReleases();
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }

    }

    @NotNull
    public static List<User> getOwners() {
        List<User> returnList = new ArrayList<>();
        ownerIds.forEach(aLong -> Main.jda.retrieveUserById(aLong).queue(returnList::add));
        return returnList;
    }

}
