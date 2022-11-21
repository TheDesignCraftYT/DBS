package de.thedesigncraft.discord.botstuff.essential.manage;

import de.thedesigncraft.discord.botstuff.essential.Checks;
import de.thedesigncraft.discord.botstuff.essential.EssentialPackage;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.console.ConsoleCommandListener;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.CommandManager;
import de.thedesigncraft.discord.botstuff.essential.manage.loop.Loop;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupMethods;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupSetup;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Main extends StartupSetup {

    public static JDA jda;

    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static CommandManager serverCmdMan;

    public Main() {

        SQLite.connect();

        new EssentialPackage().checkValues();
        StartupValues.activatedStuffPackages.forEach(IStuffPackage::checkValues);

        try {

            JDABuilder builder = JDABuilder
                    .createDefault(StartupValues.currentToken)
                    .setStatus(OnlineStatus.ONLINE);

            if (!Checks.isEmptyOrNull(StartupValues.activityText))
                builder.setActivity(Activity.playing(StartupValues.activityText));

            if (Manager.getEventListeners() != null && !Arrays.stream(Manager.getEventListeners()).toList().isEmpty())
                builder.addEventListeners(Manager.getEventListeners());

            if (!StartupValues.gatewayIntents.isEmpty())
                builder.enableIntents(StartupValues.gatewayIntents);

            if (!StartupValues.cacheFlags.isEmpty())
                builder.enableCache(StartupValues.cacheFlags);

            if (StartupValues.memberCachePolicy != null)
                builder.setMemberCachePolicy(StartupValues.memberCachePolicy);

            jda = builder.build();

            jda.awaitReady().addEventListener(serverCmdMan = new CommandManager());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        IStuffPackage.checkStuffPackageIntents();
        IStuffPackage.checkStuffPackageCacheFlags();

        logger.info("BotVersion: '" + StartupValues.currentVersion + "'");
        logger.info("Starting '" + StartupValues.currentVersionType + "' Bot.");
        logger.info("StatusUpdate: Online");

        StartupMethods.setDefaultStandardEmbedFooterPictureLink();

        ConsoleCommandListener.consoleCommands();

        Manager.getLoopExecutions().forEach(iLoopExecution -> iLoopExecution.execute(jda));
        Loop.runLoop();

    }

    public static void shutdown() {

        System.out.println("--------------------");

        if (Main.jda != null) {

            System.out.println(StartupValues.projectName + ": Shutting down...");

            System.out.println("--------------------");

            Main.jda.getPresence().setStatus(OnlineStatus.OFFLINE);
            Main.jda.shutdown();
            SQLite.disconnect();
            logger.info("StatusUpdate: Offline");
            if (Loop.loop != null)
                Loop.loop.interrupt();
            System.exit(0);

        } else {

            logger.error("JDA is null");

        }

    }

}
