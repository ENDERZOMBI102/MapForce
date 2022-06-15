package com.enderzombi102.mapforce;

import com.enderzombi102.mapforce.config.Config;
import net.fabricmc.loader.api.FabricLoader;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class MapForce implements ModInitializer, net.fabricmc.api.ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("MapForce");

	@Override
	public void onInitialize( ModContainer mod ) {
		Config.create(); // create config file
		LOGGER.info("[MapForce] Time to fuck up world generation! MapForce {}", mod.metadata().version().raw() );
	}

	@Override
	public void onInitialize() {
		Config.create(); // create config file
		LOGGER.info(
			"[MapForce] Time to fuck up world generation! MapForce {}",
			FabricLoader.getInstance()
				.getModContainer( "mapforce" )
				.orElseThrow()
				.getMetadata()
				.getVersion()
				.getFriendlyString()
		);
	}
}
