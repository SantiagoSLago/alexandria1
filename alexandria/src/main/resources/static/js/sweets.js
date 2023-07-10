const addIcon = document.querySelectorAll(".add-item-button");
const updateIcon = document.querySelectorAll(".fa-pen-to-square")
const deleteIcon = document.querySelectorAll(".fa-trash")
const addForm = document.querySelector(".form-container.add");
const updateForm = document.querySelector(".form-container.update")
const updateButton = document.querySelector(".btn-update")
const addButton = document.querySelector(".btn-add")
const textoEliminacion = "El item a eliminar tiene pedidos vinculados, en caso de eliminarlo tambien lo eliminara de dichos pedidos. "
const stateIcon = document.querySelectorAll(".fa-circle-check")

const form = document.querySelector(".form-sweet-upload");
const formData = new FormData(form);
const fileInput = document.querySelector("#file");


addIcon.forEach(function(addIcon){
addIcon.addEventListener("click",function(){
addForm.classList.toggle("show")
})
})

updateIcon.forEach(function(updateIcon){
updateIcon.addEventListener("click",function(){
updateForm.classList.toggle("show")


const id_product=updateIcon.getAttribute('value');


    const url = "/admin/sweets/"+id_product

    const settings ={
        method:"GET"
    }

     fetch(url,settings)
        .then((response)=>{
            return response.json()
        })
        .then((responseData)=>{
            autocompleteForm(responseData)
        })


})
})

deleteIcon.forEach(function(deleteIcon){


    deleteIcon.addEventListener("click", function(e) {
        e.preventDefault()
      const id_product = deleteIcon.getAttribute('value')
      console.log("id: "+id_product)

        const url = "/pedidos/purchaseBySweets/" + id_product;

        const settings = {
            method: "GET"
        }

        fetch(url,settings)
        .then((response)=>{
            if(response.status==204){

                deleteProduct(id_product)

            }else if(response.ok){

               if(confirm(textoEliminacion)){
                deleteProduct(id_product)
               }


            }
            //return response.json()
        })
        .then((responseData)=>{

            window.location.reload();
        })


    });



    })


stateIcon.forEach(function(stateIcon){


          stateIcon.addEventListener("click", function(e) {
              e.preventDefault()

            const id_product = stateIcon.getAttribute('value')

              const url = "/admin/sweets/updateState/" + id_product;

              const settings = {
                  method: "PUT"
              }

              fetch(url,settings)
              .then(()=>{
              window.location.reload()

              })
          });

          })




function deleteProduct(id){

    const url = "/admin/sweets/eliminarSweet/" + id;

    const settings = {
        method: "delete"
    }

    fetch(url,settings)
    .then((response) => {
        return response.json()
    })
    .then((responseData) => {
        console.log(responseData)

    })

}

function autocompleteForm(product){

    id_input = document.querySelector('.update_input_productId')
    name_input = document.querySelector('.update-input-name')
    size_input = document.querySelector('.update-input-size')
    price_input = document.querySelector('.update-input-price')

    id_input.value = product.id
    name_input.value = product.nombre
    size_input.value = product.peso
    price_input.value = product.precio



}


addButton.addEventListener("click", function(e) {

e.preventDefault()




formData.set('nombre',document.querySelector('#name').value)
formData.set('peso',document.querySelector('#size').value)
formData.set('precio',document.querySelector('#price').value)
formData.set("file", fileInput.files[0]);

    const url = "/admin/sweets/crearSweet"


  fetch(url, {
    method: 'POST',
    body: formData
  })
    .then((response) => {
     console.log(response)
    })
    .catch((error) => {

      console.error('Error en la solicitud fetch:', error);
    });



  });


updateButton.addEventListener("click",function(e){
e.preventDefault()


   const sweet_id = document.querySelector('.update_input_productId').value
   const name_input = document.querySelector('.update-input-name').value
   const size_input = document.querySelector('.update-input-size').value
   const price_input = document.querySelector('.update-input-price').value

    const url = "/admin/sweets/updateSweet/" + sweet_id



    data = {
            nombre: name_input,
            peso: size_input,
            precio: price_input
        }

        settings = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        }


        fetch(url,settings)
            .then((response)=>{
                return response.json()
            })
            .then((responseData)=>{
                console.log(responseData)

                window.location.reload()
            })



})







