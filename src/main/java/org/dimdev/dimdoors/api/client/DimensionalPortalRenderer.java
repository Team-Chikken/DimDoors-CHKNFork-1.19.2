package org.dimdev.dimdoors.api.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.dimdev.dimdoors.Util;
import org.dimdev.dimdoors.client.ModShaders;

import java.util.Collections;

@Environment(EnvType.CLIENT)
public final class DimensionalPortalRenderer {
	public static final Identifier WARP_PATH;
	private static final RenderLayer RENDER_LAYER;
	private static final ModelPart MODEL;
	private static final ModelPart TALL_MODEL;

	public static void renderDimensionalPortal(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, Transformer transformer, float tickDelta, int light, int overlay) {
		renderDimensionalPortal(matrixStack, vertexConsumerProvider, transformer, tickDelta, light, overlay, true);
	}

	public static void renderDimensionalPortal(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, Transformer transformer, float tickDelta, int light, int overlay, boolean tall) {
		ModelPart model = tall ? TALL_MODEL : MODEL;
		renderModelWithPortalShader(model, matrixStack, vertexConsumerProvider, transformer, tickDelta, light, overlay);
	}

	public static void renderModelWithPortalShader(ModelPart model, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, Transformer transformer, float tickDelta, int light, int overlay) {
		transformer.transform(matrixStack);
		model.render(matrixStack, vertexConsumerProvider.getBuffer(RENDER_LAYER), light, overlay);
	}

	static {
        WARP_PATH = Util.id("textures/other/warp.png");
        RENDER_LAYER = RenderLayer.getEntitySolid(WARP_PATH);
        ModelPart.Cuboid small = new ModelPart.Cuboid(0, 0, 0.2f, 0.2f, -0.1f, 15.8f, 15.8f, 0.01F, 0, 0, 0, false, 1024, 1024);
        MODEL = new ModelPart(Collections.singletonList(small), Collections.emptyMap());
        ModelPart.Cuboid big = new ModelPart.Cuboid(0, 0, 0.2f, 0.2f, -0.1f, 15.8f, 31.8f, 0.01F, 0, 0, 0, false, 1024, 1024);
        TALL_MODEL = new ModelPart(Collections.singletonList(big), Collections.emptyMap());
    }
}
