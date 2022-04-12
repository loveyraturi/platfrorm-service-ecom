package com.datafroth.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	private final Path root = Paths.get("uploads");

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			Path filePath=this.root.resolve(file.getOriginalFilename().replace(" ", "").replace("(", "").replace(")", ""));
			System.out.println(filePath);
			System.out.println("#################3");
			Files.copy(file.getInputStream(), filePath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}
	@Override
	public void saveToS3(MultipartFile file, String path,
            String fileName,
            Optional<Map<String, String>> optionalMetaData) {
		 ObjectMetadata objectMetadata = new ObjectMetadata();
	        optionalMetaData.ifPresent(map -> {
	            if (!map.isEmpty()) {
	                map.forEach(objectMetadata::addUserMetadata);
	            }
	        });
	        try {
	            amazonS3.putObject(path, fileName, file.getInputStream(), objectMetadata);
	        } catch (AmazonServiceException e) {
	            throw new IllegalStateException("Failed to upload the file", e);
	        }
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
}