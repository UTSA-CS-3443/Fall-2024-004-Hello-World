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

/***
 * Singleton class to manage the history of calculations
 *
 * @author Cole Frankland soc206
 */
public class HistoryManager {
    public static final int MAX_SAVED_HISTORY = 8;
    public static final String FILE_NAME = "history.bin";
    static String TAG = "HistoryManager";
    static HistoryManager _instance;
    private ArrayList<Calculation> historyItems;
    /***
     * Singleton instance of the HistoryManager
     * @return the instance of the HistoryManager
     */
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


    private boolean isLoaded = false;
    /***
     * Get the history items
     * @return the history items
     */
    public ArrayList<Calculation> getHistoryItems() {
        return this.historyItems;
    }
    /***
     * Add a history item to the history
     * @param item the history item to add
     */
    public void addHistoryItem(Calculation item){
        historyItems.add(item);
    }
    /***
     * Load the history from the data directory
     */
    public void Load(File dataDir){
        if(isLoaded)
            return;
        isLoaded = true;
        File historyFile = new File(dataDir, FILE_NAME);
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(historyFile));

            int count = reader.readInt();
            ArrayList<Calculation> items = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                items.add((Calculation) reader.readObject());
            }
            //In case history is added before history activity is run
            items.addAll(historyItems);
            historyItems.clear();
            historyItems = items;
        } catch (FileNotFoundException e) {
            // More than likely never saved before, just leave it
            return;
        } catch (IOException | ClassNotFoundException e) {
            Log.e(TAG, "Load: discarding", e);
            //noinspection ResultOfMethodCallIgnored
            historyFile.delete();

        }

    }
    /***
     * Save the history to the data directory
     */
    public void Save(File dataDir){
        File historyFile = new File(dataDir,FILE_NAME);
        try{
            ObjectOutputStream writer = new ObjectOutputStream(Files.newOutputStream(historyFile.toPath()));
            int count = Math.min(historyItems.size(), MAX_SAVED_HISTORY);
            writer.writeInt(count);
            for (int i = 0; i < count; i++) {
                writer.writeObject(historyItems.get(i));
            }
        }catch (IOException e){
            Log.e(TAG, "Save: ", e);
            throw new RuntimeException(e);
        }
    }

}
