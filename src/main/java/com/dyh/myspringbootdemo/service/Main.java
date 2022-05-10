package com.dyh.myspringbootdemo.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * java SPI and proxy pattern
 */

public class Main {


    public interface ServiceProvider {
        void sayHello();
    }

    static class LoadBalanceServiceProvider implements ServiceProvider {
        final List<ServiceProvider> serviceProviders;
        int index = 0;

        LoadBalanceServiceProvider(List<ServiceProvider> serviceProviders) {
            this.serviceProviders = serviceProviders;
        }

        @Override
        public void sayHello() {
            serviceProviders.get(index++ % serviceProviders.size()).sayHello();
        }
    }

    public static void main(String[] args) {

        ServiceProvider serviceProvider = (ServiceProvider) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{ServiceProvider.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        serviceProvider.sayHello();
    }
}
