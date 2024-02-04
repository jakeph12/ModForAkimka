package net.jakeph.sokweaponscore.block;

import net.jakeph.sokweaponscore.SokWeaponCore;
import net.jakeph.sokweaponscore.item.ModCreativeModeTab;
import net.jakeph.sokweaponscore.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SokWeaponCore.MOD_ID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }


    public static final RegistryObject<Block> AkimkaShit = registryBlock("akimka_shit", () ->
            new Block(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f)), ModCreativeModeTab.AkimkaForYou);

    public static final RegistryObject<Block> AkimBlock = registryBlock("akimka_block",() -> new Block(
            BlockBehaviour.Properties.of(Material.STONE).strength(6f)
                    .requiresCorrectToolForDrops()),
            ModCreativeModeTab.AkimkaForYou);
}
