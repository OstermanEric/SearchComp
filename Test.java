/**
 * name: Eric Osterman
 * Assignment: ALA9
 * assistance from Prof.Urban
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    /**
     * final variables of the file name being used that contains the dictionary
     * and number of searches which is used to compare LinkedList, BST, and HashMap
     */
    private static final String FINALNAME = "dictionary.txt";
    private static final int NUM_SEARCHES = 1_000;

    public static void main(String[] args) {
        // create instance of class hashmap for <String, String> with an initial
        // capacity of 5,000
        HashMap<String, String> hm = new HashMap<>(50_000);
        // crate an instance of BST and linkedlist for type String
        BST<String> bst = new BST<>();
        LinkedList<String> ll = new LinkedList<>();
        ArrayList<String> words = new ArrayList<>();

        // reads the file given to it and initializes values in ArrayList and HashMap
        readFile(hm, words, FINALNAME);

        // shuffling the words in ArrayList so that data structures can be better
        // compared

        // adding the randomly shufffled words to the LinkedList and BST from the now
        // shuffled ArrayList
        java.util.Collections.shuffle(words);
        for (String wrd : words) {
            ll.add(wrd);
            bst.add(wrd);
        }

        // header
        System.out.printf("\n%-20s\t%-15s\t%-3s\t%-8s", "Word", "Linked List", "BST", "Hash Map");

        /**
         * creating variables to store and keep track of total iterations done
         * in order to calculate average number of iterations at the end
         */
        int hm_total_iters = 0;
        int ll_total_iters = 0;
        int bst_total_iters = 0;

        /**
         * testContains code that compares the contaions method of LinkedList, BST, HashMap
         * for loop that loops 1000 times and picks random word to be searched for
         * displays number of iterations LinkedList, BST, HashMap all take to find
         * random word
         * every 20 random words are displayed for user to
         * prints average iterations at end of list
         * prints maximum number of collisions at the end for HashMap
         */
        for (int i = 0; i < NUM_SEARCHES; i++) {
            int rndIndex = (int) (Math.random() * words.size());
            String rnd_word = words.get(rndIndex);
            hm.get(rnd_word);
            int hm_get_iters = hm.iterations;
            hm_total_iters += hm_get_iters;
            int ll_get_iters = ll.contains(rnd_word);
            ll_total_iters += ll_get_iters;
            int bst_get_iters = bst.contains(rnd_word);
            bst_total_iters += bst_get_iters;
            // prints every 20 random words and num of iterations to find for each data
            // structure
            if (i % 50 == 0) {
                System.out.printf("\n%-20s\t%-15d\t%-3d\t%-8d", rnd_word, ll_get_iters, bst_get_iters, hm_get_iters);
            }
        }
        /**
         * prints average number of iterations it took each data structure to find random words
         * LinkedList had most iterations because it utilized a linear search approach of time complexity O(n)
         * BST had second most iterations becaue it utiliozed a binary search approach of time complexity O(log n)
         * HashMap had fastest search time with average of 1 iteration because its search approach has complexity of O(1) or constant
         */
        System.out.printf("\n\n%-20s\t%-15d\t%-3d\t%-8d", "Average", ll_total_iters / 1000, bst_total_iters / 1000,
                hm_total_iters / 1000);

        /**
         * print statment displays max number of collisions found in HashMap
         * Max number is 5
         * max number corresponds to how many words are spelled the same
         * since they are spelled the same, they are stored at the same location,
         * causing a collision
         * collision is delt with by creating a linked list at that index
         * Completed by watching ALA9 walkthrough by Dr.Oudghiri
         */
        System.out.println("\n\nMaximum number of collisions: " + hm.collisions());

    }

    /**
     * readFile method that initializes HashMap and ArrayList using information from
     * file passed as argument
     * splits the words in file by "|" character using regex
     * for ArrayList just stores the word
     * for HashMap stores word and definition as key and value
     * @param h HashMap being passed initialized with information from file
     * @param al ArrayList being initialized with information from file
     * @param filename name of file
     */
    public static void readFile(HashMap<String, String> h, ArrayList<String> al, String filename) {
        File file = new File(filename);
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] items = line.split("\\|");
                al.add(items[0]);
                h.put(items[0], items[1]);
            }
            read.close();

        } catch (FileNotFoundException fnfe) {
            System.err.println("Error. File not found.");
            System.exit(0);
        }
    }

} // end of class