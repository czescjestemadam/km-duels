package czescjestemadas.kmduels.commands;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.kits.DuelKit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static czescjestemadas.kmduels.utils.StrUtils.retMatches;
import static czescjestemadas.kmduels.utils.StrUtils.argEquals;

public class DKitCommand implements TabExecutor
{
	private final Duels duels;
	private final Component help = Component.text("/dkit <create | remove | setDisplayname | setItems | info | get> <name>").color(NamedTextColor.RED);

	public DKitCommand(Duels duels)
	{
		this.duels = duels;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1 && args[0].equalsIgnoreCase("list"))
		{
			sender.sendMessage("kits: " + duels.getKitManager().getKitNames());
			return true;
		}

		if (args.length < 2)
		{
			sender.sendMessage(help);
			return true;
		}

		final String action = args[0];
		final String name = args[1];

		if (action.equalsIgnoreCase("create") && sender.hasPermission("km-duels.kit.create"))
		{
			final ItemStack[] items = sender instanceof Player player ? player.getInventory().getContents() : new ItemStack[]{};
			if (duels.getKitManager().createKit(name, items))
				sender.sendMessage("created kit " + name);
			else
				sender.sendMessage("kit " + name + " already exists");

			return true;
		}
		else if (action.equalsIgnoreCase("remove") && sender.hasPermission("km-duels.kit.remove"))
		{
			if (duels.getKitManager().removeKit(name))
				sender.sendMessage("removed kit " + name);
			else
				sender.sendMessage("kit " + name + " not found");

			return true;
		}
		else if (action.equalsIgnoreCase("setDisplayname") && sender.hasPermission("km-duels.kit.edit") && args.length > 2)
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage("kit " + name + " not found");
				return true;
			}

			kit.setDisplayname(MiniMessage.miniMessage().deserialize(args[2]));
			sender.sendMessage(Component.text("set " + name + " displayname to: ").append(kit.getDisplayname()));
			return true;
		}
		else if (action.equalsIgnoreCase("setItems") && sender.hasPermission("km-duels.kit.edit") && sender instanceof Player player)
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage("kit " + name + " not found");
				return true;
			}

			kit.setItems(player.getInventory().getContents());
			sender.sendMessage("items set for " + name);
			return true;
		}
		else if (action.equalsIgnoreCase("info"))
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage("kit " + name + " not found");
				return true;
			}

			sender.sendMessage("name: " + kit.getName());
			sender.sendMessage(Component.text("displayname: ").append(kit.getDisplayname()));
			sender.sendMessage("items: " + Arrays.stream(kit.getItems()).filter(Objects::nonNull).toList());
			return true;
		}
		else if (action.equalsIgnoreCase("get") && sender instanceof Player player)
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage("kit " + name + " not found");
				return true;
			}

			player.getInventory().setContents(kit.getItems());
			sender.sendMessage("got items for kit " + name);
			return true;
		}

		sender.sendMessage(help);
		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1)
			return retMatches(args[0], "list", "create", "remove", "setDisplayname", "setItems", "info", "get");

		if (args.length == 2 && argEquals(args[0], "remove", "setDisplayname", "setItems", "info", "get"))
			return retMatches(args[1], duels.getKitManager().getKitNames());

		return List.of();
	}
}
