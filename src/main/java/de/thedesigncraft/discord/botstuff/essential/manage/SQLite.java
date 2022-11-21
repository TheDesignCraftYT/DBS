package de.thedesigncraft.discord.botstuff.essential.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {

    public static Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(SQLite.class);

    public static void connect() {

        connection = null;

        try {

            File database = new File("database.db");

            if (!database.exists())
                database.createNewFile();

            connection = DriverManager.getConnection("jdbc:sqlite:" + database.getPath());

            logger.info("StatusUpdate: Online");

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void disconnect() {

        try {

            if (connection != null) {

                connection.close();
                logger.info("StatusUpdate: Offline");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
