package com.hellowd.push;

import com.hellowd.monitor.ServerMonitor;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.security.cert.CertificateException;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2015-12-23
 * Time : 오후 4:45
 * To change this template use File | Settings | File and Code Templates.
 */

@Component
public class PushServer {

    static Logger logger = LoggerFactory.getLogger(PushServer.class);

    @Autowired
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress address;

    @Autowired
    @Qualifier("bossThreadCount")
    private int bossThreadCount;

    @Autowired
    @Qualifier("workerThreadCount")
    private int workerThreadCount;

    @Autowired
    @Qualifier("httpPort")
    private int httpPort;


    @Autowired
    @Qualifier("tcpPort")
    private int tcpPort;

    @Autowired
    @Qualifier("zookeeperHostPort")
    private String zookeeperHostPort;

    @Autowired
    @Qualifier("zookeeperSessionTimeout")
    private int zookeeperSessionTimeout;


    private ServerMonitor serverMonitor;

    public void start() {
        //이때 ServerMonitor가 시작을 하게 된답니다.
        serverMonitor = new ServerMonitor();
        logger.info(serverMonitor.toString());
        EventLoopGroup bossGroup = new NioEventLoopGroup(bossThreadCount);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreadCount);

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new PushServerInitializer());
            b.bind(httpPort).sync().channel().closeFuture().sync();

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public ServerMonitor getServerMonitor() {
        return serverMonitor;
    }
}
