package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.FileModel;
import com.example.demo.models.SubCategory;
@Repository

public interface FileModelRepository extends MongoRepository<FileModel, String>{

}
