package com.lluu.Demo2;

/**
 * @author jackliu  Email:
 * @description:
 * @Version
 * @create 2024-07-04 21:23
 */
public class IfElseCode {

    public static void main(String[] args) {
        new IfElseCode().handleRequest("type1");

    }

    /**
     *  模拟controller
     * @param requestType 前端传递的参数
     */
    public void handleRequest(String requestType) {
            if ("type1".equals(requestType)) {
                handleType1();
            } else if ("type2".equals(requestType)) {
                handleType2();
            } else if ("type3".equals(requestType)) {
                handleType3();
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
