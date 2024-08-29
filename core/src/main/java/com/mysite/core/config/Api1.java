package com.mysite.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "fetch API1")
public @interface Api1 {
    @AttributeDefinition(name = "API key", description = "api key string")
    String apiKey() default ",in&appid=1f87e5de93f1954a9246f0b344a4558a&units=imperial";

    @AttributeDefinition(name = "API url",description = "api url string")
    String apiUrl() default "https://api.openweathermap.org/data/2.5/weather?zip=";
}
