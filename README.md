[releases-shield]: https://img.shields.io/github/v/release/TheDesignCraftYT/DBS?include_prereleases&label=Download
[download]: #download
[discord-invite]: https://discord.gg/mYKK4BwGxe
[license]: https://github.com/TheDesignCraftYT/DBS/tree/master/LICENSE
[discord-shield]: https://discord.com/api/guilds/1007268691689341030/widget.png
[license-shield]: https://img.shields.io/badge/License-Apache%202.0-white.svg
[stable-releases-shield]: https://img.shields.io/github/v/release/TheDesignCraftYT/DBS?label=Latest%20Stable
[snapshots-shield]: https://img.shields.io/github/v/release/TheDesignCraftYT/DBS?include_prereleases&label=Latest%20Snapshot

<img align="right" src="https://github.com/TheDesignCraftYT/DBS/blob/main/blob/assets/readme/dbs_logo.png?raw=true" height="200" width="200">

[ ![releases-shield][] ][download]
[ ![license-shield][] ][license]
[ ![discord-shield][] ][discord-invite]


# DBS (Discord Bot Stuff)

DBS is a Java library that makes it very easy for you to program Discord Bots in Java. <br>
It includes many basic functions and a few easy-to-use extra elements.

## Summary

Please see the [JDA Docs](https://jda.wiki) for more general information about Discord Bots in Java.

1. [Implementing DiscordBotStuff into your project](#implementing-discordbotstuff-into-your-project)
2. [StuffPackages](#stuffpackages)
3. [Download](#download)

### Implementing [`DiscordBotStuff`](https://github.com/TheDesignCraftYT/DBS) into your project

This library is separated into some packages that you can find in [`de.thedesigncraft.discord.botstuff`](https://github.com/TheDesignCraftYT/DBS/tree/main/src/main/java/de/thedesigncraft/discord/botstuff).
All of these, except for the [`essential`](https://github.com/TheDesignCraftYT/DBS/tree/main/src/main/java/de/thedesigncraft/discord/botstuff/essential) package, are disabled by default. You can activate them in your Main.

In your Main you write [`new Main();`](https://github.com/TheDesignCraftYT/DBS/blob/main/src/main/java/de/thedesigncraft/discord/botstuff/essential/manage/Main.java) and your Main should extend the [`StartupSetup`](https://github.com/TheDesignCraftYT/DBS/blob/main/src/main/java/de/thedesigncraft/discord/botstuff/essential/setup/StartupSetup.java) so your Main should look like this:

```java
package dev.weirddude.discord.bot;

import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupSetup;

public class MyBot extends StartupSetup {

  public static void main(String[] args) {

    new Main();

  }

}
```

When you try to start your Bot now, it won't work, because the bot is missing some important things. For example, when you try to start the bot now, you'll see this error in your console:

```php
Exception in thread "main" java.lang.IllegalArgumentException: mainPackage may not be null
	at de.thedesigncraft.discord.botstuff.essential.Checks.notNull(Checks.java:13)
	at de.thedesigncraft.discord.botstuff.essential.Checks.notEmpty(Checks.java:32)
	at de.thedesigncraft.discord.botstuff.essential.EssentialPackage.checkValues(EssentialPackage.java:33)
	at de.thedesigncraft.discord.botstuff.essential.manage.Main.<init>(Main.java:33)
	at de.thedesigncraft.discord.bot.stufftest.TestBot.main(TestBot.java:10)
```

So you have to set your `mainPackage`. You can set everything that is needed at the Startup in your Main now, because you extended the [`StartupSetup`](https://github.com/TheDesignCraftYT/DBS/blob/main/src/main/java/de/thedesigncraft/discord/botstuff/essential/setup/StartupSetup.java).
For example, you can set your `mainPackage` using `setMainPackage("")`. Your main package is the package that contains all your code (So in the example above it would be `dev.weirddude.discord.bot`). After setting your `mainPackage` you'll get some more errors caused by unset values.
After setting all the required values, there are still a few more that you can set:

- Intents (`setIntents()` / `addIntents()`)
- CacheFlags (`setCacheFlags()` / `addCacheFlags()`)
- MemberCachePolicy (`setMemberCachePolicy()`)
- Embed-Templates
  - StandardEmbed-Color (`setStandardEmbedColor()`)
  - StandardEmbed-FooterPictureLink (`setStandardEmbedFooterPictureLink()`)
  - StandardEmbed-FooterText (`setStandardEmbedFooterText()`)
  - IssueEmbed-Color (`setIssueEmbedColor()`)
- Activated StuffPackages (`setActivatedStuffPackages()` / `addActivatedStuffPackages()`)
- ActivityText (`setActivityText()`)

To get more information about what you can set at the bot startup, see [`StartupSetup`](https://github.com/TheDesignCraftYT/DBS/blob/main/src/main/java/de/thedesigncraft/discord/botstuff/essential/setup/StartupSetup.java).

### StuffPackages

There are a few extra features that have been divided into [`StuffPackage`](https://github.com/TheDesignCraftYT/DBS/blob/main/src/main/java/de/thedesigncraft/discord/botstuff/essential/manage/stuffPackages/IStuffPackage.java)s in addition to the [`essential-Package`](https://github.com/TheDesignCraftYT/DBS/tree/main/src/main/java/de/thedesigncraft/discord/botstuff/essential).
You can activate them in your Main using `setActivatedStuffPackages()` or `addActivatedStuffPackages()`. We currently offer these extra features:

- BotUpdates ([`botUpdates`](https://github.com/TheDesignCraftYT/DBS/tree/main/src/main/java/de/thedesigncraft/discord/botstuff/botUpdates))
- DevCommands ([`devCommands`](https://github.com/TheDesignCraftYT/DBS/tree/main/src/main/java/de/thedesigncraft/discord/botstuff/devCommands))
- InteractionErrorDetection ([`interactionErrorDetection`](https://github.com/TheDesignCraftYT/DBS/tree/main/src/main/java/de/thedesigncraft/discord/botstuff/interactionErrorDetection))
- SomeMoreCommands ([`someMoreCommands`](https://github.com/TheDesignCraftYT/DBS/tree/main/src/main/java/de/thedesigncraft/discord/botstuff/someMoreCommands))

## Download

[ ![stable-releases-shield][] ](https://github.com/TheDesignCraftYT/DBS/releases/latest)
[ ![snapshots-shield][] ](https://github.com/TheDesignCraftYT/DBS/releases/)

Be sure to replace the **`VERSION`** key below with the one of the versions shown above!

### Maven

If you haven't yet created a `settings.xml` file or you haven't yet added your GitHub-Authentication in the `settings.xml` file, take a look at [Preparing your `settings.xml` file](#preparing-your-settingsxml-file).

```xml
  <repository>
    <id>github</id>
    <name>GitHub TheDesignCraftYT Apache Maven Packages</name>
    <url>https://maven.pkg.github.com/TheDesignCraftYT/DBS</url>
  </repository>
```

```xml
<dependency>
  <groupId>de.thedesigncraft.discord</groupId>
  <artifactId>dbs</artifactId>
  <version>VERSION</version>
</dependency>
```

#### Preparing your `settings.xml` file

If you don't already have a `settings.xml` file, create one at *~/.m2/settings.xml*.

Inside the `<servers>` tag, add a `<server>` child tag with an `<id>`, set your GitHub username as `<username>` and add a [personal GitHub access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token) in `<password>`.

In the end it should look like this:
```xml
<server>
      <id>github</id>
      <username>USERNAME</username>
      <password>TOKEN</password>
</server>
```

### Gradle

Be sure to replace **`USERNAME`** below with your GitHub username and **`TOKEN`** with a [personal GitHub access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token).

**Gradle Groovy:**

```gradle
repositories {
    maven {
        url = uri 'https://maven.pkg.github.com/TheDesignCraftYT/DBS'
        credentials {
            username = project.findProperty('gpr.user') ?: 'USERNAME'
            password = project.findProperty('gpr.key') ?: 'TOKEN'
        }
    }
}
```

```gradle
dependencies {
    implementation 'de.thedesigncraft.discord.dbs:dbs:VERSION'
}
```

**Kotlin DSL:**

```gradle
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/TheDesignCraftYT/DBS")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: "USERNAME"
            password = project.findProperty("gpr.key") as String? ?: "TOKEN"
        }
    }
}
```

```gradle
dependencies {
    implementation("de.thedesigncraft.discord.dbs:dbs:VERSION")
}
```