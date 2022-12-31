package com.twitterintegration.twitterintegration;

import java.text.SimpleDateFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class TwitterIntegrationApplication implements CommandLineRunner {

  private final TwitterTemplate twitterTemplate;

  public static void main(String[] args) {
    SpringApplication.run(TwitterIntegrationApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    long sinceId = 1;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-M-d'T'H:m:s.SSS");
    while (true){
      SearchResults balaha = twitterTemplate.searchOperations().search("java",100, sinceId, 0);
      List<Tweet> tweets = balaha.getTweets();
      for (Tweet tweet : tweets) {
        if (!tweet.isRetweet()) {
          log.info("tweet: {} user: {} tweet_id: {} posted_at: {}",tweet.getText(), tweet.getFromUser(),
              tweet.getId(),
              simpleDateFormat.format(tweet.getCreatedAt()));
        }
        sinceId = tweet.getId();
      }
      log.info("Last tweet id:{}",sinceId);
      Thread.sleep(60000);
    }
  }
}
