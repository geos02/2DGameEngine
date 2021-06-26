package gfx;


import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;



public class SpriteLibrary {

	
	public static Map<String,SpriteSet> spriteSets; // dave & matt
	public static Map<String, Image> images;

	public SpriteLibrary() {
		spriteSets = new HashMap<>();
		images = new HashMap<>();
		loadSpritesFromDisk();
	}

	private void loadSpritesFromDisk() {
		loadSpriteSets("res/sprites/units");
		loadImages("res/sprites/tiles");
		loadImages("res/sprites/effects");
	}
	
	private void loadImages(String path) {
		
		String[] imagesInFolder = getImagesInFolder(path);
		
		for(String fileName : imagesInFolder) {
			
			images.put(fileName.substring(0,fileName.length() - 4),
					  ImageUtils.loadImage(path.substring(3) + "/" + fileName));
		}
	}
	
	private void loadSpriteSets(String path) {
		String[] folderNames = getFolderNames(path);
		for(String folderName : folderNames) {
			
			SpriteSet spriteSet = new SpriteSet();
			String pathToFolder = path + "/" + folderName;
			String[] sheetsInFolder = getImagesInFolder(pathToFolder);
			
			for(String sheetName : sheetsInFolder) {
				//System.out.println(pathToFolder.substring(3) + "/" + sheetName);
				spriteSet.addSheet(sheetName.substring(0, sheetName.length() - 4),
								   ImageUtils.loadImage(pathToFolder.substring(3) + "/" + sheetName));
			}
			
			spriteSets.put(folderName, spriteSet);
		}
	}
	
	private String[] getFolderNames(String basePath) {
		
		File file = new File(basePath);	
		return file.list((current,name) -> new File(current, name).isDirectory() );
	}

	private String[] getImagesInFolder(String path) {
		File file = new File(path);	
		return file.list((current,name) -> new File(current, name).isFile() );
	}

	public SpriteSet getSpriteSet(String name) {
		return spriteSets.get(name);
	}
	
	public Image getImage(String name) {
		return images.get(name);
	}
}
