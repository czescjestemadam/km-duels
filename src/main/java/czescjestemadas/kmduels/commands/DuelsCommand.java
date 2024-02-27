package czescjestemadas.kmduels.commands;

import czescjestemadas.kmduels.Duels;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static czescjestemadas.kmduels.utils.StrUtils.retMatches;

public class DuelsCommand implements TabExecutor
{
	private final Duels duels;

	public DuelsCommand(Duels duels)
	{
		this.duels = duels;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 0)
		{
			sender.sendMessage(Component.text("KmDuels").color(TextColor.color(153, 187, 234)));
			return true;
		}

		if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("km-duels.cmd.reload"))
		{
			duels.getConfigManager().load();
			sender.sendMessage("reloaded config");
		}
		else if (args[0].equalsIgnoreCase("save") && sender.hasPermission("km-duels.cmd.save"))
		{
			duels.getMapManager().saveMaps();
			sender.sendMessage("saved maps");
			duels.getKitManager().saveKits();
			sender.sendMessage("saved kits");
			duels.getPlayerManager().savePlayers();
			sender.sendMessage("saved players");
		}

		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1)
			return retMatches(args[0], "reload", "save");

		return List.of();
	}
}
