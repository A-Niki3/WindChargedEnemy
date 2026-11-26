package org.niki3.wce.Config;

public record EntityConfig(boolean enabled, int chance) {
    public static final boolean DEFAULT_ENABLED = true;

    public static int getDefaultChance() {
        return CommonConfig.DEFAULT_CHANCE.getAsInt();
    }

    public static EntityConfig getDefault() {
        return new EntityConfig(
                DEFAULT_ENABLED,
                getDefaultChance()
        );
    }
}
