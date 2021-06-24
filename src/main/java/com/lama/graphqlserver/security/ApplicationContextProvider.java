package com.lama.graphqlserver.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(@NotNull ApplicationContext applicationContextWired) throws BeansException {
    applicationContext = applicationContextWired;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
}
