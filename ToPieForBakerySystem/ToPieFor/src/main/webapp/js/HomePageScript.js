/**
 * element toggle function
 */

const elemToggleFunc = function (elem) { elem.classList.toggle("active"); }



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

for (let i = 0; i < navbarLinks.length; i++) { navElemArr.push(navbarLinks[i]); }

/**
 * addd event on all elements for toggling navbar
 */

for (let i = 0; i < navElemArr.length; i++) {
    navElemArr[i].addEventListener("click", function () {
        elemToggleFunc(navbar);
        elemToggleFunc(overlay);
    });
}
   
/**
 * header active state
 */

const header = document.querySelector("[data-header]");

window.addEventListener("scroll", function () {
    window.scrollY >= 400 ? header.classList.add("active")
        : header.classList.remove("active");
}); 
// Get the search input and filter select elements
const searchInput = document.getElementById('search-input');
const filterSelect = document.getElementById('filter-select');

// Get the product list container
const productList = document.getElementById('product-list');

// Add event listeners for search input and filter select
searchInput.addEventListener('input', filterProducts);
filterSelect.addEventListener('change', filterProducts);

// Filter products based on search input and filter select
function filterProducts() {
  const searchValue = searchInput.value.toLowerCase();
  const filterValue = filterSelect.value;

  const productBoxes = Array.from(document.getElementsByClassName('product-box'));

  productBoxes.forEach((productBox) => {
    const itemName = productBox.querySelector('.product-title').innerText.toLowerCase();
    const price = productBox.querySelector('.price').innerText.toLowerCase();

    if (filterValue === 'name' && itemName.includes(searchValue)) {
      productBox.style.display = 'block';
    } else if (filterValue === 'price' && price.includes(searchValue)) {
      productBox.style.display = 'block';
    } else if (filterValue === '' && (itemName.includes(searchValue) || price.includes(searchValue))) {
      productBox.style.display = 'block';
    } else {
      productBox.style.display = 'none';
    }
  });
}
