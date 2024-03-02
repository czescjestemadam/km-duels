package czescjestemadas.kmduels.party;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.config.PartyConfig;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartyManager
{
	private final Duels duels;
	private final PartyConfig cfg;
	private final List<DuelParty> parties = new ArrayList<>();

	public PartyManager(Duels duels)
	{
		this.duels = duels;
		this.cfg = duels.getConfigManager().getPartyConfig();
	}

	public List<DuelParty> getParties()
	{
		return Collections.unmodifiableList(parties);
	}

	public DuelParty getParty(DuelPlayer player)
	{
		for (DuelParty party : parties)
		{
			if (party.getPlayers().contains(player))
				return party;
		}

		return null;
	}

	public DuelParty createParty(DuelPlayer player)
	{
		final DuelParty party = new DuelParty(player);
		parties.add(party);
		return party;
	}

	public DuelParty removeParty(DuelPlayer player)
	{
		final DuelParty party = getParty(player);
		if (party == null)
			return null;

		removeParty(party);
		return party;
	}

	public void removeParty(DuelParty party)
	{
		parties.remove(party);
	}
}
