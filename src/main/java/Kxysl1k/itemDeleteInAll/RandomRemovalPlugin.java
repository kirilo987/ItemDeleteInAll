package Kxysl1k.itemDeleteInAll;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Chunk;

import java.util.Random;

public class RandomRemovalPlugin extends JavaPlugin {

    private final Random random = new Random();

    @Override
    public void onEnable() {
        // Логіка запуску плагіна
        getLogger().info("RandomRemovalPlugin увімкнено.");

        // Плануємо періодичне завдання
        Bukkit.getScheduler().runTaskTimer(this, this::removeRandomItems, 0L, 20L * 60 * 20); // Кожні 20 хвилин
    }

    @Override
    public void onDisable() {
        // Логіка вимкнення плагіна
        getLogger().info("RandomRemovalPlugin вимкнено.");
    }

    private void removeRandomItems() {
        for (World world : Bukkit.getWorlds()) {
            for (Chunk chunk : world.getLoadedChunks()) {
                for (BlockState state : chunk.getTileEntities()) {
                    Block block = state.getBlock();

                    // Перевіряємо, чи є блок контейнером
                    if (block.getState() instanceof Container container) {
                        Inventory inventory = container.getInventory();

                        // Перевіряємо, чи є інвентар у контейнера
                        if (inventory.isEmpty()) continue;

                        // Вибираємо рандомний слот
                        int slot = random.nextInt(inventory.getSize());
                        ItemStack item = inventory.getItem(slot);

                        if (item != null && item.getType() != Material.AIR) {
                            int amountToRemove = random.nextInt(item.getAmount()) + 1; // Рандомна кількість від 1 до кількості в слоті
                            item.setAmount(item.getAmount() - amountToRemove);

                            if (item.getAmount() <= 0) {
                                inventory.clear(slot); // Видаляємо предмет, якщо кількість досягла 0
                            }

                            // Логування в консоль
                            getLogger().info(String.format(
                                    "Видалено %d x %s з %s на координатах [%d, %d, %d]",
                                    amountToRemove,
                                    item.getType(),
                                    block.getType(),
                                    block.getX(),
                                    block.getY(),
                                    block.getZ()
                            ));
                        }
                    }
                }
            }
        }
    }
}