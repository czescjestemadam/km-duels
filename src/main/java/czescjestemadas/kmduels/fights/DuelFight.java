package czescjestemadas.kmduels.fights;

import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.List;

public interface DuelFight
{
	List<DuelPlayer> getPlayers();

	FightState getState();

	String getName();
}
