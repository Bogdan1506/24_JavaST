function addToCart(id) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("cart").innerHTML = this.responseText;
        }
    };
    const dough_param = $(".dough_param:checked").val();
    const size_param = $(".size_param:checked").val();
    xhttp.open("GET", "/item/cart/create?id=" + id + "&dough=" +
        dough_param + "&size=" + size_param, true);
    xhttp.send();
}