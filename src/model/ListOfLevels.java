package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.Level;

/**
 * This class holds the data for all the levels created in the game. It can save new data
 * to a given file, and load data.
 * @author sahsah1
 *
 */
public class ListOfLevels {
	
	ArrayList<Level> levels = new ArrayList<Level>();
	
	/**
	 * Empty constructor.
	 */
	public ListOfLevels(){
		
	}
	
	/**
	 * This method saves all current levels to a given file.
	 * @param fileName name of the file to save into.
	 * @throws IOException
	 */
	public void saveToJsonFile(String fileName) throws IOException{
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		
		for (Level level : levels){
			String jsonLine = gson.toJson(level)+"\n";
			fw.append(jsonLine);
		}
		fw.close();
	}
	
	/**
	 * This method loads all the saved levels from the given file
	 * @param fileName the file to load from.
	 * @throws FileNotFoundException
	 */
	public void loadFromFile (String fileName) throws FileNotFoundException{
		File f = new File (fileName);
		Scanner fileInput = new Scanner(f);
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		while (fileInput.hasNextLine()){
			String jsonLine = fileInput.nextLine();
			Level level = gson.fromJson(jsonLine, Level.class);
			levels.add(level);
		}
		fileInput.close();
	}
	
	public ArrayList<Level> getLevels(){
		return levels;
	}
	
	public void setLevels(ArrayList<Level> levels){
		this.levels = levels;
	}
}
