package czescjestemadas.kmduels;

import czescjestemadas.kmduels.commands.CommandManager;
import czescjestemadas.kmduels.config.ConfigManager;
import czescjestemadas.kmduels.fights.FightManager;
import czescjestemadas.kmduels.gui.GuiManager;
import czescjestemadas.kmduels.kits.KitManager;
import czescjestemadas.kmduels.listeners.ListenerManager;
import czescjestemadas.kmduels.maps.MapManager;
import czescjestemadas.kmduels.party.PartyManager;
import czescjestemadas.kmduels.players.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Duels extends JavaPlugin
{
	private ConfigManager configManager;
	private PlayerManager playerManager;
	private KitManager kitManager;
	private MapManager mapManager;
	private PartyManager partyManager;
	private FightManager fightManager;
	private ListenerManager listenerManager;
	private GuiManager guiManager;
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

		listenerManager = new ListenerManager(this);
		listenerManager.registerListeners();

		guiManager = new GuiManager(this);

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

	public ListenerManager getListenerManager()
	{
		return listenerManager;
	}

	public GuiManager getGuiManager()
	{
		return guiManager;
	}

	public CommandManager getCommandManager()
	{
		return commandManager;
	}
}
