package monnef.jaffas.food;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Potion;

import java.util.Hashtable;

public class ItemManager {
    public static Hashtable<JaffaItem, JaffaItemInfo> ItemsInfo;
    public static JaffaItem[] mallets;
    public static JaffaItem[] malletHeads;

    public static Item getItem(JaffaItem item) {
        return ItemsInfo.get(item).getItem();
    }

    private void AddItemInfo(JaffaItem item, String name, int iconIndex, String title) {
        JaffaItemInfo newItem = new JaffaItemInfo(name);
        newItem.setIconIndex(iconIndex);
        newItem.setTitle(title);
        ItemsInfo.put(item, newItem);
    }

    public void InitializeItemInfos() {
        ItemsInfo = new Hashtable<JaffaItem, JaffaItemInfo>();
        AddItemInfo(JaffaItem.pastry, "Pastry", 13, "Pastry");
        AddItemInfo(JaffaItem.cake, "Cake", 1, "Sponge Cake");
        AddItemInfo(JaffaItem.jamO, "Jam Orange", 2, "Orange Jam");
        AddItemInfo(JaffaItem.jamR, "Jam Red", 3, "Apple Jam");
        AddItemInfo(JaffaItem.jaffaO, "Jaffa Orange", 4, "Orange Jaffa Cake");
        AddItemInfo(JaffaItem.jaffaR, "Jaffa Red", 5, "Apple Jaffa Cake");
        AddItemInfo(JaffaItem.jaffa, "Jaffa", 6, "Jaffa Cake");
        AddItemInfo(JaffaItem.chocolate, "Chocolate", 7, "Chocolate");
        AddItemInfo(JaffaItem.apples, "Apples", 10, "Apples");
        AddItemInfo(JaffaItem.beans, "Beans", 8, "Cocoa Powder");
        AddItemInfo(JaffaItem.sweetBeans, "Sweet Beans", 9, "Sweet Cocoa Powder");
        AddItemInfo(JaffaItem.butter, "Butter", 12, "Butter");
        AddItemInfo(JaffaItem.mallet, "Mallet", 23, "Little Wooden Mallet");
        AddItemInfo(JaffaItem.malletStone, "Mallet Stone", 24, "Little Stone Mallet");
        AddItemInfo(JaffaItem.malletIron, "Mallet Iron", 25, "Little Iron Mallet");
        AddItemInfo(JaffaItem.malletDiamond, "Mallet Diamond", 26, "Little Diamond Mallet");
        AddItemInfo(JaffaItem.malletHead, "Mallet Head", 27, "Wooden Mallet Head");
        AddItemInfo(JaffaItem.malletHeadStone, "Mallet Head Stone", 28, "Stone Mallet Head");
        AddItemInfo(JaffaItem.malletHeadIron, "Mallet Head Iron", 29, "Iron Mallet Head");
        AddItemInfo(JaffaItem.malletHeadDiamond, "Mallet Head Diamond", 30, "Diamond Mallet Head");
        AddItemInfo(JaffaItem.brownPastry, "Brown Pastry", 14, "Brown Pastry");
        AddItemInfo(JaffaItem.puffPastry, "Puff Pastry", 15, "Puff Pastry");
        AddItemInfo(JaffaItem.peanut, "Peanut", 16, "Peanut");
        AddItemInfo(JaffaItem.cream, "Cream", 17, "Cream");
        AddItemInfo(JaffaItem.sweetRoll, "Roll", 18, "Roll");
        AddItemInfo(JaffaItem.creamRoll, "Cream Roll", 19, "Cream Roll");
        AddItemInfo(JaffaItem.cakeTin, "Cake Tin", 20, "Cake Tin");
        AddItemInfo(JaffaItem.browniesInTin, "Brownies", 21, "Brownies");
        AddItemInfo(JaffaItem.brownie, "Brownie", 22, "Brownie");
        AddItemInfo(JaffaItem.sweetRollRaw, "Roll Raw", 31, "Raw Roll");
        AddItemInfo(JaffaItem.browniesInTinRaw, "Raw Brownies", 32, "Raw Brownies");
        AddItemInfo(JaffaItem.bunRaw, "Raw Bun", 44, "Raw Bun");
        AddItemInfo(JaffaItem.bun, "Bun", 45, "Bun");
        AddItemInfo(JaffaItem.sausageRaw, "Sausage Raw", 46, "Raw Sausage");
        AddItemInfo(JaffaItem.sausage, "Sausage", 47, "Sausage");
        AddItemInfo(JaffaItem.hotdog, "Hotdog", 48, "Hotdog");
        AddItemInfo(JaffaItem.flour, "Flour", 49, "Flour");
        AddItemInfo(JaffaItem.chocolateWrapper, "Chocolate Wrapper", 33, "Chocolate Wrapper");
        AddItemInfo(JaffaItem.chocolateBar, "Chocolate Bar", 34, "Chocolate Bar");
        AddItemInfo(JaffaItem.wrapperJaffas, "Wrapper Jaffas", 50, "Jaffa Cakes Wrapper");
        AddItemInfo(JaffaItem.jaffasPack, "Jaffa Cakes Pack", 51, "Jaffa Cakes Pack");
        AddItemInfo(JaffaItem.jaffasPackO, "Orange Jaffa Cakes Pack", 51, "Orange Jaffa Cakes Pack");
        AddItemInfo(JaffaItem.jaffasPackR, "Red Jaffa Cakes Pack", 51, "Apple Jaffa Cakes Pack");
        AddItemInfo(JaffaItem.vanillaBeans, "Vanilla Beans", 52, "Vanilla Beans");
        AddItemInfo(JaffaItem.waferIcecream, "Wafer Ice-cream", 53, "Wafer");
        AddItemInfo(JaffaItem.cone, "Icecream Cone", 54, "Cone");
        AddItemInfo(JaffaItem.vanillaPowder, "Vanilla Powder", 55, "Vanilla Powder");
        AddItemInfo(JaffaItem.vanillaIcecreamRaw, "Vanilla Ice-cream Raw", 56, "Vanilla Ice-cream");
        AddItemInfo(JaffaItem.chocolateIcecreamRaw, "Chocolate Ice-cream Raw", 57, "Chocolate Ice-cream");
        AddItemInfo(JaffaItem.icecreamRaw, "Ice-cream Raw", 58, "Ice-cream");
        AddItemInfo(JaffaItem.vanillaIcecream, "Vanilla Scooped Ice-cream", 59, "Scooped Ice-cream");
        AddItemInfo(JaffaItem.chocolateIcecream, "Chocolate Scooped Ice-cream", 60, "Scooped Ice-cream");
        AddItemInfo(JaffaItem.russianIcecream, "Russian Ice-cream", 61, "Russian Ice-cream");
        AddItemInfo(JaffaItem.vanillaIcecreamFrozen, "Vanilla Ice-cream Frozen", 62, "Vanilla Ice-cream *");
        AddItemInfo(JaffaItem.chocolateIcecreamFrozen, "Chocolate Ice-cream Frozen", 63, "Chocolate Ice-cream *");
        AddItemInfo(JaffaItem.icecreamFrozen, "Ice-cream Frozen", 64, "Ice-cream *");
        AddItemInfo(JaffaItem.donutRaw, "Donut Raw", 71, "Raw Donut");
        AddItemInfo(JaffaItem.donut, "Donut", 72, "Donut");
        AddItemInfo(JaffaItem.donutChocolate, "Donut Chocolate", 73, "Chocolate Donut");
        AddItemInfo(JaffaItem.donutPink, "Donut Apple", 74, "Apple Donut");
        AddItemInfo(JaffaItem.donutSugar, "Donut Sugar", 75, "Powdered Donut");
        AddItemInfo(JaffaItem.donutSprinkled, "Donut Sprinkled", 76, "Sprinkled Donut");
        AddItemInfo(JaffaItem.jaffaV, "Jaffa Vanilla", 77, "Vanilla Jaffa Cake");
        AddItemInfo(JaffaItem.jaffaL, "Jaffa Lemon", 78, "Lemon Jaffa Cake");
        AddItemInfo(JaffaItem.jamP, "Jam Plum", 79, "Plum Jam");
        AddItemInfo(JaffaItem.jamL, "Jam Lemon", 80, "Lemon Jam");
        AddItemInfo(JaffaItem.jamV, "Vanilla Jam", 81, "Vanilla Jam");
        AddItemInfo(JaffaItem.lemons, "Lemons", 82, "Lemons");
        AddItemInfo(JaffaItem.oranges, "Oranges", 83, "Oranges");
        AddItemInfo(JaffaItem.plums, "Plums", 84, "Plums");
        AddItemInfo(JaffaItem.sprinkles, "Sprinkles", 87, "Sprinkles");
        AddItemInfo(JaffaItem.bagOfSeeds, "Bag Of Seeds Unidentified", 89, "Bag Of Seeds [Unidentified]");
        AddItemInfo(JaffaItem.bagOfSeedsIdentified, "Bag Of Seeds", 89, "Bag Of Seeds");
        AddItemInfo(JaffaItem.magnifier, "Magnifier", 91, "Magnifier");
        AddItemInfo(JaffaItem.jaffaP, "Jaffa Plum", 86, "Plum Jaffa Cake");
        AddItemInfo(JaffaItem.jamMix, "Jam Mix", 110, "Mix of Jams");

        AddItemInfo(JaffaItem.kettle, "Kettle", 92, "Empty Kettle");
        AddItemInfo(JaffaItem.cup, "Cup", 93, "Cup");
        AddItemInfo(JaffaItem.cupCoffee, "Coffee Cup", 94, "Cup of Coffee");
        AddItemInfo(JaffaItem.cupRaw, "Raw Cup", 109, "Raw Cup");
        AddItemInfo(JaffaItem.kettleWaterCold, "Kettle Cold", 92, "Kettle With Cold Water");
        AddItemInfo(JaffaItem.kettleWaterHot, "Kettle Hot", 92, "Kettle With Hot Water");

        AddItemInfo(JaffaItem.omeletteRaw, "Omelette Raw", 97, "Raw Omelette");
        AddItemInfo(JaffaItem.omelette, "Omelette", 98, "Omelette");
        AddItemInfo(JaffaItem.tomatoChopped, "Tomato Chopped", 99, "Chopped Tomatos");
        AddItemInfo(JaffaItem.paprikaChopped, "Pepper Chopped", 100, "Chopped Peppers");

        AddItemInfo(JaffaItem.grinderMeat, "Meat Grinder", 101, "Meat Grinder");
        AddItemInfo(JaffaItem.wienerCocktail, "Cocktail Wiener", 102, "Cocktail Wiener");
        AddItemInfo(JaffaItem.jaffaStrawberry, "Jaffa Strawberry", 103, "Strawberry Jaffa Cake");
        AddItemInfo(JaffaItem.jaffaRaspberry, "Jaffa Raspberry", 104, "Raspberry Jaffa Cake");
        AddItemInfo(JaffaItem.raspberries, "Raspberries", 105, "Raspberries");
        AddItemInfo(JaffaItem.strawberries, "Strawberries", 106, "Strawberries");
        AddItemInfo(JaffaItem.jamRaspberry, "Jam Raspberry", 107, "Raspberry Jam");
        AddItemInfo(JaffaItem.jamStrawberry, "Jam Strawberry", 108, "Strawberry Jam");

        AddItemInfo(JaffaItem.rollRaw, "Not-Sweet Raw Roll", 111, "Raw Roll");
        AddItemInfo(JaffaItem.roll, "Not-Sweet Roll", 65, "Roll");
        AddItemInfo(JaffaItem.rollChopped, "Chopped Not-Sweet Roll", 66, "Chopped Roll");
        AddItemInfo(JaffaItem.meatChopped, "Chopped Meat", 67, "Chopped Meat");
        AddItemInfo(JaffaItem.skewer, "Skewer", 68, "Skewer");
        AddItemInfo(JaffaItem.ironSkewer, "Iron Skewer", 69, "Iron Skewer");
        AddItemInfo(JaffaItem.knifeKitchen, "Kitchen Knife", 70, "Kitchen Knife");

        AddItemInfo(JaffaItem.coffee, "Coffee", 8, "Coffee");
        AddItemInfo(JaffaItem.coffeeRoasted, "Roasted Coffee", 112, "Roasted Coffee");

        AddItemInfo(JaffaItem.skewerRaw, "Skewer Raw", 85, "Raw Skewer");
    }

    private void finilizeItemSetup(JaffaItemInfo info, Item item) {
        item.setItemName(info.getTitle());
        item.setIconIndex(info.getIconIndex());
        info.setItem(item);
        LanguageRegistry.addName(item, info.getTitle());
    }

    private ItemBagOfSeeds createBagOfSeed(JaffaItem ji) {
        JaffaItemInfo info = ItemsInfo.get(ji);
        ItemBagOfSeeds newJaffaItem = new ItemBagOfSeeds(info.getId());
        finilizeItemSetup(info, newJaffaItem);
        return newJaffaItem;
    }

    private ItemJaffaBase createJaffaItem(JaffaItem ji) {
        JaffaItemInfo info = ItemsInfo.get(ji);
        ItemJaffaBase newJaffaItem = new ItemJaffaBase(info.getId());
        finilizeItemSetup(info, newJaffaItem);
        return newJaffaItem;
    }

    private ItemJaffaPack createJaffaPack(JaffaItem ji, ItemStack content) {
        JaffaItemInfo info = ItemsInfo.get(ji);
        ItemJaffaPack newJaffaItem = new ItemJaffaPack(info.getId(), content);
        finilizeItemSetup(info, newJaffaItem);
        return newJaffaItem;
    }

    private ItemJaffaFood createJaffaFood(JaffaItem ji, int healAmount, float saturation) {
        JaffaItemInfo info = ItemsInfo.get(ji);
        ItemJaffaFood newJaffaItem = new ItemJaffaFood(info.getId(), healAmount, saturation);
        finilizeItemSetup(info, newJaffaItem);
        return newJaffaItem;
    }

    private ItemJaffaTool createJaffaTool(JaffaItem ji, int usageCount) {
        JaffaItemInfo info = ItemsInfo.get(ji);
        ItemJaffaTool newJaffaItem = new ItemJaffaTool(info.getId(), usageCount);
        finilizeItemSetup(info, newJaffaItem);
        return newJaffaItem;
    }

    public void createItems() {
        createJaffaItem(JaffaItem.pastry);
        createJaffaItem(JaffaItem.jamO);
        createJaffaItem(JaffaItem.jamR);

        createJaffaFood(JaffaItem.cake, 1, 0.2F);
        createJaffaFood(JaffaItem.jaffaO, 3, 0.7F).setPotionEffect(Potion.regeneration.id, 2, 1, 0.4F);
        createJaffaFood(JaffaItem.jaffaR, 3, 0.7F).setPotionEffect(Potion.regeneration.id, 2, 1, 0.4F);
        createJaffaFood(JaffaItem.jaffa, 2, 0.5F).setPotionEffect(Potion.regeneration.id, 2, 1, 0.2F);

        createJaffaItem(JaffaItem.chocolate);
        createJaffaItem(JaffaItem.apples);
        createJaffaItem(JaffaItem.beans);
        createJaffaItem(JaffaItem.sweetBeans);
        createJaffaItem(JaffaItem.butter);

        createJaffaTool(JaffaItem.mallet, 8);
        createJaffaTool(JaffaItem.malletStone, 16);
        createJaffaTool(JaffaItem.malletIron, 64);
        createJaffaTool(JaffaItem.malletDiamond, 512);

        mallets = new JaffaItem[]{JaffaItem.mallet, JaffaItem.malletStone, JaffaItem.malletIron, JaffaItem.malletDiamond};

        createJaffaItem(JaffaItem.malletHead);
        createJaffaItem(JaffaItem.malletHeadStone);
        createJaffaItem(JaffaItem.malletHeadIron);
        createJaffaItem(JaffaItem.malletHeadDiamond);

        malletHeads = new JaffaItem[]{JaffaItem.malletHead, JaffaItem.malletHeadStone, JaffaItem.malletHeadIron, JaffaItem.malletHeadDiamond};

        createJaffaItem(JaffaItem.brownPastry);
        createJaffaItem(JaffaItem.puffPastry);
        createJaffaItem(JaffaItem.peanut);
        createJaffaItem(JaffaItem.cream);
        createJaffaItem(JaffaItem.sweetRoll);
        createJaffaItem(JaffaItem.cakeTin);
        createJaffaItem(JaffaItem.browniesInTin);

        createJaffaItem(JaffaItem.sweetRollRaw);
        createJaffaItem(JaffaItem.browniesInTinRaw);

        createJaffaFood(JaffaItem.creamRoll, 4, 1F).setPotionEffect(Potion.digSpeed.id, 60, 1, 0.15F);
        createJaffaFood(JaffaItem.brownie, 2, 0.6F).setPotionEffect(Potion.jump.id, 60, 1, 0.15F);

        createJaffaItem(JaffaItem.bunRaw);
        createJaffaItem(JaffaItem.bun);
        createJaffaItem(JaffaItem.sausageRaw);
        createJaffaItem(JaffaItem.sausage);
        createJaffaItem(JaffaItem.flour);

        createJaffaFood(JaffaItem.hotdog, 3, 0.7F).setPotionEffect(Potion.damageBoost.id, 60, 1, 0.15F);

        createJaffaItem(JaffaItem.chocolateWrapper);

        createJaffaFood(JaffaItem.chocolateBar, 1, 0.5F).setPotionEffect(Potion.moveSpeed.id, 60, 1, 0.15F);

        createJaffaItem(JaffaItem.wrapperJaffas);

        createJaffaPack(JaffaItem.jaffasPack, new ItemStack(getItem(JaffaItem.jaffa), 8)).setMaxStackSize(16);
        createJaffaPack(JaffaItem.jaffasPackR, new ItemStack(getItem(JaffaItem.jaffaR), 8)).setMaxStackSize(16);
        createJaffaPack(JaffaItem.jaffasPackO, new ItemStack(getItem(JaffaItem.jaffaO), 8)).setMaxStackSize(16);

        createJaffaItem(JaffaItem.vanillaBeans);
        createJaffaItem(JaffaItem.waferIcecream);
        createJaffaItem(JaffaItem.cone);
        createJaffaItem(JaffaItem.vanillaPowder);
        createJaffaItem(JaffaItem.vanillaIcecreamRaw);
        createJaffaItem(JaffaItem.chocolateIcecreamRaw);
        createJaffaItem(JaffaItem.icecreamRaw);
        createJaffaItem(JaffaItem.vanillaIcecreamFrozen);
        createJaffaItem(JaffaItem.chocolateIcecreamFrozen);
        createJaffaItem(JaffaItem.icecreamFrozen);
        createJaffaFood(JaffaItem.vanillaIcecream, 2, 0.3F).setPotionEffect(Potion.moveSpeed.id, 70, 1, 0.25F);
        createJaffaFood(JaffaItem.chocolateIcecream, 2, 0.3F).setPotionEffect(Potion.moveSpeed.id, 70, 1, 0.25F);
        createJaffaFood(JaffaItem.russianIcecream, 2, 0.3F).setPotionEffect(Potion.moveSpeed.id, 70, 1, 0.25F);

        createJaffaItem(JaffaItem.donutRaw);
        createJaffaItem(JaffaItem.donut);
        createJaffaItem(JaffaItem.jamP);
        createJaffaItem(JaffaItem.jamL);
        createJaffaItem(JaffaItem.jamV);
        createJaffaItem(JaffaItem.lemons);
        createJaffaItem(JaffaItem.oranges);
        createJaffaItem(JaffaItem.plums);
        createJaffaItem(JaffaItem.sprinkles);
        createJaffaItem(JaffaItem.bagOfSeeds);
        //createJaffaItem(JaffaItem.bagOfSeedsIdentified);
        createJaffaItem(JaffaItem.magnifier);

        createJaffaFood(JaffaItem.jaffaP, 3, 0.7F).setPotionEffect(Potion.regeneration.id, 2, 1, 0.4F);
        createJaffaFood(JaffaItem.jaffaV, 3, 0.7F).setPotionEffect(Potion.regeneration.id, 2, 1, 0.4F);
        createJaffaFood(JaffaItem.jaffaL, 3, 0.7F).setPotionEffect(Potion.regeneration.id, 2, 1, 0.4F);

        createJaffaFood(JaffaItem.donutChocolate, 2, 0.3F).setPotionEffect(Potion.digSpeed.id, 60, 1, 0.15F);
        createJaffaFood(JaffaItem.donutPink, 2, 0.3F).setPotionEffect(Potion.digSpeed.id, 60, 1, 0.15F);
        createJaffaFood(JaffaItem.donutSugar, 2, 0.3F).setPotionEffect(Potion.damageBoost.id, 60, 1, 0.15F);
        createJaffaFood(JaffaItem.donutSprinkled, 2, 0.9F).setPotionEffect(Potion.damageBoost.id, 60, 1, 0.20F);

        createBagOfSeed(JaffaItem.bagOfSeedsIdentified);

        createJaffaItem(JaffaItem.jamMix);

        createJaffaItem(JaffaItem.kettle);
        createJaffaItem(JaffaItem.kettleWaterCold);
        createJaffaItem(JaffaItem.kettleWaterHot).setMaxDamage(5).setMaxStackSize(1);
        createJaffaItem(JaffaItem.cup);
        createJaffaFood(JaffaItem.cupCoffee, 1, 0.2F).
                setReturnItem(new ItemStack(getItem(JaffaItem.cup))).setIsDrink().
                setPotionEffect(Potion.digSpeed.id, 35, 1, 1F).setAlwaysEdible().setMaxStackSize(16);
        createJaffaItem(JaffaItem.cupRaw);
        createJaffaItem(JaffaItem.omeletteRaw);
        createJaffaFood(JaffaItem.omelette, 3, 0.5F).setPotionEffect(Potion.regeneration.id, 2, 1, 0.2F).setMaxStackSize(16);
        createJaffaItem(JaffaItem.tomatoChopped);
        createJaffaItem(JaffaItem.paprikaChopped);
        createJaffaItem(JaffaItem.grinderMeat);
        createJaffaItem(JaffaItem.wienerCocktail);
        createJaffaItem(JaffaItem.jamRaspberry);
        createJaffaItem(JaffaItem.jamStrawberry);
        createJaffaItem(JaffaItem.raspberries);
        createJaffaItem(JaffaItem.strawberries);
        createJaffaItem(JaffaItem.rollRaw);
        createJaffaItem(JaffaItem.roll);
        createJaffaItem(JaffaItem.rollChopped);
        createJaffaItem(JaffaItem.meatChopped);
        createJaffaItem(JaffaItem.ironSkewer);
        createJaffaFood(JaffaItem.skewer, 4, 0.5F).setReturnItem(new ItemStack(getItem(JaffaItem.ironSkewer))).setPotionEffect(Potion.jump.id, 60, 1, 0.15F);
        createJaffaItem(JaffaItem.skewerRaw);
        createJaffaItem(JaffaItem.knifeKitchen).setMaxDamage(4096).setMaxStackSize(1);

        createJaffaFood(JaffaItem.jaffaStrawberry, 3, 0.7F).setPotionEffect(Potion.regeneration.id, 2, 1, 0.4F);
        createJaffaFood(JaffaItem.jaffaRaspberry, 3, 0.7F).setPotionEffect(Potion.regeneration.id, 2, 1, 0.4F);

        createJaffaItem(JaffaItem.coffee);
        createJaffaItem(JaffaItem.coffeeRoasted);
    }


}