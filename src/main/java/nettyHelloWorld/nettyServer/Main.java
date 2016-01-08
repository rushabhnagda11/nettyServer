package nettyHelloWorld.nettyServer;


import com.google.common.net.InternetDomainName;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Main {
    public static void main(String args[]) throws InterruptedException {
//        InternetDomainName owner = InternetDomainName.from("http://www.google.com").topPrivateDomain().name();
//        System.out.println(owner.name());
        String baseUrl = "fairfax-international.com.au/";
        System.out.println(baseUrl.indexOf('/'));
        //baseUrl = baseUrl.substring(0,baseUrl.charAt('/'));
        Main.run();
    }
    
    public static void run() throws InterruptedException {
        ServerBootstrap b = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            

            
            b.group(bossGroup, workerGroup) 
            .channel(NioServerSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.ERROR))
            .childHandler(new BidRequestInitializer());
            
            Channel channel = b.bind(8080).sync().channel();
            
            channel.closeFuture().sync();
            
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            
        }
    }
}
