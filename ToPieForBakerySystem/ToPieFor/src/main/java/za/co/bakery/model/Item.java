package za.co.bakery.model;

import java.util.Objects;

public class Item {

    private int itemId;
    private int categoryId;
    private int recipeId;
    private float price;
    private String itemName;
    private String description;
    private String nutritionalValue;
    private String warnings;
    private String imageUrl;
    private boolean isActive;

    public Item() {
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Item(int itemId, int categoryId, int recipeId, float price, String itemName, String description, String nutritionalValue, String warnings, boolean isActive) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.recipeId = recipeId;
        this.price = price;
        this.itemName = itemName;
        this.description = description;
        this.nutritionalValue = nutritionalValue;
        this.warnings = warnings;
        this.isActive = isActive;
    }

    public Item(int itemId, int categoryId, int recipeId, String itemName, float price, String description, String nutritionalValue, String warnings, String imageUrl) {

        this.itemId = itemId;
        this.categoryId = categoryId;
        this.recipeId = recipeId;
        this.price = price;
        this.itemName = itemName;
        this.description = description;
        this.nutritionalValue = nutritionalValue;
        this.warnings = warnings;
        this.imageUrl = imageUrl;

    }

    public Item(int itemId, float price, String itemName, String description, String nutritionalValue, String warnings) {

        this.price = price;
        this.itemName = itemName;
        this.description = description;
        this.nutritionalValue = nutritionalValue;
        this.warnings = warnings;
    }

    public Item(int categoryId, int recipeId, float price, String itemName, String description, String nutritionalValue, String warnings, boolean isActive) {
        this.categoryId = categoryId;
        this.recipeId = recipeId;
        this.price = price;
        this.itemName = itemName;
        this.description = description;
        this.nutritionalValue = nutritionalValue;
        this.warnings = warnings;
        this.isActive = isActive;
    }

    public Item(int categoryId, int recipeId, float price, String itemName, String description, String nutritionalValue, String warnings, String imageUrl) {
        this.categoryId = categoryId;
        this.recipeId = recipeId;
        this.price = price;
        this.itemName = itemName;
        this.description = description;
        this.nutritionalValue = nutritionalValue;
        this.warnings = warnings;
        this.imageUrl = imageUrl;
    }

    public Item(int categoryId, float price, String itemName, String description, String nutritionalValue, String warnings, boolean isActive) {
        this.categoryId = categoryId;
        this.price = price;
        this.itemName = itemName;
        this.description = description;
        this.nutritionalValue = nutritionalValue;
        this.warnings = warnings;
        this.isActive = isActive;
    }

    public Item(int itemId, int categoryId, int recipeId, float price, String itemName, String description, String nutritionalValue, String warnings, String imageUrl, boolean isActive) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.recipeId = recipeId;
        this.price = price;
        this.itemName = itemName;
        this.description = description;
        this.nutritionalValue = nutritionalValue;
        this.warnings = warnings;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }

    public Item(int itemId, boolean isActive) {
        this.itemId = itemId;
        this.isActive = isActive;
    }

    public Item(int itemId, int categoryId, Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(String nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.itemId;
        hash = 97 * hash + this.categoryId;
        hash = 97 * hash + this.recipeId;
        hash = 97 * hash + Float.floatToIntBits(this.price);
        hash = 97 * hash + Objects.hashCode(this.itemName);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.nutritionalValue);
        hash = 97 * hash + Objects.hashCode(this.warnings);
        hash = 97 * hash + Objects.hashCode(this.imageUrl);
        hash = 97 * hash + (this.isActive ? 1 : 0);
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
        final Item other = (Item) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (this.categoryId != other.categoryId) {
            return false;
        }
        if (this.recipeId != other.recipeId) {
            return false;
        }
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.nutritionalValue, other.nutritionalValue)) {
            return false;
        }
        if (!Objects.equals(this.warnings, other.warnings)) {
            return false;
        }
        if (!Objects.equals(this.imageUrl, other.imageUrl)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "itemId=" + itemId + ", categoryId=" + categoryId + ", recipeId=" + recipeId + ", price=" + price + ", itemName=" + itemName + ", description=" + description + ", nutritionalValue=" + nutritionalValue + ", warnings=" + warnings + ", imageUrl=" + imageUrl + ", isActive=" + isActive + '}';
    }

}
