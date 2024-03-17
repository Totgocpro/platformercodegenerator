
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.tototcs.platformercodegenerator.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.tototcs.platformercodegenerator.client.gui.GendataUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PlatformercodegeneratorModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(PlatformercodegeneratorModMenus.GENDATA_UI.get(), GendataUIScreen::new);
		});
	}
}
