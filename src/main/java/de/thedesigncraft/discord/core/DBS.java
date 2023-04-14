package de.thedesigncraft.discord.core;

import de.thedesigncraft.discord.core.commands.console.ConsoleCommandListener;
import de.thedesigncraft.discord.core.commands.console.DiscordConsoleCommandListener;
import de.thedesigncraft.discord.core.commands.discord.DiscordCommandManager;
import de.thedesigncraft.discord.core.setup.DBSSetup;
import de.thedesigncraft.discord.listeners.InteractionErrorListener;
import de.thedesigncraft.discord.tools.Checks;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DBS {

    private static JDA jda;

    public static String mainPackage = null;
    public static final String dbsPackage = "de.thedesigncraft";

    /**
     * Tries to start the bot
     * @param setup The setup
     */
    public DBS(@NotNull DBSSetup setup) {
        try {
            startup(setup);
        } catch (Exception e) {
            GlobalLogger.exceptionError(e);
        }
    }

    /**
     * Tries to start the bot
     * @param setup The setup
     * @throws Exception If something goes wrong
     */
    private static void startup(DBSSetup setup) throws Exception {
        if (!setup.isValid())
            throw new IllegalArgumentException("Setup is not valid!");
        GlobalLogger.info(DBS.class, "Setup is valid!");

        mainPackage = setup.getMainPackage();

        JDABuilder builder = JDABuilder.createDefault(setup.getToken());

        if (setup.getStatus() != null) {
            builder.setStatus(setup.getStatus());
        } else {
            builder.setStatus(OnlineStatus.ONLINE);
        }

        if (setup.getActivity() != null)
            builder.setActivity(setup.getActivity());

        if (getEventListeners().length > 0)
            builder.addEventListeners(getEventListeners());

        if (setup.getEventListeners() != null && setup.getEventListeners().length > 0)
            builder.addEventListeners(setup.getEventListeners());

        if (!Checks.isNullOrEmpty(setup.getGatewayIntents()))
            builder.enableIntents(setup.getGatewayIntents());

        if (!Checks.isNullOrEmpty(setup.getCacheFlags()))
            builder.enableCache(setup.getCacheFlags());

        if (setup.getMemberCachePolicy() != null)
            builder.setMemberCachePolicy(setup.getMemberCachePolicy());

        jda = builder.build();

        while (!jda.getStatus().equals(JDA.Status.CONNECTED)) {
            Thread.sleep(100);
        }

        DiscordConsoleCommandListener.setMessageChannelById(setup.getBotLogChannel());
        setup.getBotDevelopers().forEach(InteractionErrorListener::addUserById);

        jda.awaitReady().addEventListener(new DiscordCommandManager());
        jda.awaitReady().addEventListener(new InteractionErrorListener());

        GlobalThreadPool.execute(new ConsoleCommandListener());

        GlobalLogger.info(DBS.class, "DBS is ready!");
        GlobalLogger.info(DBS.class, "Bot is online as " + jda.getSelfUser().getAsTag() + "!");
    }

    /**
     * Gets all event listeners
     * @return The event listeners
     */
    @NotNull
    private static Object @NotNull [] getEventListeners() {
        List<Object> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();
        ClassManager.getInstantiatedClasses(mainPackage, ListenerAdapter.class, List.of(DiscordCommandManager.class, InteractionErrorListener.class)).stream().filter(o -> !classes.contains(o.getClass())).forEach(o -> {
            classes.add(o.getClass());
            returnList.add(o);
        });
        ClassManager.getInstantiatedClasses(dbsPackage, ListenerAdapter.class, List.of(DiscordCommandManager.class, InteractionErrorListener.class)).stream().filter(o -> !classes.contains(o.getClass())).forEach(o -> {
            classes.add(o.getClass());
            returnList.add(o);
        });
        return returnList.toArray();
    }

    /**
     * Gets the JDA instance
     * @return The JDA instance
     */
    public static JDA getJDA() {
        return jda;
    }

    /**
     * Shuts down the bot
     */
    public static void shutdown() {
        if (DBS.jda != null) {
            GlobalLogger.info(DBS.class, "Shutting down...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            DBS.jda.getPresence().setStatus(OnlineStatus.OFFLINE);
            DBS.jda.shutdown();
            GlobalLogger.info(DBS.class, "StatusUpdate: Offline");
            System.exit(0);
        } else {
            GlobalLogger.error(DBS.class, "JDA is null");
        }
    }

}
