p1  p2  p3

p1 = pnode;//头节点
p2 = pnode->next;
while(p2 != null) {
    p3 = p2->next;
    p2.next = p1;
    p1 = p2;
    p2 = p3;
}
pnode->next = null;
pnode = p1;
return pnode;



    private ListNode reverse(ListNode head) {
    	ListNode prev = null;
    	while (head != null) {
    		ListNode next = head.next;
    		head.next = prev;
    		prev = head;
    		head = next;
    	}
    	return prev;
    }
