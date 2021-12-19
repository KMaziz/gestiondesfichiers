package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(value = "FileModel")
public class FileModel {
	  private String fileName;
	    private String fileDownloadUri;
	    private String fileType;
	    private long size;
	    @DBRef
	    private SubCategory subCategory;
		public FileModel(String fileName, String fileDownloadUri, String fileType, long size, SubCategory subCategory) {
			this.fileName = fileName;
			this.fileDownloadUri = fileDownloadUri;
			this.fileType = fileType;
			this.size = size;
			this.subCategory = subCategory;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileDownloadUri() {
			return fileDownloadUri;
		}
		public void setFileDownloadUri(String fileDownloadUri) {
			this.fileDownloadUri = fileDownloadUri;
		}
		public String getFileType() {
			return fileType;
		}
		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
		public long getSize() {
			return size;
		}
		public void setSize(long size) {
			this.size = size;
		}
		public SubCategory getSubCategory() {
			return subCategory;
		}
		public void setSubCategory(SubCategory subCategory) {
			this.subCategory = subCategory;
		}



	    
	    
	    }