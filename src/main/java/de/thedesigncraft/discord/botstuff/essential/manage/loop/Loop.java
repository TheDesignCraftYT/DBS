package de.thedesigncraft.discord.botstuff.essential.manage.loop;

import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Loop {

    public static Thread loop;
    @NotNull
    public static HashMap<ILoopExecution, Long> time = new HashMap<>();

    public static void runLoop() {

        if (Manager.getLoopExecutions().isEmpty())
            return;

        loop = new Thread(() -> {

            ILoopExecution.getActivatedLoopExecutions().forEach(iLoopExecution -> time.put(iLoopExecution, System.currentTimeMillis()));

            while (true) {

                time.forEach((iLoopExecution, aLong) -> {

                    int i = iLoopExecution.secondsInterval() * 1000;

                    if (System.currentTimeMillis() >= time.get(iLoopExecution) + i) {

                        time.replace(iLoopExecution, time.get(iLoopExecution), System.currentTimeMillis());

                        iLoopExecution.execute(Main.jda);

                    }

                });

            }

        });

        loop.setName("Loop");
        loop.start();

    }

}
