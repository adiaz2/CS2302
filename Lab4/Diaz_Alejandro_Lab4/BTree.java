/**************************************************
** Simple Program to implement B-Tree operations **
** Essentially a wrapper for the BTreeNode class **
** This file includes the basic B-tree class     **
** Programmed by Olac Fuentes                    **
** Last modified  February 21, 2017              **
** Report bugs to me                             **
***************************************************/

public class BTree{
	public BTreeNode root;
	private int t; //2t is the maximum number of childen a node can have
	private int height;
	private int numKeys;
	private int numNodes;
	
	public BTree(int t){
		root = new BTreeNode(t);
		this.t = t;
		height = 0;
		numKeys = -1;
		numNodes = 1;
	}
	
	public void printHeight(){
		System.out.println("Tree height is "+height);
	}

	public void printNumKeys(){
		System.out.println("Number of keys is "+numKeys);
	}

	public void printNumNodes(){
		System.out.println("Number of nodes is "+numNodes);
	}


	
	public void insert(int newKey){
		if (root.isFull()){//Split root;
			split();
			height++;
		}
		//if the insertion was a success, we increase the number of keys
		if(root.insert(newKey)){
			numKeys++;
			numNodes = getNumNodes(root);
		}
	}
	
	public void print(){
	// Wrapper for node print method
		root.print();
	}
	
	public void printNodes(){
	// Wrapper for node print method
		root.printNodes();
	}

	public void split(){
	// Splits the root into three nodes.
	// The median element becomes the only element in the root
	// The left subtree contains the elements that are less than the median
	// The right subtree contains the elements that are larger than the median
	// The height of the tree is increased by one

		// System.out.println("Before splitting root");
		// root.printNodes(); // Code used for debugging
		BTreeNode leftChild = new BTreeNode(t);
		BTreeNode rightChild = new BTreeNode(t);
		leftChild.isLeaf = root.isLeaf;
 		rightChild.isLeaf = root.isLeaf;
		leftChild.n = t-1;
		rightChild.n = t-1;
		int median = t-1;
		for (int i = 0;i<t-1;i++){
			leftChild.c[i] = root.c[i];
			leftChild.key[i] = root.key[i];
		}
		leftChild.c[median]= root.c[median];
		for (int i = median+1;i<root.n;i++){
			rightChild.c[i-median-1] = root.c[i];
			rightChild.key[i-median-1] = root.key[i];
		}
		rightChild.c[median]=root.c[root.n];
		root.key[0]=root.key[median];
		root.n = 1;
		root.c[0]=leftChild;
		root.c[1]=rightChild;
		root.isLeaf = false;
		// System.out.println("After splitting root");
		// root.printNodes();
	}

// (b) Print the keys in the tree that have depth d in descending order.
   public void printDescendingAtDepth(BTreeNode root, int d){
   		if(root == null || d <0){
   			return;
   		}
   		if(d == 0){
   			for(int i = root.n-1; i>=0; i--){
   				System.out.print(root.key[i] + " ");
   			}
   		}
   		else{
	   		for(int i = root.n; i>=0; i--){
	   			printDescendingAtDepth(root.c[i], d-1);
   			}
   		}
   	}

// (c) Determine if a given element k is in the tree.
   public boolean findKey(BTreeNode root, int keyToFind){
   		if(root == null){
   			System.out.println("Key "+keyToFind + " not found.");
   			return false;
   		}
		if(root.isLeaf){
   			if(root.findKeyBinary(keyToFind) == -1){
   				System.out.println("Key "+keyToFind + " not found.");
   				return false;
   			}
   			else
   				return true;
   		}
   		if(keyToFind > root.key[root.n-1])
   			return findKey(root.c[root.n], keyToFind);
   		for(int i = 0; i<root.n; i++){
   			if(keyToFind == root.key[i])
   				return true;
   			if(keyToFind > root.key[i] && keyToFind < root.key[i+1]){
   				return findKey(root.c[i+1], keyToFind);
   			}
   		}
   		return findKey(root.c[0], keyToFind);
   }

// (d) Return the minimum element in the tree.
   public int getMin(BTreeNode root){
   		if(root == null || root.n <= 0){
   			return Integer.MAX_VALUE;
   		}
		if(root.isLeaf){
			return root.key[0];
		}
		else
			return getMin(root.c[0]);
   }
// (e) Return the minimum element in the tree at a given depth d. 
   public int getMinAtDepth(BTreeNode root, int d){
    	if(root == null || root.n <= 0){
   			return Integer.MAX_VALUE;
   		}
		if(d == 0){
			return root.key[0];
		}
		else
			return getMinAtDepth(root.c[0], d-1);
   }
// (f) Return the maximum element in the tree.
   public int getMax(BTreeNode root){
   		if(root == null || root.n <= 0){
   			return Integer.MIN_VALUE;
   		}
		if(root.isLeaf){
			return root.key[root.n-1];
		}
		else
			return getMax(root.c[root.n]);    
   }
// (g) Return the maximum element in the tree at a given depth d. 
   public int getMaxAtDepth(BTreeNode root, int d){
    	if(root == null || root.n <= 0){
   			return Integer.MIN_VALUE;
   		}
		if(d == 0){
			return root.key[root.n-1];
		}
		else
			return getMaxAtDepth(root.c[root.n], d-1);  
   }
// (h) Return the number of nodes in the tree.
   public int getNumNodes(BTreeNode root){
   		if(root == null){
   			return 0;
   		}
   		if(root.isLeaf)
   			return 1;
   		else{
   			int sum = 1;
   			for(int i = 0; i<=root.n; i++){
				sum += getNumNodes(root.c[i]);
			}
			return sum;
		}
   }
// (i) Return the number of keys in the tree.
   public int getNumKeys(){
    	return numKeys;
   }
// (j) Return the number of keys in the tree at a given depth d. 
   public int getNumKeysAtDepth(BTreeNode root, int d){
   		if(root == null || root.n <= 0){
   			return 0;
   		}
		if(d == 0){
			return root.n;
		}
		else{
			int sum = 0;
			for(int i = 0; i<=root.n; i++){
				sum += getNumKeysAtDepth(root.c[i], d-1);
			}
			return sum;
		}
    
   }
// (k) Return the sum of all the keys in the tree.
   public int getKeysSum(BTreeNode root){
   		if(root == null || root.n <= 0){
   			return 0;
   		}
   		if(root.isLeaf){
    		int sum = 0;
    		for(int i = 0; i<root.n; i++){
    			sum+=root.key[i];
    		}
    		return sum;
   		}
   		else{
   			int sum = 0;
    		for(int i = 0; i<=root.n; i++){
    			if(i != root.n)
    				sum+=root.key[i];
    			sum+=getKeysSum(root.c[i]);
    		}
    		return sum;

   		}
   	}
// (l) Return the sum of all the keys in the tree at a given depth d. 
   public int getKeysSumAtDepth(BTreeNode root, int d){
   		if(root == null || root.n <= 0){
   			return 0;
   		}
   		if(d == 0){
    		int sum = 0;
    		for(int i = 0; i<root.n; i++){
    			sum+=root.key[i];
    		}
    		return sum;
   		}
   		else{
   			int sum = 0;
    		for(int i = 0; i<=root.n; i++){
    			sum+=getKeysSumAtDepth(root.c[i], d-1);
    		}
    		return sum;

   		}
    
   }
// (m) Return the number of leaves in the tree.
   public int getNumLeaves(BTreeNode root){
   		if(root == null){
   			return 0;
   		}
   		if(root.isLeaf)
   			return 1;
   		else{
   			int sum = 0;
   			for(int i = 0; i<=root.n; i++){
				sum += getNumLeaves(root.c[i]);
			}
			return sum;
		}

    
   }
// (n) Return the number of nodes in the tree that have depth d.
   public int getNumNodesAtDepth(BTreeNode root, int d){
   		if(root == null || root.n <= 0){
        return 0;
      }
    if(d == 0){
      return 1;
    }
    else{
      int sum = 0;
      for(int i = 0; i<=root.n; i++){
        sum += getNumNodesAtDepth(root.c[i], d-1);
      }
      return sum;
    }
    
   }
// (o) Return the number of nodes in the tree that are full.
   public int getNumNodesFull(BTreeNode root){
   		if(root == null){
   			return 0;
   		}
   		if(root.isLeaf){
   			if(root.isFull())
   				return 1;
   			return 0;
   		}
   		else{
   			int sum = 0;
   			if(root.isFull())
   				sum++;
   			for(int i = 0; i<=root.n; i++){
				sum += getNumNodesFull(root.c[i]);
			}
			return sum;
		}
    
   }
// (p) Given a key k, return the depth at which it is found in the tree, of -1 if k is not in the tree. 
   public int getKeyDepth(BTreeNode root, int keyToFind){
    	if(root == null){
   			System.out.println("Key "+keyToFind + " not found.");
   			return -1;
   		}
		if(root.isLeaf){
   			if(root.findKeyBinary(keyToFind) == -1){
   				System.out.println("Key "+keyToFind + " not found.");
   				return -2;
   			}
   			else
   				return 0;
   		}
   		if(keyToFind > root.key[root.n-1])
   			return getKeyDepth(root.c[root.n], keyToFind) + 1;
   		for(int i = 0; i<root.n; i++){
   			if(keyToFind == root.key[i])
   				return 0;
   			if(keyToFind > root.key[i] && keyToFind < root.key[i+1]){
   				return getKeyDepth(root.c[i+1], keyToFind) + 1;
   			}
   		}
   		return getKeyDepth(root.c[0], keyToFind) +1;
   }
// (q) Given a key k, print all the keys that are in the same node as k.)
   public void printKeysInNode(BTreeNode root, int keyToFind){
   	if(root == null){
   			System.out.println("Key "+keyToFind + " not found.");
   			return;
   		}
		if(root.isLeaf){
   			if(root.findKeyBinary(keyToFind) == -1){
   				System.out.println("Key "+keyToFind + " not found.");
   				return;
   			}
   			else{
   				for(int i = 0; i<root.n; i++){
   					System.out.print(root.key[i] + " ");
   				}
   				return;
   			}
   		}
   		if(keyToFind > root.key[root.n-1]){
   			printKeysInNode(root.c[root.n], keyToFind);
   			return;
   		}

   		for(int i = 0; i<root.n; i++){
   			if(keyToFind == root.key[i]){
   				for(i = 0; i<root.n; i++){
   					System.out.print(root.key[i] + " ");
   				}
   				return;
   			}

   			if(keyToFind > root.key[i] && keyToFind < root.key[i+1]){
   				printKeysInNode(root.c[i+1], keyToFind);
   				return;
   			}
   		}
   		printKeysInNode(root.c[0], keyToFind);
    
   }
}