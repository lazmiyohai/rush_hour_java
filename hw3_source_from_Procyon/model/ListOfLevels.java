// 
// Decompiled by Procyon v0.5.36
// 

package model;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.Iterator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.File;
import controller.Level;
import java.util.ArrayList;

public class ListOfLevels
{
    ArrayList<Level> levels;
    
    public ListOfLevels() {
        this.levels = new ArrayList<Level>();
    }
    
    public void saveToJsonFile(final String fileName) throws IOException {
        final File f = new File(fileName);
        final FileWriter fw = new FileWriter(f);
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        for (final Level level : this.levels) {
            final String jsonLine = String.valueOf(gson.toJson((Object)level)) + "\n";
            fw.append(jsonLine);
        }
        fw.close();
    }
    
    public void loadFromFile(final String fileName) throws FileNotFoundException {
        final File f = new File(fileName);
        final Scanner fileInput = new Scanner(f);
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        while (fileInput.hasNextLine()) {
            final String jsonLine = fileInput.nextLine();
            final Level level = (Level)gson.fromJson(jsonLine, (Class)Level.class);
            this.levels.add(level);
        }
        fileInput.close();
    }
    
    public ArrayList<Level> getLevels() {
        return this.levels;
    }
    
    public void setLevels(final ArrayList<Level> levels) {
        this.levels = levels;
    }
}
