package czescjestemadas.kmduels.commands;

import czescjestemadas.kmduels.Duels;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
			sender.sendMessage(Component.text("KmDuels").decoration(TextDecoration.BOLD, true).color(TextColor.color(153, 187, 234)));
			return true;
		}

		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		return List.of();
	}
}
