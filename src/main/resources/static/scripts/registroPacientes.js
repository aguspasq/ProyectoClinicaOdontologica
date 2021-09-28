let nombre = document.querySelector(".nombre");
let apellido= document.querySelector(".apellido");
let dni= document.querySelector(".dni");
let calle = document.querySelector(".calle");
let numero = document.querySelector(".numero");
let localidad= document.querySelector(".localidad");
let provincia= document.querySelector(".provincia")
let mensaje= document.querySelector(".mensaje");
let form = document.querySelector("form");
let data = {
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
    
    data.nombre= nombre.value;
    data.apellido= apellido.value;
    data.dni= dni.value;
    data.domicilio.calle= calle.value;
    data.domicilio.numero= numero.value;
    data.domicilio.localidad= localidad.value;
    data.domicilio.provincia= provincia.value;
    console.log(data);
    fetch(`http://localhost:8081/pacientes/registrar`,{
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
        'Content-Type': 'application/json'
        } 
    })
    mensaje.style.visibility = "visible";
    form.reset();
})};


