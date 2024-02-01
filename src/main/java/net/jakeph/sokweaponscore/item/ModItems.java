package net.jakeph.sokweaponscore.item;

import net.jakeph.sokweaponscore.SokWeaponCore;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SokWeaponCore.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> SwordForAkim = ITEMS.register("swordakim",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.AkimkaForYou)));
}
