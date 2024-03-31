package com.internship.portal.user.config;

import lombok.Getter;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Getter
public class UserProperties {
}
