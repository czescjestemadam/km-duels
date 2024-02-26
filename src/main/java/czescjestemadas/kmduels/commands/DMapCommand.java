package czescjestemadas.kmduels.commands;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.maps.DuelMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static czescjestemadas.kmduels.utils.StrUtils.retMatches;

public class DMapCommand implements TabExecutor
{
	private final Duels duels;
	private final Component help = Component.text("/dmap <create | remove | pointA | pointB> <name>").color(NamedTextColor.RED);

	public DMapCommand(Duels duels)
	{
		this.duels = duels;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1 && args[0].equalsIgnoreCase("list"))
		{
			sender.sendMessage("maps: " + duels.getMapManager().getMapNames());
			return true;
		}

		if (args.length < 2)
		{
			sender.sendMessage(help);
			return true;
		}

		final String action = args[0];
		final String name = args[1];

		if (action.equalsIgnoreCase("create") && sender.hasPermission("km-duels.map.create"))
		{
			if (duels.getMapManager().createMap(name))
				sender.sendMessage("created map " + name);
			else
				sender.sendMessage("map " + name + " already exists");

			return true;
		}
		else if (action.equalsIgnoreCase("remove") && sender.hasPermission("km-duels.map.remove"))
		{
			if (duels.getMapManager().removeMap(name))
				sender.sendMessage("removed map " + name);
			else
				sender.sendMessage("map " + name + " not found");

			return true;
		}
		else if (action.equalsIgnoreCase("pointA") && sender.hasPermission("km-duels.map.edit") && sender instanceof Player player)
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage("map " + name + " not found");
				return true;
			}

			map.setWorld(player.getWorld());
			map.setPointA(player.getLocation().toVector().toVector3i());
			map.normalizePoints();
			sender.sendMessage("set point A to " + map.getPointA());
			return true;
		}
		else if (action.equalsIgnoreCase("pointB") && sender.hasPermission("km-duels.map.edit") && sender instanceof Player player)
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage("map " + name + " not found");
				return true;
			}

			map.setWorld(player.getWorld());
			map.setPointB(player.getLocation().toVector().toVector3i());
			map.normalizePoints();
			sender.sendMessage("set point B to " + map.getPointB());
			return true;
		}
		else if (action.equalsIgnoreCase("setDisplayname") && sender.hasPermission("km-duels.kit.edit") && args.length > 2)
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage("map " + name + " not found");
				return true;
			}

			map.setDisplayname(MiniMessage.miniMessage().deserialize(args[2]));
			sender.sendMessage(Component.text("set " + name + " displayname to: ").append(map.getDisplayname()));
			return true;
		}
		else if (action.equalsIgnoreCase("info"))
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage("map " + name + " not found");
				return true;
			}

			sender.sendMessage("name: " + map.getName());
			sender.sendMessage(Component.text("displayname: ").append(map.getDisplayname()));
			sender.sendMessage("world: " + map.getWorld());
			sender.sendMessage("point A: " + map.getPointA());
			sender.sendMessage("point B: " + map.getPointB());
			return true;
		}

		sender.sendMessage(help);
		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1)
			return retMatches(args[0], "list", "create", "remove", "pointA", "pointB", "setDisplayname", "info");

		if (args.length == 2 && (args[0].equalsIgnoreCase("remove") || args[0].startsWith("point") || args[0].equalsIgnoreCase("setDisplayname") || args[0].equalsIgnoreCase("info")))
			return retMatches(args[1], duels.getMapManager().getMapNames());

		return List.of();
	}
}
