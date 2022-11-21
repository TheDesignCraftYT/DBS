package de.thedesigncraft.discord.botstuff.botUpdates.setup;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BotUpdatesSetup {

    public static void setOAuthToken(@NotNull String oAuthToken) {

        BotUpdateValues.oAuthToken = oAuthToken;

    }

    public static void setUpdateChannels(@NotNull MessageChannel... updateChannels) {

        BotUpdateValues.updateChannels = new ArrayList<>(Arrays.stream(updateChannels).toList());

    }

    public static void setUpdateChannels(@NotNull Collection<MessageChannel> updateChannels) {

        BotUpdateValues.updateChannels = new ArrayList<>(updateChannels);

    }

    public static void addUpdateChannels(@NotNull MessageChannel... updateChannels) {

        List<MessageChannel> updateChannels1 = BotUpdateValues.updateChannels;

        if (updateChannels1 != null) {

            updateChannels1.addAll(Arrays.stream(updateChannels).toList());
            BotUpdateValues.updateChannels = updateChannels1;

        } else {

            setUpdateChannels(updateChannels);

        }

    }

    public static void addUpdateChannels(@NotNull Collection<MessageChannel> updateChannels) {

        List<MessageChannel> updateChannels1 = BotUpdateValues.updateChannels;

        if (updateChannels1 != null) {

            updateChannels1.addAll(updateChannels);
            BotUpdateValues.updateChannels = updateChannels1;

        } else {

            setUpdateChannels(updateChannels);

        }

    }

    public static void setOwnerIds(@NotNull Long... userIds) {

        BotUpdateValues.ownerIds = Arrays.stream(userIds).toList();

    }

    public static void setOwnerIds(@NotNull Collection<Long> userIds) {

        BotUpdateValues.ownerIds = new ArrayList<>(userIds);

    }

    public static void addOwnerIds(@NotNull Long... userIds) {

        BotUpdateValues.ownerIds.addAll(Arrays.stream(userIds).toList());

    }

    public static void addOwnerIds(@NotNull Collection<Long> userIds) {

        BotUpdateValues.ownerIds.addAll(userIds);

    }

}
