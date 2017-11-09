import java.util.Scanner;
import java.io.*;

public class Diaz_Alejandro_Lab5{
	public static void main(String[] args){
		String fileName = "glove.6B.50d.txt";
        int initialSize = 47;
        int newSize;

		try {
		// Scanner console = new Scanner(System.in);
		// String fileName = console.nextLine();
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
        hashTableStrings HT = new hashTableStrings(initialSize);
        hashTableStrings newHT; //temp variable to change hashtable size

        String str = bufferedReader.readLine(); //holds the next line in the file and is checked to see if it is null
        String[] wordInfo; //stores information about words
        float[] embeddings; //stores just the embeddings of the words

        sNode iterator; //used to traverse the hashtable
        int index; //used to hold the index the word will have in the hashtable
        int ctr = 0; //used to count the number of items that have been put into the table

        //check if the line is not null, if it is not, extract the information and store it into memory
        while(str != null){
            wordInfo = str.split(" ");
            embeddings = new float[wordInfo.length-1];

            //if the load factor is higher than 0.74, then transfer the hashtable into a new list with the 
            if((float)ctr/(float)initialSize > 0.74){
                newSize = initialSize*2+1; //double the size and +1
                newHT = new hashTableStrings(newSize); //temp hashtable
                for(int k = 0; k<initialSize; k++){
                    iterator = HT.H[k];
                    while(iterator != null){
                        //add every element in the original hashtable but now with the new index
                        index = getHashIndex(iterator.word, newSize);
                        newHT.H[index] = new sNode(iterator.word, iterator.embedding, newHT.H[index]);
                        iterator = iterator.next;
                    }
                }
                initialSize = newSize;
                HT = newHT;
            }
            //turn the embeddings into floats to add to the array
            for(int i = 1; i<wordInfo.length; i++){
                embeddings[i-1] = Float.parseFloat(wordInfo[i]);
            }
            index = getHashIndex(wordInfo[0], initialSize);
            HT.H[index] = new sNode(wordInfo[0], embeddings, HT.H[index]);//add new node to beginning of linked list at index
            str = bufferedReader.readLine(); //read in the next line
            ctr++; //keep count of number of elements in the hashtable
        }
        
        //vars used to read information from the new file
        fileName = "2WordsPerLine.txt";
        fileReader = new FileReader(fileName);
        bufferedReader = new BufferedReader(fileReader);
        str = bufferedReader.readLine();
        
        int numEmpty = 0;//counts empty lists
        int[] listLengths = new int[HT.H.length];//counts the length of the lists
        double loadFactor = (float)ctr/(float)initialSize; //calculate load factor
        float mean = 0; //stores the mean of the list

        //used to print out contents of the hashTable
        for(int k = 0; k<initialSize; k++){
            iterator = HT.H[k];
            if(iterator == null){
                numEmpty++; //if the list is null from the beginning, the list is empty
            }
            while(iterator != null){
                listLengths[k]++;
                //System.out.print(iterator.word + " ");
                // for(int m = 0; m<iterator.embedding.length; m++){
                //     System.out.print(iterator.embedding[m] + " ");
                // }
                //System.out.println("\n");
                iterator = iterator.next;
            }
            mean += listLengths[k];//so we don't waste another for loop, we just add as we go!
        }

        //calculate standard deviation
        mean /= listLengths.length;
        int sum = 0; //stores the sum of the squared differences between values and the mean
        for(int i = 0; i<listLengths.length; i++){
            sum += Math.pow(listLengths[i]-mean, 2);
        }
        sum/= listLengths.length;

        //print out word similarities from file
        while(str != null){
            wordInfo = str.split(" ");
            System.out.println("The similarity of " + wordInfo[0] + " and " + wordInfo[1] + " is " + sim(wordInfo[0], wordInfo[1], HT));
            str = bufferedReader.readLine();
        }

        //print out information
        System.out.println("Load Factor: " + loadFactor);
        System.out.println("Percentage of lists that are empty: " + (float)numEmpty/(float)initialSize);
        System.out.println("Standard deviation of list lengths: " + (Math.sqrt(sum)));
	}
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
	}

    //the hashfunction that returns a hash index
    public static int getHashIndex(String word, int size){
        int sum = 0;
        for(int i = 0; i<word.length(); i++){
            sum += Math.pow(7, (word.length() - i - 1))*(int)word.charAt(i);
        }
        return sum%size;
    }

    //returns the sNode that contains the word you need
    public static sNode findWord(String word, hashTableStrings HT){
        int index = getHashIndex(word, HT.H.length);
        sNode iterator = HT.H[index];
        while(iterator != null){
            if(iterator.word.equals(word))
                return iterator;
            iterator = iterator.next;
        }
        System.out.println("Word not found");
        return null;
    }

    //calculates the similarity using the formula
    public static float sim(String w1, String w2, hashTableStrings HT){
        sNode word1 = findWord(w1, HT);
        sNode word2 = findWord(w2, HT);
        float sim = 0;
        float magnitude1 = 0;
        float magnitude2 = 0;
        for(int i = 0; i<word1.embedding.length; i++){
            sim += word1.embedding[i]*word2.embedding[i];
            magnitude1 += Math.pow(word1.embedding[i], 2);
            magnitude2 += Math.pow(word2.embedding[i], 2);
        }
        return sim/((float)Math.sqrt(magnitude1)*(float)Math.sqrt(magnitude2));
    }
}


