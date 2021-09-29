let lista = document.querySelector(".responsive-table");

window.onload = function () {

  fetch(`http://localhost:8081/odontologos`)
    .then(function (response) {
      return response.json();
    })
    .then(function (odontologos) {
      odontologos.forEach((odontologo) => {
        lista.innerHTML += `
                <li class="table-row">
                    <div class="col col-1" data-label="Matricula">${odontologo.id}</div>
                    <div class="col col-2" data-label="Matricula">${odontologo.matricula}</div>
                    <div class="col col-3" data-label="Nombre">${odontologo.nombre}</div>
                    <div class="col col-4" data-label="Apellido">${odontologo.apellido}</div>
                    <div class="col col-5" data-label="Eliminar" value=${odontologo.id}><button class="eliminar">Eliminar</button></div>
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
        botonesBorrrar[i].addEventListener("click", modificarItem)
    }

    function eliminarItem(){

        let id = this.parentNode.getAttribute('value');
        let confirmacion= confirm("¿Está seguro de que desea borrar este campo?")

        if(confirmacion){
        fetch(`http://localhost:8081/odontologos/${id}`,{

            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        } )

        location.reload();
        }
    }


}, 2000);








