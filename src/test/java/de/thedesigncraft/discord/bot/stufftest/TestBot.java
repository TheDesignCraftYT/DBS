package de.thedesigncraft.discord.bot.stufftest;

import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.categories.CommandCategoryBuilder;
import de.thedesigncraft.discord.botstuff.essential.manage.versions.Version;
import de.thedesigncraft.discord.botstuff.essential.manage.versions.VersionBuilder;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupSetup;

import java.util.Collections;

public class TestBot extends StartupSetup {

    public static void main(String[] args) {

        setMainPackage("de.thedesigncraft.discord.bot.stufftest");
        setProjectName("TestBot");
        setVersions(Collections.singletonList(new VersionBuilder().setName("v1.0.0-alpha.1").setType(Version.Type.ALPHA).build()));
        setCommandCategories(new CommandCategoryBuilder().setName("Test").build());
        setToken(Version.Type.ALPHA, "YOUR_TOKEN");

        new Main();

    }

}
