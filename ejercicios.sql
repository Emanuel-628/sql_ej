/*select nombre, apellido, nro_cedula, count(cliente.id) from cliente join factura on cliente.id = factura.cliente_id 
group by nombre, apellido, nro_cedula order by count(cliente.id) desc;*/

/*select cliente.nombre, cliente.apellido, cliente.nro_cedula, (producto.precio*factura_detalle.cantidad) from ((cliente inner join factura on cliente.id = factura.cliente_id) 
inner join  factura_detalle on factura.id = factura_detalle.factura_id) inner join 
producto on factura_detalle.producto_id = producto.id 
group by cliente.nombre, cliente.apellido, cliente.nro_cedula, producto.precio,factura_detalle.cantidad,(producto.precio*factura_detalle.cantidad) 
order by (producto.precio*factura_detalle.cantidad) desc;*/

/*select moneda.nombre, count(moneda.id) from ((cliente inner join factura on cliente.id = factura.cliente_id) inner join
moneda on factura.moneda_id = moneda.id)
group by moneda.nombre
order by count (moneda.id) desc;
*/

/*select proveedor.nombre, count(proveedor.id) from (proveedor inner join producto on proveedor.id = producto.proveedor_id) group by proveedor.nombre
order by count(proveedor.id) desc;*/

/*select producto.nombre,factura_detalle.cantidad from (producto inner join factura_detalle on factura_detalle.producto_id = producto.id) group by producto.nombre,factura_detalle.cantidad
order by factura_detalle.cantidad desc;*/

/*select producto.nombre,factura_detalle.cantidad from (producto inner join factura_detalle on factura_detalle.producto_id = producto.id) group by producto.nombre,factura_detalle.cantidad
order by factura_detalle.cantidad asc;*/

/*select factura.fecha_emision, cliente.nombre, cliente.apellido, producto.nombre, factura_detalle.cantidad, factura_tipo.nombre from ((( cliente inner join factura on factura.cliente_id = cliente.id)
inner join factura_detalle on factura_detalle.factura_id = factura.id) inner join factura_tipo on factura_tipo.id = factura.factura_tipo_id) inner join producto on producto.id =factura_detalle.producto_id
group by factura.fecha_emision, cliente.nombre, cliente.apellido, producto.nombre, factura_detalle.cantidad, factura_tipo.nombre;*/

/*select factura_detalle.cantidad from factura_detalle inner join factura on factura_detalle.factura_id = factura.id group by factura_detalle.cantidad
order by factura_detalle.cantidad desc;*/

/*select ((producto.precio*factura_detalle.cantidad)/11) from ((factura inner join factura_detalle on factura_detalle.id = factura_detalle.factura_id) 
inner join producto on factura_detalle.producto_id = producto.id) 
group by ((producto.precio*factura_detalle.cantidad)/11)
order by ((producto.precio*factura_detalle.cantidad)/11) desc;*/