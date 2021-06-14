package it.multicoredev.carpetredcraftaddons.events;

import carpet.script.CarpetEventServer.Event;
import carpet.script.value.EntityValue;
import carpet.script.value.ListValue;
import carpet.script.value.NumericValue;
import carpet.script.value.StringValue;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

import java.util.Arrays;

public class CarpetRedCraftEvents extends Event {
    public static CarpetRedCraftEvents PLAYER_INTERACTS_WITH_ENTITY_AT = new CarpetRedCraftEvents("player_interacts_with_entity_at", 4, false) {
        @Override
        public void onEntityHandActionHitVec(ServerPlayerEntity player, Entity entity, Hand enumhand, Vec3d hitRes) {
            handler.call( () -> Arrays.asList(
                    new EntityValue(player),
                    new EntityValue(entity),
                    StringValue.of(enumhand==Hand.MAIN_HAND?"mainhand":"offhand"),
                    ListValue.of(
                            new NumericValue(hitRes.x),
                            new NumericValue(hitRes.y),
                            new NumericValue(hitRes.z)
                    )
            ), player::getCommandSource);
        }
    };

    public static void noop() { }

    public void onEntityHandActionHitVec(ServerPlayerEntity player, Entity entity, Hand enumhand, Vec3d hitRes) { }

    public CarpetRedCraftEvents(String name, int reqArgs, boolean isGlobalOnly) {
        super(name, reqArgs, isGlobalOnly);
    }
}
