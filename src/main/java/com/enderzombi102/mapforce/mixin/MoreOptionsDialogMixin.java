package com.enderzombi102.mapforce.mixin;

import com.enderzombi102.mapforce.MapForce;
import com.enderzombi102.mapforce.config.Config;
import net.minecraft.client.gui.screen.world.MoreOptionsDialog;
import net.minecraft.client.world.GeneratorType;
import net.minecraft.unmapped.C_njsjipmy;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.stream.Collectors;

@Mixin(MoreOptionsDialog.class)
public abstract class MoreOptionsDialogMixin {
	@Shadow
	private Optional<Holder<GeneratorType>> generatorType;

	@Shadow
	private C_njsjipmy generatorOptions;

	@Inject( method = "<init>", at = @At( "TAIL" ) )
	public void onInit( CallbackInfo ci ) {
		Config.load(); // this is here because this mixin it's called earlier than the other
		var registry = this.generatorOptions.registryAccess().get( Registry.WORLD_PRESET_WORLDGEN );
		this.generatorType = registry.getHolder( RegistryKey.of( Registry.WORLD_PRESET_WORLDGEN, Config.get().defaultMapType ) );

		if ( Config.get().logWorldTypes )
			MapForce.LOGGER.info(
				"[MapForce] Listing all registered world types: \n - {}",
				registry.getIds().stream().map( Identifier::toString ).collect( Collectors.joining( "\n - " ) )
			);
	}

}
