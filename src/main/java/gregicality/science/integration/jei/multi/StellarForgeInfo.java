package gregicality.science.integration.jei.multi;

import gregicality.science.common.block.GAMultiblockCasing2;
import gregicality.science.common.block.GCYSciMetaBlocks;
import gregicality.science.common.block.components.EmitterCasing;
import gregicality.science.common.block.fusion.GAFusionCasing;
import gregicality.science.common.item.metal.MetalCasing2;
import gregicality.science.common.machine.GCYSciTileEntities;
import gregicality.science.integration.jei.GAMultiblockShapeInfo;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;
import java.util.List;

public class StellarForgeInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GCYSciTileEntities.STELLAR_FORGE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
         builder.aisle("###############", "######CCC######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######CCC######", "###############")
                .aisle("######C#C######", "#####FFFFF#####", "###############", "###############", "###############", "###############", "###############", "#####FFFFF#####", "######C#C######")
                .aisle("######C#C######", "###FF#####FF###", "###############", "###############", "###############", "###############", "###############", "###FF#####FF###", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "#####FFFFF#####", "###############", "###############", "###############", "#####FFFFF#####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "####F#XXX#F####", "######XXX######", "######XXX######", "######XXX######", "####F#XXX#F####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "#F####XXX####F#", "###F#X###X#F###", "#####X###X#####", "#####X###X#####", "#####X###X#####", "###F#X###X#F###", "#F####XXX####F#", "######C#C######")
                .aisle("#CCCCCCMCCCCCC#", "IF###XXXXX###FC", "f##FX#####XF##C", "C###X#####X###C", "C###X#####X###C", "C###X#####X###C", "C##FX#####XF##C", "CF###XXXXX###FC", "#CCCCCCMCCCCCC#")
                .aisle("######MMM######", "SF###XXXXX###FE", "###FX#####XF###", "####X#####X####", "####X#####X####", "####X#####X####", "###FX#####XF###", "CF###XXXXX###FC", "######MMM######")
                .aisle("#CCCCCCMCCCCCC#", "iF###XXXXX###FC", "H##FX#####XF##C", "C###X#####X###C", "C###X#####X###C", "C###X#####X###C", "C##FX#####XF##C", "CF###XXXXX###FC", "#CCCCCCMCCCCCC#")
                .aisle("######C#C######", "#F####XXX####F#", "###F#X###X#F###", "#####X###X#####", "#####X###X#####", "#####X###X#####", "###F#X###X#F###", "#F####XXX####F#", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "####F#XXX#F####", "######XXX######", "######XXX######", "######XXX######", "####F#XXX#F####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "#####FFFFF#####", "###############", "###############", "###############", "#####FFFFF#####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "###FF#####FF###", "###############", "###############", "###############", "###############", "###############", "###FF#####FF###", "######C#C######")
                .aisle("######C#C######", "#####FFFFF#####", "###############", "###############", "###############", "###############", "###############", "#####FFFFF#####", "######C#C######")
                .aisle("###############", "######CCC######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######CCC######", "###############")
                .where('M', GCYSciMetaBlocks.EMITTER_CASING.getState(EmitterCasing.CasingType.EMITTER_UV))
                .where('C', GCYSciMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.ENRICHED_NAQUADAH_ALLOY))
                .where('X', GCYSciMetaBlocks.MUTLIBLOCK_CASING2.getState(GAMultiblockCasing2.CasingType.STELLAR_CONTAINMENT))
                .where('F', GCYSciMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_COIL_2))
                .where('S', GCYSciTileEntities.STELLAR_FORGE, EnumFacing.WEST)
                .where('H', maintenanceIfEnabled(GCYSciMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.ENRICHED_NAQUADAH_ALLOY)), EnumFacing.WEST)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[8], EnumFacing.EAST)
                .where('f', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.WEST)
                .where('i', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.WEST);

        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[] {I18n.format("gtadditions.multiblock.stellar_forge.description")};
    }

    private static final ITextComponent emitterTooltip = new TextComponentTranslation("gregtech.multiblock.universal.component_casing.tooltip").setStyle(new Style().setColor(TextFormatting.RED));

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        for (EmitterCasing.CasingType casingType : EmitterCasing.CasingType.values()) {
            this.addBlockTooltip(GCYSciMetaBlocks.EMITTER_CASING.getItemVariant(casingType), emitterTooltip);
        }
    }

    @Override
    public float getDefaultZoom() {
        return 0.4f;
    }
}
