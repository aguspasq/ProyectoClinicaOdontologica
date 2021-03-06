let lista = document.querySelector(".responsive-table");
let formularioApellido = document.querySelector(".form");
let ApForm = document.querySelector(".apellido");
let mensajeAp= document.querySelector(".mensaje");


window.onload = function () {
  formularioApellido.addEventListener("submit", function (e) {
    e.preventDefault();
    lista.innerHTML = `<li class="table-header">
                            <div class="col col-1">Id</div>
                            <div class="col col-2">Nombre</div>
                            <div class="col col-3">Apellido</div>
                            <div class="col col-4">Eliminar</div>
                       </li>`;

    fetch(`http://localhost:8081/pacientes/buscar/${ApForm.value}`)
      .then(function (response) {
        return response.json();
      })
      .then(function (pacientes) {
            pacientes.forEach((paciente) => {
                        console.log(paciente)
                        lista.innerHTML += `
                                <li class="table-row">
                                    <div class="col col-1" data-label="DNI">${paciente.id}</div>
                                    <div class="col col-2" data-label="Nombre">${paciente.nombre}</div>
                                    <div class="col col-3" data-label="Apellido">${paciente.apellido}</div>
                                    <div class="col col-4" data-label="Eliminar" value=${paciente.id}><button class="eliminar">Eliminar</button></div>
                                </li> `;

                                mensajeAp.style.visibility = "hidden";
                                let botonBorrar = document.querySelector(".eliminar")

                                botonBorrar.addEventListener("click", function(e){

                                    e.preventDefault();

                                    function eliminarItem() {

                                        let confirmacion = confirm(
                                          "¿Está seguro de que desea borrar este campo?"
                                        );

                                        if (confirmacion) {
                                          fetch(`http://localhost:8081/pacientes/${ApForm.value}`, {
                                            method: "DELETE",
                                            headers: {
                                              "Content-Type": "application/json",
                                            },
                                          });

                                          location.reload();
                                    }
                                    }
                                eliminarItem();
                                }

                        )


            } )
      })
      .catch(function (error) {
        console.log(error);
        mensajeAp.style.visibility = "visible";
      });
  });


};
