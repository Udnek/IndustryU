package me.udnek.industryu.transfer;

import me.udnek.industryu.IndustryU;
import me.udnek.itemscoreu.nms.NMS;
import me.udnek.itemscoreu.utils.LogUtils;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class PipeConnector {

    protected Location currentLocation;
    protected BlockFace input;
    protected int distanceLeft;

    public PipeConnector(){}

    public void start(int maxDistance, BlockFace inputToStartLocation, Location startLocation){
        distanceLeft = maxDistance;
        currentLocation = startLocation;
        input = inputToStartLocation;
        loop();
    }

    public List<BlockFace> getOutputs(){
        return TransferBlock.getNext(input, currentLocation.getBlock());
    }

    protected boolean step(){

        distanceLeft--;

        for (Player player : Bukkit.getOnlinePlayers()) {
            NMS.get().showDebugBlock(
                    player,
                    currentLocation,
                    NamedTextColor.RED.value(),
                    String.valueOf(distanceLeft),
                    20*2
            );
        }

        if (distanceLeft <= 0) return true;

        List<BlockFace> outputs = getOutputs();

        if (outputs.isEmpty()){
            for (Player player : Bukkit.getOnlinePlayers()) {
                NMS.get().showDebugBlock(
                        player,
                        currentLocation,
                        NamedTextColor.GREEN.value(),
                        String.valueOf(distanceLeft),
                        20*5
                );
            }
            return true;

        } else if (outputs.size() == 1) {
            currentLocation = TransferBlock.getAlong(currentLocation, outputs.get(0));
            input = outputs.get(0).getOppositeFace();
        }



        else {
            for (BlockFace output : outputs) {
                PipeConnector pipeConnector = new PipeConnector();
                pipeConnector.start(distanceLeft+1, output.getOppositeFace(), TransferBlock.getAlong(currentLocation, output));
            }
            return true;
        }
        return false;
    }

    protected void loop(){

        new BukkitRunnable() {
            @Override
            public void run() {
                if (step()) cancel();
            }
        }.runTaskTimer(IndustryU.getInstance(), 5, 5);
    }

}
