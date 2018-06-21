package com.org.irsystem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.irsystem.model.UserDocument;
import com.org.irsystem.service.UserDocumentService;

@Controller
@RequestMapping("/myImage")
public class ImageController {

@Autowired
UserDocumentService userDocumentService;

@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
  public void showImage(@RequestParam("id") Long itemId, HttpServletResponse response,HttpServletRequest request) 
          throws ServletException, IOException{

	UserDocument document = userDocumentService.findById(itemId);

	if("image/jpeg".equalsIgnoreCase(document.getType())){
		response.setContentType("image/jpeg");
	    response.getOutputStream().write(document.getContent());	
	}else if("image/jpg".equalsIgnoreCase(document.getType())){
		response.setContentType("image/jpg");
	    response.getOutputStream().write(document.getContent());	
	}else if("image/png".equalsIgnoreCase(document.getType())){
		response.setContentType("image/png");
	    response.getOutputStream().write(document.getContent());	
	}else if("image/gif".equalsIgnoreCase(document.getType())){
		response.setContentType("image/gif");
	    response.getOutputStream().write(document.getContent());	
	}
    
    response.getOutputStream().close();
	}
}