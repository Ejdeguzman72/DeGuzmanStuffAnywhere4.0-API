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

import com.deguzman.DeGuzmanStuffAnywhere.file_upload_jpa_dao.AutoTrxUploadDao;
import com.deguzman.DeGuzmanStuffAnywhere.file_upload_jpa_models.AutoTrxFile;
import com.deguzman.DeGuzmanStuffAnywhere.util.AppConstants;

@Service
public class AutoTrxFileStorageService {

	@Autowired
	private AutoTrxUploadDao autoTrxDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutoTrxFileStorageService.class);
	
	public AutoTrxFile store(MultipartFile file) throws IOException {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String fileExt = FileNameUtils.getExtension(filename);
		AutoTrxFile autoTrxFile = null;
		
		if (fileExt.equals(AppConstants.JPEG) || 
				fileExt.equals(AppConstants.JPG) ||
				fileExt.equals(AppConstants.PDF)) {
			
			File uploadFile = new File(filename);
			String path = "./uploads/auto-transactions/" + uploadFile;
			
			Path targetPath = Paths.get(path);
			
			if (!Files.exists(targetPath)) {
				File autoTrxUploadDir = new File("./uploads/auto-transactions");
				
				autoTrxUploadDir.mkdirs();
			}
			
			InputStream inputStreamFile = file.getInputStream();
			
			Files.copy(inputStreamFile, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
			
			autoTrxFile = new AutoTrxFile(filename, path,file.getContentType(), file.getBytes());
			
			LOGGER.info("Uploaded file: " + filename);
		}
		
		return autoTrxDao.save(autoTrxFile);
	}
	
	public AutoTrxFile getFile(String fileId) {
		
		LOGGER.info("Retrieved file: " + fileId);
		
		return autoTrxDao.findById(fileId).get();
	}
	
	public Stream<AutoTrxFile> getAllFiles() {
		
		LOGGER.info("Retrieving all Files...");
		
		return autoTrxDao.findAll().stream();
	}
}
