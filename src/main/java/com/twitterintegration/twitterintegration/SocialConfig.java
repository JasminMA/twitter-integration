package com.twitterintegration.twitterintegration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
public class SocialConfig {


  @Value("${twitter.consumerKey}")
  private String consumerKey;
  @Value("${twitter.consumerSecret}")
  private String consumerSecret;
  @Value("${twitter.access_token}")
  private String accessToken;
  @Value("${twitter.access_token_secret}")
  private String accessTokenSecret;

//  @Bean
//  public ConnectionFactoryLocator connectionFactoryLocator() {
//    ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//    registry.addConnectionFactory(new TwitterConnectionFactory(consumerKey, consumerSecret));
//    return registry;
//  }

  @Bean
  public TwitterTemplate createTwitterTemplate() {
    return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
  }
}