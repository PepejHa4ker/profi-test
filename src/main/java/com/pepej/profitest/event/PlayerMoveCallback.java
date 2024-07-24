package com.pepej.profitest.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;


public interface PlayerMoveCallback {

    Event<PlayerMoveCallback> EVENT = EventFactory.createArrayBacked(PlayerMoveCallback.class,
            (listeners) -> (player, to) -> {
                for (PlayerMoveCallback listener : listeners) {
                    MoveResult result = listener.move(player, to);
                    if (result != MoveResult.PASS) {
                        return result;
                    }
                }
                return MoveResult.PASS;
            });


    MoveResult move(LocalPlayer player, Vec3 to);

    enum MoveResult {
        PASS,
        FAIL
    }
}


