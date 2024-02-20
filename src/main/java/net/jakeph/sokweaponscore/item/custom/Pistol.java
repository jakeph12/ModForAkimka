package net.jakeph.sokweaponscore.item.custom;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

public class Pistol extends CustomItem {
    public  static final String m_strNameBoolet = "";
    public static final int m_inBooletamount = 1;
    public static final float m_inCoolDown = 0.3f;
    public static int Boolet;

    public Pistol(Tier tier, int attackd, float speed, Properties properties) {
        super(tier, attackd, speed, properties);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        entity.sendSystemMessage(Component.literal("d"));
        return super.onEntityItemUpdate(stack, entity);
    }
    public void Fire(Player player,Level level,ItemStack itemStack){
        ShootOneFire(itemStack,level,player,10);
        OnServer(itemStack,level,player,this);
    }
    private void ShootOneFire(ItemStack itemStack, Level level, LivingEntity livingEntity, int i1){
        if (livingEntity instanceof Player player) {
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, itemStack) > 0;
            ItemStack itemstack = player.getProjectile(itemStack);
            int i = this.getUseDuration(itemStack) - i1;
            i = ForgeEventFactory.onArrowLoose(itemStack, level, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) {
                return;
            }

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                if (!(0 < 0.1)) {
                    boolean flag1 = player.getAbilities().instabuild || itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, itemStack, player);
                    level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 1 * 0.5F);
                    if (!flag1 && !player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.getInventory().removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }
    public AbstractArrow customArrow(AbstractArrow arrow) {
        return arrow;
    }
    public void OnServer(ItemStack itemStack,Level level,Player player,Pistol pistol){
        if (!level.isClientSide) {
            ArrowItem arrowitem = (ArrowItem) (itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
            AbstractArrow abstractarrow = arrowitem.createArrow(level, itemStack, player);
            abstractarrow = pistol.customArrow(abstractarrow);
            abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1 * 3.0F, 1.0F);
            if (1 == 1.0F) {
                abstractarrow.setCritArrow(true);
            }

            int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, itemStack);
            if (j > 0) {
                abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double) j * 0.5 + 0.5);
            }

            int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, itemStack);
            if (k > 0) {
                abstractarrow.setKnockback(k);
            }

            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemStack) > 0) {
                abstractarrow.setSecondsOnFire(100);
            }

            itemStack.hurtAndBreak(1, player, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(player.getUsedItemHand());
            });
            if (player.getAbilities().instabuild && (itemStack.is(Items.SPECTRAL_ARROW) || itemStack.is(Items.TIPPED_ARROW))) {
                abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }

            level.addFreshEntity(abstractarrow);
        }
    }
}