package gregicality.science.integration.jei.multi;

import gregicality.science.common.block.GAMultiblockCasing2;
import gregicality.science.common.block.GATransparentCasing;
import gregicality.science.common.block.GCYSciMetaBlocks;
import gregicality.science.common.block.components.EmitterCasing;
import gregicality.science.common.block.components.FieldGenCasing;
import gregicality.science.common.block.components.PumpCasing;
import gregicality.science.common.block.components.SensorCasing;
import gregicality.science.common.machine.GCYSciTileEntities;
import gregicality.science.integration.jei.GAMultiblockShapeInfo;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;


public class BioReactorInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GCYSciTileEntities.BIO_REACTOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shape = new ArrayList<>();
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        shape.add(builder
                .aisle("XXeXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .aisle("XXXXX", "G###G", "G#s#G", "G###G", "XXXXX")
                .aisle("XXXXX", "G#P#G", "GEFEG", "G#P#G", "XXXXX")
                .aisle("XXXXX", "G###G", "G#s#G", "G###G", "XXXXX")
                .aisle("oISOi", "XGGGX", "XGGGX", "XGGGX", "XXMXX")
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.SOUTH)
                .where('o', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.SOUTH)
                .where('i', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.SOUTH)
                .where('S', GCYSciTileEntities.BIO_REACTOR, EnumFacing.SOUTH)
                .where('M', maintenanceIfEnabled(GCYSciMetaBlocks.MUTLIBLOCK_CASING2.getState(GAMultiblockCasing2.CasingType.BIO_REACTOR)), EnumFacing.SOUTH)
                .where('X', GCYSciMetaBlocks.MUTLIBLOCK_CASING2.getState(GAMultiblockCasing2.CasingType.BIO_REACTOR))
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.UV], EnumFacing.NORTH)
                .where('E', GCYSciMetaBlocks.EMITTER_CASING.getState(EmitterCasing.CasingType.EMITTER_UV))
                .where('P', GCYSciMetaBlocks.PUMP_CASING.getState(PumpCasing.CasingType.PUMP_UV))
                .where('F', GCYSciMetaBlocks.FIELD_GEN_CASING.getState(FieldGenCasing.CasingType.FIELD_GENERATOR_UV))
                .where('s', GCYSciMetaBlocks.SENSOR_CASING.getState(SensorCasing.CasingType.SENSOR_UV))
                .where('G', GCYSciMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                .where('#', Blocks.AIR.getDefaultState())
                .build());
        return shape;
    }

    @Override
    public String[] getDescription() {
        return new String[] {I18n.format("gtadditions.multiblock.bio_reactor.description")};
    }


    @Override
    public float getDefaultZoom() {
        return 0.7f;
    }
}
