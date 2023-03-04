package de.thedesigncraft.discord.bot.stufftest;

import de.thedesigncraft.dbs.manage.DBS;
import de.thedesigncraft.dbs.manage.setup.DBSSetupBuilder;

public class TestBot {

    public static void main(String[] args) throws InterruptedException {

        new DBS(new DBSSetupBuilder()
                .setMainPackage("de.thedesigncraft.discord.bot.stufftest")
                .setToken("YOUR_TOKEN")
                .build());

    }

}
