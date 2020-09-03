package application.service;

import java.util.List;

import application.bean.ItemBean;

public interface ItemService {
	List<ItemBean> getItems();
	void save(ItemBean itemBean);
	ItemBean getItem(String itemId);
	void deleteItem(String itemId);
}
