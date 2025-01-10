# Random Remove Item

**RandomRemovalPlugin** is a Paper/Spigot-based Minecraft server plugin that periodically removes random items from containers (chests, barrels, funnels, etc.) on your server. This can be useful for cleaning up your inventory and maintaining optimal performance.

## Features
- Gives a random number of items from containers in all loaded chunks.
- Runs automatically every 20 minutes (by default).
- Logs item removal actions to the console for tracking.

## Installation
1. Download the plugin `.jar` file.
2. Move the file to your server's `plugins` folder.
3. Start or restart your server.
4. Check if the plugin has been activated using the console (`RandomRemovalPlugin is enabled.`).

## Settings
The plugin does not support configuration files at this time. However, you can change the execution time or other behavior by editing the source code:

- **Item removal frequency:**
In the `onEnable` method, change the parameters of the `runTaskTimer` function:
```java
Bukkit.getScheduler().runTaskTimer(this, this::removeRandomItems, 0L, 20L * 60 * 20);
