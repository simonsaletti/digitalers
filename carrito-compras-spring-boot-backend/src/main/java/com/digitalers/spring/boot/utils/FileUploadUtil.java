package com.digitalers.spring.boot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static void guardarArchivo(MultipartFile multipartFile) throws IOException {
		if (multipartFile != null && !multipartFile.isEmpty()) {
			String rutaCarpetaImg = "C:\\Users\\Lab JAVA\\Desktop\\Archivos para clase EducacionIT"
					+ "\\Spring\\eclipse-workspace-spring-boot - 14 oct\\carrito-compras-spring-boot-backend"
					+ "\\src\\main\\resources\\static\\img";
			String fileName = multipartFile.getOriginalFilename();

			Path uploadPath = Paths.get(rutaCarpetaImg);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			try (InputStream inputStream = multipartFile.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				throw new IOException("Error al guardar el archivo " + fileName, e);
			}

		}
	}

}
