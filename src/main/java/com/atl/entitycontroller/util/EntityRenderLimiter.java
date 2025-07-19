package com.atl.entitycontroller.util;

import net.minecraft.util.Identifier;
import com.atl.entitycontroller.config.RenderLimitConfig;

import java.util.HashMap;
import java.util.Map;

public class EntityRenderLimiter {
    private static final Map<Class<?>, Integer> countMap = new HashMap<>();

    public static boolean canRender(Class<?> entityClass, Identifier entityId) {
        int count = countMap.getOrDefault(entityClass, 0);
        int max = RenderLimitConfig.maxPerType;
        if (count >= max) return false;

        countMap.put(entityClass, count + 1);
        return true;
    }

    public static void reset() {
        countMap.clear();
    }
}
