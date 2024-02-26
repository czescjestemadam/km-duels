package czescjestemadas.kmduels.maps;

import net.kyori.adventure.text.Component;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

import java.lang.ref.WeakReference;

public class DuelMap
{
	private final String name;
	private Component displayname;
	private WeakReference<World> world;
	private Vector3i pointA;
	private Vector3i pointB;

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

	public void setWorld(@NotNull World world)
	{
		this.world = new WeakReference<>(world);
	}

	public Vector3i getPointA()
	{
		return pointA;
	}

	public void setPointA(Vector3i pointA)
	{
		this.pointA = pointA;
	}

	public Vector3i getPointB()
	{
		return pointB;
	}

	public void setPointB(Vector3i pointB)
	{
		this.pointB = pointB;
	}

	public void normalizePoints()
	{
		if (pointA == null || pointB == null)
			return;

		final int
				ax = Math.min(pointA.x, pointB.x),
				ay = Math.min(pointA.y, pointB.y),
				az = Math.min(pointA.z, pointB.z),
				bx = Math.max(pointA.x, pointB.x),
				by = Math.max(pointA.y, pointB.y),
				bz = Math.max(pointA.z, pointB.z);

		pointA = new Vector3i(ax, ay, az);
		pointB = new Vector3i(bx, by, bz);
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
				'}';
	}
}
