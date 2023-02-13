


//-----------SEGUNDA VERSION DEL SCRIPT----------//

function eventoMouse() {
    let agregarAutor = document.querySelector('#addAutor')
    let modif = document.querySelector('#modifAutor')
    console.log(agregarAutor)

    //Click de formulario de Agregar Autor
    agregarAutor.addEventListener('click', () => {
        let formulario = document.querySelector('#busquedaAutor')
        formulario.classList.toggle('oculto')

    })


    //Click de formulario de Modificar Autor


modif.addEventListener('click', (e) => {
    let form_modif = document.querySelector('#modificar_autor')
    form_modif.classList.toggle('oculto')
})


}
window.addEventListener('load', eventoMouse());
/*# sourceMappingURL=script1.js.map */


//-----------PRIMER VERSION DEL SCRIPT----------//
/*
function eventoMouse(){
    let agregarAutor = document.querySelector('#addAutor');
    console.log(agregarAutor);
agregarAutor.addEventListener('click',()=>{
    let formulario = document.querySelector('#busquedaAutor');
    formulario.style.visibility = "visible";
});
}
window.addEventListener('load',eventoMouse());*/