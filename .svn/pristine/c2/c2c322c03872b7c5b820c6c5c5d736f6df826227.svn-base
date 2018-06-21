package com.org.irsystem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.irsystem.dao.FileShareDao;
import com.org.irsystem.model.FileShare;

@Service(value="fileShareService")
public class FileShareServiceImpl implements FileShareService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	FileShareDao fileShareDao;
	
	@Override
	public List<FileShare> getAll() {
		logger.debug("Retrieving all users");

		List<FileShare> csp = fileShareDao.listShareFiles();
		return csp;
	}

	@Override
	public FileShare get(long id) {
		logger.debug("Retrieving an existing Tpa");
		
		return  fileShareDao.getShareFileById(Long.valueOf(id));
		
	}

	@Override
	public Boolean add(FileShare sf) {
		logger.debug("Adding a new Tpa");
		
		try {
			fileShareDao.addShareFile(sf);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean delete(long id) {
		logger.debug("Deleting an existing CSP");
		
		try {
			// Retrieve id to delete
			fileShareDao.removeShareFile(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Boolean edit(FileShare sf) {
		logger.debug("Editing an existing user");
		
		try {
			fileShareDao.updateShareFile(sf);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@Override
	public Long getSharedFileUserId(Long documentId,Long sharedFileUserId, Long loginUserId ){
		List<FileShare> fileShares = getAll();
		
		for(FileShare fileShare2 : fileShares){
			if(fileShare2.getDocumentId().equals(documentId) && fileShare2.getSharedFileUserId().equals(sharedFileUserId) && fileShare2.getLoginUserId().equals(loginUserId)){
				return fileShare2.getId();
			}
		}
		return null;
	}
}
