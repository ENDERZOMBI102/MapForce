package com.enderzombi102.mapforce.mixin;

import com.enderzombi102.mapforce.config.Config;
import net.minecraft.client.gui.screen.world.MoreOptionsDialog;
import net.minecraft.client.world.GeneratorType;
import net.minecraft.unmapped.C_njsjipmy;
import net.minecraft.util.Holder;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(MoreOptionsDialog.class)
public abstract class MoreOptionsDialogMixin {
	@Shadow
	private Optional<Holder<GeneratorType>> generatorType;

	@Shadow
	private C_njsjipmy generatorOptions;

	@Inject( method = "<init>", at = @At( "TAIL" ) )
	public void onInit( CallbackInfo ci ) {
		Config.load();
		var registry = this.generatorOptions.registryAccess().get( Registry.WORLD_PRESET_WORLDGEN );
		this.generatorType = registry.getHolder( RegistryKey.of( Registry.WORLD_PRESET_WORLDGEN, Config.get().defaultMapType ) );
	}

}
