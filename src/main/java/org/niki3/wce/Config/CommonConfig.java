package org.niki3.wce.Config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLED =
            BUILDER
                    .comment("WindChargedEnemyの有効化")
                    .translation("wce.configuration.enabled")
                    .define("enabled", true);

    public static final ModConfigSpec.IntValue DEFAULT_CHANCE =
            BUILDER
                    .comment("発生確率のデフォルト値設定")
                    .translation("wce.configuration.default_chance")
                    .defineInRange("defaultChance", 50, 0,100);

    public static final ModConfigSpec.BooleanValue EASTER =
            BUILDER
                    .comment("イースターモードを有効化します")
                    .translation("wce.configuration.easter")
                    .define("disabled", false);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
