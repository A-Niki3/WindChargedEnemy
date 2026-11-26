package org.niki3.wce.Effect;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.level.Level;

import java.util.Random;

public class WindBurstEffect {

    private static final double Y_OFFSET_RANGE = 0.25D;
    private static final double XZ_OFFSET_RANGE = 1.0D;

    public static void spawn(Player player, ServerLevel level){
        Random random = new Random();

        double x = player.getX() + (random.nextDouble() * 2 -1) * XZ_OFFSET_RANGE;
        double y = player.getY() + 0.5 + (random.nextDouble() * 2 -1) * Y_OFFSET_RANGE;
        double z = player.getZ() + (random.nextDouble() * 2 -1) * XZ_OFFSET_RANGE;

        InstantWindCharge charge = new InstantWindCharge(player, level, x, y, z);
        charge.detonate();
    }

    private static class InstantWindCharge extends WindCharge{

        public InstantWindCharge(Player player, Level level, double x, double y, double z) {
            super(player, level, x, y, z);
        }

        public void detonate(){
            super.explode(this.position());
        }
    }
}
