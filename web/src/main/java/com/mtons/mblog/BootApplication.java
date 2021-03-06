package com.mtons.mblog;

import com.mtons.mblog.config.NettyConfig;
import com.mtons.mblog.handel.ServerBootStrap;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.net.InetSocketAddress;

/**
 * SprintBootApplication
 */
@Slf4j
@EnableCaching
@ComponentScan("com.mtons.mblog")
@MapperScan("com.mtons.mblog.mapper")
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BootApplication extends SpringBootServletInitializer implements CommandLineRunner {
    // https://jx.618g.com/?url=https://www.iqiyi.com/v_19rrf8lxhw.html#  -- 爱奇艺
    @Autowired
    private ServerBootStrap ws;
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BootApplication.class, args);
        String serverPort = context.getEnvironment().getProperty("server.port");

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootApplication.class);
    }
    // 注意这里的 run 方法是重载自 CommandLineRunner
    @Override
    public void run(String... args) throws Exception {
        log.info("Netty's ws server is listen: " + NettyConfig.WS_PORT);
        InetSocketAddress address = new InetSocketAddress(NettyConfig.WS_HOST, NettyConfig.WS_PORT);
        ChannelFuture future = ws.start(address);

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                ws.destroy();
            }
        });

        future.channel().closeFuture().syncUninterruptibly();
    }
}