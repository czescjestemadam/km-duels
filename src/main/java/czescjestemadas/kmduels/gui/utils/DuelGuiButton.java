package czescjestemadas.kmduels.gui.utils;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public class DuelGuiButton extends DuelGuiElement
{
	protected final BiConsumer<Duels, DuelPlayer> onClickLeft;
	protected final BiConsumer<Duels, DuelPlayer> onClickRight;

	public DuelGuiButton(ItemStack item, BiConsumer<Duels, DuelPlayer> onClick)
	{
		super(item);
		this.onClickLeft = onClick;
		this.onClickRight = onClick;
	}

	public DuelGuiButton(ItemStack item, BiConsumer<Duels, DuelPlayer> onClickLeft, BiConsumer<Duels, DuelPlayer> onClickRight)
	{
		super(item);
		this.onClickLeft = onClickLeft;
		this.onClickRight = onClickRight;
	}

	public void click(Duels duels, DuelPlayer player, ClickType type)
	{
		if (type.isLeftClick())
			onClickLeft.accept(duels, player);
		else if (type.isRightClick())
			onClickRight.accept(duels, player);
	}


	@Override
	public String toString()
	{
		return "DuelGuiButton{" +
				"onClickLeft=" + onClickLeft +
				", onClickRight=" + onClickRight +
				", item=" + item +
				'}';
	}
}
