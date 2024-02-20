package net.jakeph.sokweaponscore.util;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class SecondKeyBinding {
    public static final String KEY_CATEGORY_SOKWEAPON = "key.category.sokweaponscore.mod";
    public static final String KEY_CATEGORY_FIRES = "key.category.sokweaponscore.fire";
    public static final KeyMapping FIRE_KEY = new KeyMapping(KEY_CATEGORY_FIRES, KeyConflictContext.IN_GAME,
            InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_1,KEY_CATEGORY_SOKWEAPON
    );
}
