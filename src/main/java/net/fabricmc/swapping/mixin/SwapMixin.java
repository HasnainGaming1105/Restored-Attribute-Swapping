 package net.fabricmc.swapping.mixin;package net.fabricmc.swapping.mixin;

import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class SwapMixin {
    @Shadow public ServerPlayer player;

    @Inject(method = "handleSetCarriedItem", at = @At("TAIL"))
    private void onSlotChange(ServerboundSetCarriedItemPacket packet, CallbackInfo ci) {
        if (this.player != null) {
            this.player.resetAttackStrengthTicker();
        }
    }
}
