package org.niki3.wce.Event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import org.niki3.wce.Config.CommonConfig;
import org.niki3.wce.Config.EntityConfig;
import org.niki3.wce.Config.EntityConfigManager;
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

        Entity attacker = event.getSource().getEntity();

        if(attacker == null || attacker instanceof Player) return;

        EntityConfig config = EntityConfigManager.getConfig(attacker);
        // 個別設定の有効判定
        if(!config.isEnabled()) return;
        // 個別設定/デフォルト設定の確率で判定
        if(player.getRandom().nextInt(100) >= config.getChance()) return;

        WindBurstEffect.spawn(player, serverLevel);
    }
}
