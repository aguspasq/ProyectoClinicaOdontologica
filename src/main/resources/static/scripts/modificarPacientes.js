let nombre = document.querySelector(".nombre");
let apellido= document.querySelector(".apellido");
let dni= document.querySelector(".dni");
let calle = document.querySelector(".calle");
let numero = document.querySelector(".numero");
let localidad= document.querySelector(".localidad");
let provincia= document.querySelector(".provincia")
let idForm=document.querySelector(".Id")
let mensaje= document.querySelector(".mensaje")
let mensajeb= document.querySelector(".mensajeb");
let formulario = document.querySelector(".formBuscar")
let form = document.querySelector(".form");
let contenedor= document.querySelector(".container")
let data = {
    id: 0,
    nombre: '',
    apellido: '',
    dni:0,
    domicilio:{
        calle: '',
        numero:0,
        localidad: '',
        provincia:''
    }
};


window.onload = function () {

    form.addEventListener("submit", function (e) {
    e.preventDefault();
    data.id = idForm.value;
    data.nombre= nombre.value;
    data.apellido= apellido.value;
    data.dni= dni.value;
    data.domicilio.calle= calle.value;
    data.domicilio.numero= numero.value;
    data.domicilio.localidad= localidad.value;
    data.domicilio.provincia= provincia.value;

    try{
        fetch(`http://localhost:8081/pacientes/${idForm.value}`,{
        method: 'PUT',
        headers: {
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    mensaje.style.visibility = "visible";
    form.reset();
    }catch(error) {
        mensaje.innerHTML = error;
        mensaje.style.visibility = "visible";
        console.log(error);
    };
  });

  formulario.addEventListener("submit", function (e) {
    e.preventDefault();


    fetch(`http://localhost:8081/pacientes/${idForm.value}`)
      .then(function (response) {
        return response.json();
      })
      .then(function (paciente) {
        console.log(paciente);
        nombre.value = paciente.nombre;
        apellido.value = paciente.apellido;
        dni.value= paciente.dni;
        calle.value = paciente.domicilio.calle;
        numero.value= paciente.domicilio.numero;
        localidad.value= paciente.domicilio.localidad;
        provincia.value= paciente.domicilio.provincia;
        mensajeb.style.visibility = "hidden";


      })
      .catch(function (error) {
        console.log(error);
        mensajeb.innerHTML =error;
        mensajeb.style.visibility = "visible";
      });
      form.reset()
})};






