package com.enderzombi102.mapforce;

import com.enderzombi102.mapforce.config.Config;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapForce implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("MapForce");

	@Override
	public void onInitialize( ModContainer mod ) {
		Config.create(); // create config file
		LOGGER.info("[MapForce] Time to fuck up world generation! MapForce {}", mod.metadata().version().raw() );
	}
}
