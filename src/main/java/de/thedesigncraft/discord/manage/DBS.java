package de.thedesigncraft.discord.manage;

import de.thedesigncraft.discord.listeners.InteractionErrorListener;
import de.thedesigncraft.discord.manage.commands.console.ConsoleCommandListener;
import de.thedesigncraft.discord.manage.commands.discord.DiscordCommandManager;
import de.thedesigncraft.discord.manage.setup.DBSSetup;
import de.thedesigncraft.discord.tools.Checks;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DBS {

    private static JDA jda;

    public static final Logger logger = LoggerFactory.getLogger(DBS.class);

    public static String mainPackage = null;
    public static final String dbsPackage = "de.thedesigncraft";

    public DBS(@NotNull DBSSetup setup) throws InterruptedException {
        if (!setup.isValid())
            throw new IllegalArgumentException("Setup is not valid!");
        logger.info("Setup is valid!");

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

        jda.awaitReady().addEventListener(new DiscordCommandManager());
        jda.awaitReady().addEventListener(new InteractionErrorListener());

        ConsoleCommandListener.init();

        logger.info("DBS is ready!");
        logger.info("Bot is online as " + jda.getSelfUser().getAsTag() + "!");
    }

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

    public static JDA getJDA() {
        return jda;
    }

    public static void shutdown() {
        System.out.println("--------------------");
        if (DBS.jda != null) {
            System.out.println("Shutting down...");
            System.out.println("--------------------");
            DBS.jda.getPresence().setStatus(OnlineStatus.OFFLINE);
            DBS.jda.shutdown();
            logger.info("StatusUpdate: Offline");
            System.exit(0);
        } else {
            logger.error("JDA is null");
        }
    }

}
