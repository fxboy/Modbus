package icu.weboys.modbus.poll.tcp;

import icu.weboys.modbus.core.requests.ModbusRequest;
import icu.weboys.modbus.core.responses.ModbusResponse;
import icu.weboys.modbus.core.typed.ModbusMark;
import icu.weboys.modbus.poll.tcp.codec.MessageCodec;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ModBusTcpPoll implements Runnable{
    private int flag = ModbusMark.MODBUS_FLAG;
    private CompletableFuture<Channel> future = new CompletableFuture();

    private Consumer<ModbusResponse> resp;

    private Consumer<ChannelFuture> close;

    private ModbusTcpConfig config;

    public ModBusTcpPoll(ModbusTcpConfig config){
        this(config,(response)->{}, (channel)->{});
    }
    public ModBusTcpPoll(ModbusTcpConfig config, Consumer<ModbusResponse> consumer){
        this(config,consumer, (channel)->{});
    }
    public ModBusTcpPoll(ModbusTcpConfig config, Consumer<ModbusResponse> consumer, Consumer<ChannelFuture> close){
        this.close = close;
        this.resp = consumer;
        this.config = config;
        this.init();
    }

    private void init(){
        this.config.getBootstrap().group(config.getGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch){
                        ch.pipeline().addLast(new MessageCodec());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                if(msg instanceof ModbusResponse){
                                    resp.accept((ModbusResponse<byte[]>) msg);
                                }
                            }
                        });
                    }
                });
    }
    @Override
    public void run() {
        config.getBootstrap().connect(config.getAddress(), config.getPort()).addListener((ChannelFuture channelFuture)-> {
            if(channelFuture.isSuccess()){
                channelFuture.channel().closeFuture().addListener((ChannelFuture channel)->{
                    disConnect();
                    close.accept(channel);
                });
                future.complete(channelFuture.channel());
            }else{
                future.completeExceptionally(channelFuture.cause());
            }
        });
    }

    public void connect(){
        config.getExecutor().execute(this);
    }

    public void disConnect() throws ExecutionException, InterruptedException {
        future.get().close();
        config.getGroup().shutdownGracefully();
        config.getExecutor().awaitTermination(1, TimeUnit.SECONDS);
        System.gc();
    }

    public Boolean isOpen() throws ExecutionException, InterruptedException {
        return future.get().isOpen();
    }

    public void send(ModbusRequest req) throws ExecutionException, InterruptedException {
        if(!isOpen()){
            return;
        }
        flag = req.setFlag(ModbusMark.flag(flag));
        future.get().writeAndFlush(req);
    }
}
