package czescjestemadas.kmduels.hotbar;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HotbarManager
{
	private final Duels duels;
	private final Map<UUID, HotbarState> states = new HashMap<>();

	public HotbarManager(Duels duels)
	{
		this.duels = duels;
	}

	public void setLoadedPlayers()
	{
		for (DuelPlayer player : duels.getPlayerManager().getPlayers())
			setState(player, HotbarState.LOBBY);
	}

	public HotbarState getState(DuelPlayer player)
	{
		return states.get(player.getOwner());
	}

	public void setState(DuelPlayer player, HotbarState state)
	{
		states.put(player.getOwner(), state);
		state.setItems(player.getPlayer().getInventory());
	}

	public void unsetState(DuelPlayer player)
	{
		states.remove(player.getOwner());
	}


	@Override
	public String toString()
	{
		return "HotbarManager{" +
				"states=" + states +
				'}';
	}
}
