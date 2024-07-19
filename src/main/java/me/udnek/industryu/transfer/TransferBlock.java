package me.udnek.industryu.transfer;

import me.udnek.itemscoreu.nms.NMS;
import me.udnek.itemscoreu.utils.LogUtils;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.LightningRod;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TransferBlock {

    CORNER {
        @Override
        public BlockFace[] getPossibleOutputs(Block block) {
            Stairs state = (Stairs) block.getBlockData();
            return new BlockFace[]{
                    state.getFacing(),
                    (state.getHalf() == Bisected.Half.TOP ? BlockFace.UP : BlockFace.DOWN)
            };
        }

        @Override
        public BlockFace[] getPossibleInputs(Block block) {
            return getPossibleOutputs(block);
        }
    },

    PIPE {
        @Override
        public BlockFace[] getPossibleOutputs(Block block) {
            return new BlockFace[]{((LightningRod) block.getBlockData()).getFacing()};
        }

        @Override
        public BlockFace[] getPossibleInputs(Block block) {
            return new BlockFace[]{((LightningRod) block.getBlockData()).getFacing().getOppositeFace()};
        }
    },
    FORK {
        @Override
        public BlockFace[] getPossibleOutputs(Block block) {
            return new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN};
        }

        @Override
        public BlockFace[] getPossibleInputs(Block block) {
            return new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN};
        }

        @Override
        public boolean isAppropriateInput(TransferBlock transferBlock) {
            return (transferBlock != FORK);
        }
    },
    UNKNOWN {
        @Override
        public BlockFace[] getPossibleOutputs(Block block) {
            return new BlockFace[]{};
        }

        @Override
        public BlockFace[] getPossibleInputs(Block block) {
            return new BlockFace[]{};
        }
    };


    TransferBlock(){}

    public static TransferBlock get(Block block){
        return switch (block.getType()) {
            case LIGHTNING_ROD -> PIPE;
            case WAXED_CHISELED_COPPER -> FORK;
            case WAXED_CUT_COPPER_STAIRS -> CORNER;
            default -> UNKNOWN;
        };
    }
    public abstract BlockFace[] getPossibleOutputs(Block block);
    public abstract BlockFace[] getPossibleInputs(Block block);

    public boolean isAppropriateInput(TransferBlock transferBlock){return true;}

    public static List<BlockFace> getNext(BlockFace input, Block currentBlock){

        List<BlockFace> next = new ArrayList<>();
        TransferBlock currentTransferBlock = get(currentBlock);
        for (BlockFace possibleOutput : currentTransferBlock.getPossibleOutputs(currentBlock)) {

            if (possibleOutput == input) continue;
            Block nextBlock = getAlong(currentBlock, possibleOutput);
            if (!get(nextBlock).isAppropriateInput(currentTransferBlock)) continue;
            if (hasInput(nextBlock, possibleOutput.getOppositeFace())){
                next.add(possibleOutput);
            }
        }

        return next;
    }

    public static Block getAlong(Block block, BlockFace direction){
        return block.getLocation().add(direction.getDirection()).getBlock();
    }
    public static Location getAlong(Location location, BlockFace direction){
        return location.clone().add(direction.getDirection());
    }

    public static boolean hasInput(Block block, BlockFace input){
        BlockFace[] possibleInputs = get(block).getPossibleInputs(block);
        for (BlockFace possibleInput : possibleInputs) {
            if (possibleInput == input) return true;
        }
        return false;
    }
}
