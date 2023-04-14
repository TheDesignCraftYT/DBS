package de.thedesigncraft.discord.tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public interface EmbedTemplates {

    /**
     * Creates an issue embed
     *
     * @param issue The issue
     * @return The {@link MessageEmbed}
     */
    @NotNull
    static MessageEmbed issueEmbed(@NotNull String issue) {
        return new EmbedBuilder().setTitle("❌ Error").setColor(0xff5555).setDescription("> " + issue).build();
    }

    /**
     * Creates a standard embed with a title and a description.
     *
     * @param title       The title of the embed.
     * @param description The description of the embed.
     * @return The {@link MessageEmbed}
     */
    @NotNull
    static MessageEmbed standardEmbed(@NotNull String title, @NotNull String description) {
        return new EmbedBuilder().setTitle(title).setDescription(description).setTimestamp(OffsetDateTime.now()).setColor(0xffffff).build();
    }

    /**
     * Creates a standard embed with a description.
     *
     * @param description The description of the embed.
     * @return The {@link MessageEmbed}
     */
    @NotNull
    static MessageEmbed standardEmbed(@NotNull String description) {
        return new EmbedBuilder().setDescription(description).setColor(0xffffff).build();
    }

}
