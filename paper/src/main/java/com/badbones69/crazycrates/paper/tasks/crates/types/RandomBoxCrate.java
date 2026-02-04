package com.badbones69.crazycrates.paper.tasks.crates.types;

import com.badbones69.crazycrates.paper.api.builders.types.features.CrateSpinMenu;
import com.badbones69.crazycrates.paper.api.enums.other.keys.FileKeys;
import com.badbones69.crazycrates.paper.api.objects.Crate;
import com.badbones69.crazycrates.paper.api.objects.Prize;
import com.badbones69.crazycrates.paper.api.PrizeManager;
import com.badbones69.crazycrates.paper.api.objects.gui.GuiSettings;
import com.badbones69.crazycrates.paper.managers.events.enums.EventType;
import com.ryderbelserion.fusion.paper.api.scheduler.FoliaScheduler;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.crazycrew.crazycrates.api.enums.types.KeyType;
import com.badbones69.crazycrates.paper.api.builders.CrateBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * RandomBox箱子 - 复刻自 random-box-master 插件
 * 物品从右往左滚动，类似传送带效果
 * 
 * 布局（54格，6行9列）：
 * # # # # # # # # #  ← 边框
 * # * * * * * * * #  ← 传送带
 * # * * * * * * * #  ← 传送带
 * # * * * X * * * #  ← 传送带（X为最终奖品位置）
 * # * * * * * * * #  ← 传送带
 * # # # # # # # # #  ← 边框
 * 
 * # = 玻璃板边框
 * * = 物品槽位
 * X = 最终奖品位置（slot 31）
 */
public class RandomBoxCrate extends CrateBuilder {

    public RandomBoxCrate(@NotNull final Crate crate, @NotNull final Player player, final int size) {
        super(crate, player, size);
    }

    private final Inventory inventory = getInventory();
    private final Player player = getPlayer();
    private final UUID uuid = this.player.getUniqueId();
    private final Crate crate = getCrate();

    private Map<Integer, ItemStack> items;

    @Override
    public void open(@NotNull final KeyType type, final boolean checkHand, final boolean isSilent, @Nullable final EventType eventType) {
        if (isCrateEventValid(type, checkHand, isSilent, eventType)) {
            return;
        }

        final String fileName = this.crate.getFileName();

        boolean keyCheck = this.userManager.takeKeys(this.uuid, fileName, type, this.crate.useRequiredKeys() ? this.crate.getRequiredKeys() : 1, checkHand);

        if (!keyCheck) {
            this.crateManager.removePlayerFromOpeningList(this.player);
            return;
        }

        // 设置边框
        setupBorders();

        this.items = new HashMap<>();

        // 预生成传送带上的所有物品
        for (int slot : getConveyorSlots()) {
            final Prize prize = this.crate.pickPrize(this.player);
            setItem(slot, prize.getDisplayItem(this.player, this.crate));
            this.items.put(slot, prize.getDisplayItem(this.player, this.crate));
        }

        this.player.openInventory(this.inventory);

        addCrateTask(new FoliaScheduler(this.plugin, null, this.player) {
            final List<Integer> slots = getConveyorSlots();
            
            // 迭代阶段配置（模拟 random-box-master 的多阶段速度）
            // 阶段1：快速滚动 30次，每次延迟1 tick
            // 阶段2：中速滚动 20次，每次延迟2 ticks
            // 阶段3：慢速滚动 10次，每次延迟4 ticks
            // 阶段4：超慢速 5次，每次延迟8 ticks
            final int[][] iterations = {
                {30, 1},  // {次数, 延迟}
                {20, 2},
                {10, 4},
                {5, 8}
            };

            int currentPhase = 0;
            int phaseIterations = iterations[0][0];
            int phaseDelay = iterations[0][1];
            int ticksUntilMove = phaseDelay;
            
            int full = 0;
            int open = 0;

            @Override
            public void run() {
                // 动画结束
                if (currentPhase >= iterations.length) {
                    Prize prize = null;

                    if (crateManager.isInOpeningList(player)) {
                        // 最终奖品位置（slot 31）
                        prize = crate.getPrize(items.get(31));
                    }

                    if (crate.isCyclePrize() && !PrizeManager.isCapped(crate, player)) {
                        new CrateSpinMenu(player, new GuiSettings(crate, prize, FileKeys.respin_gui.getConfiguration())).open();
                        return;
                    } else {
                        userManager.removeRespinPrize(uuid, fileName);

                        if (!crate.isCyclePersistRestart()) {
                            userManager.removeRespinCrate(uuid, fileName, userManager.getCrateRespin(uuid, fileName));
                        }
                    }

                    PrizeManager.givePrize(player, crate, prize);

                    playSound("stop-sound", Sound.Source.PLAYER, "entity.player.levelup");

                    player.closeInventory(InventoryCloseEvent.Reason.UNLOADED);

                    crateManager.removePlayerFromOpeningList(player);
                    crateManager.endCrate(player);

                    items.clear();
                    
                    cancel();
                    return;
                }

                // 延迟计数
                ticksUntilMove--;
                
                if (ticksUntilMove <= 0) {
                    // 移动物品
                    moveItems();
                    
                    // 重置延迟
                    ticksUntilMove = phaseDelay;
                    
                    // 减少当前阶段的迭代次数
                    phaseIterations--;
                    
                    // 检查是否需要进入下一阶段
                    if (phaseIterations <= 0) {
                        currentPhase++;
                        
                        if (currentPhase < iterations.length) {
                            phaseIterations = iterations[currentPhase][0];
                            phaseDelay = iterations[currentPhase][1];
                            ticksUntilMove = phaseDelay;
                        }
                    }
                }

                full++;
                open++;

                if (open > 5) {
                    player.openInventory(inventory);
                    open = 0;
                }
            }

            private void moveItems() {
                // 从右往左移动所有物品（类似传送带）
                int lastIndex = slots.size() - 1;

                // 在最右边生成新物品
                Prize newPrize = crate.pickPrize(player);
                ItemStack newItem = newPrize.getDisplayItem(player, crate);
                items.put(slots.get(lastIndex), newItem);
                setItem(slots.get(lastIndex), newItem);

                // 将所有物品向左移动一格
                for (int i = 0; i < lastIndex; i++) {
                    int currentSlot = slots.get(i);
                    int nextSlot = slots.get(i + 1);
                    
                    items.put(currentSlot, items.get(nextSlot));
                    setItem(currentSlot, inventory.getItem(nextSlot));
                }

                // 播放移动音效
                playSound("cycle-sound", Sound.Source.PLAYER, "block.note_block.hat");
            }
        }.runAtFixedRate(1, 1));
    }

    private void setupBorders() {
        // 第1行和第6行 - 玻璃板边框
        for (int i = 0; i < 9; i++) {
            setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
            setItem(45 + i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }

        // 第2-5行的左右边
        setItem(9, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        setItem(17, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        setItem(18, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        setItem(26, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        setItem(27, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        setItem(35, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        setItem(36, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        setItem(44, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
    }

    private List<Integer> getConveyorSlots() {
        // 传送带槽位（第2-5行，中间7列）
        // 从左到右，从上到下排列
        return Arrays.asList(
            // 第2行
            10, 11, 12, 13, 14, 15, 16,
            // 第3行
            19, 20, 21, 22, 23, 24, 25,
            // 第4行（包含最终奖品位置 slot 31）
            28, 29, 30, 31, 32, 33, 34,
            // 第5行
            37, 38, 39, 40, 41, 42, 43
        );
    }
}
