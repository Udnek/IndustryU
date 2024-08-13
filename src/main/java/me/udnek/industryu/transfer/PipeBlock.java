package me.udnek.industryu.transfer;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.LightningRod;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;

import java.util.ArrayList;
import java.util.List;

public enum PipeBlock {
    QUAD_FORK {
        @Override
        public BlockFace[] possibleInputs(Block block) {
            return SIDE_FACES;
        }
        public boolean canBeNextFrom(PipeBlock pipeBlock) {return (pipeBlock != QUAD_FORK);}
    },

    CORNER {
        @Override
        public BlockFace[] possibleInputs(Block block) {
            Stairs state = (Stairs) block.getBlockData();
            return new BlockFace[]{
                    state.getFacing(),
                    (state.getHalf() == Bisected.Half.TOP ? BlockFace.UP : BlockFace.DOWN)
            };
        }
    },

    PIPE {
        @Override
        public BlockFace[] possibleOutputs(Block block) {return new BlockFace[]{((LightningRod) block.getBlockData()).getFacing()};}
        @Override
        public BlockFace[] possibleInputs(Block block) {return new BlockFace[]{((LightningRod) block.getBlockData()).getFacing().getOppositeFace()};}
        @Override
        public BlockFace destinationOutput(Block block) {return ((LightningRod) block.getBlockData()).getFacing();}
        @Override
        public BlockFace destinationInput(Block block) {return destinationOutput(block).getOppositeFace();}
    },
    FORK {
        @Override
        public BlockFace[] possibleInputs(Block block) {return ALL_FACES;}
        @Override
        public boolean canBeNextFrom(PipeBlock pipeBlock) {return (pipeBlock != FORK);}
    },
    UNKNOWN {
        @Override
        public BlockFace[] possibleInputs(Block block) {return new BlockFace[]{};}
    };

    public static final BlockFace[] ALL_FACES = new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN};
    public static final BlockFace[] SIDE_FACES = new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
    PipeBlock(){}

    public static PipeBlock get(Block block){
        return switch (block.getType()) {
            case LIGHTNING_ROD -> PIPE;
            case WAXED_CHISELED_COPPER -> FORK;
            case WAXED_CUT_COPPER_STAIRS -> CORNER;
            case WAXED_CUT_COPPER_SLAB -> {
                if (((Slab) block.getBlockData()).getType() == Slab.Type.DOUBLE) {
                    yield UNKNOWN;
                }
                yield QUAD_FORK;
            }
            default -> UNKNOWN;
        };
    }
    public BlockFace[] possibleOutputs(Block block){return possibleInputs(block);}
    public abstract BlockFace[] possibleInputs(Block block);
    public BlockFace destinationOutput(Block block){return null;}
    public BlockFace destinationInput(Block block){return null;}
    public boolean canBeNextFrom(PipeBlock pipeBlock){return true;}

    public static PipeBlock get(Location location){
        return get(location.getBlock());
    }
    public static BlockFace getDestinationOutput(Block block){
        return get(block).destinationOutput(block);
    }
    public static BlockFace getDestinationInput(Block block){
        return get(block).destinationInput(block);
    }
    public static List<BlockFace> getNext(BlockFace input, Block currentBlock){

        List<BlockFace> next = new ArrayList<>();
        PipeBlock currentPipeBlock = get(currentBlock);
        for (BlockFace possibleOutput : currentPipeBlock.possibleOutputs(currentBlock)) {

            if (possibleOutput == input) continue;
            Block nextBlock = currentBlock.getRelative(possibleOutput);
            if (!get(nextBlock).canBeNextFrom(currentPipeBlock)) continue;
            if (hasInput(nextBlock, possibleOutput.getOppositeFace())){
                next.add(possibleOutput);
            }
        }

        return next;
    }

    public static boolean hasInput(Block block, BlockFace input){
        BlockFace[] possibleInputs = get(block).possibleInputs(block);
        for (BlockFace possibleInput : possibleInputs) {
            if (possibleInput == input) return true;
        }
        return false;
    }
}
