package com.atl.entitycontroller;

import com.atl.entitycontroller.config.RenderLimitConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class EntityControllerMod implements ModInitializer, ClientModInitializer {

    @Override
    public void onInitialize() {
        // Server-side init (optional)
        System.out.println("[EntityController] Mod initialized.");
    }

    @Override
    public void onInitializeClient() {
        RenderLimitConfig.load(); // Load config file on game start
        System.out.println("[EntityController] Loaded render limiter config.");
    }
}