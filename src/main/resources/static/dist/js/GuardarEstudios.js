 $(document).ready(function()
{
	 $('#email_envio').on('input', function (evt) 
		{
		  
		 console.log( $('#email_envio').val());
		 if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($('#email_envio').val()))
		 {
			 $( '#boton_bloqueable' ).prop( "disabled", false );
		 }
		 else
		 {
			 $( '#boton_bloqueable' ).prop( "disabled", true );
		 }

		})
		
		$('#boton_modal').hide();

});

function BotonModal()
{
	console.log(lista.length);
	if(lista.length == 0)
	{
		$('#boton_modal').hide();
	}
	else
	{
		$('#boton_modal').show();
	}
}

function GuardarEstudio(id, tip)
{
	//Se recogen variables en base a su id e inicial
	//var tip = $('#T_' + id).val();
	console.log(id, tip);
	var nom = $('#N_' + id + '_' + tip).val();
	var pre = $('#P_' + id + '_' + tip).val();
	var txt = $('#I_' + id + '_' + tip).val();
	var info = $('#INFO_' + txt + '_' + tip).val();
	
	CrearObjetoEstudio("ADD", nom, pre, id, tip, 1, txt, info);
	
	//Se oculta el td que ya se selecciono
	$('#' + txt + '_' + id).hide();
	
	//Y lo pega del otro lado.
	//Este es el que contiene la cantidad
	//$('#TablaEstudios').append('<tr id="' + txt + '_NUEVO_' + id +  '"><td class="col-5">' + nom + '</td><td  class="col-2"> $' + pre + '</td><td class="col-2"><button type="button" onClick="QuitarEstudio(\'' + id + '\')"><i class="fas fa-trash-alt"></i></button></td><td><input type="number" value="1" min="1" id="CANTIDAD_' + txt + '" onchange="CambiarCantidad(\'' + id + '\')"></td></tr>');
	//Este es el que no contiene cantidad
	$('#TablaEstudios').append("<tr id='" + txt + "_NUEVO_" + id +  "'><td class='col-5'>" + nom + "</td><td  class='col-2'> $" + pre + "</td><td class='col-2'><button type='button' onclick=\"QuitarEstudio(\'" + tip + "\'" + ", " + id + ");\"><i class='fas fa-trash-alt'></i></button></td></tr>");
	BotonModal();
}

function QuitarEstudio(tip, id)
{
	var nom = $('#N_' + id + '_' + tip).val();
	var pre = $('#P_' + id + '_' + tip).val();
	var txt = $('#I_' + id + '_' + tip).val();
	var info = $('#INFO_' + txt + '_' + tip).val();
	
	CrearObjetoEstudio("REST", nom, pre, id, tip, 1, txt, info);
	
	$('#' + txt + '_' + id).show();
	$('#' + txt + '_NUEVO_' + id).remove();
	BotonModal();
}

function CambiarCantidad(id, tip)
{
	var nom = $('#N_' + id + '_' + tip).val();
	var pre = $('#P_' + id + '_' + tip).val();
	var txt = $('#I_' + id + '_' + tip).val();
	var info = $('#INFO_' + txt + '_' + tip).val();
	var cantidad = $('#CANTIDAD_' + txt).val();
	
	CrearObjetoEstudio("ALTER", nom, pre, id, tip, cantidad, txt, info);
	BotonModal();
}


function CrearObjetoEstudio(accion, nom, pre, id, tip, can, txt, info)
{	
	console.log(accion, nom, pre, id, tip, can, txt, info);
	
	switch(accion) 
	{
	  case "ADD":
	    // Se añade un solo estudio al objeto
		  console.log("Voy a añadir el estudio : " + nom + " por primera vez");
		  
		  //Llenamos los array con los datos
		  ides[id] = tip;
		  cantidades[id + "_" + tip] = can;
		  
		  //Estos array son para cantidades
		  var temp = {id: id + '_' + txt, precio: pre, can: can, id_real: id, tipo: tip, nom: nom, info: info};
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
		  var temp = {id: id + '_' + txt, precio: pre, can: can, id_real: id, tipo: tip, nom: nom, info: info};
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
	
	console.log(envio);
	
	//Se calculan los precios
	var monto = 0;
	for (const prop in lista) 
	{		
		monto += parseFloat(lista[prop].can) * parseFloat(lista[prop].precio);
	}
	
	var correo = $('#email_envio').val();
	console.log(correo);


	//Solicitud Ajax
    $.ajax({
        type: "POST",
        url: "/prueba_solicitud",
        data: { data: envio, id: paciente_id, monto: monto, correo: correo},
        success: (data) => {
            //$("#error").text(data);
        	location.reload();
        	
        },
        error: (e) => {
        	location.reload();
        }
      });
    alert("Se ha enviado el correo");
    location.reload();
}
