package com.marketplace.resource;

 
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marketplace.service.impl.StorageService;

@RestController
@RequestMapping("/apiUpload")
public class UploadController {
@Autowired
StorageService storageService;


List<String> files = new ArrayList<String>();



@PostMapping("/post/{idArticle}")
public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,@PathVariable("idArticle") int idArticle) {
	String message = "";
	try {
		storageService.store(file,idArticle);
		

		message = "You successfully uploaded " + file.getOriginalFilename() + "!";
		return ResponseEntity.status(HttpStatus.OK).body(message);
	} catch (Exception e) {
		message = "FAIL to upload " + file.getOriginalFilename() + "!";
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	}
}


 


@GetMapping("/files/{membreId}")
@ResponseBody
public String getFile(@PathVariable("membreId") int membreId) throws MalformedURLException {
	byte[] file = storageService.loadFile(membreId);
	String encodedFile = Base64.getEncoder().encodeToString(file);
	return encodedFile ;
}

}
