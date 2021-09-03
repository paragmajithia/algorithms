package com.leetcode.segmenttrees;

/**
 * 307. Range Sum Query - Mutable
 * Medium
 *
 * 2279
 *
 * 125
 *
 * Add to List
 *
 * Share
 * Given an integer array nums, handle multiple queries of the following types:
 *
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 *
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * At most 3 * 104 calls will be made to update and sumRange.
 */

public class RangeSumQuery {

    public static void main(String[] args) {
        RangeSumQuery main = new RangeSumQuery(new int[] {1,3,5});
        System.out.println("Segment Tree: " + main.tree);
        System.out.println("Sum from index 0 to 2: " + main.sumRange(0,2));
        main.update(1,2);
        System.out.println("Updated Segment Tree: " + main.tree);
        System.out.println("Sum from index 0 to 2 after update: " + main.sumRange(0,2));
        main.update(1,10);
        System.out.println("Updated Segment Tree: " + main.tree);
        System.out.println("Sum from index 1 to 2 after update:: " + main.sumRange(1,2));

        RangeSumQuery main2 = new RangeSumQuery(new int[] {2,4,6,8});
        System.out.println("Segment Tree 2: " + main2.tree);
        System.out.println("Sum from index 0 to 3: " + main2.sumRange(0,3));
        System.out.println("Sum from index 1 to 2: " + main2.sumRange(1,2));
        System.out.println("Sum from index 2 to 3: " + main2.sumRange(2,3));

    }
    // Instance of segment tree
    SegmentTree tree;

    // Input:
    int[] input;

    // Segment tree data structure
    public class SegmentTree {
        public Node root;

        public SegmentTree(int[] nums) {
            root = buildTree(nums, 0, nums.length -1);
        }

        // Post order traversal
        // caculate sum of range post calculation of left and right segment
        public Node buildTree(int[] nums, int start, int end) {
            // System.out.println("Segment Build: Start:" + start + ", End: " + end);
            if (start > end || start < 0 || end >= nums.length) {
                return null;
            }

            Node node = new Node(start, end);
            if (start == end) {
                node.sum = nums[start];
                return node;
            }

            node.left = buildTree(nums, start, node.mid);
            node.right = buildTree(nums, node.mid + 1, end);
            node.sum = (node.left == null?0:node.left.sum)
                    + (node.right == null?0:node.right.sum);

            return node;
        }

        public int sumRecursive(Node node, int left, int right) {

            if (node == null || left > right) {
                return 0;
            }
            if (node.start == left && node.end == right) {
                return node.sum;
            }

            int sum = 0;
            // if this nonde has some portion to left
            if (left <= node.mid) {
                sum = sum + sumRecursive(node.left, left, Math.min(node.mid, right));
            }

            // if this node has some portion to right
            if (right >= (node.mid + 1)) {
                sum = sum + sumRecursive(node.right, Math.max(node.mid+1, left), right);
            }
            return sum;
        }

        public void update(Node node, int index, int diff) {

            if (node == null || index < node.start || index > node.end) {
                return;
            }
            node.sum = node.sum + diff;
            if (index <= node.mid) {
                update(node.left, index, diff);
            } else {
                update(node.right, index, diff);
            }

        }

        @Override
        public String toString() {
            return "SegmentTree{" +
                    "root=" + root +
                    '}';
        }
    }

    public class Node {
        int sum;
        int start;
        int end;
        int mid;
        Node left;
        Node right;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.mid = start + (end - start) / 2;
        }

        @Override
        public String toString() {
            return String.format("Node->Start:%s,End:%s,Sum:%s,\n\tLeft:%s,\n\tRight:%s", start,end,sum,left,right);
        }
    }

    // Segment tree to have sums at non leaf nodes
    // Leaf nodes have values
    public RangeSumQuery(int[] nums) {
        this.input = nums;
        tree = new SegmentTree(nums);
    }

    public void update(int index, int val) {
        int diff = val - input[index];
        input[index] = val;
        tree.update(tree.root, index, diff);
    }

    public int sumRange(int left, int right) {
        return tree.sumRecursive(tree.root, left, right);
    }


}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
