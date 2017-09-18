import java.util.Random;
public class Diaz_Alejandro_Lab2{
	public static void main(String[] args){
		iNode node = buildList(10, 10);
		iNode head = bubbleSortList(node);
		iNode iterator = head;
		while(iterator != null){
			System.out.println(iterator.item);
			iterator = iterator.next;
		}

		mergeSortList(node);
		//compareList(node);
	}

	public static iNode buildList(int n, int m){
		Random rand = new Random();
		iNode first = new iNode(rand.nextInt(m));
		for(int i=1;i<n;i++){
			first = new iNode(rand.nextInt(m), first);		
		}
		return first;
	}
	
	public static void compareList(iNode node) {
		
		//we are starting on the first node of the list
		int currentNodeNumber = 1; //keeps track of which node we are on
		iNode currentNode = node;
		
		//we will begin comparing the node to the node directly after it
		int iteratorNumber = currentNodeNumber+1;
		iNode iterator = currentNode.next;

		while(currentNode.next != null){
			System.out.println("\nCurrent Node number: " + currentNodeNumber);
			System.out.println("Current Node item: " + currentNode.item);
			while(iterator != null){
				if(currentNode.item > iterator.item)
					System.out.println("Current Node item " + currentNode.item + " is greater than node " + iteratorNumber + "\'s item " + iterator.item);

				else if(currentNode.item == iterator.item)
					System.out.println("Current Node item " + currentNode.item + " is equal to node " + iteratorNumber + "\'s item " + iterator.item);

				else
					System.out.println("Current Node item " + currentNode.item + " is less than node " + iteratorNumber + "\'s item " + iterator.item);
				iterator = iterator.next;
				iteratorNumber++;
			}
			currentNode = currentNode.next;
			iterator = currentNode.next;
			currentNodeNumber++;
			iteratorNumber = currentNodeNumber+1;
		}
	}

	public static iNode bubbleSortList(iNode node){
		iNode iterator = node;
		int temp;
		boolean didSwap = true;
		while(didSwap){
			didSwap = false;
			while(iterator.next != null){
				if(iterator.item > iterator.next.item){
					temp = iterator.item;
					iterator.item = iterator.next.item;
					iterator.next.item = temp;
					didSwap = true;
				}
				iterator = iterator.next;
			}
			iterator = node;
		}
		return node;
	}

	public static iNode mergeSortList(iNode node){
		iNode iterator = node;
		iNode head1 = node;
		int listSize = 0;
		while(iterator != null)
		{
			listSize++;
			iterator = iterator.next;
		}

		System.out.println("List size is " + listSize);
		return head1;

	}
}
