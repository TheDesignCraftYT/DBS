package de.thedesigncraft.discord.botstuff.essential.loops;

import de.thedesigncraft.discord.botstuff.essential.Checks;
import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.loop.ILoopExecution;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ActivityLoop implements ILoopExecution {

    @Override
    public boolean activated() {
        return true;
    }

    @Override
    public int secondsInterval() {
        return 5;
    }

    @Override
    public void execute(@NotNull JDA jda) {

        if (Checks.isEmptyOrNull(StartupValues.activityText))
            return;

        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(activity(StartupValues.activityText));

    }

    @NotNull
    static Activity activity(@NotNull String activityText) {

        List<Long> userIds = new ArrayList<>();

        List<User> users = new ArrayList<>();

        Main.jda.getGuilds().forEach(guild -> guild.getMembers().forEach(member -> users.add(member.getUser())));

        users.forEach(user -> {

            if (!userIds.contains(user.getIdLong()) && !user.isBot()) {

                userIds.add(user.getIdLong());

            }

        });

        int sizeOfReachableUsers = userIds.size();

        return Activity.listening(activityText.replace("%reachableUsers", String.valueOf(sizeOfReachableUsers)));

    }

}
