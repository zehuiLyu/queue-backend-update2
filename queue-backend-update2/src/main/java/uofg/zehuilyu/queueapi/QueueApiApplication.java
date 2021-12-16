package uofg.zehuilyu.queueapi;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan({"uofg.zehuilyu.queueapi.mapper"})
public class QueueApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueApiApplication.class, args);
    }

}
