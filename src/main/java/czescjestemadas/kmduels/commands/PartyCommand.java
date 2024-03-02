package czescjestemadas.kmduels.commands;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.config.PartyConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PartyCommand implements TabExecutor
{
	private final Duels duels;
	private final PartyConfig cfg;

	public PartyCommand(Duels duels)
	{
		this.duels = duels;
		this.cfg = duels.getConfigManager().getPartyConfig();
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{


		return false;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		return null;
	}
}
