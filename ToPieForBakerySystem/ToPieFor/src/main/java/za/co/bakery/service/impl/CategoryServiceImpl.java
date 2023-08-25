package za.co.bakery.service.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.CategoryDao;
import za.co.bakery.model.Category;
import za.co.bakery.service.CategoryService;

public class CategoryServiceImpl implements CategoryService, ProcessRequest {

    private CategoryDao categoryDao;
    private String categoryView;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao == null ? null : categoryDao.getAllCategories();
    }

    @Override
    public List<Category> getAllActiveCategories() {
        return categoryDao == null ? null : categoryDao.getAllActiveCategories();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        if (categoryId < 0) {
            return null;
        }

        return categoryDao == null ? null : categoryDao.getCategoryById(categoryId);
    }

    @Override
    public boolean addCategory(Category category) {
        return category == null ? false : categoryDao.addCategory(category);
    }

    @Override
    public boolean editCategory(Category category) {
        return category == null ? false : categoryDao.editCategory(category);
    }

    @Override
    public boolean activateCategory(Category category) {
        return category == null ? false : categoryDao.activateCategory(category);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        
        int categoryId = 0;
        String name = "";
        String description = "";
        Boolean isActive = false;
        List<Category> categories = null;
        Category category = null;
        

        String action = request.getParameter("act");

        System.out.println(action);
        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {
                case "addcategory":

                    try{
                     name = request.getParameter("name");
                     description = request.getParameter("description");
                    }catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (name != null && !name.trim().isEmpty()) {
                        name = name.trim();
                    }

                    if (!name.isEmpty()) {
                        if (addCategory(new Category(name, description))) {
                            request.setAttribute("ans", "Category added.");
                        } else {
                            request.setAttribute("ans", "Category not added.");
                        }
                    }

                    categories = categoryDao.getAllCategories();

                    if (categories != null && !categories.isEmpty()) {

                        request.setAttribute("allcategories", categories);

                    } else {
                        request.setAttribute("ans", "No recipes, Yet!");
                    }
                    categoryView = "categoriesview.jsp";

                case "allcategories":
                    categories = categoryDao.getAllCategories();

                    if (categories != null && !categories.isEmpty()) {

                        request.setAttribute("allcategories", categories);

                    } else {
                        request.setAttribute("ans", "No recipes, Yet!");
                    }
                    categoryView = "categoriesview.jsp";

                    break;

                case "categorybyid":

                    try{
                    categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    category = categoryDao.getCategoryById(categoryId);}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (category != null) {
                        request.setAttribute("category", category);
                    } else {
                        request.setAttribute("ans", "category not found");
                    }

                    categoryView = "editCategories.jsp";

                    break;
                case "edit":
                    try{
                    name = request.getParameter("name");
                    description = request.getParameter("description");
                    }catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    try {
                        categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    } catch (NumberFormatException ex) {
                        System.out.println("Error id: " + ex.getMessage());
                    }

                    if (categoryId > 0) {
                        if (editCategory(new Category(categoryId, name, description))) {
                            request.setAttribute("updated", "Category updated");
                            System.out.println("pass");
                        } else {
                            request.setAttribute("ans", "category not updated");
                            System.out.println("fail");
                        }
                    }
                    categories = categoryDao.getAllCategories();

                    if (categories != null && !categories.isEmpty()) {

                        request.setAttribute("allcategories", categories);

                    } else {
                        request.setAttribute("ans", "No recipes, Yet!");
                    }
                    categoryView = "categoriesview.jsp";

                    break;

                case "activate":
                    try{
                    isActive = Boolean.parseBoolean(request.getParameter("isActive"));
                    }catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    try {
                        categoryId = Integer.parseInt(request.getParameter("categoryId"));
                        System.out.println(categoryId);
                    } catch (NumberFormatException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (categoryId > 0) {
                        System.out.println("in");
                        if (activateCategory(new Category(categoryId, isActive))) {
                            System.out.println("updated");
                            request.setAttribute("ans", "Category updated");
                        } else {
                            System.out.println("fail");
                            request.setAttribute("ans", "category not updated");
                        }
                    }
                    categories = categoryDao.getAllCategories();

                    if (categories != null && !categories.isEmpty()) {

                        request.setAttribute("allcategories", categories);

                    } else {
                        request.setAttribute("ans", "No recipes, Yet!");
                    }
                    categoryView = "categoriesview.jsp";
                    break;

            }
        }

    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(categoryView);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }

}
