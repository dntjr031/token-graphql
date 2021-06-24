package com.lama.graphqlserver.util;

import com.lama.graphqlserver.security.ApplicationContextProvider;

public class BeanUtils {
  public static Object getBean(String beanName) {
    return ApplicationContextProvider.getApplicationContext().getBean(beanName);
  }

  public static <C> C getBean(Class<? extends  C> clazz) {
    return ApplicationContextProvider.getApplicationContext().getBean(clazz);
  }
}
