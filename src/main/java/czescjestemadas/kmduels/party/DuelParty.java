package czescjestemadas.kmduels.party;

import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DuelParty
{
	private DuelPlayer owner;
	private final Set<DuelPlayer> players = new HashSet<>();
	private boolean open = false;
	private final Set<UUID> bannedPlayers = new HashSet<>();

	public DuelParty(DuelPlayer owner)
	{
		this.owner = owner;
		this.players.add(owner);
	}

	public DuelPlayer getOwner()
	{
		return owner;
	}

	public void setOwner(DuelPlayer owner)
	{
		this.owner = owner;
	}

	public Set<DuelPlayer> getPlayers()
	{
		return players;
	}

	public boolean isOpen()
	{
		return open;
	}

	public void setOpen(boolean open)
	{
		this.open = open;
	}

	public Set<UUID> getBannedPlayers()
	{
		return bannedPlayers;
	}

	public boolean isPlayerBanned(UUID player)
	{
		return bannedPlayers.contains(player);
	}

	public void banPlayer(UUID player)
	{
		bannedPlayers.add(player);
	}

	public void unbanPlayer(UUID player)
	{
		bannedPlayers.remove(player);
	}

	public String getName()
	{
		return "Party " + getOwner().getPlayer().getName();
	}

}
