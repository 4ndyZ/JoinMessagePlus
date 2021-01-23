# JoinMessagePlus
![Build and Test](https://github.com/4ndyZ/JoinMessagePlus/workflows/Build%20and%20Test/badge.svg) ![Release](https://github.com/4ndyZ/JoinMessagePlus/workflows/Release/badge.svg)

[JoinMessagePlus](https://dev.bukkit.org/projects/join-message-plus/) is a Bukkit/BungeeCord Plugin which allows you to change or turn off the join and quit messages of Minecraft.

Original by MrKinau.

## Example

Join-Message:

![Join-Message](https://i.imgur.com/oRt4ljY.png)

Leave-Message:

![Leave-Message](https://i.imgur.com/ooRjyOS.png)

## Features

Supports the following plugins [PlaceHolderAPI](https://www.spigotmc.org/wiki/placeholderapi-placeholders/), [AuthMeReloaded](https://dev.bukkit.org/projects/authme-reloaded) and [BungeeCord](https://ci.md-5.net/job/BungeeCord/).

## Installation

[Download](https://dev.bukkit.org/projects/join-message-plus/files) the lastest version of the plugin and put it in the `plugin` directory of your Bukkit/Spigot or BungeeCord server. Reload or restart your Minecraft server.

## Configuration
Bukkit/Spigot:

The configuration file is located under `plugins/JoinMessagePlus/config-bukkit.yml`.
```
BungeeSupport:
  Enabled: false/true # Activate this option if you only like to have Bungee Join/Quit-Messages. To use this feature you have to enable BungeeCord in the spigot.yml.
AuthMeSupport:
  Enabled: false/true # Activate this option if you only like to have Join/Quit-Messages if an user logs in successful using AuthMe.
JoinMessage:
  Enabled: true/false # Should the Join-Message be enabled.
  Message: '&6[&a+&6] %player_name%' # Your custom Join-Message.
QuitMessage:
  Enabled: true/false # Should the Quit-Message be enabled.
  Message: '&6[&4-&6] %player_name%' # Your custom Quit-Message.
```
BungeeCord:

The configuration file is located under `plugins/JoinMessagePlus/config-bungee.yml`.
```
GlobalJoinMessage:
  Enabled: true/false # Should the Global-Join-Message be enabled.
  Message: '&6[&a+&6] &6[&bGLOBAL&6] %player_name%' # Your custom Global-Join-Message.
GlobalQuitMessage:
  Enabled: true/false # Should the Global-Quit-Message be enabled.
  Message: '&6[&4-&6]&6 [&bGLOBAL&6] %player_name%'# Your custom Global-Quit-Message.
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[GPL-3.0](https://github.com/4ndyZ/JoinMessagePlus/blob/master/LICENSE)