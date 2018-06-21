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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.irsystem.json.CustomFileShareResponse;
import com.org.irsystem.model.CSP;
import com.org.irsystem.model.FileShare;
import com.org.irsystem.service.FileShareService;

import net.sf.json.JSONObject;

/**
 * Handles CRUD requests for users
 * 
 */
@Controller
@RequestMapping("/sharefile")
public class FileShareController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name = "fileShareService")
	private FileShareService fileShareService ;
	
	@ResponseBody
	@RequestMapping(value = "/ajaxUpdate")
	public void getSearchResultViaAjax(@RequestBody String query,HttpServletRequest request) {

		try {
			System.out.println("query : "+query);
			System.out.println(request.getSession().getAttribute("userId"));
		    JSONObject json = JSONObject.fromObject(query);
			String string[] = json.get("query").toString().split(",");
			FileShare fileShare = new FileShare();
			fileShare.setLoginUserId(Long.valueOf(request.getSession().getAttribute("userId").toString()));
 			fileShare.setSharedFileUserId(Long.valueOf(string[0]));
			fileShare.setDocumentId(Long.valueOf(string[1]));
			fileShareService.add(fileShare);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxDelete")
	public void deleteSharedUser(@RequestBody String query,HttpServletRequest request) {

		try {
			System.out.println("query : "+query);
			System.out.println(request.getSession().getAttribute("userId"));
			
			
		    JSONObject json = JSONObject.fromObject(query);
			String string[] = json.get("query").toString().split(",");
			Long sharedFileId = fileShareService.getSharedFileUserId(Long.valueOf(string[1]), Long.valueOf(string[0]), Long.valueOf(request.getSession().getAttribute("userId").toString()));
			System.out.println("shared file id : "+sharedFileId);
			fileShareService.delete(sharedFileId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/displayGroupShareFile", method = RequestMethod.GET)
    public String displayShareFile(HttpServletRequest request, Model model) {
        CSP csp = new CSP();
        System.out.println("userid: "+request.getParameter("userid"));
        System.out.println("documentId: "+request.getParameter("documentId"));
        
        model.addAttribute("userid",request.getParameter("userid"));
        model.addAttribute("documentId",request.getParameter("documentId"));
        model.addAttribute("csp",csp);
        
        return "user_group_sharefile";
    }
	
	/* Getting List of objects in Json format in Spring Restful Services */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody CustomFileShareResponse getUsers() {

		CustomFileShareResponse fileShareResponse = new CustomFileShareResponse();
		
		List<FileShare> fileShareList = null;
		try {
			
			fileShareList = fileShareService.getAll();

			fileShareResponse.setPage("1");
			fileShareResponse.setRecords(String.valueOf(fileShareList.size()));
			fileShareResponse.setRows(fileShareList);
			fileShareResponse.setTotal(String.valueOf(fileShareList.size()));
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileShareResponse;
	}
}