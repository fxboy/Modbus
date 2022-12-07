package icu.weboys.modbus.poll.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ModbusTcpConfig {
    private final NioEventLoopGroup group;
    private final ThreadPoolExecutor executor;
    private final Bootstrap bootstrap;
    private final String address;
    private final int port;

    public ModbusTcpConfig(NioEventLoopGroup group, ThreadPoolExecutor executor, Bootstrap bootstrap, String address, int port) {
        this.group = group;
        this.executor = executor;
        this.bootstrap = bootstrap;
        this.address = address;
        this.port = port;
    }

    public NioEventLoopGroup getGroup() {
        return group;
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }


    public static class Builder{
        private final String address;
        private int port = 502;
        public Builder(String address,int port) {
            this.address = address;
            this.port = port;
        }

        public Builder(String address) {
            this.address = address;
        }
        public ModbusTcpConfig build(){
            return new ModbusTcpConfig(
                    new NioEventLoopGroup(),
                    new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy()),
                    new Bootstrap(),
                    this.address,
                    this.port
            );
        }
    }
}
