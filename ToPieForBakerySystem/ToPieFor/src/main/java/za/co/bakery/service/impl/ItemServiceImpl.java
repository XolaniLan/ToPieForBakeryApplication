package za.co.bakery.service.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.bakery.controller.ProcessRequest;
import za.co.bakery.dao.CategoryDao;
import za.co.bakery.dao.ItemDao;
import za.co.bakery.dao.RecipeDao;
import za.co.bakery.db.manager.DBManager;
import za.co.bakery.model.Category;
import za.co.bakery.model.Item;
import za.co.bakery.model.Recipe;
import za.co.bakery.service.ItemService;

public class ItemServiceImpl implements ItemService, ProcessRequest {

    private ItemDao itemDao;
    private RecipeDao recipeDao;
    private CategoryDao categoryDao;
    private String itemView;

    public ItemServiceImpl(ItemDao itemDao, RecipeDao recipeDao, CategoryDao categoryDao) {
        this.itemDao = itemDao;
        this.recipeDao = recipeDao;
        this.categoryDao = categoryDao;
    }

    ItemServiceImpl(DBManager dBManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems() {

        return itemDao != null ? itemDao.getAllItems() : null;
    }

    @Override
    public List<Item> getAllActiveItems() {

        return itemDao != null ? itemDao.getAllActiveItems() : null;
    }

    @Override
    public List<Item> getAllItemsByCategory(int categoryId) {

        return categoryId > 0 ? itemDao.getAllItemsByCategory(categoryId) : null;
    }

    @Override
    public List<Item> getAllItemsByPrice(float price) {

        return price > 0 ? itemDao.getAllItemsByPrice(price) : null;
    }

    @Override
    public Item getItemById(int itemId) {

        if (itemId < 0) {
            return null;
        }

        return itemDao == null ? null : itemDao.getItemById(itemId);
    }
    
    @Override
    public Item getItemNameById(int itemId) {

        if (itemId < 0) {
            return null;
        }

        return itemDao == null ? null : itemDao.getItemById(itemId);
    }

    @Override
    public Item getItemByName(String itemName) {

        if (itemName == null && itemName.isEmpty()) {
            return null;
        }
        return itemDao != null ? itemDao.getItemByName(itemName) : null;

    }

    @Override
    public boolean addItem(Item item) {

        return itemDao != null ? itemDao.addItem(item) : false;
    }

    @Override
    public boolean editItem(Item item) {
        return itemDao != null ? itemDao.editItem(item) : false;
    }

    @Override
    public boolean activateItem(Item item) {
        return item == null ? false : itemDao.activateItem(item);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        float price = 0;
        String itemName = "";
        String description = "";
        String nutritionalValue = "";
        String warnings = "";       
        String imageUrl = "";
        Boolean isActive = false;

        int categoryId = 0;
        int itemId = 0;
        int recipeId = 0;

        Item item = null;
        List<Item> itemList = null;
        List<Recipe> recipeList = null;
        List<Category> categoryList = null;

        String action = request.getParameter("act");
        System.out.println(action);

        if (action != null) {
            action = action.toLowerCase().trim();

            switch (action) {

                case "toadd":
                    recipeList = recipeDao.getAllRecipes();
                    categoryList = categoryDao.getAllCategories();

                    request.setAttribute("allRecipes", recipeList);
                    request.setAttribute("allCategories", categoryList);
                    itemView = "addItem.jsp";
                    break;

                case "additem":
                    try{
                    price = Float.parseFloat(request.getParameter("price"));
                    itemName = request.getParameter("itemName");
                    description = request.getParameter("description");
                    nutritionalValue = request.getParameter("nutritionalValue");
                    warnings = request.getParameter("warnings");
                    isActive = Boolean.parseBoolean("isActive");
                    imageUrl = request.getParameter("imageUrl");}
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    if (itemName != null && !itemName.trim().isEmpty()) {
                        itemName = itemName.trim();
                    }

                    try {
                        categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    } catch (NumberFormatException nfe) {
                    }

                    try {
                        recipeId = Integer.parseInt(request.getParameter("recipeId"));
                    } catch (NumberFormatException nfe) {
                    }

                    if (categoryId > 0 && recipeId > 0) {
                        if (addItem(new Item(categoryId, recipeId, price, itemName, description, nutritionalValue, warnings, imageUrl))) {
                            request.setAttribute("ans", "Your item was added successfully.");
                        } else {
                            request.setAttribute("ans", "The item was not added.");
                        }
                    }
                    itemList = itemDao.getAllItems();
                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allItems", itemList);
                    } else {
                        request.setAttribute("ans", "No items ");
                    }
                    itemView = "viewItems.jsp";
                    break;

                case "viewitems":

                    itemList = itemDao.getAllItems();
                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allItems", itemList);
                    } else {
                        request.setAttribute("ans", "No items ");
                    }

                    itemView = "viewItems.jsp";

                    break;

                case "activate":

                    try{
                    isActive = Boolean.parseBoolean(request.getParameter("isActive"));
                    }catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }
                    try {
                        itemId = Integer.parseInt(request.getParameter("itemId"));
                    } catch (NumberFormatException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    if (itemId > 0) {
                        if (activateItem(new Item(itemId, isActive))) {
                            request.setAttribute("ans", "item activated");
                        } else {
                            request.setAttribute("ans", "item not activated");
                        }
                    }

                    itemList = itemDao.getAllItems();
                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allItems", itemList);
                    } else {
                        request.setAttribute("ans", "No items ");
                    }
                    itemView = "viewItems.jsp";
                    break;
                case "edit":
                    System.out.println("inside");
                    categoryId = 0;
                    try {
                        categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    } catch (NumberFormatException nfe) {
                    }
                    recipeId = 0;
                    try {
                        recipeId = Integer.parseInt(request.getParameter("recipeId"));
                    } catch (NumberFormatException nfe) {
                    }

                    price = 0.0f;
                    try {
                        price = Float.parseFloat(request.getParameter("price"));
                    } catch (NumberFormatException nfe) {
                    }

                    try{
                    itemName = request.getParameter("itemName");
                    description = request.getParameter("description");
                    nutritionalValue = request.getParameter("nutritionalValue");
                    warnings = request.getParameter("warnings");
                    imageUrl = request.getParameter("imageUrl");
                    }catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }

                    System.out.println("values");
                    itemId = 0;
                    try {
                        itemId = Integer.parseInt(request.getParameter("itemId"));
                    } catch (NumberFormatException nfe) {
                    }

                    if (editItem(new Item(itemId, categoryId, recipeId, itemName, price, description, nutritionalValue, warnings, imageUrl)) && itemId > 0) {
                        request.setAttribute("item", "Item Updated");
                    } else {
                        System.out.println("fail");
                        request.setAttribute("ans", "Item not Updated");
                    }
                    itemList = itemDao.getAllItems();
                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allItems", itemList);
                    } else {
                        request.setAttribute("ans", "No items ");
                    }
                    itemView = "viewItems.jsp";
                    break;
                case "itembyprice":

                    try{
                    price = Float.parseFloat(request.getParameter("price"));
                    }catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }
                    itemList = itemDao.getAllItemsByPrice(price);

                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allitems", itemList);
                    } else {
                        request.setAttribute("allitems", "no items yet");
                    }
                    itemView = "viewItems.jsp";
                    break;
                case "itembyid":

                    try{
                    itemId = Integer.parseInt(request.getParameter("itemId"));
                    }catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }
                    item = itemDao.getItemById(itemId);

                    if (item != null) {
                        request.setAttribute("item", item);
                    } else {
                        request.setAttribute("ans", "No items yet");
                    }
                    itemView = "editItem.jsp";

                    break;
                case "itembyname":
                    try{
                    itemName = request.getParameter("itemName");
                    }catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }
                    itemList = (List<Item>) itemDao.getItemByName(itemName);

                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("all items", itemList);
                    } else {
                        request.setAttribute("ans", "no items yet");
                    }
                    itemView = "viewItems.jsp";

                    break;

                //---------------------------------------------user views----------------------------------------------
                case "index":
                    categoryList = categoryDao.getAllActiveCategories();
                    if (categoryList != null && !categoryList.isEmpty()) {
                        request.setAttribute("allcategories", categoryList);
                    } else {
                        request.setAttribute("ans", "No categories found");
                    }

                    itemList = itemDao.getAllActiveItems();
                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allItems", itemList);
                    } else {
                        request.setAttribute("ans", "No items ");
                    }

                    itemView = "index.jsp";
                    break;

                case "viewhomeitems":
                    categoryList = categoryDao.getAllActiveCategories();
                    if (categoryList != null && !categoryList.isEmpty()) {
                        request.setAttribute("allcategories", categoryList);
                    } else {
                        request.setAttribute("ans", "No categories found");
                    }

                    itemList = itemDao.getAllActiveItems();
                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allItems", itemList);
                    } else {
                        request.setAttribute("ans", "No items ");
                    }

                    itemView = "homePage.jsp";
                    break;
                case "indexcategories":
                    categoryList = categoryDao.getAllActiveCategories();
                    if (categoryList != null && !categoryList.isEmpty()) {
                        request.setAttribute("allcategories", categoryList);
                    } else {
                        request.setAttribute("ans", "No categories found");
                    }

                    categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    itemList = itemDao.getAllItemsByCategory(categoryId);

                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allItems", itemList);
                    } else {
                        request.setAttribute("ans", "No categories yet");
                    }

                    itemView = "index.jsp";
                    break;

                case "itembycategoryid":
                    categoryList = categoryDao.getAllActiveCategories();
                    if (categoryList != null && !categoryList.isEmpty()) {
                        request.setAttribute("allcategories", categoryList);
                    } else {
                        request.setAttribute("ans", "No categories found");
                    }

                    categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    itemList = itemDao.getAllItemsByCategory(categoryId);

                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allItems", itemList);
                    } else {
                        request.setAttribute("ans", "No categories yet");
                    }

                    itemView = "homePage.jsp";
                    break;
                case "indexitembycategoryid":
                    categoryList = categoryDao.getAllActiveCategories();
                    if (categoryList != null && !categoryList.isEmpty()) {
                        request.setAttribute("allcategories", categoryList);
                    } else {
                        request.setAttribute("ans", "No categories found");
                    }

                    categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    itemList = itemDao.getAllItemsByCategory(categoryId);

                    if (itemList != null && !itemList.isEmpty()) {
                        request.setAttribute("allItems", itemList);
                    } else {
                        request.setAttribute("ans", "No categories yet");
                    }

                    itemView = "index.jsp";
                    break;

            }

        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(itemView);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }
}
