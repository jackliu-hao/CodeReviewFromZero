package com.lluu.Demo2;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jackliu  Email:
 * @description:
 * @Version
 * @create 2024-07-04 21:13
 */
public class ReflectCode {

    private final Map<String, Method> requestHandlers = new HashMap<>();

    public ReflectCode() {
        try {
            requestHandlers.put("type1", this.getClass().getDeclaredMethod("handleType1"));
            requestHandlers.put("type2", this.getClass().getDeclaredMethod("handleType2"));
            requestHandlers.put("type3", this.getClass().getDeclaredMethod("handleType3"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failed to initialize request handlers", e);
        }
    }

    public static void main(String[] args) {
        new ReflectCode().handleRequest("type1");
    }

    public void handleRequest(String requestType) {
        Method method = requestHandlers.get(requestType);
        if (method != null) {
            try {
                method.invoke(this);
            } catch (Exception e) {
                throw new RuntimeException("Failed to handle request", e);
            }
        } else {
            handleUnknown();
        }
    }

    private void handleType1() {
        System.out.println("Handling type 1 request");
    }

    private void handleType2() {
        System.out.println("Handling type 2 request");
    }

    private void handleType3() {
        System.out.println("Handling type 3 request");
    }

    private void handleUnknown() {
        System.out.println("Handling unknown request type");
    }






}
