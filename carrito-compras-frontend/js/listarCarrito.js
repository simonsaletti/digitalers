const listarCarrito = () => {
    // Recuperar productos de session storage.
    let carritoTemp = JSON.parse(sessionStorage.getItem('carrito')) || [];

    const table = document.querySelector('#lista-productos');

    table.innerHTML = `<tr class= "cabecera">
                    <th>id</th>
                    <th>Cantidad</th>
                    <td>Precio unitario</td>
                    <td>Total</td>
                    </tr>`;

    carritoTemp.forEach((item) => {
        table.innerHTML += `<tr>
    <td>${item.idProducto}</td>
    <td>${item.cantidad}</td>
    <td>${item.precio}</td>
    <td>${item.cantidad * item.precio}</td>
    </tr>`;
    });
};

listarCarrito();
