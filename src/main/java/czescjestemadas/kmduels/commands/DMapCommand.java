package czescjestemadas.kmduels.commands;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.config.MapsConfig;
import czescjestemadas.kmduels.maps.DuelMap;
import czescjestemadas.kmduels.utils.ChatUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static czescjestemadas.kmduels.utils.StrUtils.argEquals;
import static czescjestemadas.kmduels.utils.StrUtils.retMatches;

public class DMapCommand implements TabExecutor
{
	private final Duels duels;
	private final MapsConfig cfg;

	public DMapCommand(Duels duels)
	{
		this.duels = duels;
		this.cfg = duels.getConfigManager().getMapsConfig();
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1 && args[0].equalsIgnoreCase("list"))
		{
			sender.sendMessage(cfg.msgListPrefix.append(
					Component.join(JoinConfiguration.commas(true),
							duels.getMapManager().getMapNames().stream()
									.map(duels.getMapManager()::getMap)
									.map(kit -> ChatUtils.mmMap(cfg.msgListItem, kit))
									.toList())
			));
			return true;
		}

		if (args.length < 2)
		{
			sender.sendMessage(cfg.msgHelp);
			return true;
		}

		final String action = args[0];
		final String name = args[1];

		if (action.equalsIgnoreCase("create") && sender.hasPermission("km-duels.map.create"))
		{
			if (duels.getMapManager().createMap(name))
				sender.sendMessage(ChatUtils.mmMap(cfg.msgCreate, duels.getMapManager().getMap(name)));
			else
				sender.sendMessage(cfg.msgAlreadyExists);

			return true;
		}
		else if (action.equalsIgnoreCase("remove") && sender.hasPermission("km-duels.map.remove"))
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map != null)
			{
				duels.getMapManager().removeMap(name);
				sender.sendMessage(ChatUtils.mmMap(cfg.msgRemove, map));
			}
			else
				sender.sendMessage(cfg.msgNotFound);

			return true;
		}
		else if (action.equalsIgnoreCase("pointA") && sender.hasPermission("km-duels.map.edit") && sender instanceof Player player)
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			map.setWorld(player.getWorld());
			map.setPointA(player.getLocation().toVector());
			map.normalizePoints();
			sender.sendMessage(ChatUtils.mmMap(cfg.msgPointA, map));
			return true;
		}
		else if (action.equalsIgnoreCase("pointB") && sender.hasPermission("km-duels.map.edit") && sender instanceof Player player)
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			map.setWorld(player.getWorld());
			map.setPointB(player.getLocation().toVector());
			map.normalizePoints();
			sender.sendMessage(ChatUtils.mmMap(cfg.msgPointB, map));
			return true;
		}
		else if (action.equalsIgnoreCase("displayname") && sender.hasPermission("km-duels.map.edit") && args.length > 2)
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			final String str = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
			map.setDisplayname(MiniMessage.miniMessage().deserialize(str));
			sender.sendMessage(ChatUtils.mmMap(cfg.msgSetDisplayname, map));
			return true;
		}
		else if (action.equalsIgnoreCase("info"))
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			sender.sendMessage(ChatUtils.mmMap(cfg.msgInfo, map));
			return true;
		}
		else if (action.equalsIgnoreCase("addSpawnPos") && sender.hasPermission("km-duels.map.edit") && sender instanceof Player player)
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			map.addSpawnPosition(player.getLocation().clone());
			sender.sendMessage(ChatUtils.mmMap(cfg.msgAddSpawnPos, map));
			return true;
		}
		else if (action.equalsIgnoreCase("clearSpawnPos") && sender.hasPermission("km-duels.map.edit"))
		{
			final DuelMap map = duels.getMapManager().getMap(name);
			if (map == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			map.getSpawnPositions().clear();
			sender.sendMessage(ChatUtils.mmMap(cfg.msgClearSpawnPos, map));
			return true;
		}

		sender.sendMessage(cfg.msgHelp);
		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1)
			return retMatches(args[0], "list", "create", "remove", "pointA", "pointB", "displayname", "info", "addSpawnPos", "clearSpawnPos");

		if (args.length == 2 && argEquals(args[0], "remove", "point", "displayname", "info", "addSpawnPos", "clearSpawnPos"))
			return retMatches(args[1], duels.getMapManager().getMapNames());

		return List.of();
	}
}
