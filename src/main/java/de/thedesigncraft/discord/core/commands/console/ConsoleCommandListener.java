package de.thedesigncraft.discord.core.commands.console;

import de.thedesigncraft.discord.core.GlobalLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleCommandListener extends Thread {

    /**
     * Checks for console commands and executes them.
     */
    public void run() {
        try {

            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while ((line = bufferedReader.readLine()) != null) {

                IConsoleCommand consoleCommand;

                if ((consoleCommand = IConsoleCommand.getActivatedConsoleCommandsMap().get(line.toLowerCase())) != null) {

                    if (consoleCommand.needsLines())
                        System.out.println("--------------------");

                    System.out.println(consoleCommand.execute());

                    if (consoleCommand.needsLines())
                        System.out.println("--------------------");

                } else {

                    System.out.println("--------------------");
                    System.out.println("This isn't a registered console command or this command isn't activated.");
                    System.out.println("For a list of available commands, run 'commands'.");
                    System.out.println("--------------------");

                }

            }

        } catch (Exception e) {
            GlobalLogger.exceptionError(e);
        }
    }

}
