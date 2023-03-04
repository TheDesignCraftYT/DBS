package de.thedesigncraft.manage.commands.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleCommandListener {

    public static void init() {

        new Thread(() -> {

            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            try {

                while ((line = bufferedReader.readLine()) != null) {

                    IConsoleCommand consoleCommand;

                    if ((consoleCommand = IConsoleCommand.getActivatedConsoleCommandsMap().get(line.toLowerCase())) != null) {

                        if (consoleCommand.needsLines())
                            System.out.println("--------------------");

                        consoleCommand.execute();

                        if (consoleCommand.needsLines())
                            System.out.println("--------------------");

                    } else {

                        System.out.println("--------------------");
                        System.out.println("This isn't a registered console command or this command isn't activated.");
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
