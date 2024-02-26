package czescjestemadas.kmduels.fights;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FightManager
{
	private final Duels duels;
	private final List<DuelFight> fights = new ArrayList<>();

	public FightManager(Duels duels)
	{
		this.duels = duels;
	}

	public void forceEndDraw()
	{

	}

	public List<DuelFight> getFights()
	{
		return Collections.unmodifiableList(fights);
	}

	public DuelFight getFight(DuelPlayer player)
	{
		for (DuelFight fight : fights)
		{
			if (fight.getPlayers().contains(player))
				return fight;
		}

		return null;
	}

	public void addFight(DuelFight fight)
	{
		fights.add(fight);
	}
}
