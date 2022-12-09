package milton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static final String HEADER = "word,count\n";
    public static void main(String[] args) throws Exception {

        String filename = args[0];
        System.out.printf("Processing : %s\n ", filename);

        // File fobj = Paths.get(filename).toFile();
        
            FileReader fr = new FileReader(filename);
            BufferedReader bdf = new BufferedReader(fr);

            // String line = bdf.readLine();
            // System.out.println("First line = >" + line);
            Integer totalWords = 0;
            Map<String, Integer> wordMap = new HashMap<>();
            

            for ( Integer i = 1 ; i<= 100; i++){
                String line = bdf.readLine();
                if (null == line)
                break;
                // System.out.printf("\n%d: %s ",i ,  line);
                i++;

                String[] words = line.trim().split(" ");
                totalWords += words.length ;
                for(String w: words){
                    String clearWord = w.replaceAll(",", "");
                    Integer v = wordMap.getOrDefault(clearWord, 0);
                    v++;
                    wordMap.put(clearWord, v);
                //     if(wordMap.containsKey(w)){
                //     Integer v = wordMap.get(w) +1 ;
                //     wordMap.put(w,v);
                //     // wordMap.put(w, wordMap.get(w) +1 );
                // }
                //     else
                //     wordMap.put(w, 1);
                }
             
                
            }

            bdf.close();

            Set<String> uniqueWords = wordMap.keySet();

            System.out.printf("The number of words in the first 100 line : %d\n", totalWords);
            System.out.printf("The number of unique words : %d\n", uniqueWords.size());

            // for(String w : uniqueWords){
            //     System.out.printf("> %s : %d\n", w , wordMap.get(w));
            // }

            // create csv file
            FileOutputStream fos = new FileOutputStream(args[1]);
            OutputStreamWriter osw = new OutputStreamWriter(fos);


            osw.write(HEADER);
            for (String w : wordMap.keySet()){
                String line = String.format("%s,%d\n",w,wordMap.get(w));
                osw.write(line);
            }
            osw.flush();
            osw.close();
            fos.close();

    }
            

    

}
