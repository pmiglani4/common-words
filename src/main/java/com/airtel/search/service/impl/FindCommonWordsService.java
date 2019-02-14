package com.airtel.search.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.airtel.search.service.IFindCommonWordsService;

@Service
public class FindCommonWordsService implements IFindCommonWordsService{

    @Override
    public List<String> findCommonWords(List<String> filePaths) {
        
        Map<String, Integer>  wordsMap=null;
        try {
          //Read first file and store words in a map
            wordsMap = getDistictWords(filePaths.get(0));
            for(int i=1;i<filePaths.size();i++) {
                readFileAndIncrementCommonWordsCount(filePaths.get(i), wordsMap);
            }
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<String> commonWords = new ArrayList<String>();
        for(Map.Entry<String, Integer> entry:wordsMap.entrySet()) {
            if(entry.getValue()==filePaths.size()) {
                commonWords.add(entry.getKey());
            }
        }
        return commonWords;
    }
    
    private void readFileAndIncrementCommonWordsCount(String filePath, Map<String, Integer> wordsMap) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(filePath));
        while (reader.hasNext()){
            String temp = reader.nextLine();
            String[] sts = temp.split(" ");
            for (int i = 0;i<sts.length;i++){
                if(sts[i] != "" && sts[i] != " " && sts[i] != "\n") {
                    String str = sts[i].toLowerCase().replaceAll("[^\\w_]", "");
                    if(wordsMap.containsKey(str)) {
                        wordsMap.put(str,wordsMap.get(str)+1);
                    }
                }
            }
        }
        reader.close();
    }

    private Map<String, Integer> getDistictWords(String filePath) throws FileNotFoundException{
        Map<String, Integer> words= new HashMap<>();
        Scanner reader = new Scanner(new File(filePath));
        while (reader.hasNext()){
            String temp = reader.nextLine();
            String[] sts = temp.split(" ");
            for (int i = 0;i<sts.length;i++){
                if(sts[i] != "" && sts[i] != " " && sts[i] != "\n")
                    words.put(sts[i].toLowerCase().replaceAll("\\W_", ""), 1);
            }
        }
        reader.close();
        return words;
    }

}
