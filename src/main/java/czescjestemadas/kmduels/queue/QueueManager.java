package czescjestemadas.kmduels.queue;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.config.QueueConfig;
import czescjestemadas.kmduels.hotbar.HotbarState;
import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueManager
{
	private final Duels duels;
	private final QueueConfig cfg;
	private final Queue<QueueEntry> queue = new LinkedList<>();

	public QueueManager(Duels duels)
	{
		this.duels = duels;
		this.cfg = duels.getConfigManager().getQueueConfig();
	}

	public void startMatchFinderTask()
	{
		final long interval = duels.getConfigManager().getQueueConfig().matchFinderInterval;
		duels.getServer().getScheduler().runTaskTimer(duels, this::matchFinder, interval, interval);
	}

	public QueueEntry getEntry(DuelPlayer player)
	{
		for (QueueEntry entry : queue)
		{
			if (entry.player.equals(player))
				return entry;
		}

		return null;
	}

	public void queue(DuelPlayer player, DuelKit kit, boolean ranked, boolean message)
	{
		if (getEntry(player) != null)
			return;

		queue.add(new QueueEntry(player, kit, ranked, System.currentTimeMillis()));
		duels.getHotbarManager().setState(player, HotbarState.QUEUED);
		if (message)
			player.getPlayer().sendMessage(cfg.msgEnter);
	}

	public void leaveQueue(DuelPlayer player, boolean message)
	{
		if (!queue.removeIf(entry -> entry.player.equals(player)))
			return;

		duels.getHotbarManager().setState(player, HotbarState.LOBBY);
		if (message)
			player.getPlayer().sendMessage(cfg.msgLeave);
	}


	private void matchFinder()
	{
		for (QueueEntry entry : queue)
		{
			final long waitingTimeSeconds = entry.getWaitingTime() / 1000;



			final List<QueueEntry> candidates = new ArrayList<>();

			for (QueueEntry entry1 : queue)
			{

			}


		}
	}


	public record QueueEntry(DuelPlayer player, DuelKit kit, boolean ranked, long queuedAt)
	{
		public long getWaitingTime()
		{
			return System.currentTimeMillis() - queuedAt;
		}
	}
}
