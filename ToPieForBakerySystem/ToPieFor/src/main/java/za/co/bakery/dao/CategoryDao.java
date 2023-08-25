package za.co.bakery.dao;

import java.util.List;
import za.co.bakery.model.Category;

public interface CategoryDao {

    public List<Category> getAllCategories();

    public List<Category> getAllActiveCategories();

    public Category getCategoryById(int categoryId);

    public boolean addCategory(Category category);

    public boolean editCategory(Category category);

    public boolean activateCategory(Category category);
}
