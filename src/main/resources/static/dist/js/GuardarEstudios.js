 $(document).ready(function(){
	 

});

 
function GuardarEstudio(id)
{
	//Se recogen variables en base a su id e inicial
	var nom = $('#N_' + id).val();
	var pre = $('#P_' + id).val();
	var tip = $('#T_' + id).val();
	var txt = $('#I_' + id).val();
	
	CrearObjetoEstudio("ADD", nom, pre, id, tip, 1, txt);
	
	//Se oculta el td que ya se selecciono
	$('#' + txt + '_' + id).hide();
	
	//Y lo pega del otro lado.
	//Este es el que contiene la cantidad
	//$('#TablaEstudios').append('<tr id="' + txt + '_NUEVO_' + id +  '"><td class="col-5">' + nom + '</td><td  class="col-2"> $' + pre + '</td><td class="col-2"><button type="button" onClick="QuitarEstudio(\'' + id + '\')"><i class="fas fa-trash-alt"></i></button></td><td><input type="number" value="1" min="1" id="CANTIDAD_' + txt + '" onchange="CambiarCantidad(\'' + id + '\')"></td></tr>');
	//Este es el que no contiene cantidad
	$('#TablaEstudios').append('<tr id="' + txt + '_NUEVO_' + id +  '"><td class="col-5">' + nom + '</td><td  class="col-2"> $' + pre + '</td><td class="col-2"><button type="button" onClick="QuitarEstudio(\'' + id + '\')"><i class="fas fa-trash-alt"></i></button></td></tr>');
}

function QuitarEstudio(id)
{
	var pre = $('#P_' + id).val();
	var nom = $('#N_' + id).val();
	var tip = $('#T_' + id).val();
	var txt = $('#I_' + id).val();
	
	CrearObjetoEstudio("REST", nom, pre, id, tip, 1, txt);
	
	$('#' + txt + '_' + id).show();
	$('#' + txt + '_NUEVO_' + id).remove();
}

function CambiarCantidad(id)
{
	var pre = $('#P_' + id).val();
	var nom = $('#N_' + id).val();
	var tip = $('#T_' + id).val();
	var txt = $('#I_' + id).val();
	var cantidad = $('#CANTIDAD_' + txt).val();
	
	CrearObjetoEstudio("ALTER", nom, pre, id, tip, cantidad, txt);
}


function CrearObjetoEstudio(accion, nom, pre, id, tip, can, txt)
{	
	console.log(accion, nom, pre, id, tip, can, txt);
	
	switch(accion) 
	{
	  case "ADD":
	    // Se añade un solo estudio al objeto
		  console.log("Voy a añadir el estudio : " + nom + " por primera vez");
		  
		  //Llenamos los array con los datos
		  ides[id] = tip;
		  cantidades[id + "_" + tip] = can;
		  
		  //Estos array son para cantidades
		  var temp = {id: id + '_' + txt, precio: pre, can: can, id_real: id, tipo: tip};
		  lista.push(temp);
		  		  
		  break;
	  case "REST":
	    // Se quita un solo estudio al objeto
		  console.log("Voy a quitar el estudio : " + nom + " por completo");
		  delete ides[id];
		  delete cantidades[id + "_" + tip];
		  
		  //Se borra tambien lo de los precios
		  var removeIndex = lista.map(function(item) { return item.id; }).indexOf(id + '_' + txt);
		  lista.splice(removeIndex, 1);
		  
		  break;
	  case "ALTER":
		 // Se suma uno al estudio ya creado
		  console.log("Voy a cambiar la cantidad de un estudio");
		  
		 //Se borra la cantidad incial con todo y key
		  var removeIndex = lista.map(function(item) { return item.id; }).indexOf(id + '_' + txt);
		  lista.splice(removeIndex, 1);
		 
		  //Se almacena la nueva con la nueva cantidad
		  var temp = {id: id + '_' + txt, precio: pre, can: can, id_real: id, tipo: tip};
		  lista.push(temp);
		  
		  break;	
	  default:
	    // code block
	}
	
	//Se calculan los precios
	var sum = 0;
	for (const prop in lista) 
	{		
		sum += parseFloat(lista[prop].can) * parseFloat(lista[prop].precio);
	}

	
	//Ya aqui se pinta el DOM
	//Se pintan en la tabla
	$('#TOTAL').text(" ");
	$('#TOTAL').text("$" + sum);
}


function Guardar() 
{
	//Se recoge paciente
	var paciente_id = $('#paciente_id').val();
	
	//Se hace el string
	envio = null;
	envio = JSON.stringify(lista);
	
	//Se calculan los precios
	var monto = 0;
	for (const prop in lista) 
	{		
		monto += parseFloat(lista[prop].can) * parseFloat(lista[prop].precio);
	}
	
	var correo = $('#email_envio').val();
	
	if(correo != null)
	{
		
	}
	else
		{
		
		}
	
	//Solicitud Ajax
    $.ajax({
        type: "POST",
        url: "/prueba_solicitud",
        data: { data: envio, id: paciente_id, monto: monto },
        success: (data) => {
            $("#error").text(data);
        },
        error: (e) => {
            $("#error").text(e.responseText);
        }
      });
}
