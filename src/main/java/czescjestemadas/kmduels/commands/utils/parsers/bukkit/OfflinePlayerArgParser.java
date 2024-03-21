package czescjestemadas.kmduels.commands.utils.parsers.bukkit;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class OfflinePlayerArgParser implements ArgParser<OfflinePlayer>
{
	@Override
	public OfflinePlayer get(String arg)
	{
		return Bukkit.getOfflinePlayer(arg);
	}
}
