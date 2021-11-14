package gregicality.science.integration.jei.multi;

import gregicality.science.api.GCYSciMaterials;
import gregicality.science.common.block.GCYSciMetaBlocks;
import gregicality.science.common.block.components.EmitterCasing;
import gregicality.science.common.block.components.FieldGenCasing;
import gregicality.science.common.block.components.PumpCasing;
import gregicality.science.common.block.components.SensorCasing;
import gregicality.science.common.item.metal.MetalCasing2;
import gregicality.science.common.machine.GCYSciTileEntities;
import gregicality.science.integration.jei.GAMultiblockShapeInfo;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockFusionCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

public class CosmicRayDetectorInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GCYSciTileEntities.COSMIC_RAY_DETECTOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "####xx###xx####", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "#######x#######", "####xxx#xxx####", "###x#######x###", "###############")
                .aisle("######XMX######", "######eSf######", "######XXX######", "###############", "###############", "#######X#######", "#####xxxxx#####", "###xx#####xx###", "##x#########x##", "###############")
                .aisle("#####XXXXX#####", "#####X###X#####", "#####X###X#####", "######XXX######", "######XXX######", "#####XXXXX#####", "####xxxxxxx####", "##xx#######xx##", "#x###########x#", "###############")
                .aisle("####XXXXXXX####", "####X#####X####", "####X#####X####", "#####X###X#####", "#####X###X#####", "####XXxxxXX####", "###xxx###xxx###", "##x#########x##", "#x###########x#", "###############")
                .aisle("###XXXXXXXXX###", "###X###E###X###", "###X#######X###", "####X#####X####", "####X##F##X####", "####XxxxxxX####", "###xx#####xx###", "#xx#########xx#", "x#############x", "###############")
                .aisle("###XXXXXXXXX###", "###X##EcE##X###", "###X###c###X###", "####X##c##X####", "####X#FcF#X####", "###XXxxExxXX###", "##xxx##C##xxx##", "#x#####C#####x#", "x######C######x", "#######s#######")
                .aisle("###XXXXXXXXX###", "###X###E###X###", "###X#######X###", "####X#####X####", "####X##F##X####", "####XxxxxxX####", "###xx#####xx###", "#xx#########xx#", "x#############x", "###############")
                .aisle("####XXXXXXX####", "####X#####X####", "####X#####X####", "#####X###X#####", "#####X###X#####", "####XXxxxXX####", "###xxx###xxx###", "##x#########x##", "#x###########x#", "###############")
                .aisle("#####XXXXX#####", "#####X###X#####", "#####X###X#####", "######XXX######", "######XXX######", "#####XXXXX#####", "####xxxxxxx####", "##xx#######xx##", "#x###########x#", "###############")
                .aisle("######XXX######", "######XXX######", "######XXX######", "###############", "###############", "#######X#######", "#####xxxxx#####", "###xx#####xx###", "##x#########x##", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "#######x#######", "####xxx#xxx####", "###x#######x###", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "####xx###xx####", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "###############")
                .where('S', GCYSciTileEntities.COSMIC_RAY_DETECTOR, EnumFacing.NORTH)
                .where('M', maintenanceIfEnabled(GCYSciMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.QUANTUM)), EnumFacing.NORTH)
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.UHV], EnumFacing.NORTH)
                .where('f', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.NORTH)
                .where('X', GCYSciMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.QUANTUM))
                .where('x', GCYSciMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.TRITANIUM))
                .where('C', MetaBlocks.FRAMES.get(GCYSciMaterials.BlackTitanium).getDefaultState())
                .where('c', MetaBlocks.FUSION_COIL.getState(BlockFusionCoil.CoilType.SUPERCONDUCTOR))
                .where('F', GCYSciMetaBlocks.FIELD_GEN_CASING.getState(FieldGenCasing.CasingType.FIELD_GENERATOR_UHV))
                .where('E', GCYSciMetaBlocks.EMITTER_CASING.getState(EmitterCasing.CasingType.EMITTER_UHV))
                .where('s', GCYSciMetaBlocks.SENSOR_CASING.getState(SensorCasing.CasingType.SENSOR_UHV))
                .where('P', GCYSciMetaBlocks.PUMP_CASING.getState(PumpCasing.CasingType.PUMP_UHV))
                .where('#', Blocks.AIR.getDefaultState())
                .build();

        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[] {I18n.format("gtadditions.multiblock.cosmic_ray_detector.description")};
    }


    @Override
    public float getDefaultZoom() {
        return 0.3f;
    }
}
