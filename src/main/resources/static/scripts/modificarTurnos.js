let odontologo = document.querySelector(".odontologo");
let odontoApellido= document.querySelector(".odontologoApellido");
let paciente= document.querySelector(".paciente");
let pacienteApellido= document.querySelector(".pacienteApellido");
let fecha= document.querySelector(".fecha");
let idForm=document.querySelector(".Id")
let mensaje= document.querySelector(".mensaje")
let mensajeb= document.querySelector(".mensajeb");
let formulario = document.querySelector(".formBuscar")
let form = document.querySelector(".form");
let contenedor= document.querySelector(".container")
let data = {
    id: 0,
    odontologo:{
        id:0
    } ,
    paciente: {
        id:0
    },
    fechahora: 0
};


window.onload = function () {

    form.addEventListener("submit", function (e) {
    e.preventDefault();

    data.id= JSON.parse(idForm.value);
    data.odontologo.id= odontologo.value;
    data.paciente.id= paciente.value;
    data.fechahora= fecha.value.replace(/T/, ' ');
    console.log(data);

    try{
        fetch(`http://localhost:8081/turnos/${idForm.value}`,{
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


    fetch(`http://localhost:8081/turnos/${idForm.value}`)
      .then(function (response) {
        return response.json();
      })
      .then(function (turno) {
        odontologo.value = turno.odontologo.id;
        odontoApellido.value = turno.odontologo.apellido;
        paciente.value = turno.paciente.id;
        pacienteApellido.value = turno.paciente.apellido;
        fecha.value = turno.fechahora.replace(' ','T');
        mensajeb.style.visibility = "hidden";


      })
      .catch(function (error) {
        console.log(error);
        mensajeb.innerHTML =error;
        mensajeb.style.visibility = "visible";
      });

      form.reset()
})};






