package czescjestemadas.kmduels.commands.utils.parsers.bukkit;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerArgParser implements ArgParser<Player>
{
	@Override
	public Player get(String arg)
	{
		return Bukkit.getPlayer(arg);
	}
}
