package net.jakeph.sokweaponscore.util;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyMappingLookup;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_SOKWEAPON = "key.category.sokweaponscore.mod";
    public static final String KEY_CATEGORY_DIE = "key.category.sokweaponscore.die";
    public static final KeyMapping DIE_KEY = new KeyMapping(KEY_CATEGORY_DIE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K ,KEY_CATEGORY_SOKWEAPON
    );



}
