package EnderChest.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryCloseEvent;
import cn.nukkit.inventory.Inventory;
import me.iwareq.fakeinventories.FakeInventory;

public class EventListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        Player player = event.getPlayer();
        Inventory inv = event.getInventory();

        if (!(inv instanceof FakeInventory))
            return;
        if (!inv.getTitle().equalsIgnoreCase("§5Enderchest"))
            return;

        player.getEnderChestInventory().setContents(inv.getContents());
    }
}
