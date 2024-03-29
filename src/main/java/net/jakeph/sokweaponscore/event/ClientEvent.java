package net.jakeph.sokweaponscore.event;

import net.jakeph.sokweaponscore.SokWeaponCore;
import net.jakeph.sokweaponscore.item.custom.CustomItem;
import net.jakeph.sokweaponscore.networking.ModMessage;
import net.jakeph.sokweaponscore.networking.packet.DieForWorld;
import net.jakeph.sokweaponscore.networking.packet.Shoot;
import net.jakeph.sokweaponscore.util.KeyBinding;
import net.jakeph.sokweaponscore.util.SecondKeyBinding;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvent {
    public static boolean pressed = false;
    @Mod.EventBusSubscriber(modid = SokWeaponCore.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE ,value = Dist.CLIENT)
    public static class ClientForgeEvent{

        @SubscribeEvent
        static void onKeyRegister(RegisterKeyMappingsEvent event){
        }
        @SubscribeEvent
        public static void OnKeyInput(InputEvent event){
            if(SecondKeyBinding.SWC_FIRE_KEY.isDown() && !pressed){
                pressed = true;
                SecondKeyBinding.SWC_FIRE_KEY.consumeClick();
            } else if (!SecondKeyBinding.SWC_FIRE_KEY.isDown() && pressed) {
                pressed = false;
            }
            if (KeyBinding.DIE_KEY.consumeClick())
                ModMessage.SendToServer(new DieForWorld());
        }
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.phase == TickEvent.Phase.START && event.player != null && event.player.isAlive()){
                ItemStack heldItem = event.player.getMainHandItem();
                if(heldItem.getItem() instanceof CustomItem && pressed){
                    ModMessage.SendToServer(new Shoot());
                }
            }
        }

    }
    @Mod.EventBusSubscriber(modid = SokWeaponCore.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvent {

        @SubscribeEvent
        static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DIE_KEY);
           // event.register(SecondKeyBinding.SWC_FIRE_KEY);
        }
    }

}