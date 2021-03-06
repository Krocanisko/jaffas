package monnef.jaffas.food.crafting;

import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import monnef.jaffas.food.JaffasException;
import monnef.jaffas.food.item.JaffaItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static monnef.jaffas.food.mod_jaffas.getItem;

public class AchievementsCraftingHandler implements ICraftingHandler {
    private static AchievementPage page;

    private static HashMap<Integer, Achievement> craftAchievement = new HashMap<Integer, Achievement>();
    private static int idCounter = 9790;

    public static void init() {
        createAchievements();
        createPage();
    }

    private static void createAchievements() {
        addAchievement(JaffaItem.flour, "FlourAch", 0, 2, getItem(JaffaItem.flour), null, false, "Flour", "Make a pack of flour from wheat and paper.");

        addAchievement(JaffaItem.malletHead, "MalletHeadWoodAch", 1, 0, getItem(JaffaItem.malletHead), null, false, "Wooden Mallet Head", "Use a string, a piece of wood and planks to make a mallet head.");
        addAchievement(JaffaItem.mallet, "MalletWoodAch", 3, 0, getItem(JaffaItem.mallet), craftAchievement.get(getItemID(JaffaItem.malletHead)), false, "Wooden Mallet", "Use sticks and a mallet head to make a mallet.");

        addAchievement(JaffaItem.malletHeadStone, "MalletHeadStoneAch", 1, -1, getItem(JaffaItem.malletHeadStone), null, false, "Stone Mallet Head", "Use strings, a piece of stone and a bunch of cobblestone to make a mallet head.");
        addAchievement(JaffaItem.malletStone, "MalletStoneAch", 3, -1, getItem(JaffaItem.malletStone), craftAchievement.get(getItemID(JaffaItem.malletHeadStone)), false, "Stone Mallet", "Use sticks and a mallet head to make a mallet.");

        addAchievement(JaffaItem.malletHeadIron, "MalletHeadIronAch", 1, -2, getItem(JaffaItem.malletHeadIron), null, false, "Iron Mallet Head", "Use strings, a block of iron and a bunch of stone to make a mallet head.");
        addAchievement(JaffaItem.malletIron, "MalletIronAch", 3, -2, getItem(JaffaItem.malletIron), craftAchievement.get(getItemID(JaffaItem.malletHeadIron)), false, "Iron Mallet", "Use sticks and a mallet head to make a mallet.");

        addAchievement(JaffaItem.malletHeadDiamond, "MalletHeadDiaAch", 1, -3, getItem(JaffaItem.malletHeadDiamond), null, false, "Diamond Mallet Head", "Use strings, slimeballs, iron ingots and a block of diamond to make a mallet head.");
        addAchievement(JaffaItem.malletDiamond, "MalletDiaAch", 3, -3, getItem(JaffaItem.malletDiamond), craftAchievement.get(getItemID(JaffaItem.malletHeadDiamond)), false, "Diamond Mallet", "Use sticks and a mallet head to make a mallet.");
    }

    private static void addAchievement(JaffaItem item, String name, int xCoord, int yCoord, Object icon, Achievement required, boolean special, String title, String desc) {
        Achievement ach = null;
        int newId = idCounter++;

        if (icon instanceof Item) {
            ach = new Achievement(newId, name, xCoord, yCoord, (Item) icon, required);
        } else if (icon instanceof Block) {
            ach = new Achievement(newId, name, xCoord, yCoord, (Block) icon, required);
        }
        if (ach == null) throw new JaffasException("wrong icon object");

        if (special) ach.setSpecial();
        ach.registerAchievement();

        addAchievementName(name, title);
        addAchievementDesc(name, desc);

        craftAchievement.put(getItemID(item), ach);
    }

    private static void addAchievementName(String ach, String name) {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
    }

    private static void addAchievementDesc(String ach, String desc) {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
    }

    private static void createPage() {
        Set<Achievement> achievs = new HashSet<Achievement>();

        achievs.addAll(craftAchievement.values());

        Achievement[] achievements = achievs.toArray(new Achievement[0]);
        page = new AchievementPage("Jaffas and more!", achievements);
        AchievementPage.registerAchievementPage(page);
    }

    private static int getItemID(JaffaItem jItem) {
        return getItem(jItem).shiftedIndex;
    }

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
        Achievement achiev = craftAchievement.get(item.itemID);
        if (achiev != null) {
            player.addStat(achiev, 1);
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
