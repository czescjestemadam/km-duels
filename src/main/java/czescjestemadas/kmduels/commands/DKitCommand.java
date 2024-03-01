package czescjestemadas.kmduels.commands;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.config.KitsConfig;
import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.utils.ChatUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
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

import static czescjestemadas.kmduels.utils.StrUtils.argEquals;
import static czescjestemadas.kmduels.utils.StrUtils.retMatches;

public class DKitCommand implements TabExecutor
{
	private final Duels duels;
	private final KitsConfig cfg;

	public DKitCommand(Duels duels)
	{
		this.duels = duels;
		this.cfg = duels.getConfigManager().getKitsConfig();
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1 && args[0].equalsIgnoreCase("list"))
		{
			sender.sendMessage(cfg.msgListPrefix.append(
					Component.join(JoinConfiguration.commas(true),
							duels.getKitManager().getKitNames().stream()
									.map(duels.getKitManager()::getKit)
									.map(kit -> ChatUtils.mmKit(cfg.msgListItem, kit))
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

		if (action.equalsIgnoreCase("create") && sender.hasPermission("km-duels.kit.create"))
		{
			final ItemStack[] items = sender instanceof Player player ? player.getInventory().getContents() : new ItemStack[]{};
			if (duels.getKitManager().createKit(name, items))
				sender.sendMessage(ChatUtils.mmKit(cfg.msgCreate, duels.getKitManager().getKit(name)));
			else
				sender.sendMessage(cfg.msgAlreadyExists);

			return true;
		}
		else if (action.equalsIgnoreCase("remove") && sender.hasPermission("km-duels.kit.remove"))
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit != null)
			{
				duels.getKitManager().removeKit(name);
				sender.sendMessage(ChatUtils.mmKit(cfg.msgRemove, kit));
			}
			else
				sender.sendMessage(cfg.msgNotFound);

			return true;
		}
		else if (action.equalsIgnoreCase("displayname") && sender.hasPermission("km-duels.kit.edit") && args.length > 2)
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			final String str = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
			kit.setDisplayname(MiniMessage.miniMessage().deserialize(str));
			sender.sendMessage(ChatUtils.mmKit(cfg.msgSetDisplayname, kit));
			return true;
		}
		else if (action.equalsIgnoreCase("items") && sender.hasPermission("km-duels.kit.edit") && sender instanceof Player player)
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			kit.setItems(player.getInventory().getContents());
			sender.sendMessage(ChatUtils.mmKit(cfg.msgSetItems, kit));
			return true;
		}
		else if (action.equalsIgnoreCase("info"))
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			sender.sendMessage(ChatUtils.mmKit(cfg.msgInfo, kit));
			return true;
		}
		else if (action.equalsIgnoreCase("get") && sender instanceof Player player)
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			player.getInventory().setContents(kit.getItems());
			sender.sendMessage(ChatUtils.mmKit(cfg.msgGet, kit));
			return true;
		}
		else if (action.equalsIgnoreCase("bindMaps") && sender.hasPermission("km-duels.kit.edit"))
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			final String[] maps = Arrays.copyOfRange(args, 2, args.length);
			if (maps.length == 0)
			{
				kit.setBindedMaps(List.of());
				sender.sendMessage(ChatUtils.mmKit(cfg.msgUnbindMaps, kit));
			}
			else
			{
				kit.setBindedMaps(Arrays.asList(maps));
				sender.sendMessage(ChatUtils.mmKit(cfg.msgBindMaps, kit));
			}
			return true;
		}
		else if (action.equalsIgnoreCase("icon") && sender.hasPermission("km-duels.kit.edit") && sender instanceof Player player)
		{
			final DuelKit kit = duels.getKitManager().getKit(name);
			if (kit == null)
			{
				sender.sendMessage(cfg.msgNotFound);
				return true;
			}

			kit.setIcon(player.getInventory().getItemInMainHand().getType());
			sender.sendMessage(ChatUtils.mmKit(cfg.msgSetIcon, kit));
			return true;
		}

		sender.sendMessage(cfg.msgHelp);
		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1)
			return retMatches(args[0], "list", "create", "remove", "displayname", "items", "info", "get", "bindMaps", "icon");

		if (args.length == 2 && argEquals(args[0], "remove", "displayname", "items", "info", "get", "bindMaps", "icon"))
			return retMatches(args[1], duels.getKitManager().getKitNames());

		if (args.length > 2 && args[0].equalsIgnoreCase("bindMaps"))
			return retMatches(args[2], duels.getMapManager().getMapNames());

		return List.of();
	}
}
