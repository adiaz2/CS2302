import java.util.Random; 
public class BTreeTest{
 
  public static void printAscending(BTreeNode T){
   //Prints all keys in the tree in ascending order
		if (T.isLeaf){
			for(int i =0; i<T.n;i++)
				System.out.print(T.key[i]+" ");
		}
		else{
			for(int i =0; i<T.n;i++){
				printAscending(T.c[i]);
				System.out.print(T.key[i]+" ");
			}
			printAscending(T.c[T.n]);

		}
   }

   public static void main(String[] args)   {
	   int [] S ={6, 3, 16, 11, 7, 17, 14, 3, 8, 5, 19, 15, 1, 2, 4, 18, 13, 9, 20, 10, 12, 21, 22};

		BTree B = new BTree(3);
      for (int i=0;i<S.length;i++){
  			B.insert(S[i]);
		    B.printNodes();
        System.out.println("*********************");
      }
      double startTime;

      B.printNumKeys();
      B.printNumNodes();
      System.out.println(B.root.c[1].findKeyBinary(17));

      BTreeNode T = B.root;
      System.out.println("(a) Print the keys in the tree in ascending order.");
      startTime = System.nanoTime();
      printAscending(T);
      System.out.println();
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(b) Print the keys in the tree that have depth d in descending order.");
      startTime = System.nanoTime();
      B.printDescendingAtDepth(T, 1);

      System.out.println();
      System.out.println("(c) Determine if a given element k is in the tree.");
      startTime = System.nanoTime();
      System.out.println(B.findKey(T, 23));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(d) Return the minimum element in the tree.");
      startTime = System.nanoTime();
      System.out.println(B.getMin(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(e) Return the minimum element in the tree at a given depth d.");
      startTime = System.nanoTime();
      System.out.println(B.getMinAtDepth(T, 0));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(f) Return the maximum element in the tree.");
      startTime = System.nanoTime();
      System.out.println(B.getMax(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(g) Return the maximum element in the tree at a given depth d.");
      startTime = System.nanoTime();
      System.out.println(B.getMaxAtDepth(T, 0));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(h) Return the number of nodes in the tree.");
      startTime = System.nanoTime();
      System.out.println(B.getNumNodes(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(i) Return the number of keys in the tree.");
      startTime = System.nanoTime();
      System.out.println(B.getNumKeys());
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(j) Return the number of keys in the tree at a given depth d.");
      startTime = System.nanoTime();
      System.out.println(B.getNumKeysAtDepth(T, 2));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(k) Return the sum of all the keys in the tree.");
      startTime = System.nanoTime();
      System.out.println(B.getKeysSum(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(l) Return the sum of all the keys in the tree at a given depth d.");
      startTime = System.nanoTime();
      System.out.println(B.getKeysSumAtDepth(T,2));      
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(m) Return the number of leaves in the tree.");
      startTime = System.nanoTime();
      System.out.println(B.getNumLeaves(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(n) Return the number of nodes in the tree that have depth d.");
      startTime = System.nanoTime();
      System.out.println(B.getNumNodesAtDepth(T,2));      
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(o) Return the number of nodes in the tree that are full.");
      startTime = System.nanoTime();
      System.out.println(B.getNumNodesFull(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(p) Given a key k, return the depth at which it is found in the tree, of -1 if k is not in the tree.");
      startTime = System.nanoTime();
      System.out.println(B.getKeyDepth(T, 23));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(q) Given a key k, print all the keys that are in the same node as k.");
      startTime = System.nanoTime();
      B.printKeysInNode(T,18);     
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      
      //Build B-tree with random elements
      Random rn = new Random();
      BTree R = new BTree(4);
      for (int i=0;i<30;i++){
  			R.insert(rn.nextInt(100));
		   R.printNodes();
         System.out.println("*********************");
      }
      T = R.root;


      printAscending(T);
      System.out.println();
      System.out.println("(a) Print the keys in the tree in ascending order.");
      startTime = System.nanoTime();
      printAscending(T);
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println();
      System.out.println("(b) Print the keys in the tree that have depth d in descending order.");
      startTime = System.nanoTime();
      R.printDescendingAtDepth(T, 1);
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println();
      System.out.println("(c) Determine if a given element k is in the tree.");
      startTime = System.nanoTime();
      System.out.println(R.findKey(T, 23));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(d) Return the minimum element in the tree.");
      startTime = System.nanoTime();
      System.out.println(R.getMin(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(e) Return the minimum element in the tree at a given depth d.");
      startTime = System.nanoTime();
      System.out.println(R.getMinAtDepth(T, 0));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(f) Return the maximum element in the tree.");
      startTime = System.nanoTime();
      System.out.println(R.getMax(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(g) Return the maximum element in the tree at a given depth d.");
      startTime = System.nanoTime();
      System.out.println(R.getMaxAtDepth(T, 0));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(h) Return the number of nodes in the tree.");
      startTime = System.nanoTime();
      System.out.println(R.getNumNodes(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(i) Return the number of keys in the tree.");
      startTime = System.nanoTime();
      System.out.println(R.getNumKeys());
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(j) Return the number of keys in the tree at a given depth d.");
      startTime = System.nanoTime();
      System.out.println(R.getNumKeysAtDepth(T, 1));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(k) Return the sum of all the keys in the tree.");
      startTime = System.nanoTime();
      System.out.println(R.getKeysSum(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(l) Return the sum of all the keys in the tree at a given depth d.");
      startTime = System.nanoTime();
      System.out.println(R.getKeysSumAtDepth(T,1));      
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(m) Return the number of leaves in the tree.");
      startTime = System.nanoTime();
      System.out.println(R.getNumLeaves(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));

      System.out.println("(n) Return the number of nodes in the tree that have depth d.");
      startTime = System.nanoTime();
      System.out.println(R.getNumNodesAtDepth(T,1));      
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(o) Return the number of nodes in the tree that are full.");
      startTime = System.nanoTime();
      System.out.println(R.getNumNodesFull(T));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(p) Given a key k, return the depth at which it is found in the tree, of -1 if k is not in the tree.");
      startTime = System.nanoTime();
      System.out.println(R.getKeyDepth(T, 23));
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
      System.out.println("(q) Given a key k, print all the keys that are in the same node as k.");
      startTime = System.nanoTime();
      B.printKeysInNode(T,18);   
      System.out.println();
      System.out.println("Runtime: " + (System.nanoTime()-startTime));
      
	}
}