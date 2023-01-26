package com.forum.utils;

public class RankTransformUtils {

    public static String transform(Integer level){
        if (level < 50)
            return "萌新";
        else if (level < 100)
            return "轻度";
        else if (level < 300)
            return "普通";
        else if (level < 600)
            return "重度";
        else if (level < 1000)
            return "大神";
        else
            return "骨灰";
    }

}
