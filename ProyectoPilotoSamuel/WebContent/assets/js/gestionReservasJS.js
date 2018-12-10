/**
 * 
 */
$(document).ready( function () {
	$('#tablaReservas').DataTable();
} );

document.getElementById("mascota").addEventListener("click", tieneMascota);
document.getElementById("mascotas").addEventListener("change", tipoMascota);
document.getElementById("centroTuristicoId").addEventListener("change", reservas_centroTuristico);

function reservas_centroTuristico(){
	var cliente = new Object();
	var centroTuristico = new Object();
		
	centroTuristico.id = document.getElementById("centroTuristicoId").value;
	
	var strJSONCentroTuristico = JSON.stringify(centroTuristico);
	
	var xhttp = new XMLHttpRequest();

	// Preparar para recibir la respuesta
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			var arrayRespuestaRecibida = JSON.parse(this.responseText);
			// Renderizar la respuesta en un elemento HTML de la
			// pagina
			var listaDesordenada = "";
			for(i = 0; i < arrayRespuestaRecibida.length; i++) {
				listaDesordenada += "<li>" + arrayRespuestaRecibida[i].nombre + " " 
				              +  arrayRespuestaRecibida[i].primerApellido + " " 
				              + arrayRespuestaRecibida[i].segundoApellido + "</li>";
				
		    }

			document.getElementById("listaDesordenada").innerHTML = listaDesordenada;
		}                               
	};


	// Crear y enviar la peticion
	xhttp.open("POST", "AJAXManager", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("jsondata=" + strJSONCentroTuristico);
};
	
	



function tipoMascota() {
	var mascotasSeleccionadas = [];
	for (i = 0; i < this.options.length; i++) {
		if (this.options[i].selected) {
			mascotasSeleccionadas.push(this.options[i].value);
		}

	}

	// Comprobar en la consola que esta funcionando bien
	console.log(mascotasSeleccionadas);
	if (mascotasSeleccionadas.includes("O")) {
		document.getElementById("descTipoMascota").style = "visibility:visible";
	} else {
		document.getElementById("descTipoMascota").style = "visibility:hidden";
	}
}

function tieneMascota() {
	if (this.checked) {
		document.getElementById("contenedorMascotas").style = "visibility:visible";
	} else {
		document.getElementById("contenedorMascotas").style = "visibility:hidden";
	}
};