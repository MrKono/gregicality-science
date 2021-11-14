package gregicality.science.common.block.components;

import com.google.common.collect.ImmutableMap;
import gregicality.science.api.GCYSciLog;
import gregicality.science.client.model.IReTexturedModel;
import gregicality.science.client.model.ReTexturedModel;
import gregicality.science.client.model.ReTexturedModelLoader;
import gregicality.science.common.GCYSciConfig;
import gregicality.science.utils.BlockPatternChecker;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class ReTexturedCasing<T extends Enum<T> & IStringSerializable> extends VariantBlock<T> implements IReTexturedModel {

    private final static ResourceLocation FRAME_MODEL = new ResourceLocation("gregicality","block/casing/frame");
    private final static ResourceLocation GLASS_MODEL = new ResourceLocation("gregicality","block/casing/glass");
    private final ResourceLocation CORE_MODEL;
    private ControllerProperty CONTROLLER;


    public ReTexturedCasing(ResourceLocation core) {
        super(Material.IRON);
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        CORE_MODEL = core;
    }

    @Override
    public void register(IBlockState state, ResourceLocation path) {
        if (GCYSciConfig.client.AdvancedCasingModel) {
            ReTexturedModelLoader.register(this, path, new ReTexturedModel(getModels(state)));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ResourceLocation[] getModels(IBlockState blockState) {
        return new ResourceLocation[]{CORE_MODEL, FRAME_MODEL, GLASS_MODEL};
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ImmutableMap<String, String> reTextured(IBlockState blockState, EnumFacing enumFacing, ResourceLocation model) {
        if (model == FRAME_MODEL && blockState instanceof IExtendedBlockState && enumFacing == null) {
            MultiblockControllerBase controller = ((IExtendedBlockState) blockState).getValue(CONTROLLER);
            if (controller == null) return null;
            ICubeRenderer texture = controller.getBaseTexture(null);
            if (texture == null) return null;
            return new ImmutableMap.Builder<String,String>()
                    .put("1", controller.getBaseTexture(null).getParticleSprite().getIconName())
                    .build();
        }
        return null;
    }

    @Override
    public boolean shouldRenderInLayer(IBlockState blockState, EnumFacing side, ResourceLocation model, BlockRenderLayer layer) {
        if (blockState == null) return true;
        if (GLASS_MODEL == model && layer == BlockRenderLayer.TRANSLUCENT) {
            return true;
        } else if (GLASS_MODEL != model && layer == BlockRenderLayer.CUTOUT_MIPPED) {
            return true;
        }
        return false;
    }

    @Override
    public IModel loadModel(ResourceLocation variant, ResourceLocation modelRes, IModel model) {
        if (modelRes == CORE_MODEL && variant instanceof ModelResourceLocation) {
            String[] tierS = ((ModelResourceLocation) variant).getVariant().split("_");
            if (tierS.length > 0) {
                return model.retexture(new ImmutableMap.Builder<String,String>()
                        .put("0", "gregicality:blocks/casing/"+tierS[tierS.length-1])
                        .build());
            }
        }
        return model;
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state instanceof IExtendedBlockState && FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            state = ((IExtendedBlockState) state).withProperty(CONTROLLER, findController(world, pos));
        }
        return super.getExtendedState(state, world, pos);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        Class<T> enumClass = GTUtility.getActualTypeParameter(this.getClass(), ReTexturedCasing.class, 0);
        this.VARIANT = PropertyEnum.create("variant", enumClass);
        this.VALUES = enumClass.getEnumConstants();
        CONTROLLER = new ControllerProperty();
        return new BlockStateContainer.Builder(this).add(this.VARIANT).add(CONTROLLER).build();
    }

    @SideOnly(Side.CLIENT)
    protected MultiblockControllerBase findController(IBlockAccess world, BlockPos pos) {
        try {
            if (world == null || pos == null) {
                return null;
            }
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    for (int z = 0; z < 10; z++) {
                        for (BlockPos blockPos : new BlockPos[] {pos.add(x, y, z), pos.add(-x, y, z), pos.add(x, -y, z)
                                , pos.add(x, y, -z), pos.add(-x, -y, z), pos.add(x, -y, -z)
                                , pos.add(-x, y, -z), pos.add(-x, -y, -z)}) {
                            TileEntity te = world.getTileEntity(blockPos);
                            if (te instanceof MetaTileEntityHolder && ((MetaTileEntityHolder) te).getMetaTileEntity() instanceof MultiblockControllerBase) {
                                MultiblockControllerBase controller = (MultiblockControllerBase) ((MetaTileEntityHolder) te).getMetaTileEntity();
                                PatternMatchContext result = BlockPatternChecker.checkPatternAt(controller);
                                if (result != null && result.get("validPos") != null) {
                                    List<BlockPos> validPos = result.get("validPos");
                                    if (validPos.contains(pos)) {
                                        return controller;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable e) {
            GCYSciLog.logger.error(pos);
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.CUTOUT_MIPPED || layer == BlockRenderLayer.TRANSLUCENT;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    /** @deprecated */
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    private static class ControllerProperty implements IUnlistedProperty<MultiblockControllerBase>{
        @Override
        public String getName() {
            return "controller";
        }

        @Override
        public boolean isValid(MultiblockControllerBase controller) {
            return true;
        }

        @Override
        public Class<MultiblockControllerBase> getType() {
            return MultiblockControllerBase.class;
        }

        @Override
        public String valueToString(MultiblockControllerBase controller) {
            return controller == null ? "null" : controller.toString();
        }
    }

}
