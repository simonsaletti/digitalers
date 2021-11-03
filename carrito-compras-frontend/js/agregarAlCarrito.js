const listarProductos = () => {
    fetch('http://localhost:8081/productos')
        .then((resp) => resp.json())
        .then((data) => {
            if (data.status === 200) {
                if (data.productos.length > 0) {
                    data.productos.forEach((producto) => {
                        document.querySelector('#lista-productos > div').appendChild(crearCard(producto));
                    });
                } else {
                    // const renglones = document.querySelector('#lista-productos tr');
                    // if (renglones != null) {
                    //     renglones.remove();
                    // }
                    // parrafoError = document.querySelector('#msjError');
                    // parrafoError.innerHTML = 'No hay productos disponibles.';
                }
            } else {
                alert(data.status + ' ' + data.mensaje);
            }
        });
};

const crearCard = (producto) => {
    const col = document.createElement('div');
    col.classList.add('col-4');
    col.innerHTML = `
    <div class="card">
    <img src="http://localhost:8081/img/${producto.urlimagen}" class="card-img-top" alt="${producto.descripcion}">
    <div class="card-body">
      <h5 class="card-title">${producto.descripcion}</h5>
      <p class="card-text">${producto.precio}</p>
      <a href="#" class="btn btn-primary">Agregar</a>
    </div>
  </div>
    `;
    return col;
};

listarProductos();
