package edu.utsa.cs3443.fall_2024_helloworld.History;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import edu.utsa.cs3443.fall_2024_helloworld.Model.Calculation;

public class HistoryManager {
    static String TAG = "HistoryManager";
    static HistoryManager _instance;
    private ArrayList<Calculation> historyItems;
    public static HistoryManager Instance(){
        if(_instance == null){
            _instance = new HistoryManager();
        }
        return _instance;
    }
    //DON'T CREATE A NEW CONSTRUCTOR THIS SHOULD BE A SINGLETON
    private HistoryManager(){
        this.historyItems = new ArrayList<>();
    }




    public ArrayList<Calculation> getHistoryItems() {
        return this.historyItems;
    }
    public void addHistoryItem(Calculation item){
        historyItems.add(item);
    }

    public void Load(File dataDir){
        File historyFile = new File(dataDir,"history.bin");
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(historyFile));

            int count = reader.readInt();
            ArrayList<Calculation> items = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                items.set(i,(Calculation) reader.readObject());
            }
            //In case history is added before history activity is run
            items.addAll(historyItems);
            historyItems.clear();
            historyItems = items;
        } catch (FileNotFoundException e) {
            // More than likely never saved before, just leave it
            return;
        } catch (IOException | ClassNotFoundException e) {
            Log.e(TAG, "Load: ", e);
            throw new RuntimeException(e);
        }

    }
    public void Save(File dataDir){
        File historyFile = new File(dataDir,"history.bin");
        try{
            ObjectOutputStream writer = new ObjectOutputStream(Files.newOutputStream(historyFile.toPath()));
            writer.writeInt(historyItems.size());
            for (Calculation item : historyItems) {
                writer.writeObject(item);
            }
        }catch (IOException e){
            Log.e(TAG, "Save: ", e);
            throw new RuntimeException(e);
        }
    }

}
