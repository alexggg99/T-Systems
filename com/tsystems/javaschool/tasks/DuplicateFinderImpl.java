package com.tsystems.javaschool.tasks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author alexey
 * Данный класс реализует интерфейс DuplicateFinder
 * получает на входе два файла
 * в результат записывает отсортированные по алфавиту неповторяющиеся строки исходного файла
 * метод возвращает false, в случае возникновения ошибок
 */


public class DuplicateFinderImpl implements DuplicateFinder {

    private TreeMap<String, Integer> map;

    public DuplicateFinderImpl() {
        map = new TreeMap<>();
    }

    @Override
    public boolean process(File sourceFile, File targetFile) {
        //clean map befor processing
        map.clear();
        BufferedReader bufReader = null;
        FileReader reader = null;
        boolean isProcessOk = false;
        try {
            reader = new FileReader(sourceFile);
            bufReader = new BufferedReader(reader);
            isProcessOk = makeProcessing(bufReader);
            isProcessOk = saveToFile(targetFile);
        } catch (IOException ex) {
            //if file does not exist
            return false;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return isProcessOk;
    }

    //this class fills in treemap
    private boolean makeProcessing(BufferedReader bufReader){
        String line = "";
        Integer countLines = new Integer(0);
        try {
            while((line = bufReader.readLine()) != null){
                //escape white spaces
                if(line.isEmpty())
                    continue;
                //filter  duplacates
                if(map.containsKey(line)){
                    countLines = map.get(line);
                    countLines = map.put(line, countLines+1);
                }else{
                    Integer put = map.put(line, 1);
                }
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    //output file
    private boolean saveToFile(File targetFile){
        BufferedWriter bufWriter = null;
        FileWriter writer = null;
        try {
            //append content to File if file exists
            writer = new FileWriter(targetFile,true);
            bufWriter = new BufferedWriter(writer);
            for(Map.Entry<String, Integer> keys:map.entrySet()){
                writer.write(keys.getKey()+" ["+keys.getValue()+"]");
                writer.write("\n");
            }    
        } catch (IOException ex) {
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return true;
    }
}
