package com.learn;

import com.learn.bind.SynSink;
import com.learn.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication(scanBasePackages = "com.learn.*")
@EnableBinding({Source.class, SynSink.class})
@EnableScheduling
public class SpringKafkaApplication {


    @Autowired
    private PollableMessageSource messageSource;

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaApplication.class, args);
    }

/*
    @StreamListener("input")
    public void consumeMessage(Employee employee) {
        System.out.println(employee);
    }
*/

  /*@Bean
  public ApplicationRunner getRunner() {
      return this::run;
  }

    private void run(ApplicationArguments args) {
        try {
            while (true) {
                if (!messageSource.poll(m -> System.out.println(m.getPayload()),
                        new ParameterizedTypeReference<Employee>() {
                        })) {
                    Thread.sleep(5000);
                }
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }*/

  @Scheduled(fixedRate = 5000)
  public void getMessage() {
      messageSource.poll(m -> System.out.print(m.getPayload()), new ParameterizedTypeReference<Employee>() {
      });
  }
}
