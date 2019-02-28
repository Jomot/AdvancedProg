import java.io.File;
import java.io.IOException;

public abstract class FileHandler {

	File fp;
	String source_name;
	String destination_name;
	int file_size;
	int is_directory;
	String file_type;
	
	public FileHandler() {}
	public FileHandler(String source_name) {
		// TODO Auto-generated constructor stub
		this.source_name = source_name;
		this.fp = new File(this.source_name);
	}
	public FileHandler(String source_file, String destination_file) {
		this.source_name = source_file;
		this.destination_name = destination_file;
		this.fp = new File(this.source_name);
	}
	
	
	public abstract void readFile() throws IOException;
	public abstract void writeFile(String file_name) throws IOException;

	
	/*
	 * Introduce getters and setters methods for the class variables
	 */
	
	public File getFp() {
		return fp;
	}
	public void setFp(File fp) {
		this.fp = fp;
	}
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public String getDestination_name() {
		return destination_name;
	}
	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public int getIs_directory() {
		return is_directory;
	}
	public void setIs_directory(int is_directory) {
		this.is_directory = is_directory;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	
	
}
