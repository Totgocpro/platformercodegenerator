package fr.tototcs.platformercodegenerator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

import fr.tototcs.platformercodegenerator.PlatformercodegeneratorMod;

public class GenerateProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		double i = 0;
		double endx = 0;
		double endy = 0;
		double endz = 0;
		double worldsizeX = 0;
		double worldsizeY = 0;
		double worldsizeZ = 0;
		List<Object> blocks = new ArrayList<>();
		String currentcode = "";
		File file = new File("");
		i = 0;
		endx = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:SizeX") ? ((EditBox) guistate.get("text:SizeX")).getValue() : "");
		endy = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:SizeY") ? ((EditBox) guistate.get("text:SizeY")).getValue() : "");
		endz = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:SizeZ") ? ((EditBox) guistate.get("text:SizeZ")).getValue() : "");
		worldsizeX = endx;
		worldsizeY = endy;
		worldsizeZ = endz;
		//get all blocks an store it
		for (int index0 = 0; index0 < (int) (endx * endy * endz); index0++) {
			blocks.add((world.getBlockState(BlockPos.containing(i % worldsizeX, Math.round(i / worldsizeX) % worldsizeY, Math.round(i / (worldsizeX * worldsizeY))))));
			i = i + 1;
		}
		//generate level code
		currentcode = worldsizeX + "-" + worldsizeY + "-" + worldsizeZ + "!";
		i = 0;
		for (int index1 = 0; index1 < (int) blocks.size(); index1++) {
			currentcode = currentcode + "" + (ForgeRegistries.BLOCKS.getKey((blocks.get((int) i) instanceof BlockState _bs ? _bs : Blocks.AIR.defaultBlockState()).getBlock()).toString()) + "@";
			i = i + 1;
		}
		currentcode = currentcode + "+";
		file = new File((Minecraft.getInstance().gameDirectory.getAbsolutePath() + "" + File.separator
				+ (world.isClientSide() ? Minecraft.getInstance().getSingleplayerServer().getWorldData().getLevelName() : ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()) + File.separator), File.separator + "code.code");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		try {
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter filebw = new BufferedWriter(filewriter);
			{
				filebw.write(currentcode);
				filebw.newLine();
			}
			filebw.close();
			filewriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		PlatformercodegeneratorMod.LOGGER.info(currentcode);
		PlatformercodegeneratorMod.LOGGER.info(file.getPath());
		if (entity instanceof Player _player)
			_player.closeContainer();
	}
}
