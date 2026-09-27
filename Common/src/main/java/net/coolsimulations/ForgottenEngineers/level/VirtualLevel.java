package net.coolsimulations.ForgottenEngineers.level;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.attribute.EnvironmentAttributeReader;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.LevelTickAccess;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class VirtualLevel implements LevelAccessor {

    private final Level realLevel;
    private BlockPos virtualPos;
    private BlockState virtualState;

    public VirtualLevel(Level realLevel, BlockPos virtualPos, BlockState virtualState) {
        this.realLevel = realLevel;
        this.virtualPos = virtualPos;
        this.virtualState = virtualState;
    }

    @Override
    public long nextSubTickCount() {
        return 0;
    }

    @Override
    public LevelData getLevelData() {
        return null;
    }

    @Override
    public @Nullable MinecraftServer getServer() {
        return realLevel.getServer();
    }

    @Override
    public ChunkSource getChunkSource() {
        return null;
    }

    @Override
    public RandomSource getRandom() {
        return realLevel.getRandom();
    }

    @Override
    public void playSound(@Nullable Entity except, BlockPos pos, SoundEvent sound, SoundSource source, float volume, float pitch) {

    }

    @Override
    public void addParticle(ParticleOptions particle, double x, double y, double z, double xd, double yd, double zd) {

    }

    @Override
    public void levelEvent(@Nullable Entity source, @LevelEvent.Value int type, BlockPos pos, int data) {

    }

    @Override
    public void gameEvent(Holder<GameEvent> gameEvent, Vec3 position, GameEvent.Context context) {

    }

    @Override
    public LevelLightEngine getLightEngine() {
        return realLevel.getLightEngine();
    }

    @Override
    public WorldBorder getWorldBorder() {
        return realLevel.getWorldBorder();
    }

    @Override
    public @Nullable BlockEntity getBlockEntity(BlockPos pos) {
        return null;
    }

    @Override
    public BlockState getBlockState(BlockPos pos) {
        if (pos.equals(this.virtualPos)) {
            return virtualState;
        }
        return null;
    }

    @Override
    public FluidState getFluidState(BlockPos pos) {
        return null;
    }

    @Override
    public List<Entity> getEntities(@Nullable Entity except, AABB bb, Predicate<? super Entity> selector) {
        return List.of();
    }

    @Override
    public <T extends Entity> List<T> getEntities(EntityTypeTest<Entity, T> type, AABB bb, Predicate<? super T> selector) {
        return List.of();
    }

    @Override
    public List<? extends Player> players() {
        return realLevel.players();
    }

    @Override
    public @Nullable ChunkAccess getChunk(int chunkX, int chunkZ, ChunkStatus targetStatus, boolean loadOrGenerate) {
        return null;
    }

    @Override
    public int getHeight(Heightmap.Types type, int x, int z) {
        return realLevel.getHeight(type, x, z);
    }

    @Override
    public int getSkyDarken() {
        return realLevel.getSkyDarken();
    }

    @Override
    public BiomeManager getBiomeManager() {
        return null;
    }

    @Override
    public Holder<Biome> getUncachedNoiseBiome(int quartX, int quartY, int quartZ) {
        return null;
    }

    @Override
    public boolean isClientSide() {
        return false;
    }

    @Override
    public int getSeaLevel() {
        return realLevel.getSeaLevel();
    }

    @Override
    public DimensionType dimensionType() {
        return realLevel.dimensionType();
    }

    @Override
    public RegistryAccess registryAccess() {
        return realLevel.registryAccess();
    }

    @Override
    public FeatureFlagSet enabledFeatures() {
        return realLevel.enabledFeatures();
    }

    @Override
    public EnvironmentAttributeReader environmentAttributes() {
        return realLevel.environmentAttributes();
    }

    @Override
    public boolean isStateAtPosition(BlockPos pos, Predicate<BlockState> predicate) {
        return false;
    }

    @Override
    public boolean isFluidAtPosition(BlockPos pos, Predicate<FluidState> predicate) {
        return false;
    }

    @Override
    public boolean setBlock(BlockPos pos, BlockState blockState, @Block.UpdateFlags int updateFlags, int updateLimit) {
        return false;
    }

    @Override
    public boolean removeBlock(BlockPos pos, boolean movedByPiston) {
        return false;
    }

    @Override
    public boolean destroyBlock(BlockPos pos, boolean dropResources, @Nullable Entity breaker, int updateLimit) {
        return false;
    }

    @Override
    public LevelTickAccess<Block> getBlockTicks() {
        return null;
    }

    @Override
    public LevelTickAccess<Fluid> getFluidTicks() {
        return null;
    }
}
