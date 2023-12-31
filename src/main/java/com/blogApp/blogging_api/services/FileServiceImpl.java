package com.blogApp.blogging_api.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		//File Name
		String name = file.getOriginalFilename();
		
		//Random Name Generate File
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//Full Path
		String filePath = path+File.separator+fileName1;
		filePath = filePath.trim().replace("\\", "");
		
		//Create folder if not created
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName1;
	}

	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String fullPath = path+File.separator+fileName;
		fullPath = fullPath.trim().replace("\\", "");

		 InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}
