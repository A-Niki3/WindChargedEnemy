package org.niki3.wce;

import org.niki3.wce.Config.CommonConfig;
import org.niki3.wce.Event.PlayerDamageHandler;
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


@Mod(WindChargedEnemy.MODID)
public class WindChargedEnemy {
    public static final String MODID = "wce";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WindChargedEnemy(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new PlayerDamageHandler());
        modContainer.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, MODID + "/common.toml");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("WCE Complete");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("WCE Complete!(Server)");
    }

}
