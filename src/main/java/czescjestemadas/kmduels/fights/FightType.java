package czescjestemadas.kmduels.fights;

import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.maps.DuelMap;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

public class FightType
{
	private final String name;
	private Component displayname;
	private WeakReference<DuelKit> kit;
	private List<WeakReference<DuelMap>> maps;

	public FightType(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public Component getDisplayname()
	{
		return displayname;
	}

	public void setDisplayname(Component displayname)
	{
		this.displayname = displayname;
	}

	public DuelKit getKit()
	{
		return kit == null ? null : kit.get();
	}

	public void setKit(@NotNull DuelKit kit)
	{
		this.kit = new WeakReference<>(kit);
	}

	public List<DuelMap> getMaps()
	{
		return maps == null ? null : maps.stream().map(Reference::get).filter(Objects::nonNull).toList();
	}

	public void setMaps(List<DuelMap> maps)
	{
		this.maps = maps.stream().map(WeakReference::new).toList();
	}


	@Override
	public String toString()
	{
		return "FightType{" +
				"name='" + name + '\'' +
				", displayname=" + displayname +
				", kit=" + kit +
				", maps=" + maps +
				'}';
	}
}
