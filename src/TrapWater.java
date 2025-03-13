public class TrapWater {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Water Quantity is: " + trapWater(height));
    }

    public static int trapWater(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0; // Start pointer
        int right = height.length - 1; // End pointer
        int leftMax = 0; // Maximum height on the left
        int rightMax = 0; // Maximum height on the right
        int water = 0; // Total water trapped

        while (left < right) {
            // Determine which side to process
            if (height[left] < height[right]) {
                // Process left side
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                // Process right side
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }
}
