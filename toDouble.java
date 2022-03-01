import java.util.List;

public class toDouble {

    public static double[] convert(int length, List<String> str){
        double[] nums = new double[length];
        
        for (int i = 0; i < length; ++i) {
            nums[i] = Double.parseDouble(str.get(i));
        }

        return nums;
    }
}
