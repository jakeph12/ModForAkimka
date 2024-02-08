package net.jakeph.sokweaponscore.item;

import net.jakeph.sokweaponscore.SokWeaponCore;
import net.jakeph.sokweaponscore.item.custom.Bullet;
import net.jakeph.sokweaponscore.item.custom.Pistol;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
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
            () -> new Bullet(new Item.Properties().tab(ModCreativeModeTab.AkimkaForYou)));
    public static final RegistryObject<Item> AkimOrge = ITEMS.register("akim_orge",
            () -> new ShieldItem(new Item.Properties().tab(ModCreativeModeTab.AkimkaForYou).stacksTo(1)));
    public static final RegistryObject<Item> AkimMain = ITEMS.register("akim_main",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.AkimkaForYou).
                    food(new FoodProperties.Builder().alwaysEat().nutrition(2).build())));
    public static final RegistryObject<Item> Glock = ITEMS.register("glock",
            () -> new Pistol(Tiers.IRON,10,100,new Item.Properties().tab(ModCreativeModeTab.AkimkaForYou).stacksTo(1)));

    public static final RegistryObject<Item> Supershorty = ITEMS.register("supershorty",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.AkimkaForYou).stacksTo(1)));
}
