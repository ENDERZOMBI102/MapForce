package com.enderzombi102.mapforce.mixin;

import com.enderzombi102.mapforce.imixin.GeneratorTypeAccessor;
import net.minecraft.client.world.GeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(GeneratorType.class)
public abstract class GeneratorTypeMixin implements GeneratorTypeAccessor {
	@Override
	@Accessor("VALUES")
	public abstract List<GeneratorType> getValues();
}
