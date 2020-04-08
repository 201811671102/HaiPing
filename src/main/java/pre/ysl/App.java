package pre.ysl;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
@MapperScan("pre.ysl.mapper")//扫描mybatis的mapper
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
