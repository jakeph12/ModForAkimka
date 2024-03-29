package net.jakeph.sokweaponscore.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DieForWorld {
    public DieForWorld(){

    }
    public DieForWorld(FriendlyByteBuf buf){

    }
    public void toByte(FriendlyByteBuf buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.hurt(new DamageSource("younoob"),player.getHealth());
        });
        return  true;
    }
}
