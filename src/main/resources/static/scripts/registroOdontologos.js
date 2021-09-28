let nombre = document.querySelector(".nombre");
let apellido= document.querySelector(".apellido")
let matricula= document.querySelector(".matricula")
let mensaje= document.querySelector(".mensaje")
let form = document.querySelector("form");
let data = {
    nombre: '',
    apellido: '',
    matricula:0
};


window.onload = function () {

    form.addEventListener("submit", function (e) {
    e.preventDefault();
    
    data.nombre= nombre.value;
    data.apellido= apellido.value;
    data.matricula= matricula.value;
    console.log(data);
    fetch(`http://localhost:8081/odontologos/registrar`,{
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
        'Content-Type': 'application/json'
        }
    })
    mensaje.style.visibility = "visible";
    form.reset();
})};


