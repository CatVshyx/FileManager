package Manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;
import javax.swing.JOptionPane;
import java.nio.file.*;

public class FileManipulator  {
	public static void readFile(String path) throws IOException{
		File f =  new File(path);
		if (!f.exists()) {
			JOptionPane.showConfirmDialog(null, "File  doesnt exist", "Inform", JOptionPane.CLOSED_OPTION);
			return;
		}
		Desktop d = Desktop.getDesktop();
		d.open(f);
	}
	public static void write(String text, String path){
		try {
			FileWriter file = new FileWriter(path,true);
			file.write(text);
			file.close();
		}catch(IOException exp) {}
	}
	public static  void moveFile(String pathA, String pathB)  {
		System.out.println(pathA+" "+pathB);
		try {
			File oldFile = new File(pathA);
			StringBuilder nFilePath = new StringBuilder(pathB);
			nFilePath.append(File.separator);
			nFilePath.append("copied_" + oldFile.getName());
			File newFile = new File(nFilePath.toString());
			Files.copy(oldFile.toPath(), newFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
