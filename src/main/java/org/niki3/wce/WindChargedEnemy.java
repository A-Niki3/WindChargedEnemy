package org.niki3.wce;

import com.mojang.authlib.GameProfile;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Mod(WindChargedEnemy.MODID)
public class WindChargedEnemy {
    public static final String MODID = "wce";
    public static final String MOD_NAME = "WindChargedEnemy";
    public static final String LOG_TAG = "[" + MOD_NAME + "]";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final GameProfile gameProfile = new GameProfile(UUID.nameUUIDFromBytes("WindChargedEnemy".getBytes(StandardCharsets.UTF_8)), LOG_TAG);

    public WindChargedEnemy(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("WCE SETUP COMPLETE!");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("WCE in Server Complete!");
    }
}
