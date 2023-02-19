package com.deguzman.DeGuzmanStuffAnywhere.file_upload_service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.apache.commons.compress.utils.FileNameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.deguzman.DeGuzmanStuffAnywhere.file_upload_jpa_dao.PhotoUploadDao;
import com.deguzman.DeGuzmanStuffAnywhere.file_upload_jpa_models.Photos;
import com.deguzman.DeGuzmanStuffAnywhere.util.AppConstants;

@Service
public class PhotosStorageService {

	@Autowired
	private PhotoUploadDao photoDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotosStorageService.class);
	
	public Photos store(MultipartFile file) throws IOException {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String fileExt = FileNameUtils.getExtension(filename);
		Photos photo = new Photos();
		
		if (fileExt.equals(AppConstants.JPEG) || 
				fileExt.equals(AppConstants.JPG) ||
				fileExt.equals(AppConstants.PDF)) {
			
			try {
				File uploadFile = new File(filename);
				String path = "./uploads" + "/photos/" + uploadFile;
				
				Path targetPath = Paths.get(path);
				
				if (!Files.exists(targetPath)) {
					File photoUploadsDirectory = new File("./uploads/photos");
					
					photoUploadsDirectory.mkdirs();
				}
				
				InputStream inputStreamFile = file.getInputStream();
				
				Files.copy(inputStreamFile, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
				
				photo.setFilename(filename);
				photo.setType(file.getContentType());
				photo.setData(file.getBytes());
				
				LOGGER.info("Uploaded file: " + filename);
			} catch (Exception e) {
				LOGGER.error("Error in handling upload: " + e.toString());
			}
		}
	
		return photoDao.save(photo);
	}
	
	public Photos getPhoto(String photoId) {
		
		LOGGER.info("Retrieved file: " + photoId);
		
		return photoDao.getById(photoId);
	}
	
	public Stream<Photos> getAllPhotos() {
		
		LOGGER.info("Retrieving all Files...");
		
		return photoDao.findAll().stream();
	}
}
