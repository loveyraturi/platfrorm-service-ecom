package com.datafroth.service;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

public interface FilesStorageService {
	public void init();

	public void save(MultipartFile file);

	public void saveToS3(MultipartFile file, String path, String fileName,
			Optional<Map<String, String>> optionalMetaData);

	public Resource load(String filename);

	public void deleteAll();

	public Stream<Path> loadAll();
}