package utils;

import java.io.File;

public class FileUtilities {

	public static boolean validFilename(String fileName) {
		return fileName.lastIndexOf('.') != -1;
	}

	public static File fixFilePath(File file) { // If file has no extension, automatically add the .jpg at the end
		String fixedFilePath = file.getAbsolutePath() + ".jpg";
        return new File(fixedFilePath);
	}
	
}
