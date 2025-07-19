package com.atl.entitycontroller.config;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RenderLimitConfig {
    public static int renderRadius = 64;
    public static int maxPerType = 100;

    private static final String CONFIG_PATH = "config/render_limit_config.json";

    public static void load() {
        try (FileReader reader = new FileReader(CONFIG_PATH)) {
            JsonObject json = new Gson().fromJson(reader, JsonObject.class);
            renderRadius = json.get("renderRadius").getAsInt();
            maxPerType = json.get("maxPerType").getAsInt();
        } catch (IOException e) {
            System.out.println("[EntityController] Failed to load config. Using defaults.");
        }
    }

    public static void save() {
        JsonObject json = new JsonObject();
        json.addProperty("renderRadius", renderRadius);
        json.addProperty("maxPerType", maxPerType);
        try (FileWriter writer = new FileWriter(CONFIG_PATH)) {
            new Gson().toJson(json, writer);
        } catch (IOException e) {
            System.out.println("[EntityController] Failed to save config.");
        }
    }
}