async function crearProducto() {
    const formData = new FormData();
    formData.append('descripcion', txtDescripcion.value.trim());
    formData.append('precio', txtPrecio.value.trim());
    formData.append('stock', txtStock.value.trim());
    formData.append('file', uplFoto.files[0]);
    const response = await fetch('http://localhost:8081/productos', {
        method: 'POST',
        body: formData,
    });

    switch (response.status) {
        case 201:
            alert('Producto creado correctamente.');
            break;
        case 400:
            alert('Error 400. Algo mandamos mal.');
            break;
        default:
            alert('Error grave.');
            break;
    }
}
