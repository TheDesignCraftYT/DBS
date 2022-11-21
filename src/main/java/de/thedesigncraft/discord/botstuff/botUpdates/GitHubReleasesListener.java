package de.thedesigncraft.discord.botstuff.botUpdates;

import de.thedesigncraft.discord.botstuff.botUpdates.setup.BotUpdateValues;
import de.thedesigncraft.discord.botstuff.essential.manage.loop.ILoopExecution;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;
import net.dv8tion.jda.api.JDA;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kohsuke.github.GHRelease;

import java.util.ArrayList;
import java.util.List;

public class GitHubReleasesListener implements ILoopExecution {

    @Override
    public boolean activated() {
        return IStuffPackage.activatedStuffPackageClasses().contains(BotUpdatesPackage.class);
    }

    @Override
    public int secondsInterval() {
        return 5;
    }

    @Nullable
    private static List<GHRelease> latestReleases;

    @Override
    public void execute(@NotNull JDA jda) {

        if (!IStuffPackage.activatedStuffPackageClasses().contains(BotUpdatesPackage.class))
            return;

        if (latestReleases == null)
            latestReleases = BotUpdateValues.getReleases();

        if (latestReleases.equals(BotUpdateValues.getReleases()))
            return;

        if (BotUpdateValues.getReleases() == null)
            return;

        List<GHRelease> releasesToSend = new ArrayList<>(BotUpdateValues.getReleases());
        releasesToSend.removeAll(latestReleases);
        latestReleases = BotUpdateValues.getReleases();

        releasesToSend.forEach(ghRelease -> {

            StartupValues.botVersions.forEach(version -> {

                if (ghRelease.getTagName().equalsIgnoreCase(version.getName())) {

                    Updates.sendUpdateMessageToAll(version);

                }

            });

        });

    }
}
