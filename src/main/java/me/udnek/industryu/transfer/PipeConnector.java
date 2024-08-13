package me.udnek.industryu.transfer;

import me.udnek.itemscoreu.nms.Nms;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PipeConnector {

    // TODO: 7/22/2024 REMOVE??
    public static final boolean DEBUG = true;
    private static int classesAmount;

    protected Block currentBlock;
    protected BlockFace inputFace;
    protected int distanceLeft;
    protected List<Block> destinations;
    private PipeConnector(){
        classesAmount++;
    }
    public static @NotNull List<Block> findDestinationsFromExternal(int maxDistance, Block start){
        classesAmount = 0;
        List<Block> destinations = new ArrayList<>();
        if (PipeBlock.get(start) == PipeBlock.UNKNOWN){
            for (BlockFace outputFace : PipeBlock.ALL_FACES) {
                if (PipeBlock.get(start.getRelative(outputFace)) == PipeBlock.UNKNOWN) continue;
                List<Block> localDestinations = findDestinationsFromExternal(maxDistance, start, outputFace);
                if (localDestinations == null) continue;
                destinations.addAll(localDestinations);
            }
        }
        for (Block destination : destinations) {
            debug(destination, NamedTextColor.GOLD.value(), String.valueOf(classesAmount), 20*2);
        }
        return destinations;
    }

    public static @Nullable List<Block> findDestinationsFromExternal(int maxDistance, Block start, BlockFace outputFace){
        Block relative = start.getRelative(outputFace);
        if (PipeBlock.getDestinationInput(relative) != outputFace.getOppositeFace()) return null;
        return findDestinationsFromPipe(maxDistance, relative, outputFace.getOppositeFace());
    }

    public static @Nullable List<Block> findDestinationsFromPipe(int maxDistance, Block start, BlockFace inputFace){
        return new PipeConnector().startFromPipe(maxDistance, start, inputFace);
    }

    private @Nullable List<Block> startFromPipe(int maxDistance, Block start, BlockFace inputFace){
        distanceLeft = maxDistance;
        currentBlock = start;
        this.inputFace = inputFace;
        loop();
        return destinations;
    }
    public static boolean isSameBlock(Block b0, Block b1){
        return (b0.getX() == b1.getX() &&
                b0.getY() == b1.getY() &&
                b0.getZ() == b1.getZ()
        );
    }
    public void moveAlong(BlockFace direction){
        currentBlock = currentBlock.getRelative(direction);
        inputFace = direction.getOppositeFace();
    }
    private @NotNull List<BlockFace> getOutputs(){
        return PipeBlock.getNext(inputFace, currentBlock);
    }

    private boolean step(){

        distanceLeft--;
        debug(NamedTextColor.RED.value(), 20*2);
        if (distanceLeft <= 0) return true;
        List<BlockFace> outputs = getOutputs();

        if (outputs.isEmpty()){
            debug(NamedTextColor.GREEN.value(), 20*2);
            BlockFace destinationOutput = PipeBlock.getDestinationOutput(currentBlock);
            if (destinationOutput == null) return true;
            moveAlong(destinationOutput);
            destinations = List.of(currentBlock);
            return true;

        } else if (outputs.size() == 1) {
            moveAlong(outputs.get(0));

        } else {
            destinations = new ArrayList<>();
            for (BlockFace output : outputs) {
                List<Block> localDestinations = PipeConnector.findDestinationsFromPipe(
                        distanceLeft+1,
                        currentBlock.getRelative(output),
                        output.getOppositeFace()
                );
                if (localDestinations == null) continue;
                destinations.addAll(localDestinations);
            }
            return true;
        }
        return false;
    }

    private void loop(){
        while (true){
            if (step()) break;
        }
    }

    // TODO: 7/22/2024 REMOVE??
    private void debug(int color, int duration){
        debug(currentBlock, color, String.valueOf(distanceLeft), duration);
    }
    public static void debug(Block block, int color, int duration){
        debug(block, color, "", duration);
    }
    public static void debug(Block block, int color, String text, int duration){
        if (!DEBUG) return;
        for (Player player : Bukkit.getOnlinePlayers()) {
            Nms.get().showDebugBlock(
                    player,
                    block.getLocation(),
                    color,
                    text,
                    duration
            );
        }
    }

}
