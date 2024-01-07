package com.learn.ratelimiter.areaCalculator.listener;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.listener.ListAddListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RqueueListener implements ListAddListener {
    @Override
    public void onListAdd(String name) {
      log.info("entry added to queue name: " + name);
    }
}
