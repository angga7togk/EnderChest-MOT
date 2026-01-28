package EnderChest.commands;

import EnderChest.Main;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.inventory.InventoryType;
import me.iwareq.fakeinventories.FakeInventory;

public class EnderChest extends Command {

    public EnderChest() {
        super("enderchest", "Open your ender chest", "/enderchest [player]", new String[] { "ec" });
        this.setPermission("enderchest.use");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }
        if (!this.testPermission(sender)) {
            return false;
        }

        Player player = (Player) sender;

        // /ec
        if (args.length == 0) {

            FakeInventory ec = new FakeInventory(InventoryType.ENDER_CHEST);
            ec.setTitle("§5Enderchest");
            ec.setContents(player.getEnderChestInventory().getContents());

            player.addWindow(ec);
            return true;
        }

        // /ec <player>
        if (args.length == 1) {

            if (!player.hasPermission("enderchest.other") && !player.isOp()) {
                player.sendMessage(Main.getPlugin().getEnderchestConfig().hasNotPermission());
                return true;
            }

            Player target = Server.getInstance().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Main.getPlugin().getEnderchestConfig().isNotOnline());
                return true;
            }

            FakeInventory ec = new FakeInventory(InventoryType.CHEST);
            ec.setTitle("§5Enderchest from §e" + target.getName());
            ec.setContents(target.getEnderChestInventory().getContents());

            // view only
            ec.setDefaultItemHandler((item, event) -> event.setCancelled());

            player.addWindow(ec);
            return true;
        }

        player.sendMessage(this.usageMessage);
        return true;
    }
}
