

function agregarEliminarForm(){
    let botonAgregar = document.querySelector('#agregarMesa-btn')
    let botonEliminar = document.querySelector('#eliminarMesa-btn')
    let formularioAgregar = document.querySelector('#agregarMesa-form')
    let formularioEliminar = document.querySelector('#eliminarMesa-form')


    botonAgregar.addEventListener('click',()=>{
        formularioAgregar.classList.toggle('oculto')
    })
    
    botonEliminar.addEventListener('click',()=>{
        formularioEliminar.classList.toggle('oculto')
    })

}

window.addEventListener('load',agregarEliminarForm())