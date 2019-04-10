package com.app.alcohol.config;


/**
 * save current from request
 */
public class CurrentUser {


    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    public static void saveUserId(String username){
        threadLocal.set(username);
    }
    public static String getCurrentUser(){
        return threadLocal.get();
    }

}
