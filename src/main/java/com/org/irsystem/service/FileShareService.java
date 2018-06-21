package com.org.irsystem.service;

import java.util.List;

import com.org.irsystem.model.FileShare;

public interface FileShareService {

	/**
	 * Retrieves all Tpas
	 * 
	 * @return list of Tpas
	 */
	public List<FileShare> getAll();

	/**
	 * Retrieves a single Tpa based on id
	 * 
	 * @param id the id of the Tpa
	 * @return the Tpa
	 */
	public FileShare get(long id);

	/**
	 * Add a new CSP
	 * 
	 * @param Tpa the new CSP
	 * @return true if successful
	 */
	public Boolean add(FileShare sf);

	/**
	 * Delete an existing Tpa
	 * 
	 * @param TPA the existing Tpa
	 * @return true if successful
	 */
	public Boolean delete(long id);

	/**
	 * Edit an existing CSP
	 * 
	 * @param TPA the existing CSP
	 * @return true if successful
	 */
	public Boolean edit(FileShare sf);

	public Long getSharedFileUserId(Long documentId, Long sharedFileUserId, Long loginUserId);
}