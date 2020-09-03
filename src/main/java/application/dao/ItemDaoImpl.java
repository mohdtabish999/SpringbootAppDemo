package application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import application.model.Item;
import application.repository.ItemRepository;

@Repository
public class ItemDaoImpl implements ItemDao {
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<Item> getItems() {
	//	return Collections.(itemRepository.findAll());//findAll();
	return	Lists.newArrayList(itemRepository.findAll());
	}

	@Override
	public void save(Item item) {
		itemRepository.save(item);

	}
	
	@Override
	public Item getItem(String itemId) {
		Optional<Item> opItem = itemRepository.findById(Integer.parseInt(itemId));
		 return opItem.get();
	}
	
	@Override
	public void deleteItem(String itemId) {
		itemRepository.deleteById(Integer.parseInt(itemId));
	}
}
