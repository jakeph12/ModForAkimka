package net.jakeph.sokweaponscore.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.lwjgl.glfw.GLFW;

public class CustomItem extends SwordItem {
    public CustomItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }
    @Override
    public final boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }
}
