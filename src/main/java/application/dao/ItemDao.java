package application.dao;

import java.util.List;

import application.model.Item;

public interface ItemDao {
	List<Item> getItems();
	void save(Item item);
}
