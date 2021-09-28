let menuPaciente= document.querySelector("#menuPaciente");
let seccion = document.querySelector(".seccion");

seccion.addEventListener('mouseover',function(e) {
    e.preventDefault();

    menuPaciente.style.visibility = "visible";

})  

menuPaciente.addEventListener('mouseover',function(e) {
    e.preventDefault();

    menuPaciente.style.visibility = "visible";

})  

menuPaciente.addEventListener('mouseout',function(e) {
    e.preventDefault();

    menuPaciente.style.visibility = "hidden";

})  

