package net.jakeph.sokweaponscore.event;

import net.jakeph.sokweaponscore.SokWeaponCore;
import net.jakeph.sokweaponscore.item.custom.Bullet;
import net.jakeph.sokweaponscore.item.custom.CustomItem;
import net.jakeph.sokweaponscore.item.custom.Pistol;
import net.jakeph.sokweaponscore.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

public class ClientEvent {
    public static Thread m_thFireThread;
    private static CustomItem m_curItem;
    public static boolean m_bAlreadyPressed = false;
    public static void Set_m_bAlreadyPressed(boolean value,@Nullable Player pl,@Nullable Pistol ps){
        m_bAlreadyPressed = value;
        if(!m_bAlreadyPressed && m_thFireThread != null){
            m_thFireThread.stop();
            m_thFireThread.interrupt();
            m_thFireThread = null;
        }else if(m_bAlreadyPressed){
            Fire(ps,pl);
        }
    }
    public static void Set_m_bAlreadyPressed(boolean value){
        Set_m_bAlreadyPressed(value,null,null);
    }

    @Mod.EventBusSubscriber(modid = SokWeaponCore.MOD_ID,value = Dist.CLIENT)
    public static class ClientForgeEvent{

        @SubscribeEvent
        static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.FIRE_KEY);
        }
        @SubscribeEvent
        public static void OnKeyInput(InputEvent event){
            var player = Minecraft.getInstance().player;
            if(player == null ||!(player.getMainHandItem().getItem() instanceof CustomItem)) return;

            if(KeyBinding.FIRE_KEY.isDown() && !m_bAlreadyPressed){
                player.sendSystemMessage(Component.literal("Pressend"));
                Set_m_bAlreadyPressed(true,player,(Pistol)  player.getMainHandItem().getItem());
            }else if(!KeyBinding.FIRE_KEY.isDown() && m_bAlreadyPressed){
                player.sendSystemMessage(Component.literal("UnPresed"));
                Set_m_bAlreadyPressed(false);
            }
        }

    }
    @Mod.EventBusSubscriber(modid = SokWeaponCore.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvent {

        @SubscribeEvent
        static void onKeyRegister(RegisterKeyMappingsEvent event) {
            //event.register(KeyBinding.FIRE_KEY);
        }
    }
    public static void Fire(Pistol pistol,Player pl){
        m_thFireThread = new Thread(() ->{
            while (m_bAlreadyPressed){
                try {
                    pistol.Fire(pl);
                    Thread.sleep(pistol.m_inCoolDown * 1000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }

            }
        });
        m_thFireThread.start();
    }

}