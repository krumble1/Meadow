package net.satisfyu.meadow.terrablender;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.satisfyu.meadow.world.MeadowSurfaceRules;
import terrablender.api.*;

import java.util.List;
import java.util.function.Consumer;

import static net.satisfyu.meadow.Meadow.MOD_ID;


public class MeadowRegion extends Region {

    public MeadowRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        addModifiedVanillaOverworldBiomes(mapper, builder -> {

            List<Climate.ParameterPoint> meadowForestPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F))
                    .humidity(Climate.Parameter.span(-1.0F, -0.36F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F))
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_1, ParameterUtils.Erosion.EROSION_2, ParameterUtils.Erosion.EROSION_3)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.PEAK_NORMAL, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .build();

            meadowForestPoints.forEach(point -> builder.replaceBiome(point, MeadowSurfaceRules.MEADOW_FOREST_KEY));

            List<Climate.ParameterPoint> meadowClearingPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F))
                    .humidity(Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F))
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_1, ParameterUtils.Erosion.EROSION_2, ParameterUtils.Erosion.EROSION_3)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .build();

            meadowClearingPoints.forEach(point -> builder.replaceBiome(point, MeadowSurfaceRules.MEADOW_CLEARING_KEY));
        });
    }



    public static void loadTerrablender() {
        Regions.register(new MeadowRegion(new ResourceLocation(MOD_ID, "overworld"), 10));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, MeadowSurfaceRules.makeRules());
    }

}
