package czescjestemadas.kmduels.commands.utils.parsers.duels;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import czescjestemadas.kmduels.kits.DuelKit;

public class DuelKitArgParser implements ArgParser<DuelKit>
{
	private final Duels duels;

	public DuelKitArgParser(Duels duels)
	{
		this.duels = duels;
	}

	@Override
	public DuelKit get(String arg)
	{
		return duels.getKitManager().getKit(arg);
	}
}
