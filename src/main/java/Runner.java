import hannaeismant.m101j.SparkConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        context.getBean(SparkConfiguration.class);
    }
}
