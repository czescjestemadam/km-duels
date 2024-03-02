package czescjestemadas.kmduels.party;

import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.ArrayList;
import java.util.List;

public class DuelParty
{
	private final DuelPlayer owner;
	private final List<DuelPlayer> players = new ArrayList<>();

	public DuelParty(DuelPlayer owner)
	{
		this.owner = owner;
	}

	public DuelPlayer getOwner()
	{
		return owner;
	}

	public List<DuelPlayer> getPlayers()
	{
		return players;
	}



}
