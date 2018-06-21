/**
 * 
 */
package com.org.irsystem;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.org.irsystem.json.CustomUserResponse;
import com.org.irsystem.model.QueryText;
import com.org.irsystem.model.Status;
import com.org.irsystem.model.User;
import com.org.irsystem.model.UserDocument;
import com.org.irsystem.service.UserDocumentService;
import com.org.irsystem.service.UserService;

/**
 * Handles CRUD requests for users
 * 
 */
@Controller
public class UserController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name = "userService")
	private UserService userService;
	@Resource
	private UserDocumentService userDocumentService;
	
	@RequestMapping(value = "/user/displayLogin", method = RequestMethod.GET)
    public ModelAndView displayLoginUser() {
 
        User user = new User();
        user.setRole("user");
      
        return new ModelAndView("user_login", "user", user);
    }
	
	@RequestMapping(value = "/authority/displayLogin", method = RequestMethod.GET)
    public ModelAndView displayAuthorityLogin() {
 
        User user = new User();
        user.setRole("authority");
      
        return new ModelAndView("authority_login", "user", user);
    }
	
	@RequestMapping(value = "/user/displayRegistration", method = RequestMethod.GET)
    public ModelAndView displayRegistration() {
 
        User user = new User();
        user.setRole("user");
        return new ModelAndView("user_registration", "user", user);
    }
	
	@RequestMapping(value = "/authority/displayRegistration", method = RequestMethod.GET)
    public ModelAndView displayAutherityRegistration() {
 
        User user = new User();
        user.setRole("authority");
        return new ModelAndView("authority_registration", "user", user);
    }
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("userForm") User user,HttpServletRequest request, ModelMap model) {
		
		try {
			user =  userService.isValidUser(user);
	        
	        boolean flag = (null!=user)?true:false;
	        
	        if(flag){
	        	
	     	    List<UserDocument> userDocuments = userDocumentService.getRecommendedDocuments(user.getId());
	        	model.addAttribute("userDocuments", userDocuments);

	        	System.out.println("user.getEmail() : "+user.getId());
	            request.getSession().setAttribute("userId", user.getId());
	            request.getSession().setAttribute("user", user);

	            
	        	model.addAttribute("user", user);
	        	model.addAttribute("message","Login successful !");
	        	return "user_home";
	        }else{
	        	System.out.println("inside else");
	        	
	        	model.addAttribute("user", user);
	        	model.addAttribute("message","Login not successful !");
	        	return "user_login";
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
	@RequestMapping(value = "/user/view-all-products", method = RequestMethod.GET)
    public String displayAllProducts(HttpServletRequest request, ModelMap model) {
    	
 	    List<UserDocument> userDocuments = userDocumentService.findAll();
    	model.addAttribute("userDocuments", userDocuments);
    
    	return "user_home";

    }
	
	@RequestMapping(value = "/authority/login", method = RequestMethod.POST)
    public String loginAuthority(@ModelAttribute("userForm") User user,HttpServletRequest request, ModelMap model) {
		
		try {
			user =  userService.isValidUser(user);
	        
	        boolean flag = (null!=user)?true:false;
	        
	        if(flag){
	        	
	        	System.out.println("user.getEmail() : "+user.getId());
	            request.getSession().setAttribute("userId", user.getId());
	            
	        	System.out.println("inside if");
	        	model.addAttribute("user", user);
	        	model.addAttribute("message","Login successful !");
	        	return "authority_home";
	        }else{
	        	System.out.println("inside else");
	        	user = new User();
	        	model.addAttribute("user", user);
	        	model.addAttribute("message","Login not successful !");
	        	return "authority_login";
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
	
	@RequestMapping(value = "/user/displayRegistredUsers", method = RequestMethod.GET)
    public ModelAndView displayRegistredUsers() {
 
        User user = new User();
        user.setRole("user");
        return new ModelAndView("user_registred_users", "user", user);
    }
	
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model) {
		try {
			System.out.println("inside add User");
			user.setRole("user");
			userService.add(user);
			model.addAttribute("user", user);
			model.addAttribute("message", "User registred succesfully !");
	    } catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		
		return "user_registration";
	}
	
	@RequestMapping(value = "/authority/create", method = RequestMethod.POST)
	public String addAuthority(@ModelAttribute("user") User user, Model model) {
		try {
			System.out.println("inside add User");
			userService.add(user);
			model.addAttribute("user", user);
			model.addAttribute("message", "User registred succesfully !");
	    } catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		
		return "user_registration";
	}
	
	
	@RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("id") Long id, Model model) {
		User user = null;
		try {
			
			user = userService.findById(id);
			userService.edit(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new ModelAndView("user_edit", "user", user);
	}
	
	@RequestMapping(value = "/user/editProfile", method = RequestMethod.POST)
	public ModelAndView editUserById(@ModelAttribute("user") User user) {
		try {
			userService.edit(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new ModelAndView("user_edit", "user", user);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("id") long id, ModelMap model) {
		User user = null;
		try {
			user = userService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new ModelAndView("userjson", "userForm", user);
	}
	

	/* Getting List of objects in Json format in Spring Restful Services */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public @ResponseBody CustomUserResponse getUsers() {

		CustomUserResponse userResponse = new CustomUserResponse();
		
		List<User> userList = null;
		try {
			
			userList = userService.getAll();

			userResponse.setPage("1");
			userResponse.setRecords(String.valueOf(userList.size()));
			userResponse.setRows(userList);
			userResponse.setTotal(String.valueOf(userList.size()));
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userResponse;
	}
	
	
	
	/* Getting List of objects in Json format in Spring Restful Services */
	@RequestMapping(value = "/user/listOfUsersForSharingFile", method = RequestMethod.GET)
	public @ResponseBody CustomUserResponse getSharedFileUsers(HttpServletRequest request) {

		CustomUserResponse userResponse = new CustomUserResponse();
		
		
		User user = null;

		
		List<User> userList = null;
		try {
			int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString()); 
			user = userService.get(userId);
			userList = userService.getAll();
			userList.remove(user);
			System.out.println("User : "+user);
			System.out.println("User List : "+userList);
			userResponse.setPage("1");
			userResponse.setRecords(String.valueOf(userList.size()));
			userResponse.setRows(userList);
			userResponse.setTotal(String.valueOf(userList.size()));
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userResponse;
	}
	
	/* Delete an object from DB in Spring Restful Services */
	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteUser(@PathVariable("id") long id) {

		try {
			userService.delete(id);
			return new Status(1, "Employee deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}
}