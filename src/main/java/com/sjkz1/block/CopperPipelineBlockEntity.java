package com.sjkz1.block;

import com.sjkz1.handler.CopperPipelineScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class CopperPipelineBlockEntity extends LootableContainerBlockEntity {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);


    protected CopperPipelineBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlock.COPPER_PIPELINE_BLOCK_ENTITY, blockPos, blockState);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inventory);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory);
        }
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.copper_pipeline");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new CopperPipelineScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    public int chooseNonEmptySlot(Random random) {
        this.checkLootInteraction(null);
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.inventory.size(); k++) {
            if (!this.inventory.get(k).isEmpty() && random.nextInt(j++) == 0) {
                i = k;
            }
        }

        return i;
    }

    public static void serverTick(World world, BlockPos blockPos, BlockState state, CopperPipelineBlockEntity copperPipelineBlockEntity) {
        BlockPointerImpl blockPointerImpl = new BlockPointerImpl((ServerWorld) world, blockPos);
        CopperPipelineBlockEntity copperPipelineBlockEntity1 = blockPointerImpl.getBlockEntity();
        int i = copperPipelineBlockEntity1.chooseNonEmptySlot(world.random);
        if (i >= 0) {
            ItemStack itemStack = copperPipelineBlockEntity.getStack(i);
            if (world.getTime() % 20 == 0) {
                if (!itemStack.isEmpty()) {
                    var direction = world.getBlockState(blockPos).get(Properties.FACING);
                    Inventory inventory = HopperBlockEntity.getInventoryAt(world, blockPos.offset(direction));
                    ItemStack itemStack2;
                    if (inventory == null) {
                        itemStack2 = itemStack;
                    } else {
                        itemStack2 = HopperBlockEntity.transfer(copperPipelineBlockEntity1, inventory, itemStack.copy().split(1), direction.getOpposite());
                        if (itemStack2.isEmpty()) {
                            itemStack2 = itemStack.copy();
                            itemStack2.decrement(1);
                        } else {
                            itemStack2 = itemStack.copy();
                        }
                    }
                    copperPipelineBlockEntity1.setStack(i, itemStack2);
                }
            }
        }
    }
}
