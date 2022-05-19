package es.deloitte.dc.meetup.restfull.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
  public OpenAPI customOpenAPI() {
    List<Server> servers = new ArrayList<>();
    servers.add(new Server().url(local).description("Servicio Web Local"));
    return new OpenAPI()
        .info(
            new Info()
                .title(appName)
                .description("Meetup API Rest")
                .version(appVersion))
        .servers(servers);
  }
}
