package com.milford.churchcms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
@EnableScheduling
public class ChurchWebCMSApplication {

        static ConfigurableApplicationContext context;
        public static void main(String[] args) {
                context = SpringApplication.run(ChurchWebCMSApplication.class, args);
        }
//        
//        @Bean
//        public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//            PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
//
//            properties.setLocation(new ClassPathResource( "application.properties" ));
//            properties.setIgnoreResourceNotFound(false);
//
//            return properties;
//        }
        
        
//       --------JMS -----------------
//        @Bean
//        public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
//                                                        DefaultJmsListenerContainerFactoryConfigurer configurer) {
//            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//            // This provides all boot's default to this factory, including the message converter
//            configurer.configure(factory, connectionFactory);
//            // You could still override some of Boot's default if necessary.
//            return factory;
//        }
//
//        @Bean // Serialize message content to json using TextMessage
//        public MappingJackson2MessageConverter jacksonJmsMessageConverter() {
//            MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//            converter.setTargetType(MessageType.TEXT);
//            converter.setTypeIdPropertyName("_type");
//            return converter;
//        }
//        
//        @Bean
//        public JmsTemplate myJmsMessage(){
//            return context.getBean(JmsTemplate.class);
//        }
}
