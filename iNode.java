public class iNode{
	public int item;
	public iNode next;

	public iNode(int i, iNode n){
		item = i;
		next = n;
	}

	public iNode(int i){
		item = i;
		next = null;
	}
}
