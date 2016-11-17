package food.model;

public class Ingredients {
	protected int ingredientId;
	protected String ingredientName;
	
	public Ingredients(int ingredientId, String ingredientName) {
		super();
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
	}
	
	public Ingredients(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public Ingredients(String ingredientName) {
		super();
		this.ingredientName = ingredientName;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

}
