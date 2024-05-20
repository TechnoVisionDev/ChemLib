package com.smashingmods.chemlib.registry;

import com.smashingmods.chemlib.api.ChemicalBlockType;
import com.smashingmods.chemlib.common.blocks.ChemicalBlock;
import com.smashingmods.chemlib.common.blocks.LampBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.stream.Stream;

import static net.minecraft.state.property.Properties.LIT;

public class BlockRegistry {

    public static final List<ChemicalBlock> METAL_BLOCKS = new ArrayList<>();
    public static final List<ChemicalBlock> LAMP_BLOCKS = new ArrayList<>();

    public static final FabricBlockSettings METAL_PROPERTIES = FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(5.0f, 12.0f).sounds(BlockSoundGroup.METAL).requiresTool();

    public static final FabricBlockSettings LAMP_PROPERTIES = FabricBlockSettings.copyOf(Blocks.GLASS).strength(2.0f, 2.0f).sounds(BlockSoundGroup.GLASS).requiresTool().luminance(state -> state.get(LIT) ? 15 : 0);

    public static Optional<ChemicalBlock> getChemicalBlockByName(String name) {
        return getAllChemicalBlocks().stream().filter(blockRegistryObject -> blockRegistryObject.toString().equals(name)).findFirst();
    }

    public static List<ChemicalBlock> getMetalBlocks() {
        return METAL_BLOCKS;
    }

    public static List<ChemicalBlock> getLampBlocks() {
        return LAMP_BLOCKS;
    }

    public static List<ChemicalBlock> getAllChemicalBlocks() {
        List<ChemicalBlock> all = new ArrayList<>();
        all.addAll(METAL_BLOCKS);
        all.addAll(LAMP_BLOCKS);
        return all;
    }

    public static List<ChemicalBlock> getChemicalBlocksByType(ChemicalBlockType pChemicalBlockType) {
        return switch (pChemicalBlockType) {
            case METAL -> METAL_BLOCKS;
            case LAMP -> LAMP_BLOCKS;
        };
    }

    public static Stream<ChemicalBlock> getChemicalBlocksStreamByType(ChemicalBlockType pChemicalBlockType) {
        return getChemicalBlocksByType(pChemicalBlockType)
                .stream().filter(block -> block.getBlockType().equals(pChemicalBlockType));
    }

    public static Optional<ChemicalBlock> getChemicalBlockByNameAndType(String pName, ChemicalBlockType pChemicalBlockType) {
        return getChemicalBlocksStreamByType(pChemicalBlockType)
                .filter(block -> block.getChemical().getChemicalName().equals(pName))
                .findFirst();
    }

    protected static ChemicalBlock registerBlock(Identifier chemicalIdentifier, Identifier blockIdentifier, ChemicalBlockType type) {
        ChemicalBlock chemicalBlock;
        FabricBlockSettings settings;
        if (type == ChemicalBlockType.METAL) {
            settings = METAL_PROPERTIES;
            chemicalBlock = new ChemicalBlock(chemicalIdentifier, type, settings);
            METAL_BLOCKS.add(chemicalBlock);
        } else {
            settings = LAMP_PROPERTIES;
            chemicalBlock = new LampBlock(chemicalIdentifier, type, settings);
            LAMP_BLOCKS.add(chemicalBlock);
        }
        Registry.register(Registries.BLOCK, blockIdentifier, chemicalBlock);
        return chemicalBlock;
    }
}
