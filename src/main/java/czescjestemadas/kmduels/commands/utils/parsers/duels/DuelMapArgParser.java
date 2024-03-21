package czescjestemadas.kmduels.commands.utils.parsers.duels;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import czescjestemadas.kmduels.maps.DuelMap;

public class DuelMapArgParser implements ArgParser<DuelMap>
{
	private final Duels duels;

	public DuelMapArgParser(Duels duels)
	{
		this.duels = duels;
	}

	@Override
	public DuelMap get(String arg)
	{
		return duels.getMapManager().getMap(arg);
	}
}
