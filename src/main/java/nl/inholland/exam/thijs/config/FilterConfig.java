package nl.inholland.exam.thijs.config;

import nl.inholland.exam.thijs.filter.ApiKeyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ApiKeyFilter> apiKeyFilter() {
        FilterRegistrationBean<ApiKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiKeyFilter());
        registrationBean.addUrlPatterns("/ships/*", "/crewmembers/*", "/missions/*");
        return registrationBean;
    }
}
