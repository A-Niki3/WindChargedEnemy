package org.niki3.wce.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.neoforged.fml.loading.FMLPaths;
import org.niki3.wce.WindChargedEnemy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EntityConfigManager {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get()
            .resolve(WindChargedEnemy.MODID)
            .resolve("entities");

    public static EntityConfig getConfig(Entity entity){
        // wce以上のディレクトリが存在しない場合デフォルトを返す
        if (!Files.isDirectory(CONFIG_PATH.getParent())){
            return EntityConfig.getDefault();
        }
        // ResourceLocation -> Identifier
        Identifier entityId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());

        // JSONファイルパスの生成
        Path configPath = CONFIG_PATH
                .resolve(entityId.getNamespace())
                .resolve(entityId.getPath() + ".json");

        // JSON存在しない場合は初期値で生成
        if(!Files.exists(configPath)){
            return createDefaultConfig(configPath);
        }

        // JSON読み込み
        try(var reader = Files.newBufferedReader(configPath)){
            JsonObject json = GSON.fromJson(reader, JsonObject.class);

            if (json == null){
                return EntityConfig.getDefault();
            }

            boolean enabled = json.has("enabled")
                    ? json.get("enabled").getAsBoolean()
                    : EntityConfig.DEFAULT_ENABLED;

            int chance = json.has("chance")
                    ? json.get("chance").getAsInt()
                    : EntityConfig.DEFAULT_CHANCE;
            // 確率を0~100に制限
            chance = Math.max(0, Math.min(100, chance));

            return new EntityConfig(enabled, chance);
        } catch (Exception e){
            WindChargedEnemy.LOGGER.error(
                    "Failed to load entity config: {}",
                    configPath,
                    e
            );

            return EntityConfig.getDefault();
        }
    }

    public static EntityConfig createDefaultConfig(Path configPath){
        EntityConfig config = EntityConfig.getDefault();

        try{
            Files.createDirectories(configPath.getParent());

            JsonObject json = new JsonObject();
            json.addProperty("enabled", config.isEnabled());
            json.addProperty("chance", config.getChance());
            try(var writer = Files.newBufferedWriter(configPath)){
                GSON.toJson(json, writer);
            }
        } catch (IOException e){
            WindChargedEnemy.LOGGER.error(
                    "Failed to create entity config: {}",
                    configPath,
                    e
            );
        }

        return config;
    }
}
