package space.libs.mixins.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Packet;
import net.minecraft.util.MessageSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MessageSerializer.class)
public abstract class MixinMessageSerializer {

    @Shadow(prefix = "original$")
    protected void original$encode(ChannelHandlerContext context, Packet<?> packet, ByteBuf byteBuf) {}

    public void encode(ChannelHandlerContext context, Object packet, ByteBuf byteBuf) {
        this.original$encode(context, (Packet<?>) packet, byteBuf);
    }
}
