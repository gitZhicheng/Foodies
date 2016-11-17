package food.model;

public class CuisineTypes {
	protected int cuisineTypeId;
	protected String cuisineTypeName;
	protected int parentId;
	protected String parentIds;
	
	public CuisineTypes(int cuisineTypeId, String cuisineTypeName, int parentId, String parentIds) {
		super();
		this.cuisineTypeId = cuisineTypeId;
		this.cuisineTypeName = cuisineTypeName;
		this.parentId = parentId;
		this.parentIds = parentIds;
	}
	
	public CuisineTypes(int cuisineTypeId) {
		this.cuisineTypeId = cuisineTypeId;
	}

	public CuisineTypes(String cuisineTypeName, int parentId, String parentIds) {
		super();
		this.cuisineTypeName = cuisineTypeName;
		this.parentId = parentId;
		this.parentIds = parentIds;
	}

	public int getCuisineTypeId() {
		return cuisineTypeId;
	}

	public void setCuisineTypeId(int cuisineTypeId) {
		this.cuisineTypeId = cuisineTypeId;
	}

	public String getCuisineTypeName() {
		return cuisineTypeName;
	}

	public void setCuisineTypeName(String cuisineTypeName) {
		this.cuisineTypeName = cuisineTypeName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

}
