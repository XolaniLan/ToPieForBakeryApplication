// Cart Open Close
// Cart Open Close
let cartIcon = document.querySelector("#cart-icon");
let cart = document.querySelector(".cart");
let closeCart = document.querySelector("#close-cart");

// Open Cart
cartIcon.onclick = () => {
    cart.classList.add("active");
}

// Close Cart
closeCart.onclick = () => {
    cart.classList.remove("active");
}

// Making Adding to Cart
// Cart Working Js
if (document.readyState == "loading") {
    document.addEventListener("DOMContentLoaded", ready);
} else {
    ready();
}

// Making Function
function ready() {
    // Remove Items from cart
    var removeCartButtons = document.getElementsByClassName("cart-remove");
    for (var i = 0; i < removeCartButtons.length; i++) {
        var button = removeCartButtons[i];
        button.addEventListener("click", removeCartItem);
    }

    // Quantity Change
    var quantityInputs = document.getElementsByClassName("cart-quantity");
    for (var i = 0; i < quantityInputs.length; i++) {
        var input = quantityInputs[i];
        input.addEventListener("change", quantityChanged);
    }

    //  Add to cart
    var addCart = document.getElementsByClassName("add-cart");
    for (var i = 0; i < addCart.length; i++) {
        var button = addCart[i];
        button.addEventListener("click", addCartClicked);
    }


    loadCartItems();
}

// Remove from cart
function removeCartItem(event) {
    var buttonClicked = event.target;
    buttonClicked.parentElement.remove();
    updatetotal();
    saveCartItems();
}

// Quantity Change
function quantityChanged(event) {
    var input = event.target;
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 1;
    }
    updatetotal();
    saveCartItems();
    updateCartIcon();
}

// Add Cart function
function addCartClicked(event) {
    var button = event.target;
    var shopProducts = button.parentElement;
    var title = shopProducts.getElementsByClassName("product-title")[0].innerText;
    var price = shopProducts.getElementsByClassName("price")[0].innerText;
    var productImg = shopProducts.getElementsByClassName("product-img")[0].src;
  //  var productId = shopProducts.getElementsByClassName("product-id")[0].innerText;
    var productId = shopProducts.getElementsByClassName("product-id")[0].value;
    addProductToCart(title, price, productImg, productId);
    updatetotal();
    saveCartItems();
    updateCartIcon();
}

function addProductToCart(title, price, productImg, productId) {
    var cartShopBox = document.createElement("div");
    cartShopBox.classList.add("cart-box");
    var cartItems = document.getElementsByClassName("cart-content")[0];
    var cartItemsNames = cartItems.getElementsByClassName("cart-product-title");
    for (var i = 0; i < cartItemsNames.length; i++) {
        if (cartItemsNames[i].innerText == title) {
            alert("You have already added this item to cart");
            return;
        }
    }
    var cartBoxContent = ` <img src="${productImg}" alt="" class="cart-img">
                           <div class="detail-box">
                           <input type="hidden" class="cart-product-id" value="${productId}"></input>
                               <div class="cart-product-title">${title}</div>
                               <div class="cart-price">${price}</div>
                               <input type="number" name="" id="" value="1" class="cart-quantity"/>
                          </div>
                          <!-- Remove Item -->
                          <i class='bx bx-trash-alt cart-remove'></i>`

    cartShopBox.innerHTML = cartBoxContent;
    cartItems.append(cartShopBox);
    cartShopBox.getElementsByClassName('cart-remove')[0]
            .addEventListener('click', removeCartItem);
    cartShopBox.getElementsByClassName('cart-quantity')[0]
            .addEventListener('change', quantityChanged);
    saveCartItems();
    
    
    updateCartIcon();

}

// Update Total
function updatetotal() {
    var cartContent = document.getElementsByClassName("cart-content")[0];
    var cartBoxes = cartContent.getElementsByClassName("cart-box");
    var total = 0;
    for (var i = 0; i < cartBoxes.length; i++) {
        var cartBox = cartBoxes[i];
        var priceElement = cartBox.getElementsByClassName("cart-price")[0];
        var quantityElement = cartBox.getElementsByClassName("cart-quantity")[0];
        var price = parseFloat(priceElement.innerText.replace("R", ""));
        var quantity = quantityElement.value;
        total += price * quantity;
    }
    // if the price contains some cents
    total = (Math.round(total * 100) / 100).toFixed(2);
    
    document.getElementsByClassName("total-price")[0].innerText = "R" + total;
    //Save Total to localStorage
    sessionStorage.setItem("cartTotal", total);

}

//Keep items in cart when page refresh with local storage

function saveCartItems() {
    var cartContent = document.getElementsByClassName("cart-content")[0];
    var cartBoxes = cartContent.getElementsByClassName("cart-box");
    var cartItems = [];

    for (var i = 0; i < cartBoxes.length; i++) {
        var cartBox = cartBoxes[i];
        var titleElement = cartBox.getElementsByClassName("cart-product-title")[0];
        var priceElement = cartBox.getElementsByClassName("cart-price")[0];
        var idElement = cartBox.getElementsByClassName("cart-product-id")[0];
        var quantityElement = cartBox.getElementsByClassName("cart-quantity")[0];

        var productImg = cartBox.getElementsByClassName("cart-img")[0].src;

        var item = {

            itemId: idElement.value,
            quantity: quantityElement.value,
            unitCost: priceElement.innerText,

        };

        cartItems.push(item);
    }
    updateCartIcon();
    sessionStorage.setItem("cartItems", JSON.stringify(cartItems));

}


function getCart() {
    var items = sessionStorage.getItem("cartItems");
    document.getElementById("cart").setAttribute("value", items);

    getCartAmount();

}

function getCartAmount() {
    var total = sessionStorage.getItem("cartTotal");
    document.getElementById("total").setAttribute("value", total);
}

function clearCart() {
    var cartTotal = sessionStorage.getItem("cartTotal");
    var items = sessionStorage.getItem("cartItems");
    sessionStorage.clear("cartTotal");
    sessionStorage.clear("cartItems");
}


// Loads in Cart
function loadCartItems() {
    var cartItems = sessionStorage.getItem("cartItems");

    if (cartItems) {
        cartItems = JSON.parse(cartItems);

        for (var i = 0; i < cartItems.length; i++) {
            var item = cartItems[i];
            addProductToCart(item.title, item.price, item.productImg, item.productId);

            var cartBoxes = document.getElementsByClassName("cart-box");
            var cartBox = cartBoxes[cartBoxes.length - 1];
            var quantityElement = cartBox.getElementsByClassName("cart-quantity")[0];

            quantityElement.value = item.quantity;
        }
    }
    var cartTotal = sessionStorage.getItem("cartTotal");

    if (cartTotal) {
        document.getElementsByClassName("total-price")[0].innerText = "R" + cartTotal;
    }
    updateCartIcon();
}

// Quantity In Cart Icon
function updateCartIcon() {
    var cartBoxes = document.getElementsByClassName('cart-box');
    var quantity = 0;

    for (var i = 0; i < cartBoxes.length; i++) {
        var cartBox = cartBoxes[i];
        var quantityElement = cartBox.getElementsByClassName("cart-quantity")[0];
        quantity += parseInt(quantityElement.value);
    }
    var cartIcon = document.querySelector("#cart-icon");
    cartIcon.setAttribute("data-quantity", quantity);
}

// ------------------------------------------------------------------------------------
const buttons = document.querySelectorAll('.btnn');
const boxes = document.querySelectorAll('.product-box');
const searchBox = document.querySelector("#search");

/* Search Product by Textbox */
searchBox.addEventListener('keyup', (e) => {
    searchText = e.target.value.toLowerCase().trim();

    boxes.forEach((box) => {
        const data = box.dataset.item;
        if (data.includes(searchText)) {
            box.style.display = 'block';
        } else {
            box.style.display = 'none';
        }
    });

    buttons.forEach((button) => {
        button.classList.remove('btn-clicked');
    });
    buttons[0].classList.add('btn-clicked');
});

buttons.forEach((button) => {
    button.addEventListener('click', (e) => {

        e.preventDefault();
        setActiveBtn(e);
        const btnfilter = e.target.dataset.filter;

        boxes.forEach((box => {
            if (btnfilter == 'all') {
                box.style.display = "block";
            } else {

                const boxfilter = box.dataset.item;
                if (btnfilter == boxfilter) {
                    box.style.display = "block";
                } else {
                    box.style.display = "none";
                }

            }
        }));


    });
});


function setActiveBtn(e) {
    buttons.forEach((button) => {
        button.classList.remove('btn-clicked');
    });
    e.target.classList.add('btn-clicked');
}

/**
 * element toggle function
 */

const elemToggleFunc = function (elem) {
    elem.classList.toggle("active");
}



/**
 * navbar toggle
 */

const navbar = document.querySelector("[data-navbar]");
const overlay = document.querySelector("[data-overlay]");
const navCloseBtn = document.querySelector("[data-nav-close-btn]");
const navOpenBtn = document.querySelector("[data-nav-open-btn]");
const navbarLinks = document.querySelectorAll("[data-nav-link]");

const navElemArr = [overlay, navCloseBtn, navOpenBtn];

/**
 * close navbar when click on any navbar link
 */

for (let i = 0; i < navbarLinks.length; i++) {
    navElemArr.push(navbarLinks[i]);
}

/**
 * addd event on all elements for toggling navbar
 */

// for (let i = 0; i < navElemArr.length; i++) {
//     navElemArr[i].addEventListener("click", function () {
//         elemToggleFunc(navbar);
//         elemToggleFunc(overlay);
//     });
// }



/**
 * header active state
 */

const header = document.querySelector("[data-header]");

window.addEventListener("scroll", function () {
    window.scrollY >= 400 ? header.classList.add("active")
            : header.classList.remove("active");
});


// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal///////////////////////////////////////////////////
var btn = document.getElementById("myBtn");


// Get the <span> element that closes the modal///////////////////////////////////////////////////////////
var span = document.getElementsByClassName("close"); // change



// When the user clicks the button, open the modal ///////////////////////////////////////////////////////
btn.onclick = function () { //change
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function () { // change
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) { // change
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Modal Pop Up code 2 *************************************************************

var success_popup = document.getElementById("success");
var error_popup = document.getElementById("error");
var s_close = document.getElementById("s_button");
var e_close = document.getElementById("e_button");
var s_btn = document.getElementById("success_trigger");
var e_btn = document.getElementById("error_trigger");
s_btn.onclick = function () {
    success_popup.style.display = "block";

}
e_btn.onclick = function () {
    error_popup.style.display = "block";
}
s_close.onclick = function () {
    success_popup.style.display = "none";
}
e_close.onclick = function () {
    error_popup.style.display = "none";
}