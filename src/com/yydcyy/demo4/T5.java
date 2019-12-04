package com.yydcyy.demo4;

import java.util.List;

/**
 * @author YYDCYY
 * @create 2019-11-28
 */
public class T5 {
    private List<Integer> nums;

    //precondition: nums.size() > 0
//nums contains Integer objects
    public void numQuest() {
        int k = 0;
        Integer zero = new Integer(0);
        while (k < nums.size()) {
            if (nums.get(k).equals(zero))
                nums.remove(k);
            k++;
        }
    }
}
