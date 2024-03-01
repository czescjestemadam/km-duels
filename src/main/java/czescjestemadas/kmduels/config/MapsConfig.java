package czescjestemadas.kmduels.config;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class MapsConfig implements Config
{
	public boolean rollback;
	public boolean rollbackRemoveEndcrystals;
	public boolean preventEscaping;

	public Component msgHelp;
	public Component msgListPrefix;
	public String msgListItem;
	public String msgCreate;
	public String msgRemove;
	public String msgPointA;
	public String msgPointB;
	public String msgSetDisplayname;
	public String msgInfo;
	public String msgAddSpawnPos;
	public String msgClearSpawnPos;
	public Component msgAlreadyExists;
	public Component msgNotFound;

	@Override
	public void load(ConfigurationSection cfg)
	{
		rollback = cfg.getBoolean("rollback");
		rollbackRemoveEndcrystals = cfg.getBoolean("rollback-remove-endcrystals");
		preventEscaping = cfg.getBoolean("prevent-escaping");

		final ConfigurationSection msg = cfg.getConfigurationSection("messages");
		msgHelp = miniMessage().deserialize(msg.getString("help"));
		msgListPrefix = miniMessage().deserialize(msg.getString("list.prefix"));
		msgListItem = msg.getString("list.item");
		msgCreate = msg.getString("create");
		msgRemove = msg.getString("remove");
		msgPointA = msg.getString("point-a");
		msgPointB = msg.getString("point-b");
		msgSetDisplayname = msg.getString("set-displayname");
		msgInfo = msg.getString("info");
		msgAddSpawnPos = msg.getString("add-spawn-pos");
		msgClearSpawnPos = msg.getString("clear-spawn-pos");
		msgAlreadyExists = miniMessage().deserialize(msg.getString("already-exists"));
		msgNotFound = miniMessage().deserialize(msg.getString("not-found"));
	}
}
