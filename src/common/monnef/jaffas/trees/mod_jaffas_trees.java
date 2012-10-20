package monnef.jaffas.trees;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import monnef.jaffas.food.mod_jaffas;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import net.minecraftforge.common.Configuration;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.logging.Level;

import static monnef.jaffas.food.mod_jaffas.getJaffaItem;

@Mod(modid = "moen-jaffas-trees", name = "Jaffas - trees", version = "0.4.1", dependencies = "required-after:moen-jaffas;required-after:moen-monnef-core")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class mod_jaffas_trees {
    private static MinecraftServer server;
    public static boolean bonemealingAllowed;

    public static final String[] treeTypes = new String[]{"normal", "apple", "cocoa", "vanilla", "lemon", "orange", "plum"};
    public static final String[] seedsNames = new String[]{"[UNUSED]", "Apple Seeds", "Cocoa Seeds", "Vanilla Seeds", "Lemon Seeds", "Orange Seeds", "Plum Seeds"};

    public static fruitType getActualLeavesType(Block block, int blockMetadata) {
        BlockFruitLeaves b = (BlockFruitLeaves) block;
        int index = b.serialNumber * 4 + blockMetadata;
        fruitType fruitType = mod_jaffas_trees.fruitType.indexToFruitType(index);
        if (fruitType == null) {
            throw new RuntimeException("fruit not found!");
        }

        return fruitType;
    }

    public static enum fruitType {
        Normal(0), Apple(1), Cocoa(2), Vanilla(3), Lemon(4), Orange(5), Plum(6);
        private int value;
        private int blockNumber;
        private int metaNumber;

        fruitType(int value) {
            this.value = value;
            this.blockNumber = value % 4;
            this.metaNumber = value / 4;

            mapper.indexToFruitMap.put(value, this);
        }

        public int getValue() {
            return value;
        }

        public int getBlockNumber() {
            return blockNumber;
        }

        public static fruitType indexToFruitType(int index) {
            return mapper.indexToFruitMap.get(index);
        }

        private static class mapper {
            private static HashMap<Integer, fruitType> indexToFruitMap;

            static {
                indexToFruitMap = new HashMap<Integer, fruitType>();
            }
        }
    }

    public static mod_jaffas_trees instance;

    public static ArrayList<LeavesInfo> leavesList = new ArrayList<LeavesInfo>();

    public static final int leavesBlocksAllocated = 3;
    public static final int leavesTypesCount = 6;

    public static int startID;
    private int actualID;

    public static boolean debug;

    private int itemLemonID;
    private int itemOrangeID;
    private int itemPlumID;
    public static ItemJaffaFruit itemLemon;
    public static ItemJaffaFruit itemOrange;
    public static ItemJaffaFruit itemPlum;

    public static enum bushType {
        Coffee, Strawberry;
    }

    public static EnumMap<bushType, BushInfo> BushesList = new EnumMap<bushType, BushInfo>(bushType.class);

    private int itemSeedsCoffeeID;
    public static ItemJaffaSeeds itemSeedsCoffee;
    private int blockCoffeeID;
    public static BlockJaffaCrops blockCoffee;
    private int itemCoffeeID;
    public ItemJaffaBerry itemCoffee;

    private int itemSeedsStrawberriesID;
    public static ItemJaffaSeeds itemSeedsStrawberries;
    private int blockStrawberriesID;
    public static BlockJaffaCrops blockStrawberries;
    private int itemStrawberriesID;
    public Item itemStrawberries;

    public final static String textureFile = "/jaffas_02.png";

    private int getID() {
        return this.actualID++;
    }

    private int getBlockID() {
        return getID()/* + 256*/;
    }

    public mod_jaffas_trees() {
        instance = this;
    }

    @SidedProxy(clientSide = "monnef.jaffas.trees.ClientProxyTutorial", serverSide = "monnef.jaffas.trees.CommonProxyTutorial")
    public static CommonProxyTutorial proxy;

    @Mod.PreInit
    public void PreLoad(FMLPreInitializationEvent event) {
        //this.startID = mod_jaffas.topDefaultID;
        this.startID = 3500;
        this.actualID = this.startID;

        PopulateBushInfo();

        Configuration config = new Configuration(
                event.getSuggestedConfigurationFile());

        try {
            config.load();

            if (this.actualID <= 0) {
                throw new RuntimeException("unable to get ID from parent");
            }

//            blockFruitSaplingID = config.getOrCreateIntProperty("fruit tree sapling", Configuration.CATEGORY_BLOCK, getBlockID()).getInt();
            itemLemonID = config.getOrCreateIntProperty("lemon", Configuration.CATEGORY_ITEM, getID()).getInt();
            itemOrangeID = config.getOrCreateIntProperty("orange", Configuration.CATEGORY_ITEM, getID()).getInt();
            itemPlumID = config.getOrCreateIntProperty("plum", Configuration.CATEGORY_ITEM, getID()).getInt();

            //blockFruitLeavesID = config.getOrCreateIntProperty("fruit leaves", Configuration.CATEGORY_BLOCK, getBlockID()).getInt();
            for (int i = 0; i < leavesBlocksAllocated; i++) {
                int leavesID = config.getOrCreateIntProperty("fruit leaves " + i, Configuration.CATEGORY_BLOCK, getBlockID()).getInt();
                int saplingID = config.getOrCreateIntProperty("fruit tree sapling " + i, Configuration.CATEGORY_BLOCK, getBlockID()).getInt();
                int seedsID = config.getOrCreateIntProperty("fruit seeds " + i, Configuration.CATEGORY_ITEM, getID()).getInt();

                leavesList.add(new LeavesInfo(leavesID, saplingID, seedsID, i));
            }

            for (EnumMap.Entry<bushType, BushInfo> entry : BushesList.entrySet()) {
                BushInfo info = entry.getValue();

                info.itemSeedsID = config.getOrCreateIntProperty(info.getSeedsConfigName(), Configuration.CATEGORY_ITEM, getID()).getInt();
                info.blockID = config.getOrCreateIntProperty(info.getBlockConfigName(), Configuration.CATEGORY_BLOCK, getBlockID()).getInt();
                info.itemFruitID = config.getOrCreateIntProperty(info.getFruitConfigName(), Configuration.CATEGORY_ITEM, getID()).getInt();
            }

            /*
            itemSeedsCoffeeID = config.getOrCreateIntProperty("coffee seeds", Configuration.CATEGORY_ITEM, getID()).getInt();
            blockCoffeeID = config.getOrCreateIntProperty("coffee plant", Configuration.CATEGORY_BLOCK, getBlockID()).getInt();
            itemCoffeeID = config.getOrCreateIntProperty("coffee", Configuration.CATEGORY_ITEM, getID()).getInt();

            itemStrawberriesID = config.getOrCreateIntProperty("strawberries seeds", Configuration.CATEGORY_ITEM, getID()).getInt();
            blockStrawberriesID = config.getOrCreateIntProperty("strawberries plant", Configuration.CATEGORY_BLOCK, getBlockID()).getInt();
            itemStrawberriesID = config.getOrCreateIntProperty("strawberries", Configuration.CATEGORY_ITEM, getID()).getInt();
            */

            debug = config.getOrCreateBooleanProperty("debug", Configuration.CATEGORY_GENERAL, false).getBoolean(false);
            bonemealingAllowed = config.getOrCreateBooleanProperty("bonemeal", Configuration.CATEGORY_GENERAL, false).getBoolean(false);

        } catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, "Mod Jaffas (trees) can't read config file.");
        } finally {
            config.save();
        }
    }

    private void PopulateBushInfo() {
        AddBushInfo(bushType.Coffee, "coffee", "Coffee Seeds", 2, "Coffee Plant", 0, "Coffee", 33, Item.appleGold, 3, 1);
    }

    private void constructItemsInBushInfo() {
        for (EnumMap.Entry<bushType, BushInfo> entry : BushesList.entrySet()) {
            /*
            itemSeedsCoffee = new ItemJaffaSeeds(itemSeedsCoffeeID, blockCoffeeID, Block.tilledField.blockID);
            itemSeedsCoffee.setItemName("seeds_coffee").setIconIndex(2);
            LanguageRegistry.addName(itemSeedsCoffee, "Coffee Seeds");

            blockCoffee = new BlockJaffaCrops(blockCoffeeID, 0, 7, Item.appleGold, itemSeedsCoffee, 1);
            GameRegistry.registerBlock(blockCoffee);
            LanguageRegistry.addName(blockCoffee, "Coffee Plant");

            itemCoffee = new ItemJaffaBerry(itemCoffeeID);
            itemCoffee.setItemName("coffee").setIconIndex(17).setTabToDisplayOn(CreativeTabs.tabMisc);
            LanguageRegistry.addName(itemCoffee, "Coffee");
            */

            BushInfo info = entry.getValue();

            ItemJaffaSeeds seeds = new ItemJaffaSeeds(info.itemSeedsID, info.blockID, Block.tilledField.blockID);
            seeds.setItemName(info.getSeedsLanguageName()).setIconIndex(info.seedsTexture);
            LanguageRegistry.addName(seeds, info.seedsName);
            info.itemSeeds = seeds;

            BlockJaffaCrops crops = new BlockJaffaCrops(info.blockID, info.plantTexture, info.phases, info.product, info.itemSeeds, info.renderer);
            crops.setBlockName(info.getPlantLanguageName());
            GameRegistry.registerBlock(crops);
            LanguageRegistry.addName(crops, info.plantName);
            info.block = crops;

            ItemJaffaBerry fruit = new ItemJaffaBerry(info.itemFruitID);
            fruit.setItemName(info.getFruitLanguageName()).setIconIndex(info.fruitTexture).setTabToDisplayOn(CreativeTabs.tabMaterials);
            LanguageRegistry.addName(fruit, info.fruitName);
            info.itemFruit = fruit;
        }
    }

    private void AddBushInfo(bushType type, String name, String seedsName, int seedsTexture, String plantName, int plantTexture, String fruitName, int fruitTexture, Item product, int phases, int renderer) {
        BushInfo info = new BushInfo();

        info.name = name;
        info.seedsName = seedsName;
        info.seedsTexture = seedsTexture;
        info.plantName = plantName;
        info.plantTexture = plantTexture;
        info.fruitName = fruitName;
        info.fruitTexture = fruitTexture;
        info.product = product;
        info.phases = phases;
        info.renderer = renderer;
        info.type = type;

        BushesList.put(type, info);
    }

    @Mod.Init
    public void load(FMLInitializationEvent event) {
        AddFruitTreesSequence(0, 0, 32, 4);
        AddFruitTreesSequence(1, 4, 32 + 4, 3);

        GameRegistry.registerTileEntity(TileEntityFruitLeaves.class, "fruitLeaves");

        itemLemon = new ItemJaffaFruit(itemLemonID);
        itemLemon.setItemName("lemon").setIconCoord(4, 4);
        LanguageRegistry.addName(itemLemon, "Lemon");

        itemOrange = new ItemJaffaFruit(itemOrangeID);
        itemOrange.setItemName("orange").setIconCoord(5, 4);
        LanguageRegistry.addName(itemOrange, "Orange");

        itemPlum = new ItemJaffaFruit(itemPlumID);
        itemPlum.setItemName("plum").setIconCoord(6, 4);
        LanguageRegistry.addName(itemPlum, "Plum");


        //TODO generalize!
        /*
        itemSeedsCoffee = new ItemJaffaSeeds(itemSeedsCoffeeID, blockCoffeeID, Block.tilledField.blockID);
        itemSeedsCoffee.setItemName("seeds_coffee").setIconIndex(2);
        LanguageRegistry.addName(itemSeedsCoffee, "Coffee Seeds");

        blockCoffee = new BlockJaffaCrops(blockCoffeeID, 0, 7, Item.appleGold, itemSeedsCoffee, 1);
        GameRegistry.registerBlock(blockCoffee);
        LanguageRegistry.addName(blockCoffee, "Coffee Plant");

        itemCoffee = new ItemJaffaBerry(itemCoffeeID);
        itemCoffee.setItemName("coffee").setIconIndex(17).setTabToDisplayOn(CreativeTabs.tabMisc);
        LanguageRegistry.addName(itemCoffee, "Coffee");
*/
        constructItemsInBushInfo();

        installRecipes();

        // texture stuff
        proxy.registerRenderThings();

        //GameRegistry.registerCraftingHandler(new JaffaCraftingHandler());
    }

    private void AddFruitTreesSequence(int i, int leavesTexture, int seedTexture, int subCount) {
        LeavesInfo leaves = leavesList.get(i);
        leaves.leavesBlock = new BlockFruitLeaves(leaves.leavesID, leavesTexture, subCount);
        leaves.leavesBlock.serialNumber = i;
        leaves.leavesBlock.setLeavesRequiresSelfNotify().setBlockName("fruitLeaves" + i).setCreativeTab(CreativeTabs.tabDeco).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep);
        GameRegistry.registerBlock(leaves.leavesBlock);
        LanguageRegistry.addName(leaves.leavesBlock, "Leaves");

        leaves.saplingBlock = new BlockFruitSapling(leaves.saplingID, 15);
        leaves.saplingBlock.serialNumber = i;
        leaves.saplingBlock.setBlockName("fruitSapling" + i).setCreativeTab(CreativeTabs.tabMisc);
        GameRegistry.registerBlock(leaves.saplingBlock);
        LanguageRegistry.addName(leaves.saplingBlock, "Fruit Sapling");

        for (int j = 0; j < subCount; j++) {
            LanguageRegistry.instance().addStringLocalization("item.fruitSeeds" + i + "." + j + ".name", seedsNames[j + i * 4]);
        }
        leaves.seedsItem = new ItemFruitSeeds(leaves.seedsID, leaves.saplingID, seedTexture, subCount);
        leaves.seedsItem.setItemName("fruitSeeds" + i);
        leaves.seedsItem.serialNumber = i;
        LanguageRegistry.addName(leaves.seedsItem, "Fruit Seeds");
    }

    @Mod.ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {
        server = ModLoader.getMinecraftServerInstance();
        ICommandManager commandManager = server.getCommandManager();
        ServerCommandManager serverCommandManager = ((ServerCommandManager) commandManager);
        addCommands(serverCommandManager);
    }

    private void addCommands(ServerCommandManager manager) {
        if (debug) {
            manager.registerCommand(new CommandFruitDebug());
        }
    }

    private void installRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(getJaffaItem(mod_jaffas.JaffaItem.lemons)),
                new ItemStack(mod_jaffas_trees.itemLemon),
                new ItemStack(mod_jaffas_trees.itemLemon),
                new ItemStack(mod_jaffas_trees.itemLemon),
                new ItemStack(mod_jaffas_trees.itemLemon));
        GameRegistry.addShapelessRecipe(new ItemStack(getJaffaItem(mod_jaffas.JaffaItem.oranges)),
                new ItemStack(mod_jaffas_trees.itemOrange),
                new ItemStack(mod_jaffas_trees.itemOrange),
                new ItemStack(mod_jaffas_trees.itemOrange),
                new ItemStack(mod_jaffas_trees.itemOrange));
        GameRegistry.addShapelessRecipe(new ItemStack(getJaffaItem(mod_jaffas.JaffaItem.plums)),
                new ItemStack(mod_jaffas_trees.itemPlum),
                new ItemStack(mod_jaffas_trees.itemPlum),
                new ItemStack(mod_jaffas_trees.itemPlum),
                new ItemStack(mod_jaffas_trees.itemPlum));
    }
}
