package com.org.irsystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.org.irsystem.model.UserDocument;
import com.org.irsystem.service.UserDocumentService;

@Controller
public class WebController {

	@Autowired
	UserDocumentService userDocumentService;
	
	
  /* @RequestMapping(value = "*", method = RequestMethod.GET)
   public String index() {
	   return "index";
   }*/
	
	@RequestMapping(value = "*", method = RequestMethod.GET)
	public ModelAndView index() {
		List<UserDocument> userDocuments = userDocumentService.findAll();
	    return new ModelAndView("index", "userDocuments", userDocuments);	
	}
   
   @RequestMapping(value = "/home", method = RequestMethod.GET)
   public ModelAndView home() {
	   List<UserDocument> userDocuments = userDocumentService.findAll();
       return new ModelAndView("index", "userDocuments", userDocuments);
   }
   
   @RequestMapping(value = "/staticPage", method = RequestMethod.GET)
   public String redirect() {
     
      return "redirect:/pages/final.htm";
   }
   
   @ExceptionHandler(HttpSessionRequiredException.class)
   @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason="The session has expired")
   public String handleSessionExpired(){		
     return "sessionExpired";
   }
}