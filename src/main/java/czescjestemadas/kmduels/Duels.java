package czescjestemadas.kmduels;

import czescjestemadas.kmduels.commands.CommandManager;
import czescjestemadas.kmduels.config.ConfigManager;
import czescjestemadas.kmduels.fights.FightManager;
import czescjestemadas.kmduels.gui.GuiManager;
import czescjestemadas.kmduels.hotbar.HotbarManager;
import czescjestemadas.kmduels.kits.KitManager;
import czescjestemadas.kmduels.listeners.ListenerManager;
import czescjestemadas.kmduels.maps.MapManager;
import czescjestemadas.kmduels.party.PartyManager;
import czescjestemadas.kmduels.players.PlayerManager;
import czescjestemadas.kmduels.queue.QueueManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Duels extends JavaPlugin
{
	private ConfigManager configManager;
	private PlayerManager playerManager;
	private KitManager kitManager;
	private MapManager mapManager;
	private PartyManager partyManager;
	private FightManager fightManager;
	private QueueManager queueManager;
	private ListenerManager listenerManager;
	private GuiManager guiManager;
	private HotbarManager hotbarManager;
	private CommandManager commandManager;

	@Override
	public void onEnable()
	{
		configManager = new ConfigManager(this);
		configManager.load();

		playerManager = new PlayerManager(this);
		playerManager.loadOnlinePlayers();

		kitManager = new KitManager(this);
		kitManager.loadKits();

		mapManager = new MapManager(this);
		mapManager.loadMaps();

		partyManager = new PartyManager(this);

		fightManager = new FightManager(this);

		queueManager = new QueueManager(this);
		queueManager.getMatchFinder().start();

		listenerManager = new ListenerManager(this);
		listenerManager.registerListeners();

		guiManager = new GuiManager(this);
		guiManager.startSelectorUpdaterTask();

		hotbarManager = new HotbarManager(this);
		hotbarManager.setLoadedPlayers();

		commandManager = new CommandManager(this);
		commandManager.registerCommands();
	}

	@Override
	public void onDisable()
	{
		guiManager.forceCloseAll();
		fightManager.forceEndDraw();
		mapManager.saveMaps();
		kitManager.saveKits();
		playerManager.savePlayers();
	}

	public ConfigManager getConfigManager()
	{
		return configManager;
	}

	public PlayerManager getPlayerManager()
	{
		return playerManager;
	}

	public KitManager getKitManager()
	{
		return kitManager;
	}

	public MapManager getMapManager()
	{
		return mapManager;
	}

	public PartyManager getPartyManager()
	{
		return partyManager;
	}

	public FightManager getFightManager()
	{
		return fightManager;
	}

	public QueueManager getQueueManager()
	{
		return queueManager;
	}

	public ListenerManager getListenerManager()
	{
		return listenerManager;
	}

	public GuiManager getGuiManager()
	{
		return guiManager;
	}

	public HotbarManager getHotbarManager()
	{
		return hotbarManager;
	}

	public CommandManager getCommandManager()
	{
		return commandManager;
	}
}
