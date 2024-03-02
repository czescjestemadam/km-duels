package czescjestemadas.kmduels.queue;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.config.QueueConfig;
import czescjestemadas.kmduels.utils.ChatUtils;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

import java.util.*;

public class MatchFinder
{
	private final Duels duels;
	private final QueueConfig cfg;
	private final Map<UUID, Long> lastMsg = new HashMap<>();

	public MatchFinder(Duels duels, QueueConfig cfg)
	{
		this.duels = duels;
		this.cfg = cfg;
	}

	public void start()
	{
		duels.getServer().getScheduler().runTaskTimer(duels, this::matchFinder, cfg.matchFinderInterval, cfg.matchFinderInterval);
	}

	private void matchFinder()
	{
		for (DuelQueue queue : duels.getQueueManager().getQueues().values())
			queueMatchFinder(queue);
	}

	private void queueMatchFinder(DuelQueue queue)
	{
		for (DuelQueue.Entry e : queue.getEntries())
		{
			sendQueuedMessage(e);


		}
	}

	private void sendQueuedMessage(DuelQueue.Entry e)
	{
		if (lastMsg.getOrDefault(e.player().getOwner(), 0L) + (cfg.matchFinderMessageInterval * 50L) > System.currentTimeMillis())
			return;

		e.player().getPlayer().sendMessage(ChatUtils.mm(cfg.msgSearching, Placeholder.unparsed("range", e.getWaitingTime() + "")));

		lastMsg.put(e.player().getOwner(), System.currentTimeMillis());
	}
}
