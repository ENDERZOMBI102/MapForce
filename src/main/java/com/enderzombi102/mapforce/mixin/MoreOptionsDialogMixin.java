package com.enderzombi102.mapforce.mixin;

import com.enderzombi102.mapforce.MapForce;
import com.enderzombi102.mapforce.config.Config;
import net.minecraft.client.gui.screen.world.MoreOptionsDialog;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
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
public class MoreOptionsDialogMixin {

	@Shadow
	private CyclingButtonWidget<Holder<GeneratorType>> mapTypeButton;

	@Shadow
	private Optional<Holder<GeneratorType>> generatorType;

	@Shadow
	private C_njsjipmy generatorOptions;

	@Shadow
	private ButtonWidget unchangeableMapTypeButton;

	@Inject(
		method = "init",
		at = @At( "TAIL" )
	)
	public void onInit( CallbackInfo ci ) {
		Config.load();
		var registry = this.generatorOptions.registryAccess().get( Registry.WORLD_PRESET_WORLDGEN );
		var genType = registry.getHolder( RegistryKey.of( Registry.WORLD_PRESET_WORLDGEN, Config.get().defaultMapType ) );
		this.generatorType = genType;
		this.mapTypeButton.setValue( genType.orElseThrow() );
	}

}
