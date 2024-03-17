package fr.tototcs.platformercodegenerator.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.tototcs.platformercodegenerator.world.inventory.GendataUIMenu;
import fr.tototcs.platformercodegenerator.network.GendataUIButtonMessage;
import fr.tototcs.platformercodegenerator.PlatformercodegeneratorMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class GendataUIScreen extends AbstractContainerScreen<GendataUIMenu> {
	private final static HashMap<String, Object> guistate = GendataUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox SizeX;
	EditBox SizeY;
	EditBox SizeZ;
	Button button_generate;

	public GendataUIScreen(GendataUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 179;
		this.imageHeight = 176;
	}

	private static final ResourceLocation texture = new ResourceLocation("platformercodegenerator:textures/screens/gendata_ui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		SizeX.render(guiGraphics, mouseX, mouseY, partialTicks);
		SizeY.render(guiGraphics, mouseX, mouseY, partialTicks);
		SizeZ.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (SizeX.isFocused())
			return SizeX.keyPressed(key, b, c);
		if (SizeY.isFocused())
			return SizeY.keyPressed(key, b, c);
		if (SizeZ.isFocused())
			return SizeZ.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		SizeX.tick();
		SizeY.tick();
		SizeZ.tick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.platformercodegenerator.gendata_ui.label_generate_code"), 8, 3, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.platformercodegenerator.gendata_ui.label_sizex"), 8, 21, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.platformercodegenerator.gendata_ui.label_sizey"), 8, 57, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.platformercodegenerator.gendata_ui.label_sizez"), 8, 93, -12829636, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		SizeX = new EditBox(this.font, this.leftPos + 9, this.topPos + 31, 118, 18, Component.translatable("gui.platformercodegenerator.gendata_ui.SizeX"));
		SizeX.setMaxLength(32767);
		guistate.put("text:SizeX", SizeX);
		this.addWidget(this.SizeX);
		SizeY = new EditBox(this.font, this.leftPos + 9, this.topPos + 67, 118, 18, Component.translatable("gui.platformercodegenerator.gendata_ui.SizeY"));
		SizeY.setMaxLength(32767);
		guistate.put("text:SizeY", SizeY);
		this.addWidget(this.SizeY);
		SizeZ = new EditBox(this.font, this.leftPos + 9, this.topPos + 103, 118, 18, Component.translatable("gui.platformercodegenerator.gendata_ui.SizeZ"));
		SizeZ.setMaxLength(32767);
		guistate.put("text:SizeZ", SizeZ);
		this.addWidget(this.SizeZ);
		button_generate = Button.builder(Component.translatable("gui.platformercodegenerator.gendata_ui.button_generate"), e -> {
			if (true) {
				PlatformercodegeneratorMod.PACKET_HANDLER.sendToServer(new GendataUIButtonMessage(0, x, y, z));
				GendataUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 53, this.topPos + 147, 67, 20).build();
		guistate.put("button:button_generate", button_generate);
		this.addRenderableWidget(button_generate);
	}
}
