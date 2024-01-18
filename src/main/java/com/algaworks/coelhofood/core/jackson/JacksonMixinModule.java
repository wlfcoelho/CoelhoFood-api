package com.algaworks.coelhofood.core.jackson;

import com.algaworks.coelhofood.api.model.mixin.RestauranteMixin;
import com.algaworks.coelhofood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule(){
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
