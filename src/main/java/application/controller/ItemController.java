package application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.bean.ItemBean;
import application.service.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/item")
@Api(description = "Service for items")
public class ItemController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;

	@ApiOperation(value = "Getting All Items")
	@RequestMapping(method = RequestMethod.GET)
	public List<ItemBean> getItems() {
		LOGGER.debug("Getting Items");
		return itemService.getItems();
	}

	@ApiOperation(value = "Creating an Item")
	@RequestMapping(method = RequestMethod.POST)
	public String save(@RequestBody ItemBean itemBean) {
		itemService.save(itemBean);
		return "Item Saved";
	}
	
	@ApiOperation(value = "Get Item by id")
	@RequestMapping(path = "/{itemId}" , method = RequestMethod.GET)
	public ItemBean getItem(@PathVariable String itemId) {
		LOGGER.debug("Get request {}", itemId);
		return itemService.getItem(itemId);
	}
	
	@ApiOperation(value = "Delete Item by id")
	@RequestMapping(path = "/{itemId}" , method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable String itemId) {
		LOGGER.debug("deleting Item {}", itemId);
		 itemService.deleteItem(itemId);
	}
}
