package pixelart;

import net.minecraft.network.NetHandlerPlayServer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ColorMessage implements IMessage {
    private int col;
    private int x;
    private int y;
    private int z;
    private String s;

    public ColorMessage() { }

    public ColorMessage(int col, int x, int y, int z) {
        this.col = col;
        this.x = x;
        this.y = y;
        this.z = z;
        
        this.s = Integer.toString(col) + "," + Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        s = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, s);
    }

    public static class Handler implements IMessageHandler<ColorMessage, IMessage> {
        
        @Override
        public IMessage onMessage(ColorMessage message, MessageContext ctx) {
            String[] ss = message.s.split(",");
            
            int color = Integer.valueOf(ss[0]);
            int cx = Integer.valueOf(ss[1]);
            int cy = Integer.valueOf(ss[2]);
            int cz = Integer.valueOf(ss[3]);
            
            NetHandlerPlayServer nhps = ctx.getServerHandler();
            PixelTile pTile = (PixelTile)nhps.playerEntity.worldObj.getTileEntity(cx, cy, cz);
            
            int red = color & 0xFF;
    	    int green = (color >> 8) & 0xFF;
    	    int blue = (color >> 16) & 0xFF;
    	    
            pTile.r = red;
            pTile.g = green;
            pTile.b = blue;
            
            return null;
        }
    }
}