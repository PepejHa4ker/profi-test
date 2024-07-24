package com.pepej.profitest;

import com.pepej.profitest.event.PlayerMoveCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public class ProfiTest implements ModInitializer {

    public static final BoundingBox RESTRICTED_AREA = new BoundingBox(20, 0, 0, 100, 256, 100);

    @Override
    public void onInitialize() {
        PlayerMoveCallback.EVENT.register((player, to) -> { 
            if (RESTRICTED_AREA.isInside((int) to.x(), (int) to.y(), (int) to.z())) {
                return PlayerMoveCallback.MoveResult.FAIL;
            }
            return PlayerMoveCallback.MoveResult.PASS;
        });
    }
}
