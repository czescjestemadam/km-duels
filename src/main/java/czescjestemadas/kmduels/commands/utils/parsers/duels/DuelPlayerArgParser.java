package czescjestemadas.kmduels.commands.utils.parsers.duels;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import czescjestemadas.kmduels.players.DuelPlayer;

public class DuelPlayerArgParser implements ArgParser<DuelPlayer>
{
	private final Duels duels;

	public DuelPlayerArgParser(Duels duels)
	{
		this.duels = duels;
	}

	@Override
	public DuelPlayer get(String arg)
	{
		return duels.getPlayerManager().getPlayer(arg);
	}
}
