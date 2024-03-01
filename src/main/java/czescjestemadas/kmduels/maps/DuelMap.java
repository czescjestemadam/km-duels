package czescjestemadas.kmduels.maps;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DuelMap
{
	private final String name;
	private Component displayname;
	private WeakReference<World> world;
	private Vector pointA;
	private Vector pointB;
	private List<Location> spawnPositions = new ArrayList<>();
	private Material icon = Material.GRASS_BLOCK;

	public DuelMap(String name, Component displayname)
	{
		this.name = name;
		this.displayname = displayname;
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

	public World getWorld()
	{
		return world == null ? null : world.get();
	}

	public String getWorldName()
	{
		return getWorld() == null ? "null" : getWorld().getName();
	}

	public void setWorld(@NotNull World world)
	{
		this.world = new WeakReference<>(world);
	}

	public Vector getPointA()
	{
		return pointA;
	}

	public void setPointA(Vector pointA)
	{
		this.pointA = pointA;
	}

	public Vector getPointB()
	{
		return pointB;
	}

	public void setPointB(Vector pointB)
	{
		this.pointB = pointB;
	}

	public void normalizePoints()
	{
		if (pointA == null || pointB == null)
			return;

		final int
				ax = Math.min(pointA.getBlockX(), pointB.getBlockX()),
				ay = Math.min(pointA.getBlockY(), pointB.getBlockY()),
				az = Math.min(pointA.getBlockZ(), pointB.getBlockZ()),
				bx = Math.max(pointA.getBlockX(), pointB.getBlockX()),
				by = Math.max(pointA.getBlockY(), pointB.getBlockY()),
				bz = Math.max(pointA.getBlockZ(), pointB.getBlockZ());

		pointA = new Vector(ax, ay, az);
		pointB = new Vector(bx, by, bz);
	}

	public List<Location> getSpawnPositions()
	{
		return spawnPositions;
	}

	public void setSpawnPositions(List<Location> spawnPositions)
	{
		this.spawnPositions = spawnPositions;
	}

	public void addSpawnPosition(Location location)
	{
		getSpawnPositions().add(location);
	}

	public Material getIcon()
	{
		return icon;
	}

	public void setIcon(Material icon)
	{
		this.icon = icon;
	}

	@Override
	public String toString()
	{
		return "DuelMap{" +
				"name='" + name + '\'' +
				", displayname=" + displayname +
				", world=" + world +
				", pointA=" + pointA +
				", pointB=" + pointB +
				", spawnPositions=" + spawnPositions +
				", icon=" + icon +
				'}';
	}
}
