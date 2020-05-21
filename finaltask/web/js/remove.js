function remove(value) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("cart").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "/item/cart/remove?id=" + value, true);
    xhttp.send();
}
