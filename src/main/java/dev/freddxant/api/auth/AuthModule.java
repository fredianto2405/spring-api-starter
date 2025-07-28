package dev.freddxant.api.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "dev.freddxant.api.auth",
        "dev.freddxant.api.user",
        "dev.freddxant.api.common"
})
public class AuthModule {
}
