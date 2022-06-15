package com.enderzombi102.mapforce.config;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonGrammar;
import blue.endless.jankson.JsonPrimitive;
import blue.endless.jankson.api.SyntaxError;
import com.enderzombi102.mapforce.MapForce;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.QuiltLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
	private static final JsonGrammar JSON5_GRAMMAR = JsonGrammar.builder()
		.withComments(true)
		.printUnquotedKeys(true)
		.printTrailingCommas(true)
		.bareSpecialNumerics(true)
		.build();
	private static final Jankson JANKSON = Jankson.builder()
		.registerDeserializer(
			String.class,
			Identifier.class,
			( string, marshaller ) -> new Identifier( string )
		)
		.registerSerializer(
			Identifier.class,
			( identifier, marshaller ) -> new JsonPrimitive( identifier.toString() )
		)
		.build();
	private static final Path CONFIG_LOCATION = QuiltLoader.getConfigDir().resolve( "mapforce.json5" );
	private static @Nullable ConfigData DATA = null;

	public static @NotNull ConfigData get() {
		if ( DATA == null )
			load();
		return DATA;
	}

	public static void save() {
		try {
			Files.writeString(
				CONFIG_LOCATION,
				JANKSON.toJson( DATA ).toJson( JSON5_GRAMMAR )
			);
		} catch ( IOException ex ) {
			MapForce.LOGGER.error( "Failed to save config file!", ex );
		}
	}

	public static void load() {
		try {
			DATA = JANKSON.fromJson( Files.readString( CONFIG_LOCATION ), ConfigData.class );
		} catch ( SyntaxError e ) {
			throw new RuntimeException( e );
		} catch ( IOException e ) {
			MapForce.LOGGER.warn( "Config file does not exist, will create a new one." );
			create();
		}
	}

	public static void create() {
		DATA = new ConfigData();
		save();
	}
}
