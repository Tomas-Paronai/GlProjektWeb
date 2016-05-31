package com.emporganizer.dao.items;

import java.util.List;

import com.emporganizer.dao.RootDAO;
import com.emporganizer.models.items.DBItem;

public interface ItemDAO extends RootDAO{
	public List<DBItem> getItems(String table);
	public DBItem getItem(String table, int id);
	public DBItem getLastItem(String table);
	public void insertItem(String value, String table);
	public void updateItem(int id, String value, String table);
	public void deleteItem(int id, String table);
}
