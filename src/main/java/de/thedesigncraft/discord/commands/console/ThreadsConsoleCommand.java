package de.thedesigncraft.discord.commands.console;

import de.thedesigncraft.discord.core.GlobalThreadPool;
import de.thedesigncraft.discord.core.commands.console.IConsoleCommand;
import org.jetbrains.annotations.NotNull;

public class ThreadsConsoleCommand implements IConsoleCommand {
    @Override
    public @NotNull String getName() {
        return "threads";
    }

    @Override
    public boolean isActivated() {
        return true;
    }

    @Override
    public boolean needsLines() {
        return false;
    }

    @Override
    public @NotNull String execute() {
        return "----------------------------------------\n" +
                "**__Threadpool Stats__**\n" +
                "**Status:**\n" +
                GlobalThreadPool.getStatus() + "\n" +
                "**Pool Size:\tLargest Pool Size:\tMaximum Pool Size:**\n" +
                GlobalThreadPool.getPoolSize() + "\t\t\t\t\t\t" + GlobalThreadPool.getLargestPoolSize() + "\t\t\t\t\t\t\t\t\t" + GlobalThreadPool.getMaximumPoolSize() + "\n" +
                "**Active Threads:\tQueue Size:**\n" +
                GlobalThreadPool.getActiveCount() + "\t\t\t\t\t\t\t\t\t" + GlobalThreadPool.getQueueSize() + "\n" +
                "**Tasks:**\n" +
                GlobalThreadPool.getCompletedTaskCount() + "/" + GlobalThreadPool.getTaskCount() + " completed\n" +
                "----------------------------------------\n";
    }
}
