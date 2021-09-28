let lista = document.querySelector(".responsive-table");
let formulario = document.querySelector("form");
let idForm = document.querySelector(".Id");
let mensaje= document.querySelector(".mensaje");

window.onload = function () {
  formulario.addEventListener("submit", function (e) {
    e.preventDefault();

    console.log(idForm.value);
    fetch(`http://localhost:8081/turnos/${idForm.value}`)
      .then(function (response) {
        return response.json();
      })
      .then(function (turno) {

        console.log(turno.paciente.apellido);
            lista.innerHTML = `
                    <li class="table-header">
                        <div class="col col-1">Id</div>
                        <div class="col col-2">Paciente</div>
                        <div class="col col-3">Odontologo   </div>
                        <div class="col col-4">Eliminar</div>
                    </li>
                    <li class="table-row">
                        <div class="col col-1" data-label="DNI">${turno.id}</div>
                        <div class="col col-2" data-label="Nombre">${turno.paciente.apellido}</div>
                        <div class="col col-3" data-label="Apellido">${turno.odontologo.apellido}</div>
                        <div class="col col-4" data-label="Eliminar" value=${turno.paciente.id}><button class="eliminar">Eliminar</button></div>
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
