package Levels;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelLoader {

    Map<Integer, String[][]> levelList = new HashMap<>();
    Map<Integer,int[][]> exitsList = new HashMap<>();
    public LevelLoader(){

    }
    public void loadLevels(){
        String csvFilePath = "Levels.csv";
        URL urlPath = getClass().getClassLoader().getResource(csvFilePath);

        try{
            Reader reader = Files.newBufferedReader(Paths.get(urlPath.toURI()));
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .build();
            Iterable<CSVRecord> records = csvFormat.parse(reader);
            String[][] level = new String[0][0];
            int columnSize = 0;
            int currentRow = 0;
            int levelId = 0;
            for(CSVRecord record : records){
                if(record.get(0).equals("level")){
                    levelId = Integer.parseInt(record.get(1));
                    int width  = Integer.parseInt(record.get(2));
                    int height = Integer.parseInt(record.get(3));
                    level = new String[height][width];
                    columnSize = width;
                }
                else if(record.get(0).equals("end")){
                    currentRow = 0;
                    levelList.put(levelId,level);
                }
                else{
                    for(int i = 0; i < columnSize; i++){
                        level[currentRow][i] = record.get(i);
                    }
                    currentRow++;
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void exitsLoader(){
        String csvFilePath = "Exits.csv";
        URL urlPath = getClass().getClassLoader().getResource(csvFilePath);

        try{
            Reader reader = Files.newBufferedReader(Paths.get(urlPath.toURI()));
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .build();
            Iterable<CSVRecord> records = csvFormat.parse(reader);
            int[][] exits = new int[0][0];
            final int columnSize = 7;
            int currentRow = 0;
            int levelId = 0;
            for(CSVRecord record : records){
                if(record.get(0).equals("level")){
                    levelId = Integer.parseInt(record.get(1));
                    int exitsCount = Integer.parseInt(record.get(3));
                    exits = new int[exitsCount][columnSize];
                }
                else if(record.get(0).equals("end")){
                    currentRow = 0;
                    exitsList.put(levelId,exits);
                }
                else{
                    for(int i = 0; i < columnSize; i++){
                        exits[currentRow][i] = Integer.parseInt(record.get(i));
                    }
                    currentRow++;
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public Map<Integer,String[][]> getLevelList(){
        return levelList;
    }
    public Map<Integer,int[][]> getExistsList(){
        return exitsList;
    }

}
