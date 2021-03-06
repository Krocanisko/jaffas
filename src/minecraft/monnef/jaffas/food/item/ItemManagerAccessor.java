package monnef.jaffas.food.item;

import monnef.core.IDProvider;
import monnef.jaffas.food.common.ModulesEnum;
import net.minecraft.item.Item;

public abstract class ItemManagerAccessor {
    public abstract ModulesEnum getMyModule();

    protected abstract void InitializeItemInfos();

    protected abstract void CreateItems();

    protected void AddItemInfo(JaffaItem item, String name, int iconIndex, String title) {
        ItemManager.AddItemInfo(item, name, iconIndex, title, this.getMyModule());
    }

    protected Item createJaffaItem(JaffaItem ji) {
        return ItemManager.createJaffaItem(ji, JaffaItemType.basic, this.getMyModule());
    }

    protected IItemTool createJaffaTool(JaffaItem ji) {
        return ItemManager.createJaffaTool(ji, this.getMyModule());
    }

    protected IItemFood createJaffaFood(JaffaItem ji) {
        return ItemManager.createJaffaFood(ji, this.getMyModule());
    }

    protected IItemPack createJaffaPack(JaffaItem ji) {
        return ItemManager.createJaffaPack(ji, this.getMyModule());
    }

    protected Item createJaffaItemManual(JaffaItem ji, Class<? extends Item> item) {
        return ItemManager.createJaffaItemManual(ji, item);
    }

    protected Item createJaffaItemManual(JaffaItem ji, Item item) {
        return ItemManager.createJaffaItemManual(ji, item);
    }

    public void LoadItemsFromConfig(IDProvider idProvider) {
        ItemManager.LoadItemsFromConfig(this.getMyModule(), idProvider);
    }

    public void RegisterItemType(JaffaItemType type, Class<? extends Item> clazz) {
        ItemManager.RegisterItemTypeForModule(this.getMyModule(), type, clazz);
    }
}
