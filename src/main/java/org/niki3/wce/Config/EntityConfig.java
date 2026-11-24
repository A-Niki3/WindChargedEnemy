package org.niki3.wce.Config;

public class EntityConfig {
    public static final boolean DEFAULT_ENABLED = true;
    public static final int DEFAULT_CHANCE = 50;
    private final boolean enabled;
    private final int chance;

    public EntityConfig(boolean enabled, int chance){
        this.enabled = enabled;
        this.chance = chance;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public int getChance(){
        return  chance;
    }

    public static EntityConfig getDefault(){
        return new EntityConfig(
                DEFAULT_ENABLED,
                DEFAULT_CHANCE
        );
    }
}
