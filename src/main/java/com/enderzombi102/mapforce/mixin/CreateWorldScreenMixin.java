package com.enderzombi102.mapforce.mixin;

import com.enderzombi102.mapforce.config.Config;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {
	@Shadow
	private ButtonWidget moreOptionsButton;

	@Inject(
		method = "init",
		at = @At( "TAIL" )
	)
	public void onInit( CallbackInfo ci ) {
		this.moreOptionsButton.active = Config.get().moreWorldOptionsButtonEnabled;
	}
}
