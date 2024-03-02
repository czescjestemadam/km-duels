package czescjestemadas.kmduels.kits;

import czescjestemadas.kmduels.Duels;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class KitManager
{
	private final Duels duels;
	private final File kitsFile;
	private final List<DuelKit> kits = new ArrayList<>();

	public KitManager(Duels duels)
	{
		this.duels = duels;
		kitsFile = new File(duels.getDataFolder(), "kits.yml");
	}

	public void loadKits()
	{
		if (!kitsFile.exists())
			return;

		final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(kitsFile);
		for (String name : cfg.getKeys(false))
		{
			final ConfigurationSection cfgKit = cfg.getConfigurationSection(name);

			final DuelKit kit = new DuelKit(
					name,
					MiniMessage.miniMessage().deserialize(cfgKit.getString("displayname", name)),
					cfgKit.getList("items", new ArrayList<>()).toArray(ItemStack[]::new)
			);
			kit.setBindedMaps(cfgKit.getStringList("binded-maps"));
			kit.setIcon(Material.getMaterial(cfgKit.getString("icon")));

			kits.add(kit);
		}
	}

	public void saveKits()
	{
		if (kits.isEmpty())
			return;

		final YamlConfiguration cfg = new YamlConfiguration();
		for (DuelKit kit : kits)
		{
			final ConfigurationSection cfgKit = cfg.createSection(kit.getName());
			cfgKit.set("displayname", MiniMessage.miniMessage().serialize(kit.getDisplayname()));
			cfgKit.set("items", kit.getItems());
			cfgKit.set("binded-maps", kit.getBindedMaps() == null ? List.of() : kit.getBindedMaps());
			cfgKit.set("icon", kit.getIcon().toString());
		}

		try
		{
			cfg.save(kitsFile);
		}
		catch (IOException e)
		{
			duels.getLogger().log(Level.SEVERE, "cant save kits", e);
		}
	}

	public List<String> getKitNames()
	{
		return kits.stream().map(DuelKit::getName).toList();
	}

	public DuelKit getKit(String name)
	{
		for (DuelKit kit : kits)
		{
			if (kit.getName().equalsIgnoreCase(name))
				return kit;
		}

		return null;
	}

	public boolean createKit(String name, ItemStack[] items)
	{
		if (getKit(name) != null)
			return false;

		return kits.add(new DuelKit(name, Component.text(name), items));
	}

	public boolean removeKit(String name)
	{
		final DuelKit kit = getKit(name);
		if (kit == null)
			return false;

		return kits.remove(kit);
	}


	@Override
	public String toString()
	{
		return "KitManager{" +
				"kitsFile=" + kitsFile +
				", kits=" + kits +
				'}';
	}
}
