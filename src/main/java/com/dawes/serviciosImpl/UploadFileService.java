package com.dawes.serviciosImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileService.class);
	private String folder = "images/";
	
	public String saveImage(MultipartFile file) throws IOException  {
		if (!file.isEmpty()) {
			// en bytes - para que pueda enviarse desde el cliente al servidor
			byte [] bytes = file.getBytes();
			Path path = Paths.get(folder+file.getOriginalFilename());
			Files.write(path, bytes);
			 logger.info("Saved image: " + path.toString());
			return file.getOriginalFilename();
		}
		return "default.jpg";
	}
	
	public void deleteImage(String nombre) {
		String ruta = "images//";
		File file = new File(ruta+nombre);
//		file.delete();
		 boolean deleted = file.delete();
	        if (deleted) {
	            logger.info("Deleted image: " + ruta + nombre);
	        } else {
	            logger.warn("Failed to delete image: " + ruta + nombre);
	        }
	}

}
