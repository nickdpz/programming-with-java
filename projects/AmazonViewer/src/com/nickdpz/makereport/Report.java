package com.nickdpz.makereport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Report {

	private String nameFile;
	private String title;
	private String content;
	private String extension;

	
	/** 
	 * @return String
	 */
	public String getNameFile() {
		return nameFile;
	}

	
	/** 
	 * @param nameFile
	 */
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	
	/** 
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	
	/** 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	
	/** 
	 * @return String
	 */
	public String getContent() {
		return content;
	}

	
	/** 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * It creates a file with the name, title and content.
	 */
	public void makeReport() {
		if ((getNameFile() != null) && (getTitle() != null) && (getContent() != null)) {
			// Crear el archivo
			try {

				File file = new File(getNameFile() + "." + getExtension());
				FileOutputStream fos = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(getContent());
				bw.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("Ingresa los datos del archivo");
		}
	}

	
	/** 
	 * @return String
	 */
	public String getExtension() {
		return extension;
	}

	
	/** 
	 * @param extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

}
