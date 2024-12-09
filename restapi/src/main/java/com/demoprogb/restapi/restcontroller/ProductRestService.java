package com.demoprogb.restapi.restcontroller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demoprogb.objects.entities.Marca;
import com.demoprogb.objects.entities.Producto;
import com.demoprogb.restapi.dao.ProductDAO;

@RestController
@RequestMapping(value="/rest")
public class ProductRestService {

@Autowired
private ProductDAO productoDAO;

@RequestMapping(value="/welcome")
public String welcome(){
	return "azukcita la enana mas enana delincuente de la historia";
}

@RequestMapping(value = "/currentDatetime")
public String currentDatetime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    return LocalDateTime.now().format(formatter);
}

@RequestMapping(value = "/reverseString")
public String reverseString(@RequestParam String string) {
    StringBuilder html = new StringBuilder();
    StringBuilder sb=new StringBuilder(string);
    html.append("<p style=\"color: green; margin: 0;adding: 0; font-size:1.1em; \"><b>¡Hola! he recibido este mensaje: </b>[MESSAGE_IN]</p>");
    html.append("<br>");
    html.append("<p style=\"color: red; font-family:Verdana; margin: 0;adding: 0; font-size:1.1em;\">Y ahora te lo devuelvo   invertido: <b>[MESSAGE_OUT]</b></p>");
    return html.toString().replace("[MESSAGE_IN]", string).replace("[MESSAGE_OUT]",sb.reverse());
}

@GetMapping(value = "getProducto")
@ResponseStatus(HttpStatus.OK)
@ResponseBody
public ResponseEntity<Producto> getProducto(@RequestParam String productoId) {
		Producto producto = productoDAO.getProducto(Integer.parseInt(productoId));
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
@GetMapping(value = "getProductos")
@ResponseStatus(HttpStatus.OK)
@ResponseBody
public ResponseEntity<Collection<Producto>> getProductos(@RequestParam String filterName) {
		Collection<Producto> productos = productoDAO.getProductos(filterName);
		return new ResponseEntity<Collection<Producto>>(productos, HttpStatus.OK);
}
    
@PostMapping(value = "/saveProducto")
@ResponseStatus(HttpStatus.CREATED)
@ResponseBody
public void saveMarca(@RequestBody Producto producto){
        productoDAO.save(producto);
    }
    
@PostMapping(value = "/deleteProducto")
@ResponseStatus(HttpStatus.OK)
@ResponseBody
public void deleteMarca(@RequestBody Producto producto){
        productoDAO.delete(producto);
}    
	
@GetMapping(value = "/getMarcas")
@ResponseStatus(HttpStatus.OK)
@ResponseBody
public ResponseEntity<List<Marca>> getMarcas(@RequestParam String filterName) {
	List<Marca> marcas = productoDAO.getMarcas(filterName);
		return new ResponseEntity<List<Marca>>(marcas, HttpStatus.OK);
}
    
@PostMapping(value = "/saveMarca")
@ResponseStatus(HttpStatus.CREATED)
@ResponseBody
public void saveMarca(@RequestBody Marca marca){
      productoDAO.save(marca);
}
    
@PostMapping(value = "/deleteMarca")
@ResponseStatus(HttpStatus.OK)
@ResponseBody
public void deleteMarca(@RequestBody Marca marca){
        productoDAO.delete(marca);
}


}
