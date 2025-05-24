package com.nodus.client.modules.player;

import com.nodus.client.Module;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class AutoTool extends Module {
    public AutoTool() {
        super("AutoTool", "Switches to best tool for the block you're looking at.");
        ClientTickEvents.END_CLIENT_TICK.register(client -> this.onTick());
    }

    @Override public void onEnable() {}
    @Override public void onDisable() {}

    @Override
    public void onTick() {
        if (!enabled) return;
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        ClientWorld world = client.world;
        if (player == null || world == null) return;
        if (client.crosshairTarget instanceof BlockHitResult hit) {
            BlockPos pos = hit.getBlockPos();
            float bestSpeed = 0;
            int bestSlot = -1;
            for (int i = 0; i < 9; i++) {
                ItemStack stack = player.getInventory().getStack(i);
                float speed = stack.getMiningSpeedMultiplier(world.getBlockState(pos));
                if (speed > bestSpeed) {
                    bestSpeed = speed;
                    bestSlot = i;
                }
            }
            if (bestSlot != -1 && player.getInventory().selectedSlot != bestSlot) {
                player.getInventory().selectedSlot = bestSlot;
            }
        }
    }
}
