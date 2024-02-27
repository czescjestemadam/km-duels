package czescjestemadas.kmduels.queue;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.LinkedList;
import java.util.Queue;

public class QueueManager
{
	private final Duels duels;
	private final Queue<DuelPlayer> queuedPlayers = new LinkedList<>();

	public QueueManager(Duels duels)
	{
		this.duels = duels;
	}

	public void startMatchFinderTask()
	{
		final long interval = 10;
		duels.getServer().getScheduler().runTaskTimer(duels, this::matchFinder, interval, interval);
	}




	private void matchFinder()
	{

	}
}
