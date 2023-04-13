package de.thedesigncraft.discord.bot.stufftest;

import de.thedesigncraft.discord.core.DBS;
import de.thedesigncraft.discord.core.setup.DBSSetupBuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class TestBot {

    public static void main(String[] args) {

        new DBS(new DBSSetupBuilder()
                .setMainPackage("de.thedesigncraft.discord.bot.stufftest")
                .setToken(System.getenv("TOKEN"))
                .setBotLogChannelById(831503129610158090L)
                .addBotDevelopersById(Long.parseLong(System.getenv("DEV_ID")))
                .addGatewayIntents(GatewayIntent.MESSAGE_CONTENT)
                .build());

    }

}
