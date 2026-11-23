package org.niki3.wce.Config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLED =
            BUILDER
                    .comment("WindChargedEnemyの有効化")
                    .translation("wce.configuration.enabled")
                    .define("enabled", true);

    public static final ModConfigSpec.BooleanValue EASTER =
            BUILDER
                    .comment("イースターモードを有効化します")
                    .translation("wce.configuration.easter")
                    .define("disabled", false);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
