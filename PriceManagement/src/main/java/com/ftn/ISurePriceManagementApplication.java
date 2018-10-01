package com.ftn;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ISurePriceManagementApplication {

    private static Logger log = LoggerFactory.getLogger(ISurePriceManagementApplication.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ISurePriceManagementApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        StringBuilder sb = new StringBuilder("Application beans:\n");
        for (
                String beanName : beanNames)

        {
            sb.append(beanName + "\n");
        }
        log.info(sb.toString());
    }

    /**
     * By defining the {@link KieContainer} as a bean here, we ensure that
     * Drools will hunt out the kmodule.xml and rules on application startup.
     * Those can be found in <code>src/main/resources</code>.
     *
     * @return The {@link KieContainer}.
     */
    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("com.ftn","drools-spring-kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(1_000);
        return kContainer;
    }
}