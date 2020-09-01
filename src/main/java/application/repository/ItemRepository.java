package application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import application.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {
	
	//List<Item> findAll();
}
