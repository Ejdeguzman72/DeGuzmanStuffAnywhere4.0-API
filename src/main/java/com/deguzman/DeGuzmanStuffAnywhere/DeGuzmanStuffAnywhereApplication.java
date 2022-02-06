package com.deguzman.DeGuzmanStuffAnywhere;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.deguzman.DeGuzmanStuffAnywhere.file_upload_service.AutoTransactionFilesStorageService;
import com.deguzman.DeGuzmanStuffAnywhere.file_upload_service.GeneralTransactionFileStorageService;
import com.deguzman.DeGuzmanStuffAnywhere.file_upload_service.MedicalTransactionFilesStorageService;
import com.deguzman.DeGuzmanStuffAnywhere.file_upload_service.PhotoFilesStorageService;
import com.deguzman.DeGuzmanStuffAnywhere.file_upload_service.VideoFilesStorageService;

@SpringBootApplication
public class DeGuzmanStuffAnywhereApplication {

	@Resource
	GeneralTransactionFileStorageService generalTrxfilesStorageService;

	@Resource
	MedicalTransactionFilesStorageService medicalTrxFilesStorageService;

	@Resource
	AutoTransactionFilesStorageService autoTrxFilesStorageService;

	@Resource
	PhotoFilesStorageService photosFilesStorageService;

	@Resource
	VideoFilesStorageService videosFilesStorageService;

	public static void main(String[] args) {
		SpringApplication.run(DeGuzmanStuffAnywhereApplication.class, args);
	}

}
