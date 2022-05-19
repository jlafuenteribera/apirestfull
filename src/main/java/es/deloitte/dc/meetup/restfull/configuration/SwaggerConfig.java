package es.deloitte.dc.meetup.restfull.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

  @Value("${swagger.web.local}")
  private String local;

  @Value("${app.version}")
  private String appVersion;

  @Value("${app.name}")
  private String appName;

  @Bean
  public OpenAPI openApiBuilder() {
    List<Server> servers = new ArrayList<>();
    servers.add(new Server().url(local).description("Servicio Local"));
    return new OpenAPI()
        .components(new Components())
        .info(
            new Info()
                .title(appName)
                .description("Formación API Rest Meetup Deloitte DC")
                .version(appVersion))
        .servers(servers);
  }


}
