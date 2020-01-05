import java.io.*;
import java.text.Collator;
import java.util.*;


public class Spellchecker {
    private static Object notInDic;
    private static ArrayList<String> arr = new ArrayList<String>(); // words that are in the dictionary
    private static ArrayList<String> original  = new ArrayList<String>(); // all the words in the original format
    private static ArrayList<String> low  = new ArrayList<String>(); // lowercase words
    private static ArrayList<String> dic  = new ArrayList<String>(); // words being added to the dictionary
    private static ArrayList<String> all  = new ArrayList<String>(); // all the words in the user file
    private static ArrayList<String> arr1 = new ArrayList<String>(); // words that are not in the dictionary
    private static ArrayList<String> wordtoreplace = new ArrayList<String>(); // words that are being replaced
    private static ArrayList<String> replaced = new ArrayList<String>(); // words that are replaced
    private static ArrayList<String> replac = new ArrayList<String>(); // words that are replaced

    public static void main(String[] args) throws IOException {
        //dictionary("Dictionary.txt");
        Scanner scanner = new Scanner(System.in); // creating a scanner

        System.out.print("Enter the file name: "); //  prompt for the file name

        String file = scanner.next(); // scanners taht are next
        int v = 0; // counter

        if (correct(file)){ // calling the function correct

        }


        for(int i = 0; i < arr.size(); i++){ // for loop
        Scanner scanner1 = new Scanner(System.in); // scanner
        //  prompt for the file name
        System.out.print( "Sorry the word - " + arr.get(i) + " - is not in the Dictionary" + " would you like to add it to the dictionary?" + " (Yes/No) ");
        // get their input as a String
        String add = scanner1.next();

            if(add.equals("yes") || add.equals("Yes")){ // if the user types yes
                //File files = new File(file);
                PrintWriter print1 = new PrintWriter(new FileOutputStream("Dictionary.txt", true)); // read dictionary

                print1.println(arr.get(i)); // add the word in the dictionary
                print1.close(); // close the file
                replaced.add(arr.get(i)); // add the word in the replaced variable
                dic.add(arr.get(i)); // add the dictionary word
                System.out.println(arr.get(i) + " Was Successfully Added to the Dictionary"); //prompt message to the
                // user
                System.out.println(" ");


            }
           else{
                wordtoreplace.add(arr.get(i)); // add the word to word replaced

                Scanner scanner2 = new Scanner(System.in); // create a scanner
                //  prompt for the file name
                System.out.println("Please enter a replacement Word "); // message to the user
                // get their input as a String
                String file2 = scanner2.next(); // store the words
                //replaceWord(file, file2, arr.get(i));
                replac.add(file2); // add the word to the array
                replaced.add(file2); // add the word to teh array
                System.out.println("The word " + arr.get(i)+ " is sucessfully replaced" ); // message to the user
                System.out.println(" ");

                }
            }

        if(replaced.size() > 0) { // if the replaced word does not equal zero
            for (int a = 0; a < replac.size(); a++) { // loop
                for (int b = 0; b < original.size(); b++) { //loop
                    if (all.get(b).equals(wordtoreplace.get(a))) { //if the word in all is in replaced
                        original.set(b, replac.get(a)); // replace the word in the orignial
                    }

                }

            }
        }



        String result = String.join( " ", original); // join into one senter

        replaced.addAll(arr1); // add the word to all
        PrintWriter print1 = new PrintWriter(new FileOutputStream("CorrectSpelling.txt", true)); // create teh file
        //writer.newLine();   //Add new line
        print1.println(result); // write the word
        print1.close(); // close the file



        sortFile(); // sort the file
        }




   /*///////////////////////////////////////////////////////////////////////////////////////////

       This method sorts the dictionary into alphabetical order

    ///////////////////////////////////////////////////////////////////////////////////*/

    public static void sortFile() throws IOException
    {
        FileReader fileReader = new FileReader("Dictionary.txt"); // reading the file
        BufferedReader bufferedReader = new BufferedReader(fileReader); // reading the file
        List<String> lines = new ArrayList<String>(); // creating an array list
        String line = null; // to konw the end of the list
        while ((line = bufferedReader.readLine()) != null) { // until the end
            lines.add(line); // add all the words
        }
        bufferedReader.close(); // close the file

        Collections.sort(lines, Collator.getInstance()); // sort the file

        FileWriter writer = new FileWriter("Dictionary.txt"); // rewrite the file
        for(String str: lines) {
            writer.write(str + "\r\n"); // create lines while reading
        }
        writer.close(); // close
    }




    ////////////////////////////////////////////////////////////////////////////////////

    /*
    - This method is created to find words in the users file that is not in the dictionary
    -1. creating a hash table for dictionary words
    2. finding the words
     */

    ////////////////////////////////////////////////////////////////////////////////////


        public static <isavailable> boolean correct (String word) throws IOException {

            Hashtable<Integer, String> hash = new Hashtable<Integer, String>(); // create a hash table for the dictionary
            String line = ""; // create a string for lines


            BufferedReader br= new BufferedReader(new FileReader("Dictionary.txt")); // read the dictionary file
            int count = 0; // variable to count

            int i = 0; // variable to iterate over the hash table
            while ((line = br.readLine()) != null){ // checks the iteration is not in the last line
                hash.put(i, line); // store the dictionary word in the hash table
                i++; // incremeent
            }


            //Hashtable<Integer, String> hash1 = new Hashtable<Integer, String>();
            ArrayList<String> stored1 = new ArrayList<String>(); // new array list
            ArrayList<String> stored = new ArrayList<String>(); // new array list
            String line1 = ""; // turing all objects into strings

            Scanner scan = new Scanner(new File(word)); // reading the users file
            int v = 0; // counter
            while (scan.hasNext()){ // going to the next word in the user
                String s = scan.next(); // storing the word into an array list
                String y = s.toLowerCase(); // storing every word into an array list
                stored.add(y); // words that are in lower case
                original.add(s); // words that are not in lower case

                low.add(y); // words that is loser case

                v++; // increment
            }

            String trying; // creating a new variable
            boolean isavailable = false; // initiating is available to false which means it is not in the dictionary
            boolean available = true; // it is in the idctionary
            int sizeCheck = stored.size(); // size of the words in the file

            for (String s : stored) {
                s = s.replace("\'", ""); // getting rid of apostrophe in the user file
                s = s.replace(":", ""); // getting rid of apostrophe in the user file
                s = s.replace("-", ""); // getting rid of apostrophe in the user file
                s = s.replace(".", ""); // getting rid of apostrophe in the user file
                s = s.replace("(", ""); // getting rid of apostrophe in the user file
                s = s.replace(")", ""); // getting rid of apostrophe in the user file
                s = s.replace("?", ""); // getting rid of apostrophe in the user file
                s = s.replace("!", ""); // getting rid of apostrophe in the user file
                trying = s; // storing the words that has no apostrophe

                isavailable = hash.containsValue(trying); // checking if the word is in the dictionary

                //j++;

                if (!isavailable) {
                    available = false; // if it is not available change this to false
                    Spellchecker.arr.add(s); // add the word into the array
                    //Spellchecker.replaced.add(s);
                    //arr.get(s).toLowerCase();

                    Spellchecker.notInDic = s; // add the word not in dictinoaary
                    all.add(s); // add the word in all


                }
                else{
                    available = true; // change to true
                    Spellchecker.arr1.add(s); // add the word that is in teh dictionary
                    Spellchecker.replaced.add(s); // add the word being replaced
                    all.add(s); // add all words

                }


            }
            return available;// return available

        }
}

