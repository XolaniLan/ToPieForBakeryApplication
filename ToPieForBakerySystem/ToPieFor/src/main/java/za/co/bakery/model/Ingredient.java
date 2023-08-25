package za.co.bakery.model;

import java.util.Objects;

public class Ingredient {

    private int ingredientId;
    private int unitId;
    private int minimumStockLevel;
    private int quantityInStock;
    private String name;
    private boolean isActive;

    public Ingredient() {
    }

    public Ingredient(int ingredientId, int unitId, int quantityInStock, String name, int minimumStockLevel, boolean isActive) {
        this.ingredientId = ingredientId;
        this.unitId = unitId;
        this.minimumStockLevel = minimumStockLevel;
        this.quantityInStock = quantityInStock;
        this.name = name;
        this.isActive = isActive;
    }

    public Ingredient(int unitId, int minimumStockLevel, int quantityInStock, String name) {
        this.unitId = unitId;
        this.minimumStockLevel = minimumStockLevel;
        this.quantityInStock = quantityInStock;
        this.name = name;
    }
    
      public Ingredient(int ingredientId, int quantityInStock) {
        this.ingredientId = ingredientId;
         this.quantityInStock = quantityInStock;
    }

    public Ingredient(boolean isActive, int ingredientId) {
        this.ingredientId = ingredientId;
        this.isActive = isActive;
    }

    public Ingredient(int quantityInStock, String name, int minimumStockLevel) {
        this.minimumStockLevel = minimumStockLevel;
        this.quantityInStock = quantityInStock;
        this.name = name;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public void setMinimumStockLevel(int minimumStockLevel) {
        this.minimumStockLevel = minimumStockLevel;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.ingredientId;
        hash = 53 * hash + this.unitId;
        hash = 53 * hash + Objects.hashCode(this.name);
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
        final Ingredient other = (Ingredient) obj;
        if (this.ingredientId != other.ingredientId) {
            return false;
        }
        if (this.unitId != other.unitId) {
            return false;
        }
        if (this.minimumStockLevel != other.minimumStockLevel) {
            return false;
        }
        if (!Objects.equals(this.quantityInStock, other.quantityInStock)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "ingredientId=" + ingredientId + ", unitId=" + unitId + ", minimumStockLevel=" + minimumStockLevel + ", quantityInStock=" + quantityInStock + ", name=" + name + '}';
    }

}
