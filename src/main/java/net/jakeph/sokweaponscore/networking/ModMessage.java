package net.jakeph.sokweaponscore.networking;

import net.jakeph.sokweaponscore.SokWeaponCore;
import net.jakeph.sokweaponscore.networking.packet.DieForWorld;
import net.jakeph.sokweaponscore.networking.packet.Shoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessage {
    private static SimpleChannel INSTANCE;

    private static int pocketId = 0;
    private static int id(){
        return pocketId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder.
                named(new ResourceLocation(SokWeaponCore.MOD_ID,"message"))
                .networkProtocolVersion(()-> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;
        net.messageBuilder(DieForWorld.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DieForWorld::new)
                .encoder(DieForWorld::toByte)
                .consumerMainThread(DieForWorld::handle)
                .add();
        net.messageBuilder(Shoot.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(Shoot::new)
                .encoder(Shoot::toByte)
                .consumerMainThread(Shoot::handle)
                .add();
    }
    public static <MSG> void SendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void SendtoPlayer(MSG message, ServerPlayer pl){
        INSTANCE.send(PacketDistributor.PLAYER.with(() ->pl),message);
    }
}
