package com.damage.showme.client.data;

import com.damage.showme.ShowMeYourDamage;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public class ModDataAttachment {
    public static AttachmentType<Float> GRADIENT = AttachmentRegistry.create(ShowMeYourDamage.id("gradient"));
}
