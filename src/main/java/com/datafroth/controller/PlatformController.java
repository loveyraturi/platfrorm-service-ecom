package com.datafroth.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.datafroth.service.FilesStorageService;
import com.datafroth.service.ResponseMessage;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PlatformController {
	@Autowired
	FilesStorageService storageService;

	@GetMapping("/test")
	public String test() {
		return "SUCCESS";
	}
	@PostMapping("/createProduct")
	public ResponseEntity<ResponseMessage> createProduct(@RequestBody Map<String,Object> createProductRequest){
		System.out.println(createProductRequest);
		createProductRequest.get("name");
		createProductRequest.get("description");
		createProductRequest.get("price");
		createProductRequest.get("user_id");
		createProductRequest.get("category_id");
		createProductRequest.get("size_id");
		createProductRequest.get("discount_id");
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Success"));
	}

	@PostMapping("/uploadImages")
	public ResponseEntity<ResponseMessage> uploadImages(
			@RequestParam("product_id") String producId,
			@RequestParam("images") MultipartFile[] files) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
		String message = "";
		try {
			List<String> fileNames = new ArrayList<String>();
			System.out.println(files.length);
			Arrays.asList(files).stream().forEach(file -> {
				System.out.println(file.getOriginalFilename().replace(" ", "").replace("(", "").replace(")", ""));
				storageService.save(file);
				System.out.println("@@@@@@@@@@@@@@@@@@@@############11");
				fileNames.add(file.getOriginalFilename());
				System.out.println("@@@@@@@@@@@@@@@@@@@@############");
			});
			message = "Uploaded the files successfully: " + fileNames;
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Fail to upload files!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
}
