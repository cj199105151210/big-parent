package com.bosssoft.bigdata.manager.framework.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import lombok.experimental.UtilityClass;

/**
 * @author LCN on 2019/7/6.
 */
@UtilityClass
public class SocketUtils {

	public String getJson(Object msg) {
		String json;
		try {
			ByteBuf buf = (ByteBuf) msg;
			byte[] bytes = new byte[buf.readableBytes()];
			buf.readBytes(bytes);
			json = new String(bytes);
		} finally {
			ReferenceCountUtil.release(msg);
		}
		return json;

	}

	public void sendMsg(ChannelHandlerContext ctx, String msg) {
		ctx.writeAndFlush(Unpooled.buffer().writeBytes(msg.getBytes()));
	}


	public void sendMsg(Channel ctx, String msg) {
		ctx.writeAndFlush(Unpooled.buffer().writeBytes(msg.getBytes()));
	}
}
