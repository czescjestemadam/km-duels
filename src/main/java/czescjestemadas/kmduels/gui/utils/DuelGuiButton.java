package czescjestemadas.kmduels.gui.utils;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public class DuelGuiButton extends DuelGuiElement
{
	protected final BiConsumer<Duels, DuelPlayer> onClick;

	public DuelGuiButton(ItemStack item, BiConsumer<Duels, DuelPlayer> onClick)
	{
		super(item);
		this.onClick = onClick;
	}

	public void click(Duels duels, DuelPlayer player)
	{
		onClick.accept(duels, player);
	}


	@Override
	public String toString()
	{
		return "DuelGuiButton{" +
				"onClick=" + onClick +
				", item=" + item +
				'}';
	}
}
