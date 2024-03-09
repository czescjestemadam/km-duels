package czescjestemadas.kmduels.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PlayerUtils
{
	public static List<Player> getPlayers(Predicate<Player> filter)
	{
		final List<Player> players = new ArrayList<>();

		for (Player player : Bukkit.getOnlinePlayers())
		{
			if (filter.test(player))
				players.add(player);
		}

		return players;
	}

	public static List<Player> getPlayers()
	{
		return getPlayers(p -> true);
	}

	public static List<Player> getVisiblePlayersFor(Player player)
	{
		return getPlayers(player::canSee);
	}
}
