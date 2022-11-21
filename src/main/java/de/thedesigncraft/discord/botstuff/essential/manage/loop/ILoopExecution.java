package de.thedesigncraft.discord.botstuff.essential.manage.loop;

import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import net.dv8tion.jda.api.JDA;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface ILoopExecution {

    boolean activated();

    int secondsInterval();

    void execute(@NotNull JDA jda);

    @NotNull
    static List<ILoopExecution> getLoopExecutions() {

        return Manager.getLoopExecutions();

    }

    @NotNull
    static List<ILoopExecution> getActivatedLoopExecutions() {

        return new ArrayList<>(getLoopExecutions().stream().filter(ILoopExecution::activated).toList());

    }

}
