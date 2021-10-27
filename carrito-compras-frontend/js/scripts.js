const listarProductos = () => {
    fetch('http://localhost:8081/productos')
        .then((resp) => resp.json())
        .then((data) => {
            if (data.status === 200) {
                const table = document.querySelector('#lista-productos');
                if (data.productos.length > 0) {
                    table.innerHTML = `<tr class= "cabecera">
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Acciones</th>
                    </tr>`;

                    data.productos.forEach((producto) => {
                        table.innerHTML += `<tr>
                    <td>${producto.descripcion}</td>
                    <td>${producto.precio}</td>
                    <td><input type="number" min="1" max="${producto.stock}" value="1"/></td>
                    <td><button type="button" class="btn-agregar-carrito" id="${producto.id}">Agregar al carrito</button></td>
                    </tr>`;
                    });

                    const botones = document.querySelectorAll('.btn-agregar-carrito');
                    botones.forEach((boton) => {
                        boton.addEventListener('click', () => {
                            const idProducto = boton.getAttribute('id');
                            const cantidad = Number(boton.parentElement.parentElement.cells[2].firstChild.value);
                            const precio = Number(boton.parentElement.parentElement.cells[1].firstChild.textContent);

                            // Recuperar productos de session storage.
                            let carritoTemp = JSON.parse(sessionStorage.getItem('carrito')) || [];

                            const indiceProductoActual = carritoTemp.findIndex((producto) => producto.idProducto === idProducto);

                            if (indiceProductoActual > -1) {
                                carritoTemp[indiceProductoActual] = { idProducto: idProducto, cantidad: cantidad, precio: precio };
                            } else {
                                carritoTemp.push({ idProducto: idProducto, cantidad: cantidad, precio: precio });
                            }

                            // Guardamos el producto en session storage.
                            sessionStorage.setItem('carrito', JSON.stringify(carritoTemp));
                        });
                    });
                } else {
                    const renglones = document.querySelector('#lista-productos tr');
                    if (renglones != null) {
                        renglones.remove();
                    }
                    parrafoError = document.querySelector('#msjError');
                    parrafoError.innerHTML = 'No hay productos disponibles.';
                }
            } else {
                alert(data.status + ' ' + data.mensaje);
            }
        });
};

listarProductos();
