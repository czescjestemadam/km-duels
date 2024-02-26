package czescjestemadas.kmduels.fights;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FightManager
{
	private final Duels duels;
	private final List<FightType> types = new ArrayList<>();
	private final List<DuelFight> fights = new ArrayList<>();

	public FightManager(Duels duels)
	{
		this.duels = duels;
	}

	public void loadFightTypes()
	{

	}

	public void forceEndDraw()
	{

	}

	public List<String> getTypeNames()
	{
		return types.stream().map(FightType::getName).toList();
	}

	public FightType getType(String name)
	{
		for (FightType type : types)
		{
			if (type.getName().equalsIgnoreCase(name))
				return type;
		}

		return null;
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
