package za.co.bakery.model;

import java.util.List;
import java.util.Objects;

public class Recipe {

    private int recipeId;
    private String recipeName;
    private String description;
    private int ingredientId;
    private int ingredientQuantity;
    private boolean isActive;

    public Recipe() {
    }

    public Recipe(int recipeId, String recipeName, String description, boolean isActive) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.description = description;
        this.isActive = isActive;
    }
    public Recipe(int recipeId, String recipeName, String description) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.description = description;
        
    }

    public Recipe(String recipeName, String description, boolean isActive) {
        this.recipeName = recipeName;
        this.description = description;
        this.isActive = isActive;
    }

    public Recipe(String recipeName, String description) {
        this.recipeName = recipeName;
        this.description = description;
        this.isActive = isActive;
    }

    public Recipe(int recipeId, boolean isActive) {
        this.recipeId = recipeId;
        this.isActive = isActive;
    }

    public Recipe(int recipeId, int ingredientId, int ingredientQuantity) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientQuantity = ingredientQuantity;
    }
     

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.recipeId;
        hash = 59 * hash + Objects.hashCode(this.recipeName);
        hash = 59 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recipe other = (Recipe) obj;
        if (this.recipeId != other.recipeId) {
            return false;
        }
        if (!Objects.equals(this.recipeName, other.recipeName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Recipe{" + "recipeId=" + recipeId + ", recipeName=" + recipeName + ", description=" + description + '}';
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getQuantity() {
        return ingredientQuantity;
    }

    public void setQuantity(int quantity) {
        this.ingredientQuantity = quantity;
    }

}
