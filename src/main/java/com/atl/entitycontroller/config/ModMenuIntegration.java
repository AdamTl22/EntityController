package com.atl.entitycontroller.config;

import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    static {
        System.out.println("✅ ModMenuIntegration active for mod ID 'entitycontroller'");
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.of("Entity Controller Settings"))
                    .setSavingRunnable(RenderLimitConfig::save);

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            builder.getOrCreateCategory(Text.of("Performance Limits"))
                    .addEntry(entryBuilder.startIntField(Text.of("Render Radius"), RenderLimitConfig.renderRadius)
                            .setDefaultValue(64)
                            .setMin(16)
                            .setMax(128)
                            .setSaveConsumer(val -> RenderLimitConfig.renderRadius = val)
                            .build())
                    .addEntry(entryBuilder.startIntField(Text.of("Max Entities Per Type"), RenderLimitConfig.maxPerType)
                            .setDefaultValue(100)
                            .setMin(1)
                            .setMax(500)
                            .setSaveConsumer(val -> RenderLimitConfig.maxPerType = val)
                            .build());

            return builder.build();
        };
    }
}