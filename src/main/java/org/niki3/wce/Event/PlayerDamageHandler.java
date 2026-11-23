package org.niki3.wce.Event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import org.niki3.wce.Config.CommonConfig;
import org.niki3.wce.Effect.WindBurstEffect;

public class PlayerDamageHandler {
    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent.Post event){
        // Modの有効化確認(config/wce/common.tomlより)
        if(!CommonConfig.ENABLED.get())return;
        // 被攻撃対象がPlayer以外は無視する
        if(!(event.getEntity() instanceof Player player)) return;
        // サーバー側のみの処理
        if(!(player.level() instanceof ServerLevel serverLevel)) return;

        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();

        if(attacker == null || attacker instanceof Player) return;

        if(player.getRandom().nextFloat() >= 0.5F) return;

        WindBurstEffect.spawn(player, serverLevel);
    }
}
