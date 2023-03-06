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

DBS is a Java library that makes it very easy for you to create Discord Bots in Java. <br>
It includes many basic functions and a few easy-to-use extra elements.

## Summary

Please see the [JDA Docs](https://jda.wiki) for more general information about Discord Bots in Java.

1. [Implementing DiscordBotStuff into your project](#implementing-discordbotstuff-into-your-project)
2. [Download](#download)

### Implementing [`DiscordBotStuff`](https://github.com/TheDesignCraftYT/DBS) into your project

To activate and use [`DiscordBotStuff`](https://github.com/TheDesignCraftYT/DBS) into your project, you have to put the following line into your Main class: 
```java
new de.thedesigncraft.discord.manage.DBS(YOUR_SETUP);
```
Be sure to replace `YOUR_SETUP` with a [`DBSSetup`](https://github.com/TheDesignCraftYT/DBS/blob/main/src/main/java/de/thedesigncraft/discord/manage/setup/DBSSetup.java), which you can create using the [`DBSSetupBuilder`](https://github.com/TheDesignCraftYT/DBS/blob/main/src/main/java/de/thedesigncraft/discord/manage/setup/DBSSetupBuilder.java). An example Main class could look like this:

```java
package dev.weirddude.discord.bot;

import de.thedesigncraft.discord.manage.DBS;
import de.thedesigncraft.discord.manage.setup.DBSSetup;
import de.thedesigncraft.discord.manage.setup.DBSSetupBuilder;

public class MyBot {

    public static void main(String[] args) {

        DBSSetupBuilder builder = new DBSSetupBuilder();
        
        builder.setMainPackage("dev.weirddude.discord.bot");
        builder.setToken("YOUR_TOKEN");
        
        DBSSetup setup = builder.build();

        new DBS(setup);

    }

}
```
After setting all the required setup values, there are still a few more that you can set:

- Intents (`addGatewayIntents()`)
- CacheFlags (`addCacheFlags()`)
- MemberCachePolicy (`setMemberCachePolicy()`)
- EventListeners (`addEventListeners()` !! Note: You only have to add EventListeners here, that are not in the main package, which you have set in the setup !!)
- Activity (`setActivity()`)
- Status (`setStatus()`)

To get more information about what you can set at the bot startup, see [`DBSSetupBuilder`](https://github.com/TheDesignCraftYT/DBS/blob/main/src/main/java/de/thedesigncraft/discord/manage/setup/DBSSetupBuilder.java).

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