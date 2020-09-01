package application.model;

public class ItemPojo {
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemPojo() {
	}

	public ItemPojo(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
