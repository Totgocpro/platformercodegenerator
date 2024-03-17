
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.tototcs.platformercodegenerator.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import fr.tototcs.platformercodegenerator.network.StartgenerateMessage;
import fr.tototcs.platformercodegenerator.PlatformercodegeneratorMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class PlatformercodegeneratorModKeyMappings {
	public static final KeyMapping STARTGENERATE = new KeyMapping("key.platformercodegenerator.startgenerate", GLFW.GLFW_KEY_KP_9, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PlatformercodegeneratorMod.PACKET_HANDLER.sendToServer(new StartgenerateMessage(0, 0));
				StartgenerateMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(STARTGENERATE);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				STARTGENERATE.consumeClick();
			}
		}
	}
}
