package de.thedesigncraft.discord.bot.stufftest;

import de.thedesigncraft.discord.listeners.InteractionErrorListener;
import de.thedesigncraft.discord.manage.DBS;
import de.thedesigncraft.discord.manage.setup.DBSSetupBuilder;

public class TestBot {

    public static void main(String[] args) throws InterruptedException {

        new DBS(new DBSSetupBuilder()
                .setMainPackage("de.thedesigncraft.discord.bot.stufftest")
                .setToken("YOUR_TOKEN")
                .build());

        InteractionErrorListener.users.add(DBS.getJDA().retrieveUserById("DEV_ID").complete());

    }

}
