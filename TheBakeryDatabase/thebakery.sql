-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.25 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table thebakery.address
CREATE TABLE IF NOT EXISTS `address` (
  `addressId` int NOT NULL AUTO_INCREMENT,
  `addr1` varchar(50) NOT NULL DEFAULT '0',
  `addr2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `province` varchar(50) NOT NULL DEFAULT '0',
  `city` varchar(50) NOT NULL DEFAULT '0',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `isActive` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`addressId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.address: ~2 rows (approximately)
INSERT INTO `address` (`addressId`, `addr1`, `addr2`, `province`, `city`, `code`, `isActive`) VALUES
	(5, '12 Greenaisles road', 'Greenside', 'Gauteng', 'Johannesburg', '1655', 1),
	(6, 'E510 Xikundu', 'Malamulele', 'Limpopo', 'GY', '0980', 1);

-- Dumping structure for table thebakery.category
CREATE TABLE IF NOT EXISTS `category` (
  `categoryId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isActive` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`categoryId`) USING BTREE,
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.category: ~8 rows (approximately)
INSERT INTO `category` (`categoryId`, `name`, `description`, `isActive`) VALUES
	(1, 'Cookies', 'A cookie, or a biscuit, is a baked or cooked snack or dessert that is typically small, flat and sweet.', 1),
	(2, 'Cakes', 'An item of soft, sweet food made from a mixture of flour, shortening, eggs, sugar, and other ingredients, baked and often decorated', 1),
	(3, 'Cupcake', 'A small cake baked in a cup-shaped container and typically iced', 1),
	(4, 'Fresh Breads', 'One of the oldest human-made foods, having been of significance since the dawn of agriculture', 1),
	(5, 'Pies', 'Baked dish which is usually made of a pastry dough casing that contains a filling of various sweet or savoury ingredients', 1),
	(6, 'Brownies', 'A chocolate baked confection', 1),
	(7, 'Pastries', 'Made with a dough of flour, water and shortening that may be savoury or sweetened', 0),
	(8, 'Muffins', 'Individually portioned baked product; however, the term can refer to one of two distinct items', 0);

-- Dumping structure for table thebakery.ingredient
CREATE TABLE IF NOT EXISTS `ingredient` (
  `ingredientId` int NOT NULL AUTO_INCREMENT,
  `unitId` int NOT NULL,
  `quantityinStock` int NOT NULL DEFAULT '0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `minimumstockLevel` int NOT NULL,
  `isActive` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`ingredientId`) USING BTREE,
  KEY `FK_ingredients_unit` (`unitId`) USING BTREE,
  CONSTRAINT `FK_ingredients_unit` FOREIGN KEY (`unitId`) REFERENCES `unit` (`unitId`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.ingredient: ~22 rows (approximately)
INSERT INTO `ingredient` (`ingredientId`, `unitId`, `quantityinStock`, `name`, `minimumstockLevel`, `isActive`) VALUES
	(1, 1, 46, 'Eggs', 12, 1),
	(18, 1, 183, 'Flour', 50, 1),
	(19, 1, 91, 'Butter', 10, 1),
	(20, 3, 60, 'Oil', 20, 1),
	(21, 1, 20, 'Castor Sugar', 10, 1),
	(22, 1, 60, 'Brown Sugar', 10, 1),
	(23, 1, 20, 'Icing Sugar', 10, 1),
	(24, 1, 12, 'Cocoa Powder', 10, 1),
	(25, 1, 1, 'Chocolate', 5, 1),
	(26, 3, 20, 'Whip Cream', 5, 1),
	(27, 3, -7, 'Milk', 5, 1),
	(28, 1, 5, 'Yogurt', 5, 1),
	(29, 3, 10, 'Condensed Milk', 4, 1),
	(30, 1, -4, 'Baking Powder', 2, 1),
	(31, 1, -4, 'Salt', 2, 1),
	(32, 1, 8, 'Coconut', 2, 1),
	(33, 1, 3, 'Cinnamon', 1, 1),
	(34, 1, -13, 'Ginger', 1, 1),
	(35, 1, -14, 'Oats', 1, 1),
	(36, 1, 5, 'Carrots', 2, 1),
	(37, 2, 60, 'Vanilla Essence', 10, 1),
	(38, 2, 40, 'Yeast', 20, 1);

-- Dumping structure for table thebakery.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `invoiceId` int NOT NULL AUTO_INCREMENT,
  `orderId` int NOT NULL,
  `date` date NOT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  `totalAmount` float NOT NULL,
  PRIMARY KEY (`invoiceId`) USING BTREE,
  KEY `FK__orders` (`orderId`) USING BTREE,
  CONSTRAINT `FK__orders` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.invoice: ~6 rows (approximately)
INSERT INTO `invoice` (`invoiceId`, `orderId`, `date`, `status`, `totalAmount`) VALUES
	(23, 88, '2023-06-28', 1, 65),
	(24, 89, '2023-06-29', 1, 0),
	(25, 90, '2023-06-29', 1, 0),
	(26, 91, '2023-06-29', 1, 165),
	(27, 92, '2023-06-29', 1, 65),
	(28, 93, '2023-06-29', 1, 65);

-- Dumping structure for table thebakery.item
CREATE TABLE IF NOT EXISTS `item` (
  `itemId` int NOT NULL AUTO_INCREMENT,
  `categoryId` int NOT NULL,
  `recipeId` int NOT NULL,
  `itemName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` float NOT NULL DEFAULT '0',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nutritionalValue` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `warnings` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `isActive` tinyint NOT NULL DEFAULT '1',
  `image` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`itemId`) USING BTREE,
  UNIQUE KEY `itemName` (`itemName`),
  KEY `FK_item_category` (`categoryId`) USING BTREE,
  KEY `FK_item_recipe` (`recipeId`) USING BTREE,
  CONSTRAINT `FK_item_category` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`),
  CONSTRAINT `FK_item_recipe` FOREIGN KEY (`recipeId`) REFERENCES `recipe` (`recipeId`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.item: ~18 rows (approximately)
INSERT INTO `item` (`itemId`, `categoryId`, `recipeId`, `itemName`, `price`, `description`, `nutritionalValue`, `warnings`, `isActive`, `image`) VALUES
	(1, 1, 1, 'Choc chip Cookie', 20, 'A chocolate chip cookie is a drop cookie that features chocolate chips or chocolate morsels as its distinguishing ingredient.', 'none', 'none', 1, 'top-view-chocolate-chip-cookies.jpg'),
	(2, 1, 2, 'Ginger Cookie', 15, 'A thin brittle cookie flavoured with ginger', 'none', 'none', 1, 'Biscuit.jpg'),
	(3, 1, 3, 'Oat Cookie', 30, 'An oatmeal cookie is a type of drop cookie made from an oatmeal-based dough', 'none', 'none', 1, 'fresh-tasty-oat-biscuits.jpg'),
	(4, 2, 4, 'Chocolate Cake', 120, 'Vanilla cake is a basic sponge which is enriched with vanilla flavouring and typically covered in a plain buttercream.', 'none', 'none', 1, 'Chocolate_Cake.jpg'),
	(5, 2, 5, 'Carrot Cake', 130, 'A moist cake containing grated carrot', 'none', 'none', 1, 'Carrot-Cake-.jpg'),
	(6, 2, 6, 'Vanila Cake', 100, 'enriched with vanilla flavouring and typically covered in a plain buttercream', 'none', 'none', 1, 'vanilla.jpg'),
	(7, 3, 7, 'Choc Cupcake', 30, 'Red Velvet Cupcakes topped with Coconut Cream and rolled in shredded coconu', 'none', 'none', 1, 'Choc cup.jpg'),
	(8, 3, 8, 'Funfetti Cupcake', 40, 'Funfetti Cupcakes with Cake Batter Frosting', 'none', 'none', 1, 'Funfetti.jpg'),
	(9, 3, 9, 'Coconut Cupcake', 25, 'Red Velvet Cupcakes topped with Coconut Cream ', 'none', 'none', 1, 'coco.jpg'),
	(10, 4, 10, 'Rustic Olive Bread', 33, 'salty kalamata olives to add a depth of flavour to this rustic bread', 'none', 'none', 1, 'rustic.jpg'),
	(11, 4, 11, 'Milk Bread', 20, 'very moist and also has a light, fluffy and slightly chewy texture', 'none', 'none', 1, 'bread.jpg'),
	(12, 4, 12, 'Crusty Bread', 25, 'breads with a hard crust that crunches when you bite into it', 'none', 'none', 1, 'crusty bread.jpg'),
	(43, 5, 13, 'Steak & Kidneyn Pie', 19, 'savoury pie filled principally with a mixture of diced beef, diced kidney and onion', 'none', 'none', 1, 'sk.jpg'),
	(44, 5, 37, 'Pepper Steak', 18, 'Meltingly tender steak in a savoury gravy encased in flaky, buttery, golden pastry', 'none', 'none', 1, '29.BeefPepperSteakPie_1024x1024.webp'),
	(45, 5, 38, 'Chicken & Mushroom', 18, 'Meltingly tender steak in a savoury gravy encased in flaky, buttery, golden pastry none', 'none', 'none', 1, 'chicken.jpg'),
	(46, 6, 39, 'Pecan Brownie', 15, 'has the perfect amount of everything, including semisweet and milk chocolate chips and pecan', 'none', 'none', 1, 'pecan.jpg'),
	(47, 6, 40, 'Peanut Butter Brownie', 15, 'Homemade fudgy brownies are stuffed with a peanut butter filling', 'none', 'none', 1, 'peanut.jpg'),
	(48, 6, 41, 'Cappuchino Brownies', 16, 'ich, dense, and fudgy, these Cappuccino Brownies are topped with a white chocolate ganache to give you the froth-y flavour of a cappuccino.', 'none', 'none', 1, 'capp.jpg');

-- Dumping structure for table thebakery.orderlineitem
CREATE TABLE IF NOT EXISTS `orderlineitem` (
  `orderLineItemId` int NOT NULL AUTO_INCREMENT,
  `orderId` int NOT NULL,
  `itemId` int NOT NULL,
  `quantity` int NOT NULL,
  `unitCost` float NOT NULL DEFAULT '0',
  `discount` double DEFAULT '0',
  PRIMARY KEY (`orderLineItemId`) USING BTREE,
  KEY `FK_orderlineitem_orders` (`orderId`) USING BTREE,
  KEY `FK_orderlineitem_item` (`itemId`) USING BTREE,
  CONSTRAINT `FK_orderlineitem_item` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`),
  CONSTRAINT `FK_orderlineitem_orders` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.orderlineitem: ~12 rows (approximately)
INSERT INTO `orderlineitem` (`orderLineItemId`, `orderId`, `itemId`, `quantity`, `unitCost`, `discount`) VALUES
	(57, 88, 2, 1, 15, 0),
	(58, 88, 3, 1, 30, 0),
	(59, 88, 1, 1, 20, 0),
	(60, 91, 4, 1, 120, 0),
	(61, 91, 3, 1, 30, 0),
	(62, 91, 2, 1, 15, 0),
	(63, 92, 1, 1, 20, 0),
	(64, 92, 2, 1, 15, 0),
	(65, 92, 3, 1, 30, 0),
	(66, 93, 2, 1, 15, 0),
	(67, 93, 1, 1, 20, 0),
	(68, 93, 3, 1, 30, 0);

-- Dumping structure for table thebakery.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deliveryAddress` varchar(50) NOT NULL DEFAULT '',
  `orderDate` date NOT NULL,
  `totalAmount` float NOT NULL DEFAULT '0',
  `status` tinyint NOT NULL DEFAULT '1',
  `time` time DEFAULT NULL,
  PRIMARY KEY (`orderId`) USING BTREE,
  KEY `FK_order_user` (`username`),
  CONSTRAINT `FK_order_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.orders: ~7 rows (approximately)
INSERT INTO `orders` (`orderId`, `username`, `deliveryAddress`, `orderDate`, `totalAmount`, `status`, `time`) VALUES
	(88, 'tshepangmorake07@gmail.com', '22 fourie street', '2023-06-28', 65, 1, '10:53:02'),
	(89, 'vsmpisane21@gmail.com', 'user', '2023-06-29', 0, 1, '11:45:41'),
	(90, 'vsmpisane21@gmail.com', 'user', '2023-06-29', 0, 1, '11:45:43'),
	(91, 'vsmpisane21@gmail.com', 'user', '2023-06-29', 165, 1, '11:46:33'),
	(92, 'tshepangmorake07@gmail.com', 'jhb', '2023-06-29', 65, 1, '11:49:05'),
	(93, 'tshepangmorake07@gmail.com', 'jhb', '2023-06-29', 65, 1, '11:51:10'),
	(94, 'tshepangmorake07@gmail.com', 'jhb', '2023-06-29', 150, 1, '11:54:41');

-- Dumping structure for table thebakery.recipe
CREATE TABLE IF NOT EXISTS `recipe` (
  `recipeId` int NOT NULL AUTO_INCREMENT,
  `recipeName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isActive` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`recipeId`) USING BTREE,
  UNIQUE KEY `recipeName` (`recipeName`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.recipe: ~18 rows (approximately)
INSERT INTO `recipe` (`recipeId`, `recipeName`, `description`, `isActive`) VALUES
	(1, '	Chocolate Chip Cookie', '-Heat oven to 180C/160C fan/gas 4 and line two baking sheets with parchment. Cream the butter and sugars together until very light and fluffy, then beat in the egg and vanilla. Once combined, stir in the flour, bicarb, chocolate and ÃÂ¼ tsp salt.-Scoop 10 large tbsps of the mixture onto the trays, leaving enough space between each to allow for spreading. Bake for 10-12 mins or until firm at the edges but still soft in the middle Ã¢ they will harden a little as they cool. Leave to cool on the tray for a few mins before eating warm, or transfer to a wire rack to cool completely.', 1),
	(2, 'Ginger Cookie', 'Ginger cookie\r\n-Preheat the oven to 350 degrees F (175 degrees C). Set 2 tablespoons sugar in a small bowl; set aside.-Sift together flour, ginger, baking soda, cinnamon, cloves, and salt in a bowl.-Cream margarine and remaining 1 cup sugar in a large bowl until light and fluffy.Beat in egg, then stir in molasses and water. Gradually stir the sifted ingredients into the molasses mixture until well combined. -Use floured hands to shape dough into 24 walnut-sized balls. Roll each ball in the reserved sugar until coated. Place cookies 2 inches apart onto ungreased cookie sheets, and flatten slightly with the bottom of a glass. -Bake in the preheated oven for 8 to 10 minutes, switching racks halfway through. -Remove from the oven and allow cookies to cool on the baking sheets for 5 minutes, then transfer to a wire rack to cool completely.', 1),
	(3, 'Oat Cookie', '-Heat the oven to 180C/160C fan/gas 4. Oil and line the base and sides of two 20cm cake tins with baking parchment. Whisk the oil, yogurt, eggs, vanilla and zest in a jug. Mix the flour, sugar, cinnamon and nutmeg with a good pinch of salt in a bowl. Squeeze any lumps of sugar through your fingers, shaking the bowl a few times to bring the lumps to the surface. -Add the wet ingredients to the dry, along with the carrots, raisins and half the nuts, if using. Mix well to combine, then divide between the tins.-Bake for 25-30 mins or until a skewer inserted into the centre of the cake comes out clean. If any wet mixture clings to the skewer, return to the oven for 5 mins, then check again. Leave to cool in the tins.-To make the icing, beat the butter and sugar together until smooth. Add half the soft cheese  and beat again, then add the rest (adding it bit by bit prevents the icing from splitting). -Remove the cakes from the tins and sandwich together with half the icing. Top with the remaining icing and scatter with the remaining walnuts. Will keep in the fridge for up to five days.Best eaten at room temperature.', 1),
	(4, 'Chocolate Cake', '-Preheat the oven to 180C/160C Fan/Gas 4. Grease and line two 20cm/8in sandwich tins. -For the cake, place all of the cake ingredients, except the boiling water, into a large mixing bowl. Using a wooden spoon, or electric whisk, beat the mixture until smooth and well combined. -Add the boiling water to the mixture, a little at a time, until smooth. (The cake mixture will now be very liquid.) -Divide the cake batter between the sandwich tins and bake in the oven for 25–35 minutes, or until the top is firm to the touch and a skewer inserted into the centre of the cake comes out clean. -Remove the cakes from the oven and allow to cool completely, still in their tins, before icing. -For the chocolate icing, heat the chocolate and cream in a saucepan over a low heat until the chocolate melts. Remove the pan from the heat and whisk the mixture until smooth, glossy and thickened. Set aside to cool for 1–2 hours, or until thick enough to spread over the cake. -To assemble the cake, run a round-bladed knife around the inside of the cake tins to loosen the cakes. Carefully remove the cakes from the tins. -Spread a little chocolate icing over the top of one of the chocolate cakes, then carefully top with the other cake.-Transfer the cake to a serving plate and ice the cake all over with the chocolate icing,using a palette knife.', 1),
	(5, 'Carrot Cake', '-Heat the oven to 180C/160C fan/gas 4. Oil and line the base and sides of two 20cm cake tins with baking parchment. Whisk the oil, yogurt, eggs, vanilla and zest in a jug. Mix the flour, sugar, cinnamon and nutmeg with a good pinch of salt in a bowl. Squeeze any lumps of sugar through your fingers, shaking the bowl a few times to bring the lumps to the surface.-Add the wet ingredients to the dry, along with the carrots, raisins and half the nuts, if using. Mix well to combine, then divide between the tins.-Bake for 25-30 mins or until a skewer inserted into the centre of the cake comes out clean. If any wet mixture clings to the skewer, return to the oven for 5 mins, then check again. Leave to cool in the tins.-To make the icing, beat the butter and sugar together until smooth. Add half the soft cheese  and beat again, then add the rest (adding it bit by bit prevents the icing from splitting).-Remove the cakes from the tins and sandwich together with half the icing. Top with the remaining icing and scatter with the remaining walnuts. Will keep in the fridge for up to five days.Best eaten at room temperature', 1),
	(6, 'Vanilla Cake', 'STEP 1 Heat oven to 160C/140C fan/gas 3. Grease a round, deep 20cm tin, then line the base and sides with non-stick baking paper. STEP 2 Using electric beaters or a tabletop mixer, beat the butter, sugar, vanilla and Â¼ tsp salt together until pale and fluffy,  then pour in the eggs, one at a time, giving the mix a really good beating before adding the next. STEP 3 Add 1 tbsp of the plain flour if the mix starts to look slimy rather than fluffy. Beat in the yogurt. Mix the flours;  then, using a large metal spoon, fold them into the batter, followed by the milk. STEP 4 Spoon the mix into the tin and bake for 1 hr 20 mins or until well risen and golden â a skewer inserted into the middle should come out clean. STEP 5 Leave to cool completely, then either wrap the cake well or fill and ice it.', 1),
	(7, 'Chocolate Cupcake', 'STEP 1 Preheat oven to 350 degrees F (175 degrees C). Grease two muffin pans or line with 20 paper baking cups.  STEP 2 In a medium bowl, beat the butter and sugar with an electric mixer until light and fluffy. Mix in the eggs,  almond extract and vanilla. Combine the flour, cocoa, baking powder and salt; stir into the batter,  alternating with the milk, just until blended. Spoon the batter into the prepared cups, dividing evenly.  STEP 3 Bake in the preheated oven until the tops spring back when lightly pressed, 20 to 25 minutes. Cool in  the pan set over a wire rack. When cool, arrange the cupcakes on a serving platter. Frost with your favorite frosting.', 1),
	(8, 'Funfetti Cupcake', 'Make the batter: Whisk together cake flour, baking soda, baking powder and salt in a bowl and set aside. In a separate  bowl whisk together eggs and vanilla until well blended. Whisk in sugar until well blended. Slowly whisk in butter and  mix well to combine. Add Â½ of the dry mixture to the wet mixture and mix until combined. Mix in the buttermilk and stir  until combined. Mix in remaining dry mixture and mix just until combined.  Add the sprinkles: Gently fold in sprinkles and mix just until combined.   Bake: Scoop batter into prepared cupcake liners about 2/3 of the way full. Bake for 18-22 minutes or until a toothpick  inserted into the center comes out clean. Remove from oven and allow to completely cool prior to frosting.', 1),
	(9, 'Coconut Cupcake', 'STEP 1 Chill the can of coconut milk overnight. The next day, make the cakes. Heat oven to 180C/160C fan/gas 4 and line a 12-hole  muffin tin with muffin cases. Put the coconut oil and sugar in a large bowl and use an electric whisk to beat together for  about 5 mins until light and creamy. Add the eggs, one at a time, beating until fully combined before adding more. Working  in three additions, add the flour, alternating with the coconut milk, then add the vanilla extract.  STEP 2 Scoop the batter into the prepared muffin tin using an ice cream scoop, then bake for 20-22 mins or until the cakes are golden  and a skewer inserted into the middle comes out clean. Remove the cupcakes from the tin and cool on a wire rack.  STEP 3 The whipped cream is best made within a few hours of serving. Open the can of coconut milk upside down and pour off the thin liquid.  Scoop the thick coconut milk into a large bowl, add the icing sugar and the vanilla, and whisk for 2-3 mins until soft and fluffy.  Spread or scoop the whipped cream onto the cupcakes and sprinkle with the desiccated coconut. Best stored in the fridge and eaten within  a couple of days.', 1),
	(10, 'Rustic olive bread', 'Place the shaped and scored dough (on the flour/cornmeal dusted pan) in the preheated oven on the center rack. Bake for 35-40 minutes or  until the crust is golden brown. How to test for donenessâ give the warm loaf a light tap. If it sounds hollow, itâs done. For a more accurate  test, the bread is done when an instant-read thermometer reads the center of the loaf as 195Â°F (90Â°C). Remove the bread from the oven and allow to cool for at least 10-20 minutes before slicing and serving. Store leftovers loosely covered at room  temperature for up to 3 days or in the refrigerator for up to 10 days', 1),
	(11, 'Milk Bread', 'Step 1: Place the flour in a large mixing bowl of the bowl of a freestanding mixer with the dough hook attachment. Add the salt, sugar and yeast making  sure the yeast and salt do not touch.  Step 2: Add the butter and 240ml of the milk and gently mix until the dough starts to form then add more of the milk, a little at a time until all of the  flour is mixed into the dough. You might not need to use all of the milk. Continue to mix until the dough is soft and smooth.  Step 3: Tip the dough onto your surface that has been lightly coated with oil, and knead for 5-10 minutes until the dough becomes smooth and silky to the  touch, place the dough in a lightly oiled, large bowl and cover with a tea towel and leave to rise for 2-3 hours until the dough has doubled in size.  Step 4: Next tip the dough onto a lightly floured surface and fold inwards to knock the air out forming an oblong shape. Roll this up from the shortest side  making sure it will fit into your 2lb/1kg loaf tin. Lightly grease the loaf tin then place the dough join side down into the tin. Slash the top with  a sharp knife. Cover with oiled cling film or place into a clean plastic bag and leave to prove for about 1 hour.  Step 5: Heat the oven to 210Â°C (190Â°C, gas mark 7). When the dough had proved bake in the oven for 25 minutes or until the bread sounds hollow when tapped  underneath.  Step 6: Leave to cool on a wire rack.', 1),
	(12, 'Crusty Bread', 'STEP 1 Tip the flour into a large bowl along with the yeast. Pour over 325g room-temperature water (youâll get a more accurate amount by weighing the water,but you can also use 325ml, depending on your scales), then use your hands or a spatula to mix until a dough forms. Itâs important that you ensure all of the flour is mixed in, including any bits around the side of the bowl. Cover with a damp tea towel and leave to rest for 30 mins.  STEP 2 Sprinkle over the salt, then work it in by stretching the dough over the salt several times until fully incorporated. Tip out onto a lightly floured surface. Use the heel of one hand to stretch the dough while you hold it with your other hand. Then, fold the dough back onto itself, turn it 90 degrees to the left and repeat. Do this for about 5 mins, or until the dough looks shinier and immediately bounces back when rolled into a tight ball or gently poked. Roll the dough into a tight ball, cover with a damp tea towel and leave to prove at room temperature for 1 hr until doubled in size. Or, for a deeper flavour, transfer to a clean bowl, cover and leave to prove in the fridge overnight until doubled in size. Cold dough is easier to work with, so overnight is best.', 1),
	(13, 'Steak & Kidney Pie', 'STEP 1 It\'s important to cook the meat a day ahead, so that you can discard any fat that has risen to the top, and so that the pastry doesn\'t slump in the face of a  too-warm filling, so up to 48 hours ahead -make the pastry. Whizz the flour and a pinch of fine sea salt together for a few seconds in a food processor,  then add the butter and whizz until the mixture looks like coarse breadcrumbs. Whisk together the egg yolk and 3 tbsp water and whizz with the pastry until  it collects in a ball. Wrap in cling film and leave it to rest in the fridge for at least one hour.  STEP 2 Cut button-mushroom-sized lobes of kidney from the white central core (discarding). Cut the beef into bite-sized cubes and cut the mushrooms into chunks.  STEP 3 Heat 1 tbsp of the oil in a large, heavy frying pan over a medium-high heat. Throw in the kidney and fry until lightly coloured. Tip into a colander to drain.  STEP 4 Preheat the oven to 160C/gas 3/fan 140C. Tip the 85g/3oz flour into a large plastic bag, and season it generously. Throw in the beef and shake until lightly  floured.Return the frying pan to a mediumhigh heat, adding a little more oil and butter if needed. Shake off any excess flour (reserving it) then fry the beef  in batches until golden-brown. As each batch is done, transfer it to the casserole.', 1),
	(37, 'Pepper Steak Pie', '-Preheat the oven to 200 Â°C. -Roll the beef cubes in the pepper and dust with the flour. -Heat the oil in a pan and brown the cubes on all sides. -Stir in the water and the contents of the sachet of Knorr Hearty Beef Stew Cook-in-Sauce. Simmer for 25 - 30 minutes, or until the beef is tender.   Place in a greased ovenproof dish, or in 4 individual dishes. -Roll out the pastry and place it over the mixture to create the lid of the pie. If you are using individual pie dishes, cut the pastry into separate circles. -Brush the pastry lid or lids with beaten egg. Bake at 200 Â°C for 15 - 20 minutes, or until the filling is piping hot and the pastry is golden brown. -Serve immediately with peas or a green salad.', 1),
	(38, 'Chicken & Mushroom Pie', '-Preheat oven to 350Â°. In a microwave, melt chocolate and butter; stir until smooth. Cool slightly. Dissolve coffee granules in hot water. In a large bowl,  beat eggs and sugar. Stir in vanilla, chocolate mixture and coffee mixture. Combine flour and salt; gradually add to chocolate mixture until blended.  Fold in walnuts. -Transfer to a greased and floured 13x9-in. baking pan. Bake until a toothpick inserted in the center comes out clean, 25-30 minutes. Cool completely  on a wire rack. -For topping, in a large bowl, beat cream cheese and butter until blended. Add confectioners\' sugar, cinnamon and vanilla; beat on low speed until combined.  Spread over bars. Refrigerate until firm, about 1 hour. -For glaze, dissolve coffee granules in hot water. In a microwave, melt chocolate and butter; cool slightly. Stir in cream and coffee mixture. Spread over   cream cheese layer. Let stand until set. Cut into bars. Refrigerate leftovers', 1),
	(39, 'Chocolate Pecan Brownie', '-In a saucepan over low heat, melt butter and chocolate. Stir in sugar; cool slightly. Add eggs and vanilla; mix well. Stir in flour and pecans. -Spread into a greased 8-in. square baking pan. Bake at 350Â° for 15-20 minutes or until a toothpick inserted in the center comes out clean. Cool on a wire rack.', 1),
	(40, 'Peanut Butter Brownies	', 'STEP 1 Set aside 50g each of the peanut butter and chocolate. Heat oven to 180C/160C fan/gas 4 and line a 20cm square baking tin with baking parchment. Gently melt  remaining peanut butter, chocolate and all the sugar in a pan, stirring occasionally, until the sugar has just about melted. Transfer mix into a bowl to cool  down slightly. Turn off heat and use a wooden spoon to beat in the eggs one by one. Stir in the flour and scrape into the tin.  STEP 2 Melt reserved peanut butter in the microwave on High for 45 secs, or in a pan, until runny, then drizzle over the brownie. Bake for 20-25 mins until it has a  crust, but the middle still seems slightly uncooked.  STEP 3 Melt reserved chocolate, drizzle over the brownie, then cool in the tin before cutting into squares.', 1),
	(41, 'Cappuchino Brownies', 'Rich, dense, and fudgy, these Cappuccino Brownies are topped with a white chocolate ganache to give you the froth-y flavour of a cappuccino.', 1);

-- Dumping structure for table thebakery.recipeingredients
CREATE TABLE IF NOT EXISTS `recipeingredients` (
  `ingredientId` int NOT NULL,
  `recipeId` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`ingredientId`,`recipeId`) USING BTREE,
  KEY `FK_recipeingredients_recipe` (`recipeId`) USING BTREE,
  CONSTRAINT `FK_recipeingredients_ingredients` FOREIGN KEY (`ingredientId`) REFERENCES `ingredient` (`ingredientId`),
  CONSTRAINT `FK_recipeingredients_recipe` FOREIGN KEY (`recipeId`) REFERENCES `recipe` (`recipeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.recipeingredients: ~77 rows (approximately)
INSERT INTO `recipeingredients` (`ingredientId`, `recipeId`, `quantity`) VALUES
	(1, 1, 10),
	(1, 2, 8),
	(1, 3, 3),
	(1, 4, 7),
	(1, 5, 10),
	(1, 6, 8),
	(1, 7, 3),
	(1, 8, 5),
	(1, 10, 10),
	(1, 12, 2),
	(1, 13, 1),
	(1, 37, 1),
	(1, 39, 2),
	(1, 40, 2),
	(1, 41, 2),
	(18, 1, 30),
	(18, 2, 1),
	(18, 3, 2),
	(18, 4, 3),
	(18, 5, 3),
	(18, 6, 3),
	(18, 7, 1),
	(18, 8, 2),
	(18, 10, 2),
	(18, 11, 2),
	(18, 12, 4),
	(19, 1, 1),
	(19, 2, 1),
	(19, 3, 1),
	(19, 4, 1),
	(19, 5, 1),
	(19, 6, 1),
	(19, 7, 1),
	(19, 9, 1),
	(19, 11, 3),
	(19, 38, 2),
	(20, 8, 1),
	(21, 10, 2),
	(22, 5, 2),
	(22, 9, 2),
	(22, 12, 9),
	(23, 8, 2),
	(24, 4, 2),
	(24, 7, 2),
	(25, 1, 2),
	(25, 4, 1),
	(25, 7, 2),
	(26, 9, 3),
	(27, 1, 1),
	(27, 2, 2),
	(27, 3, 1),
	(27, 4, 3),
	(27, 5, 1),
	(27, 6, 2),
	(27, 7, 2),
	(27, 10, 2),
	(27, 11, 2),
	(27, 12, 5),
	(30, 1, 1),
	(30, 2, 2),
	(30, 3, 2),
	(30, 4, 1),
	(30, 6, 2),
	(30, 9, 2),
	(30, 11, 9),
	(30, 12, 4),
	(31, 1, 1),
	(31, 11, 3),
	(31, 12, 1),
	(32, 8, 1),
	(32, 9, 1),
	(34, 2, 2),
	(34, 5, 1),
	(35, 3, 2),
	(36, 5, 3),
	(37, 6, 1),
	(37, 8, 2);

-- Dumping structure for table thebakery.role
CREATE TABLE IF NOT EXISTS `role` (
  `roleId` int NOT NULL AUTO_INCREMENT,
  `roleDescription` int NOT NULL DEFAULT '0',
  `isActive` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.role: ~2 rows (approximately)
INSERT INTO `role` (`roleId`, `roleDescription`, `isActive`) VALUES
	(1, 1, 1),
	(2, 2, 1);

-- Dumping structure for table thebakery.unit
CREATE TABLE IF NOT EXISTS `unit` (
  `unitId` int NOT NULL,
  `unitDescription` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`unitId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.unit: ~4 rows (approximately)
INSERT INTO `unit` (`unitId`, `unitDescription`) VALUES
	(1, 'Kilogram'),
	(2, 'Grams'),
	(3, 'Litres'),
	(4, 'Millilitres');

-- Dumping structure for table thebakery.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `roleId` int NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `surname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contactNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isActive` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`),
  KEY `FK_users_roles` (`roleId`) USING BTREE,
  CONSTRAINT `FK_users_roles` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.user: ~6 rows (approximately)
INSERT INTO `user` (`username`, `roleId`, `password`, `name`, `surname`, `contactNumber`, `email`, `isActive`) VALUES
	('josh.rossouw15@gmail.com', 2, 'Josh12346', 'Joshua', 'Rossouw', '0766242464', 'josh.rossouw15@gmail.com', 1),
	('josh@topiefor.co.za', 1, '54321', 'Joshua', 'Rossouw', '0766242465', 'josh@topiefor.co.za', 1),
	('tshepang@topiefor.co.za', 1, '98765', 'Tshepang', 'Morake', '0818632525', 'tshepang@topiefor.co.za', 1),
	('tshepangmorake07@gmail.com', 2, 'Morake12345', 'Tshepang', 'Morake', '0818632525', 'tshepangmorake07@gmail.com', 1),
	('vsmpisane21@gmail.com', 2, 'Vukosi@21', 'Vukosi', 'Mpisani', '0677019478', 'vsmpisane21@gmail.com', 1),
	('vukosi@topiefor.co.za', 1, '12345', 'Vukosi', 'Mpisani', '0662420327', 'vukosi@topiefor.co.za', 1);

-- Dumping structure for table thebakery.useraddress
CREATE TABLE IF NOT EXISTS `useraddress` (
  `addressId` int NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `isActive` tinyint NOT NULL DEFAULT '1',
  KEY `FK__address` (`addressId`) USING BTREE,
  KEY `FK_useraddress_user` (`username`),
  CONSTRAINT `FK__address` FOREIGN KEY (`addressId`) REFERENCES `address` (`addressId`),
  CONSTRAINT `FK_useraddress_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table thebakery.useraddress: ~2 rows (approximately)
INSERT INTO `useraddress` (`addressId`, `username`, `isActive`) VALUES
	(5, 'vukosi@topiefor.co.za', 1),
	(6, 'vukosi@topiefor.co.za', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
