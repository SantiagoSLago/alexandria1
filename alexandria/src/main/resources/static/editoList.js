/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

//------------ESTE SCRIPT TAMBIEN APLICA A EL TEMPLATE DE CAFE------------


function eventoMouse2(){
let botonAgregar = document.querySelector('#agregarRegistro-btn')
let formularioAgregar = document.querySelector('#agregarRegistro-form');

botonAgregar.addEventListener('click',()=>{
    
        formularioAgregar.classList.toggle('oculto');
        console.log("entro");
});


}

window.addEventListener('load',eventoMouse2());