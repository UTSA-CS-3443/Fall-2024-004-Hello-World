package edu.utsa.cs3443.fall_2024_helloworld.History;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class HistoryManager {
    static String TAG = "HistoryManager";
    static HistoryManager _instance;
    public static HistoryManager Instance(){
        if(_instance == null){
            _instance = new HistoryManager();
        }
        return _instance;
    }

    private ArrayList<HistoryItem> historyItems = new ArrayList<>();

    public ArrayList<HistoryItem> getHistoryItems() {
        return historyItems;
    }
    public void addHistoryItem(HistoryItem item){
        historyItems.add(item);
    }

    public void Load(File dataDir){
        File historyFile = new File(dataDir,"history.bin");
        try {
            DataInputStream reader = new DataInputStream(new FileInputStream(historyFile));

            int count = reader.readInt();
            ArrayList<HistoryItem> items = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                //TODO: replace string with index, but I need a calculator list for that
                String name = reader.readUTF();
                double output = reader.readDouble();
                int inputCount = reader.readInt();
                String[] inputs = new String[inputCount];
                for (int j = 0; j < inputCount; j++) {
                    inputs[j] = reader.readUTF();
                }
                items.add(new HistoryItem(name,output,inputs));
            }
            //In case history is added before history activity is run
            items.addAll(historyItems);
            historyItems.clear();
            historyItems = items;
        } catch (FileNotFoundException e) {
            // More than likely never saved before, just leave it
            return;
        } catch (IOException e) {
            Log.e(TAG, "Load: ", e);
            throw new RuntimeException(e);
        }

    }
    public void Save(File dataDir){
        File historyFile = new File(dataDir,"history.bin");
        try{
            DataOutputStream writer = new DataOutputStream(Files.newOutputStream(historyFile.toPath()));
            writer.writeInt(historyItems.size());
            for (HistoryItem item:historyItems) {
                writer.writeUTF(item.calculatorName);
                writer.writeDouble(item.output);
                writer.writeInt(item.inputs.length);
                for (String s: item.inputs) {
                    writer.writeUTF(s);
                }
            }
        }catch (IOException e){
            Log.e(TAG, "Save: ", e);
            throw new RuntimeException(e);
        }
    }

}
