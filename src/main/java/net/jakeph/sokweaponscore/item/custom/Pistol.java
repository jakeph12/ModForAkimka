package net.jakeph.sokweaponscore.item.custom;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DebugStickItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Debug;


public class Pistol extends CustomItem {
    public  static final String m_strNameBoolet = "";
    public static final int m_inBooletamount = 1;
    public static final int m_inCoolDown = 3;
    public static int Boolet;

    public Pistol(Tier tier, int attackd, float speed, Properties properties) {
        super(tier, attackd, speed, properties);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        entity.sendSystemMessage(Component.literal("d"));
        return super.onEntityItemUpdate(stack, entity);
    }
    public void Fire(Player player){
        player.sendSystemMessage(Component.literal("Hi"));
    }
}