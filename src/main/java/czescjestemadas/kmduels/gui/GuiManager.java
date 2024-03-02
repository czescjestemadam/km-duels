package czescjestemadas.kmduels.gui;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.gui.utils.DuelGui;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiManager
{
	private final Duels duels;
	private final Map<UUID, DuelGui> openGuis = new HashMap<>();

	public GuiManager(Duels duels)
	{
		this.duels = duels;
	}

	public void startSelectorUpdaterTask()
	{
		final int interval = duels.getConfigManager().getGuiConfig().selectorsUpdateInterval;
		duels.getServer().getScheduler().runTaskTimer(duels, this::selectorUpdater, interval, interval);
	}

	public void forceCloseAll()
	{
		for (DuelPlayer player : duels.getPlayerManager().getPlayers())
			close(player);
	}

	public void open(DuelPlayer player, DuelGui gui)
	{
		openGuis.put(player.getOwner(), gui);
		player.getPlayer().openInventory(gui.getInventory());
		gui.onOpen();
	}

	public void close(DuelPlayer player)
	{
		player.getPlayer().closeInventory();
		onClose(player);
	}

	public void onClose(DuelPlayer player)
	{
		openGuis.remove(player.getOwner());
	}

	public boolean tryClick(DuelPlayer player, int slot)
	{
		final DuelGui gui = openGuis.get(player.getOwner());
		if (gui == null)
			return false;

		gui.click(duels, player, slot);
		return true;
	}

	private void selectorUpdater()
	{
		for (DuelGui gui : openGuis.values())
			gui.onUpdate();
	}


	@Override
	public String toString()
	{
		return "GuiManager{" +
				"openGuis=" + openGuis +
				'}';
	}
}
