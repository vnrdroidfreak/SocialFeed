package com.virudhairaj.feedsdk.states;

/**
 * Generic Like types
 * for customization, create enum with your own like types and your own logic
 */

public enum LikeType {
    LIKE, UNLIKE;

    public static LikeType parseByName(String name) {
        LikeType obj = LikeType.LIKE;
        try{
            obj= LikeType.valueOf(name);
        }catch (Exception e){
        }
        return obj;
    }
}
