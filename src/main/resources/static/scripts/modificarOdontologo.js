let nombre = document.querySelector(".nombre");
let apellido= document.querySelector(".apellido")
let matricula= document.querySelector(".matricula")
let idForm=document.querySelector(".Id")
let mensaje= document.querySelector(".mensaje")
let mensajeb= document.querySelector(".mensajeb");
let formulario = document.querySelector(".formBuscar")
let form = document.querySelector(".form");
let contenedor= document.querySelector(".container")
let data = {
    id: 0,
    matricula:0,
    nombre: '',
    apellido: ''
};


window.onload = function () {

    form.addEventListener("submit", function (e) {
    e.preventDefault();

    data.id= idForm.value;
    data.nombre= nombre.value;
    data.apellido= apellido.value;
    data.matricula= matricula.value;
    console.log(JSON.stringify(data));
    try{
        fetch(`http://localhost:8081/odontologos/${idForm.value}`,{
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
        'Content-Type': 'application/json'
        }
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


    fetch(`http://localhost:8081/odontologos/${idForm.value}`)
      .then(function (response) {
        return response.json();
      })
      .then(function (odontologo) {
        nombre.value = odontologo.nombre;
        apellido.value = odontologo.apellido;
        matricula.value = odontologo.matricula;
        mensajeb.style.visibility = "hidden";


      })
      .catch(function (error) {
        console.log(error);
        mensajeb.innerHTML =error;
        mensajeb.style.visibility = "visible";
      });
})};






