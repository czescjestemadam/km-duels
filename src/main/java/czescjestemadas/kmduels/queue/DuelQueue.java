package czescjestemadas.kmduels.queue;

import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.LinkedList;
import java.util.Queue;

public class DuelQueue
{
	private final DuelKit kit;
	private final Queue<Entry> entries = new LinkedList<>();

	public DuelQueue(DuelKit kit)
	{
		this.kit = kit;
	}

	public DuelKit getKit()
	{
		return kit;
	}

	public Queue<Entry> getEntries()
	{
		return entries;
	}


	@Override
	public String toString()
	{
		return "DuelQueue{" +
				"kit=" + kit +
				", entries=" + entries +
				'}';
	}

	public record Entry(DuelPlayer player, long queuedAt)
	{
		public long getWaitingTime()
		{
			return System.currentTimeMillis() - queuedAt;
		}

		@Override
		public String toString()
		{
			return "Entry{" +
					"player=" + player +
					", queuedAt=" + queuedAt +
					'}';
		}
	}
}
