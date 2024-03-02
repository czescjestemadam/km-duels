package czescjestemadas.kmduels.queue;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.config.QueueConfig;
import czescjestemadas.kmduels.hotbar.HotbarState;
import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QueueManager
{
	private final Duels duels;
	private final QueueConfig cfg;
	private final Map<DuelKit, DuelQueue> queues = new HashMap<>();
	private final MatchFinder matchFinder;

	public QueueManager(Duels duels)
	{
		this.duels = duels;
		this.cfg = duels.getConfigManager().getQueueConfig();
		this.matchFinder = new MatchFinder(duels, cfg);
	}

	public Map<DuelKit, DuelQueue> getQueues()
	{
		return queues;
	}

	public DuelQueue getQueue(DuelKit kit)
	{
		return queues.get(kit);
	}

	public @NotNull DuelQueue createQueue(DuelKit kit)
	{
		final DuelQueue queue = new DuelQueue(kit);
		queues.put(kit, queue);
		return queue;
	}

	public @NotNull DuelQueue getOrCreateQueue(DuelKit kit)
	{
		DuelQueue queue = getQueue(kit);
		if (queue == null)
			queue = createQueue(kit);
		return queue;
	}

	public DuelQueue getQueue(DuelPlayer player)
	{
		for (DuelQueue queue : queues.values())
		{
			for (DuelQueue.Entry entry : queue.getEntries())
			{
				if (entry.player().equals(player))
					return queue;
			}
		}

		return null;
	}

	public DuelQueue.Entry getQueueEntry(DuelPlayer player)
	{
		for (DuelQueue queue : queues.values())
		{
			for (DuelQueue.Entry entry : queue.getEntries())
			{
				if (entry.player().equals(player))
					return entry;
			}
		}

		return null;
	}

	public boolean removeQueueEntry(DuelPlayer player)
	{
		for (DuelQueue queue : queues.values())
		{
			for (Iterator<DuelQueue.Entry> iterator = queue.getEntries().iterator(); iterator.hasNext(); )
			{
				final DuelQueue.Entry entry = iterator.next();
				if (entry.player().equals(player))
				{
					iterator.remove();
					return true;
				}
			}
		}

		return false;
	}

	public void queue(DuelPlayer player, DuelKit kit, @Nullable HotbarState nextState, boolean message)
	{
		if (getQueueEntry(player) != null)
			return;

		final DuelQueue queue = getOrCreateQueue(kit);
		queue.getEntries().add(new DuelQueue.Entry(player, System.currentTimeMillis()));
		player.getPlayer().closeInventory();

		if (nextState != null)
			duels.getHotbarManager().setState(player, nextState);
		if (message)
			player.getPlayer().sendMessage(cfg.msgEnter);
	}

	public void leaveQueue(DuelPlayer player, @Nullable HotbarState nextState, boolean message)
	{
		if (!removeQueueEntry(player))
			return;

		if (nextState != null)
			duels.getHotbarManager().setState(player, nextState);
		if (message)
			player.getPlayer().sendMessage(cfg.msgLeave);
	}

	public MatchFinder getMatchFinder()
	{
		return matchFinder;
	}


	@Override
	public String toString()
	{
		return "QueueManager{" +
				"queues=" + queues +
				", matchFinder=" + matchFinder +
				'}';
	}
}
