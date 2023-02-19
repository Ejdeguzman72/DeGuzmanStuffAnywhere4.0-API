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

import com.deguzman.DeGuzmanStuffAnywhere.file_upload_jpa_dao.MedicalTrxUploadDao;
import com.deguzman.DeGuzmanStuffAnywhere.file_upload_jpa_models.MedicalTrxFile;
import com.deguzman.DeGuzmanStuffAnywhere.util.AppConstants;

@Service
public class MedicalTrxFileStorageService {

	@Autowired
	private MedicalTrxUploadDao medicalTrxDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MedicalTrxFileStorageService.class);
	
	public MedicalTrxFile store(MultipartFile file) throws IOException {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String fileExt = FileNameUtils.getExtension(filename);
		MedicalTrxFile medicalTrxFile = new MedicalTrxFile();
		
		if (fileExt.equals(AppConstants.JPEG) || 
				fileExt.equals(AppConstants.JPG) ||
				fileExt.equals(AppConstants.PDF)) {
			
			try {
				File uploadFile = new File(filename);
				
				String path = "./uploads/medical-transactions/" + uploadFile;
				
				Path targetPath = Paths.get(path);
				
				if (Files.exists(targetPath)) {
					File medicalTrxUploadDir = new File("/uploads/medical-transactions");
					
					medicalTrxUploadDir.mkdirs();
				}
				
				InputStream inputStreamFile = file.getInputStream();
				
				Files.copy(inputStreamFile, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
				
				medicalTrxFile.setFilename(filename);
				medicalTrxFile.setType(file.getContentType());
				medicalTrxFile.setData(file.getBytes());
				
				LOGGER.info("Uploaded file: " + filename);
			} catch (Exception e) {
				LOGGER.error("Error in handling upload: " + e.toString());
			}
			
		}
		
		return medicalTrxDao.save(medicalTrxFile);
	}
	
	public MedicalTrxFile getFile(String fileId) {
		
		LOGGER.info("Retrieved file: " + fileId);
		
		return medicalTrxDao.findById(fileId).get();
	}
	
	public Stream<MedicalTrxFile> getAllFiles() {
		
		LOGGER.info("Retrieving all Files...");
		
		return medicalTrxDao.findAll().stream();
	}
}
