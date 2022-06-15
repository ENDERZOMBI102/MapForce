package com.enderzombi102.mapforce.config;

import blue.endless.jankson.Comment;
import net.minecraft.util.Identifier;

public class ConfigData {
	@Comment("Set the default world generator to this type")
	public Identifier defaultMapType = new Identifier( "normal" );
	@Comment("Set to false to disable the \"More World Options..\" button")
	public boolean moreWorldOptionsButtonEnabled = true;
	@Comment("Set to true to log all registered world gen types IDs (will be logged when the world creation screen is opened).")
	public boolean logWorldTypes = false;
}
