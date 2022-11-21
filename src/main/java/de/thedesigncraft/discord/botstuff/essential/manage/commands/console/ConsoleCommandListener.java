package de.thedesigncraft.discord.botstuff.essential.manage.commands.console;

import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleCommandListener {

    public static void consoleCommands() {

        new Thread(() -> {

            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            try {

                while ((line = bufferedReader.readLine()) != null) {

                    IConsoleCommand consoleCommand;

                    if ((consoleCommand = IConsoleCommand.getActivatedConsoleCommands().get(line.toLowerCase())) != null) {

                        if (consoleCommand.printLines())
                            System.out.println("--------------------");

                        consoleCommand.code();

                        if (consoleCommand.printLines())
                            System.out.println("--------------------");

                    } else {

                        System.out.println("--------------------");
                        System.out.println(StartupValues.projectName + ": This isn't a registered console command or this command isn't activated.");
                        System.out.println("For a list of available commands, run 'commands'.");
                        System.out.println("--------------------");

                    }

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

    }

}
