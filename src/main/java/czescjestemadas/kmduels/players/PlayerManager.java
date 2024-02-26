package czescjestemadas.kmduels.players;

import czescjestemadas.kmduels.Duels;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class PlayerManager
{
	private final Duels duels;
	private final List<DuelPlayer> players = new ArrayList<>();

	public PlayerManager(Duels duels)
	{
		this.duels = duels;
	}

	public void loadOnlinePlayers()
	{
		for (Player player : duels.getServer().getOnlinePlayers())
		{

		}
	}

	public void savePlayers()
	{
		for (DuelPlayer player : players)
		{

		}
	}
}
