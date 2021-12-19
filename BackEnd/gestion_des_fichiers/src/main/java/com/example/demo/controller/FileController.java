package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Category;
import com.example.demo.models.FileModel;
import com.example.demo.models.SubCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.FileModelRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FileStorageService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
public class FileController {

    @Autowired
	private CategoryRepository categoryRepositry;
	
    @Autowired
   	private SubCategoryRepository subCategoryRepositry;
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private FileModelRepository fileModelRepository;
   	
	  @GetMapping("/category")
	  public  ResponseEntity<List<Category>> getAllCategories() {
		        return new ResponseEntity<>( categoryRepositry.findAll(), HttpStatus.OK);
	    
	  }

	  @GetMapping("/subcategory")
	  public  ResponseEntity<List<SubCategory>> getAllSubCategories() {
		        return new ResponseEntity<>( subCategoryRepositry.findAll(), HttpStatus.OK);
	    
	  }
	  @GetMapping("/files")
	  public  ResponseEntity<List<FileModel>> getAllFile() {
		        return new ResponseEntity<>( fileModelRepository.findAll(), HttpStatus.OK);
	    
	  }
	  

	  
	  @GetMapping("/subcategory/{id}")
	    public ResponseEntity<?> findSubCategoryById(@PathVariable String id) {
		  SubCategory subC = subCategoryRepositry.findById(id).get();
	        return ResponseEntity.ok().body(subC);
	    }
	 
	  @GetMapping("/category/{id}")
	    public ResponseEntity<?> findCategoryById(@PathVariable String id) {
		  Category c = categoryRepositry.findById(id).get();
	        return ResponseEntity.ok().body(c);
	    }
	 
	  @PostMapping("/addacategory")
	    public ResponseEntity<?> saveCategory(@RequestBody Category c) {
		  Category savedC = categoryRepositry.save(c);
		  System.out.println(c.getName());
	        return ResponseEntity.ok().body(savedC);
	    }
	  @PostMapping("/addsubcategory")
	    public ResponseEntity<?> saveSubCategory(@RequestBody SubCategory subc) {
		  SubCategory savedsubc = subCategoryRepositry.save(subc);
	        return ResponseEntity.ok().body(savedsubc);
	    }
	  
	  @PostMapping("/subcategorybycategoy")
	    public List<SubCategory> findSubCategoryByCategory(@RequestBody Category c) {
		        return categoryService.getSubCategoryByCategory(c);
	    }
	  
	  

	    @Autowired
	    private FileStorageService fileStorageService;
	    
	    @PostMapping("/uploadFile/{id}")
	    public FileModel uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String id) {
	        SubCategory subCategory = subCategoryRepositry.findById(id).get();
	    	
	    	String fileName = fileStorageService.storeFile(file);

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/api/downloadFile/")
	                .path(fileName)
	                .toUriString();
	        
	        FileModel fileModel = new FileModel(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize(),subCategory);
	        
	        return fileModelRepository.save(fileModel);
	        
	        
	    }

	 

	    @GetMapping("/downloadFile/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
	}



