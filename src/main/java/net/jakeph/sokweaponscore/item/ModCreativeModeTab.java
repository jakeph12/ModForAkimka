package net.jakeph.sokweaponscore.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab AkimkaForYou = new CreativeModeTab("newmodeitem") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SwordForAkim.get());
        }
    };
}
