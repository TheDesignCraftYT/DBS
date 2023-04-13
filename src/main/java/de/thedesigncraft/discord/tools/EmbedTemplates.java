package de.thedesigncraft.discord.tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public interface EmbedTemplates {

    @NotNull
    static MessageEmbed issueEmbed(@NotNull String issue) {
        return new EmbedBuilder().setTitle("❌ Error").setColor(0xff5555).setDescription("> " + issue).build();
    }

    @NotNull
    static MessageEmbed standardEmbed(@NotNull String title, @NotNull String description) {
        return new EmbedBuilder().setTitle(title).setDescription(description).setTimestamp(OffsetDateTime.now()).setColor(0xffffff).build();
    }

    @NotNull
    static MessageEmbed standardEmbed(@NotNull String description) {
        return new EmbedBuilder().setDescription(description).setColor(0xffffff).build();
    }

}
