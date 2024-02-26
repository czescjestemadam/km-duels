package czescjestemadas.kmduels.kits;

import czescjestemadas.kmduels.Duels;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class KitManager
{
	private final Duels duels;
	private final File kitsDir;
	private final List<DuelKit> kits = new ArrayList<>();

	public KitManager(Duels duels)
	{
		this.duels = duels;
		kitsDir = new File(duels.getDataFolder(), "kits");
		kitsDir.mkdirs();
	}

	public void loadKits()
	{
		for (File file : kitsDir.listFiles(File::isFile))
		{

		}
	}

	public void saveKits()
	{

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
}
