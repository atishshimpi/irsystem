package com.org.irsystem.dao;

import java.util.List;

import com.org.irsystem.model.FileShare;

public interface FileShareDao {
 	public void addShareFile(FileShare sf);
    public void updateShareFile(FileShare sf);
    public List<FileShare> listShareFiles();
    public FileShare getShareFileById(Long id);
    public void removeShareFile(Long id);
}
