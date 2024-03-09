package czescjestemadas.kmduels.listeners;

import czescjestemadas.kmduels.Duels;
import org.bukkit.event.Listener;

public final class ListenerManager
{
	private final Duels duels;

	public ListenerManager(Duels duels)
	{
		this.duels = duels;
	}

	public void registerListeners()
	{
		register(new JoinQuitListener(duels));
		register(new HotbarListener(duels));
		register(new GuiListener(duels));
		register(new PartyChatListener(duels));
	}

	private void register(Listener listener)
	{
		duels.getServer().getPluginManager().registerEvents(listener, duels);
	}
}
