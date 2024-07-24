package com.pepej.profitest.mixin;

import com.pepej.profitest.event.PlayerMoveCallback;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class PlayerMoveEventMixin {

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    private void onMove(MoverType moverType, Vec3 vec3, CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        Vec3 to = player.position().add(vec3);
        PlayerMoveCallback.MoveResult result = PlayerMoveCallback.EVENT.invoker().move(player, to);
        if (result != PlayerMoveCallback.MoveResult.PASS) {
            ci.cancel();
        }
    }
}






