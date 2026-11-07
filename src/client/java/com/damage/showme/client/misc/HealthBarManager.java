package com.damage.showme.client.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HealthBarManager {
    private static final List<HealthBarLine> healthBarLines = new ArrayList<>();
    private static final Comparator<HealthBarLine> sortMethod = Comparator.comparingDouble(HealthBarLine::priority);

    public static void addHealthBarLine(float height, getLineContent content, float priority) {
        healthBarLines.add(new HealthBarLine(height, content, priority));
        healthBarLines.sort(sortMethod);
    }

    public static List<HealthBarLine> getHealthBarLines() {
        return healthBarLines;
    }

    public interface getLineContent {
        Component get(LivingEntity entity);}

    public record HealthBarLine(float height, getLineContent content, float priority) {}
}
