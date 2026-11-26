package org.niki3.wce.Effect;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.level.Level;

import java.util.Random;

public class WindBurstEffect {

    private static final double OFFSET_RANGE = 0.25D;

    public static void spawn(Player player, ServerLevel level){
        Random random = new Random();

        double x = player.getX() + (random.nextDouble() * 2 -1) * OFFSET_RANGE;
        double y = player.getY() + 0.5 + (random.nextDouble() * 2 -1) * OFFSET_RANGE;
        double z = player.getZ() + (random.nextDouble() * 2 -1) * OFFSET_RANGE;

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
