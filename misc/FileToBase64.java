package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class FileToBase64 {
	public String encodeFileToBase64Binary(File file){
	    String encodedfile = null;
	    try {
	        FileInputStream fileInputStreamReader = new FileInputStream(file);
	        byte[] bytes = new byte[(int)file.length()];
	        fileInputStreamReader.read(bytes);
	        encodedfile = Base64.encodeBase64(bytes).toString();
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	     
	    return encodedfile;
	}
}
