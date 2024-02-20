package net.jakeph.sokweaponscore.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Shotgun extends BowItem {
    public Shotgun(Properties properties) {
        super(properties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        player.sendSystemMessage(Component.literal("dd"));
        return super.onLeftClickEntity(stack, player, entity);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        var obj = player.getItemInHand(interactionHand);
        super.releaseUsing(obj,level,player,1);
        return InteractionResultHolder.success(obj);
    }

    @Override
    public boolean useOnRelease(ItemStack p_41464_) {
        return true;
    }
}
