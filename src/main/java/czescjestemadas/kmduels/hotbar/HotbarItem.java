package czescjestemadas.kmduels.hotbar;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class HotbarItem
{
	private final int slot;
	private final ItemStack item;
	private final HotbarItemAction action;
	private Optional<String> command = Optional.empty();

	public HotbarItem(int slot, ItemStack item, HotbarItemAction action)
	{
		this.slot = slot;
		this.item = item;
		this.action = action;
	}

	public HotbarItem(int slot, ItemStack item, @NotNull String command)
	{
		this.slot = slot;
		this.item = item;
		this.action = HotbarItemAction.COMMAND;
		this.command = Optional.of(command);
	}

	public int getSlot()
	{
		return slot;
	}

	public ItemStack getItem()
	{
		return item;
	}

	public HotbarItemAction getAction()
	{
		return action;
	}

	public Optional<String> getCommand()
	{
		return command;
	}

	public void setCommand(String command)
	{
		this.command = Optional.ofNullable(command);
	}


	@Override
	public String toString()
	{
		return "HotbarItem{" +
				"slot=" + slot +
				", item=" + item +
				", action=" + action +
				", command=" + command +
				'}';
	}
}
