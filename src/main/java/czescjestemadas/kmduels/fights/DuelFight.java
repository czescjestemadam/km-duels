package czescjestemadas.kmduels.fights;

import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.maps.DuelMap;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.List;

public interface DuelFight
{
	void end(EndCause cause);

	List<DuelPlayer> getPlayers();

	DuelKit getKit();

	DuelMap getMap();

	FightState getState();

	long getStartedAt();


	enum EndCause
	{
		DEATH,
		TIMEOUT,
		PLUGIN
	}
}
