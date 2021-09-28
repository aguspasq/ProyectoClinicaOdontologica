let lista = document.querySelector(".responsive-table");

window.onload = function () {

  fetch(`http://localhost:8081/pacientes`)
    .then(function (response) {
      return response.json();
    })
    .then(function (pacientes) {
      pacientes.forEach((paciente) => {
        lista.innerHTML += `
                <li class="table-row">
                    <div class="col col-1" data-label="DNI">${paciente.id}</div>
                    <div class="col col-2" data-label="Nombre">${paciente.nombre}</div>
                    <div class="col col-3" data-label="Apellido">${paciente.apellido}</div>
                    <div class="col col-4" data-label="Eliminar" value=${paciente.id}><button class="eliminar">Eliminar</button></div>
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
        fetch(`http://localhost:8081/pacientes/${id}`,{
            
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        } )

        location.reload();
        }
    }

}, 2000);


    





