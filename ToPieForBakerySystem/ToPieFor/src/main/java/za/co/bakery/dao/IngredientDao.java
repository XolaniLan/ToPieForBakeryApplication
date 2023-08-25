package za.co.bakery.dao;

import java.util.List;
import za.co.bakery.model.Ingredient;

public interface IngredientDao {

    public List<Ingredient> getAllIngridients();

    public Ingredient getIngredientByName(String name);

    public boolean activateIngredient(Ingredient ingredient);

    public boolean addIngredient(Ingredient ingredient);
    
    public boolean addStock(Ingredient ingredient);
    
}
