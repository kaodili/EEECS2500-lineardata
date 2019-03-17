import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

// Written By: Kaodili Okwuaka

class SortProgram {
    static int NUM_OF_INT = 50000;
    static int randomNum;
    static int[] dataArray  = new int[NUM_OF_INT];
    static int[] dataArray1 = new int[NUM_OF_INT];
    static int[] dataArray2 = new int[NUM_OF_INT];
    static int numOfSwaps;
    
    public static void PrintToFile() throws Exception {
        FileOutputStream writer = new FileOutputStream("ProjectOrder.txt");
        PrintStream P = new PrintStream(writer);
        
        FileOutputStream writer1 = new FileOutputStream
                                    ("ProjectDescending.txt");
        PrintStream P1 = new PrintStream(writer1);
        
        FileOutputStream writer2 = new FileOutputStream("ProjectRandom.txt");
        PrintStream P2 = new PrintStream(writer2);     
        
        for (int i = 1; i <= NUM_OF_INT; i++) {
            dataArray[i - 1] = (i);
            P.println(dataArray[i - 1]);
        }
        int j;
        j = 0;
        for (int i = NUM_OF_INT; i > 0; i--) {
            dataArray1[j] = (i);
            P1.println(dataArray1[j]);
            j++;
        }
        for (int i = 0; i < NUM_OF_INT; i++) {
            randomNum = (int) ((Math.random() * NUM_OF_INT + 1));
            dataArray2[i] = (randomNum);
            P2.println(dataArray2[i]);
        }
    }    
    
    public static void insertionSort() {
        for (int count = 1; count < NUM_OF_INT; count++) {
            insertElement(0,count);
        }  
    }
  
    public static void insertElement(int start, int end) {
        boolean finished, more;
        finished = false;
        more = true;
        
        while (more && !finished) {           
            if ((dataArray[end]) < (dataArray[end - 1])) {
                swap(end, end - 1); 
                numOfSwaps++;
                end--;
                more = (end != start);
            } else {
                finished = true;
            }           
        }  
    }

    private static void swap(int first, int second) {
        int temp;
        temp = dataArray[first];
        dataArray[first] = dataArray[second];
        dataArray[second] = temp;
    } 
    }
    public class SortArray {
        public static void main(String[] args) throws Exception {
            int before, after, total;

            SortProgram.PrintToFile();
            readFile("ProjectOrder.txt");
            System.out.println("\nOrdered Array ");
            for (int i = 0; i < 15; i++) {
                 System.out.print(SortProgram.dataArray[i] + " ");
            }
            System.out.println();
            for (int i = 15; i < 30; i++) {
                System.out.print(SortProgram.dataArray[i] + " ");
            }
            System.out.println();
            SortProgram.numOfSwaps = 0;
            before = (int) System.currentTimeMillis();
            SortProgram.insertionSort();
            after = (int) System.currentTimeMillis();
            total = after - before;

            System.out.println("Total Sorting Runtime = " + total
                + "ms. \nNumber of Comparisons = " + SortProgram.numOfSwaps);

            readFile("ProjectDescending.txt");
            SortProgram.numOfSwaps = 0;
            System.out.println();
            System.out.println("\nReverse Array");
            for (int i = 0; i < 15; i++) {
                System.out.print(SortProgram.dataArray1[i] + " ");
            }
            System.out.println();
            for (int i = 15; i < 30; i++) {
                System.out.print(SortProgram.dataArray1[i] + " ");
            }
            before = (int) System.currentTimeMillis();
            SortProgram.insertionSort();
            after = (int) System.currentTimeMillis();
            total = after - before;

            System.out.println("\nTotal Sorting Runtime = " + total
                + "ms. \n Number of Comparisons = " + SortProgram.numOfSwaps);

            readFile("ProjectRandom.txt");
            SortProgram.numOfSwaps = 0;
            
            System.out.println("\nRandom Array");
            for (int i = 0; i < 15; i++) {
                System.out.print(SortProgram.dataArray2[i] + " ");
            }
            System.out.println();
            for (int i = 15; i < 30; i++) {
                System.out.print(SortProgram.dataArray2[i] + " ");
            }
            before = (int) System.currentTimeMillis();
            SortProgram.insertionSort();
            after = (int) System.currentTimeMillis();
            total = after - before;

            System.out.println("\nTotal Sorting Runtime = " + total
                + "ms. \n Number of Comparisons = " + SortProgram.numOfSwaps);
    }

    public static void readFile(String fileName) throws Exception {
        File file = new File(fileName);
        Scanner fileScan = new Scanner(file);
        int nextNum;

        for (int i = 0; i < 50000; i++) {
            nextNum = Integer.parseInt(fileScan.next());
            SortProgram.dataArray[i] = nextNum;
        }
    }
}



   
