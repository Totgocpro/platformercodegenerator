
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.tototcs.platformercodegenerator.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import fr.tototcs.platformercodegenerator.world.inventory.GendataUIMenu;
import fr.tototcs.platformercodegenerator.PlatformercodegeneratorMod;

public class PlatformercodegeneratorModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, PlatformercodegeneratorMod.MODID);
	public static final RegistryObject<MenuType<GendataUIMenu>> GENDATA_UI = REGISTRY.register("gendata_ui", () -> IForgeMenuType.create(GendataUIMenu::new));
}
