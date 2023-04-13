package de.thedesigncraft.discord.bot.stufftest;

import de.thedesigncraft.discord.listeners.InteractionErrorListener;
import de.thedesigncraft.discord.manage.DBS;
import de.thedesigncraft.discord.manage.setup.DBSSetupBuilder;

public class TestBot {

    public static void main(String[] args) {

        new DBS(new DBSSetupBuilder()
                .setMainPackage("de.thedesigncraft.discord.bot.stufftest")
                .setToken(System.getenv("TOKEN"))
                .build());

        InteractionErrorListener.addUserById(Long.parseLong(System.getenv("DEV_ID")));

    }

}
