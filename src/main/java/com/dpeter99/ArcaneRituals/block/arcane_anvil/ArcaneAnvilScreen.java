package com.dpeter99.ArcaneRituals.block.arcane_anvil;

import com.dpeter99.ArcaneRituals.ArcaneRituals;
import com.dpeter99.ArcaneRituals.util.ui.ModContainerScreen;
import com.dpeter99.ArcaneRituals.util.ui.SourceTexture;
import com.dpeter99.ArcaneRituals.util.ui.TextureRegion;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class ArcaneAnvilScreen extends ModContainerScreen<ArcaneAnvilContainer> {

    public ArcaneAnvilScreen(ArcaneAnvilContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.ySize = 226;

        GUI = new SourceTexture(ArcaneRituals.location("textures/gui/demonic_anvil.png"),256,256);

        drawStack.add(new TextureRegion(GUI,0,0,176,226,0,0));
    }
}