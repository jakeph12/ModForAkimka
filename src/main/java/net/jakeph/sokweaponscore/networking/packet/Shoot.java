package net.jakeph.sokweaponscore.networking.packet;

import net.jakeph.sokweaponscore.item.custom.Pistol;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class Shoot {
    public Shoot(){

    }
    public Shoot(FriendlyByteBuf buf){

    }
    public void toByte(FriendlyByteBuf buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            ItemStack itemStack = player.getMainHandItem();
            Pistol pistol = (Pistol) itemStack.getItem();

            pistol.Fire(player,level,itemStack);
        });
        return  true;
    }
}
