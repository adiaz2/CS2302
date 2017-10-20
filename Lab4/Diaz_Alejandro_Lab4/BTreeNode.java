/********************************************************
** Simple Program to implement insertions              **
** and traversals on B-trees                           **
** This file includes basic operations on B-tree nodes **
** Programmed by Olac Fuentes                          **
** Last modified October 9, 2017                       **
** Report bugs to me                                   **
*********************************************************/
public class BTreeNode{
	public int n;          // Actual number of keys on the node
   	public boolean isLeaf; // Boolean indicator 
  	public int[] key;      // Keys stored in the node. They are sorted ion ascending order
	public BTreeNode[] c;  // Children of node. Keys in c[i] are less than key[i] (if it exists) 
                          // and greater than key[i-1] if it exists
			 	
	public BTreeNode(int t){  // Build empty node                
		isLeaf = true;
		key = new int[2*t-1];   // Array sizes are set to maximum possible size
		c = new BTreeNode[2*t];
		n=0;	                  // Number of elements is zero, since node is empty
   }
	
	public boolean isFull(){
		return n==key.length;
	}
	
	public boolean insert(int newKey){
	// Instert new key to current node
	// We make sure that the current node is not full by checking and
	// splitting if necessary before descending to node
	
	//System.out.println("inserting " + newKey); // Debugging code
      int t = c.length/2;
		int i=n-1;
		//case where the duplicate is the first in the leaf
		if(i>=0 && key[i] == newKey){
					System.out.println("Item " + newKey + " is already in the list");
					return false;
				}
		if (isLeaf){
			while ((i>=0)&& (newKey<key[i])) { // Shift key greater than newKey to left

				//case where the duplicate is somewhere else on the leaf
				if(i>0 && key[i-1] == newKey){
					System.out.println("Item " + newKey + " is already in the list");
					return false;
				}

				key[i+1] = key[i];             
            	i--;
			}
			n++;                    // Update number of keys in node
			key[i+1]=newKey;        // Insert new key
		}
		else{
			while ((i>=0)&& (newKey<key[i])) {
				i--;
				//if we find a duplicate before we get to the leaf, we can stop here
				if(i>0 && key[i-1] == newKey){
					System.out.println("Item " + newKey + " is already in the list");
					return false;
				}
			}
			int insertChild = i+1;  // Subtree where new key must be inserted
			if (c[insertChild].isFull()){
			// The root of the subtree where new key will be inserted has to be split
			// We promote the median of that root to the current node and
			// update keys and references accordingly
				n++;
				c[n]=c[n-1];
				for(int j = n-1;j>insertChild;j--){
					c[j] =c[j-1];
					key[j] = key[j-1];
				}
				key[insertChild]= c[insertChild].key[t-1];
				c[insertChild].n = t-1;
				
				BTreeNode newNode = new BTreeNode(t);
				for(int k=0;k<t-1;k++){
					newNode.c[k] = c[insertChild].c[k+t];
					newNode.key[k] = c[insertChild].key[k+t];
				}

				newNode.c[t-1] = c[insertChild].c[2*t-1];
				newNode.n=t-1;
				newNode.isLeaf = c[insertChild].isLeaf;
				c[insertChild+1]=newNode;
						
				if (newKey <key[insertChild]){
					c[insertChild].insert(newKey);					}
				else{
					c[insertChild+1].insert(newKey);				}
			}
			else
				c[insertChild].insert(newKey);  //No need to split node
		}
		return true;
	}
		
	public void print(){
	//Prints all keys in the tree in ascending order
		if (isLeaf){
			for(int i =0; i<n;i++)
				System.out.print(key[i]+" ");
			//System.out.println();
		}
		else{
			for(int i =0; i<n;i++){
				c[i].print();
				System.out.print(key[i]+" ");
			}
			c[n].print();
		}
	}
	
	public void printNodes(){
	//Prints all keys in the tree, node by node, using preorder
	//It also prints the indicator of whether a node is a leaf
	//Used mostly for debugging purposes
		for(int i =0; i<n;i++)
			System.out.print(key[i]+" ");
		System.out.println(isLeaf);
		if (!isLeaf){
			for(int i =0; i<=n;i++){
				c[i].printNodes();
			}
		}
	}

	public int findKeyBinary(int keyToFind){
		int highest = n-1;
		int lowest = 0;
		int middle;
		while(highest>=lowest){
			middle = lowest + (highest-lowest)/2;
			if(key[middle] == keyToFind)
				return middle;
			if(key[middle] < keyToFind)
				lowest = middle+1;
			else
				highest = middle-1;
		}
		return -1;
	}

}










