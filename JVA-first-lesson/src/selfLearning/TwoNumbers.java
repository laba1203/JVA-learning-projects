package selfLearning;

/**
 * Created by Family on 07-Oct-17.
 */

/****
 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit.
 Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 *
 ***/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class TwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String one = Integer.toString(l1.val);
        String sec = Integer.toString(l2.val);


        ListNode listNode = new ListNode(0);


        return listNode;

    }

}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
