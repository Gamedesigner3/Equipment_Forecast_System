package gsq.booker.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("gsq.booker.sys.dao")
@SpringBootApplication
public class ForeSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForeSysApplication.class, args);
    }
}