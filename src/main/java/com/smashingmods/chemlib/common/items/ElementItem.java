package com.smashingmods.chemlib.common.items;

import com.smashingmods.chemlib.api.Element;
import com.smashingmods.chemlib.api.MatterState;
import com.smashingmods.chemlib.api.MetalType;
import com.smashingmods.chemlib.registry.ItemRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElementItem extends Item implements Element {

    private final String elementName;
    private final int atomicNumber;
    private final String abbreviation;
    private final int group;
    private final int period;
    private final MatterState matterState;
    private final MetalType metalType;
    private final boolean artificial;
    private final int color;

    public ElementItem(String pChemicalName, int pAtomicNumber, String pAbbreviation, int pGroup, int pPeriod, MatterState pMatterState, MetalType pMetalType, boolean pArtificial, String pColor) {
        /*super(new FabricItemSettings().group(ItemRegistry.ELEMENTS_TAB));*/
        super(new FabricItemSettings());
        this.elementName = pChemicalName;
        this.atomicNumber = pAtomicNumber;
        this.abbreviation = pAbbreviation;
        this.group = pGroup;
        this.period = pPeriod;
        this.matterState = pMatterState;
        this.metalType = pMetalType;
        this.artificial = pArtificial;
        this.color = (int) Long.parseLong(pColor, 16);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.literal(String.format("%s (%d)", getAbbreviation(), atomicNumber)).setStyle(Style.EMPTY.withColor(Formatting.DARK_AQUA)));
        if (!getGroupName().isEmpty()) {
            tooltip.add(Text.literal(getGroupName()).setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
        }
    }

    @Override
    public String getChemicalName() {
        return this.elementName;
    }

    @Override
    public int getAtomicNumber() {
        return this.atomicNumber;
    }

    @Override
    public String getAbbreviation() {
        return this.abbreviation;
    }

    @Override
    public int getPeriod() {
        return period;
    }

    @Override
    public int getElementGroup() {
        return group;
    }

    public String getGroupName() {
        return switch(group) {
            case 1, 6, 7, 8, 15, 16, 34 -> "Reactive Non-Metals";
            case 3, 11, 19, 37, 55, 87 -> "Alkali Metals";
            case 4, 12, 20, 38, 56, 88 -> "Alkaline Earth Metals";
            case 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 72, 73, 74, 75, 76, 77, 78, 79, 80, 104, 105, 106, 107, 108 -> "Transition Metals";
            case 13, 31, 49, 50, 81, 82, 83, 84 -> "Post-Transition Metals";
            case 109, 110, 111, 112, 113, 114, 115, 116, 117, 118 -> "Unknown Properties";
            case 5, 14, 32, 33, 51, 52 -> "Metalloids";
            case 9, 17, 35, 53, 85 -> "Halogens";
            case 2, 10, 18, 36, 54, 86 -> "Noble Gasses";
            case 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71 -> "Lanthanides";
            case 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103 -> "Actinides";
            default -> "";
        };
    }

    @Override
    public MatterState getMatterState() {
        return matterState;
    }

    @Override
    public MetalType getMetalType() {
        return metalType;
    }

    @Override
    public boolean isArtificial() {
        return artificial;
    }

    @Override
    public String getChemicalDescription() {
        return "";
    }

    @Override
    public int getColor() {
        return this.color;
    }

    public int getColor(ItemStack pItemStack, int pTintIndex) {
        return pTintIndex > 0 ? -1 : color;
    }

}
