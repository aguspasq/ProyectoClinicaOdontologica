let odontologo = document.querySelector(".odontologo");
let paciente= document.querySelector(".paciente");
let fecha= document.querySelector(".fecha");
let mensaje= document.querySelector(".mensaje");
let form = document.querySelector("form");
let data = {
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
    
    data.odontologo.id= odontologo.value;
    data.paciente.id= paciente.value;
    data.fechahora= fecha.value.replace(/T/, ' ');
    
        
    fetch(`http://localhost:8081/turnos/registrar`,{
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
        'Content-Type': 'application/json'
        } 
    })
    mensaje.style.visibility = "visible";
    form.reset();
})};



