package selfLearning;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Family on 07-Oct-17.
 */
public class Testing {

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] nums = new int[]{45, 43, 5, 2,45,  7};

        twoSum.twoSum(nums, 9);
        System.out.println(Arrays.toString(twoSum.twoSum(nums, 9)));
    }
}
