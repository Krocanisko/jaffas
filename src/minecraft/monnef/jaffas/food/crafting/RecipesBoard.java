package monnef.jaffas.food.crafting;

import monnef.jaffas.food.JaffasException;
import monnef.jaffas.food.item.JaffaItem;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

import static monnef.jaffas.food.mod_jaffas.getItem;

public class RecipesBoard {
    private static HashMap<Integer, BoardRecipe> recipes = new HashMap<Integer, BoardRecipe>();

    public static void addRecipeSimple(JaffaItem input, JaffaItem output) {
        addRecipe(input, 1, output, 1);
    }

    public static void addRecipe(JaffaItem input, int inputCount, JaffaItem output, int outputCount) {
        addRecipe(new ItemStack(getItem(input), inputCount), new ItemStack(getItem(output), outputCount));
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        if (input == null || output == null) {
            throw new JaffasException("null in recipe");
        }

        if (input.stackSize <= 0 || output.stackSize <= 0) {
            throw new JaffasException("wrong stack size");
        }

        recipes.put(input.itemID, new BoardRecipe(input, output));
    }

    public static boolean isRecipeFor(ItemStack input) {
        return getRecipeOutputFor(input, false, false) != null;
    }

    public static ItemStack getRecipeOutputAndDecreaseInputStack(ItemStack input) {
        return getRecipeOutputFor(input, true, true);
    }

    public static ItemStack getRecipeOutput(ItemStack input) {
        return getRecipeOutputFor(input, false, false);
    }

    private static ItemStack getRecipeOutputFor(ItemStack input, boolean copyOutput, boolean decreaseOutput) {
        if (input == null) return null;
        BoardRecipe recipe = recipes.get(input.itemID);

        if (recipe == null) return null;
        if (recipe.getInput().stackSize > input.stackSize) return null;

        if (decreaseOutput) {
            input.stackSize -= recipe.getInput().stackSize;
        }

        return copyOutput ? recipe.getOutput().copy() : recipe.getOutput();
    }
}
