package com.smashingmods.chemlib.common.items;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import com.smashingmods.chemlib.api.Chemical;
import com.smashingmods.chemlib.api.ChemicalBlockType;
import com.smashingmods.chemlib.api.Element;
import com.smashingmods.chemlib.api.MatterState;
import com.smashingmods.chemlib.common.blocks.ChemicalBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@MethodsReturnNonnullByDefault
public class ChemicalBlockItem extends BlockItem implements Chemical {

    private final ChemicalBlock block;
    private final ChemicalBlockType type;

    public ChemicalBlockItem(ChemicalBlock block, FabricItemSettings settings) {
        super(block, settings);
        this.block = block;
        this.type = block.getBlockType();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (getChemical() instanceof Element element) {
            tooltip.add(Text.literal(String.format("%s (%d)", getAbbreviation(), element.getAtomicNumber())).setStyle(Style.EMPTY.withColor(Formatting.DARK_AQUA)));
            tooltip.add(Text.literal(element.getGroupName()).setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
        }
    }

    public Chemical getChemical() {
        return block.getChemical();
    }

    @Override
    public String getChemicalName() {
        return block.getChemicalName();
    }

    @Override
    public String getAbbreviation() {
        return getChemical().getAbbreviation();
    }

    @Override
    public MatterState getMatterState() {
        return getChemical().getMatterState();
    }

    @Override
    public String getChemicalDescription() {
        return getChemical().getChemicalDescription();
    }

    @Override
    public int getColor() {
        return getChemical().getColor();
    }

    public int getColor(ItemStack pItemStack, int pTintIndex) {
        return getColor();
    }

    public ChemicalBlockType getType() {
        return type;
    }
}
