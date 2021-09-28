let lista = document.querySelector(".responsive-table");
let formulario = document.querySelector("form");
let idForm = document.querySelector(".Id");
let mensaje= document.querySelector(".mensaje");

window.onload = function () {
  formulario.addEventListener("submit", function (e) {
    e.preventDefault();

    console.log(idForm.value);
    fetch(`http://localhost:8081/pacientes/${idForm.value}`)
      .then(function (response) {
        return response.json();
      })
      .then(function (paciente) {
            lista.innerHTML = `
                    <li class="table-header">
                        <div class="col col-1">Id</div>
                        <div class="col col-2">Nombre</div>
                        <div class="col col-3">Apellido</div>
                        <div class="col col-4">Eliminar</div>
                    </li>
                    <li class="table-row">
                        <div class="col col-1" data-label="DNI">${paciente.id}</div>
                        <div class="col col-2" data-label="Nombre">${paciente.nombre}</div>
                        <div class="col col-3" data-label="Apellido">${paciente.apellido}</div>
                        <div class="col col-4" data-label="Eliminar" value=${paciente.id}><button class="eliminar">Eliminar</button></div>
                    </li> `;

                    mensaje.style.visibility = "hidden";
                    let botonBorrar = document.querySelector(".eliminar")

                    botonBorrar.addEventListener("click", function(e){

                        e.preventDefault();

                        function eliminarItem() {

                            let confirmacion = confirm(
                              "¿Está seguro de que desea borrar este campo?"
                            );

                            if (confirmacion) {
                              fetch(`http://localhost:8081/pacientes/${idForm.value}`, {
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


      })
      .catch(function (error) {
        console.log(error);
        mensaje.style.visibility = "visible";
      });
  });


};
