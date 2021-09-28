let lista = document.querySelector(".responsive-table");

window.onload = function () {

  fetch(`http://localhost:8081/turnos`)
    .then(function (response) {
      return response.json();
    })
    .then(function (turnos) {
      turnos.forEach((turno) => {
        lista.innerHTML += `
                <li class="table-row">
                    <div class="col col-1" data-label="FechaHo">${turno.fechahora}</div>
                    <div class="col col-2" data-label="Nombre">${turno.paciente.nombre} ${turno.paciente.apellido}</div>
                    <div class="col col-3" data-label="Apellido">${turno.odontologo.nombre} ${turno.odontologo.apellido}</div>
                    <div class="col col-4" data-label="Eliminar" value=${turno.id}><button class="eliminar">Eliminar</button></div>
                </li> `;
      });
    })
    .catch(function (error) {
    
        console.log(error);
    });
};


setTimeout(function(){ 

    let botonesBorrar = document.getElementsByClassName("eliminar");
    for (let i = 0; i < botonesBorrar.length; i++){

    botonesBorrar[i].addEventListener("click", eliminarItem);
    }

    function eliminarItem(){

        let id = this.parentNode.getAttribute('value');
        let confirmacion= confirm("¿Está seguro de que desea borrar este campo?")

        if(confirmacion){
        fetch(`http://localhost:8081/turnos/${id}`,{
            
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        } )

        location.reload();
        }        
    }

}, 2000);


    





