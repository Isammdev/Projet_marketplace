package com.marketplace.service.impl;

 
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marketplace.domain.Article;
import com.marketplace.service.ArticleService;

 
@Service
public class StorageService {
	@Autowired
	private ArticleService aa;
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	 
	public void store(MultipartFile file,int idArticle) {
		try {
			Article a= aa.findById(idArticle);
			a.setPhotoArticle(file.getBytes()); 
			
			aa.save(a);
		
			
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
	 
	public byte[] loadFile(int idArticle) throws MalformedURLException {
		Article m=aa.findById(idArticle);
			byte[] photo=m.getPhotoArticle() ;
			
				return photo;
	}
	 
 
		
	public void deleteAll() {
	
	}

	public void init() {
		
	}
}
