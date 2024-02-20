package net.jakeph.sokweaponscore.event;

import net.jakeph.sokweaponscore.SokWeaponCore;
import net.jakeph.sokweaponscore.item.custom.CustomItem;
import net.jakeph.sokweaponscore.item.custom.Pistol;
import net.jakeph.sokweaponscore.networking.ModMessage;
import net.jakeph.sokweaponscore.networking.packet.DieForWorld;
import net.jakeph.sokweaponscore.networking.packet.Shoot;
import net.jakeph.sokweaponscore.util.KeyBinding;
import net.jakeph.sokweaponscore.util.SecondKeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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
    private static Item curItem;
    public static void Set_m_bAlreadyPressed(boolean value, Level level, @Nullable Player pl, @Nullable ItemStack ps){
        m_bAlreadyPressed = value;
        if(!m_bAlreadyPressed || ps.getItem() == curItem && m_thFireThread != null){
            m_thFireThread.stop();
            m_thFireThread.interrupt();
            m_thFireThread = null;
        }else if(m_bAlreadyPressed){
            curItem = ps.getItem();
            Fire(ps,level,pl);
        }
    }
    public static void Set_m_bAlreadyPressed(boolean value){
        Set_m_bAlreadyPressed(value,null,null,null);
    }

    @Mod.EventBusSubscriber(modid = SokWeaponCore.MOD_ID,value = Dist.CLIENT)
    public static class ClientForgeEvent{

        @SubscribeEvent
        static void onKeyRegister(RegisterKeyMappingsEvent event){
        }
        @SubscribeEvent
        public static void OnKeyInput(InputEvent event){
            if (KeyBinding.DIE_KEY.consumeClick()) {
                ModMessage.SendToServer(new DieForWorld());
            }
            var player = Minecraft.getInstance().player;
            if(player == null ||!(player.getMainHandItem().getItem() instanceof CustomItem)) return;

            if(SecondKeyBinding.FIRE_KEY.isDown() && !m_bAlreadyPressed){
                player.sendSystemMessage(Component.literal("Pressend"));
                Set_m_bAlreadyPressed(true,Minecraft.getInstance().level,player,player.getMainHandItem());
                System.out.println(player.level);
            }else if(!SecondKeyBinding.FIRE_KEY.isDown() && m_bAlreadyPressed){
                player.sendSystemMessage(Component.literal("UnPresed"));
                Set_m_bAlreadyPressed(false);
            }
        }

    }
    @Mod.EventBusSubscriber(modid = SokWeaponCore.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvent {

        @SubscribeEvent
        static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DIE_KEY);
        }
    }
    public static void Fire(ItemStack itemStack,Level level,Player pl){
        var pistol = (Pistol) itemStack.getItem();
        m_thFireThread = new Thread(() ->{
            while (m_bAlreadyPressed){
                try {
                    if(pl == null || pl.getMainHandItem().isEmpty()
                            || pl.getMainHandItem() != itemStack){
                        Set_m_bAlreadyPressed(false);
                        return;
                    }
                    ModMessage.SendToServer(new Shoot());
                    Thread.sleep((int)(pistol.m_inCoolDown * 1000));
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }

            }
        });
        m_thFireThread.start();
    }
}