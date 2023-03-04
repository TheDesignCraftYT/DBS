package de.thedesigncraft.discord.bot.stufftest;

import de.thedesigncraft.manage.DBS;
import de.thedesigncraft.manage.setup.DBSSetupBuilder;

public class TestBot {

    public static void main(String[] args) throws InterruptedException {

        new DBS(new DBSSetupBuilder().setMainPackage("de.thedesigncraft.discord.bot.stufftest").setToken("MTAzNDExNzY1MTMzMDUwNjgyMw.GvcWDk.CX6bKYl_QaK8tnw7RZ7bWBo5ohcjXgoNy3-KY4").build());

    }

}
