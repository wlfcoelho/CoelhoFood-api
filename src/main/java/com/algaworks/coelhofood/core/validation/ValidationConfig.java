package com.algaworks.coelhofood.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {


    //este método especifica o arquivo que vamos utilizar na validação - ex: ValidationMessages.properties
    //neste caso não estamos usando o arquivo citado apenas estamos usando Messages
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource meessageSource){

        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(meessageSource);
        return bean;
    }

}
