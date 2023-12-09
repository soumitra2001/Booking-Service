package com.supriya.Booking_Service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Supriya",
                        email = "supriyagro2001@gmail.com",
                        url = "https://www.linkedin.com/in/itsmesupriya/"
                ),
                description = "OpenApi documentation for Booking Service Application",
                title = "Booking Service",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://3.17.174.79:8080"
                )
        }
)
public class SwaggerConfig {
}
