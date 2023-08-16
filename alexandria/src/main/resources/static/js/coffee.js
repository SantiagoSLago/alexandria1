const addIcon = document.querySelectorAll(".add-item-button");
const updateIcon = document.querySelectorAll(".fa-pen-to-square")
const deleteIcon = document.querySelectorAll(".fa-trash")
const addForm = document.querySelector(".form-container.add");
const updateForm = document.querySelector(".form-container.update")
const updateButton = document.querySelector(".btn-update")
const addButton = document.querySelector(".btn-add")
const textoEliminacion = "El item a eliminar tiene pedidos vinculados, en caso de eliminarlo tambien lo eliminara de dichos pedidos. "
const stateIcon = document.querySelectorAll(".fa-circle-check")

const form = document.querySelector(".form-coffee-upload");
const formData = new FormData(form);
const fileInput = document.querySelector("#file");


addIcon.forEach(function (addIcon) {
    addIcon.addEventListener("click", function () {
        addForm.classList.toggle("show")
    })
})

updateIcon.forEach(function (updateIcon) {
    updateIcon.addEventListener("click", function () {
        updateForm.classList.toggle("show")


        const id_product = updateIcon.getAttribute('value');


        const url = "/admin/cafes/" + id_product

        const settings = {
            method: "GET"
        }

        fetch(url, settings)
            .then((response) => {
                return response.json()
            })
            .then((responseData) => {
                autocompleteForm(responseData)
            })


    })
})

deleteIcon.forEach(function (deleteIcon) {


    deleteIcon.addEventListener("click", function (e) {
        e.preventDefault()
        const id_product = deleteIcon.getAttribute('value')
        const url = "/pedidos/purchaseByCoffee/" + id_product;

        const settings = {
            method: "GET"
        }

        fetch(url, settings)
            .then((response) => {
                if (response.status == 204) {

                    deleteProduct(id_product)

                } else if (response.ok) {

                    if (confirm(textoEliminacion)) {
                        deleteProduct(id_product)
                    }


                }
                //return response.json()
            })
            .then((responseData) => {

                window.location.reload();
            })


    });



})

stateIcon.forEach(function (stateIcon) {


    stateIcon.addEventListener("click", function (e) {
        e.preventDefault()



        const id_product = stateIcon.getAttribute('value')



        const url = "/admin/cafes/updateState/" + id_product;

        const settings = {
            method: "PUT"
        }

        fetch(url, settings)
            .then(response => {

                if (response.status === 403) {
                    window.alert("Unauthorized")
                } else if(response.status === 200){
                    window.alert("Succesful action");
                }

            })
            .catch((error) => {
                console.log("Error en la solicitud fetch: " + error);
            })


    });



})



addButton.addEventListener("click", function (e) {

    e.preventDefault()




    formData.set('nombre', document.querySelector('#name').value)
    formData.set('medida', document.querySelector('#size').value)
    formData.set('precio', document.querySelector('#price').value)
    formData.set("file", fileInput.files[0]);

    const url = "/admin/cafes/crearCafe"


    fetch(url, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.status === 403) {
                window.alert("Unauthorized")
            } else if (response.status === 200) {
                window.alert("Succesful action");
            }
        })



});

updateButton.addEventListener("click", function (e) {
    e.preventDefault()


    coffee_id = document.querySelector('.update_input_productId').value
    name_input = document.querySelector('.update-input-name').value
    size_input = document.querySelector('.update-input-size').value
    price_input = document.querySelector('.update-input-price').value

    const url = "/admin/cafes/updateCoffee/" + coffee_id



    data = {
        nombre: name_input,
        medida: size_input,
        precio: price_input
    }

    settings = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    }


    fetch(url, settings)
        .then(response => {

            if (response.status === 403) {
                window.alert("Unauthorized")
            } else {
                window.alert("Succesful action");
            }

        })
        .catch((error) => {
            console.log("Error en la solicitud fetch: " + error);
        })



})






function deleteProduct(id) {

    const url = "/admin/cafes/eliminarCafe/" + id;

    const settings = {
        method: "delete"
    }

    fetch(url, settings)
        .then(response => {

            if (response.status === 403) {
                window.alert("Unauthorized")
            } else {
                window.alert("Succesful action");
            }

        })
        .catch((error) => {
            console.log("Error en la solicitud fetch: " + error);
        })

}

function autocompleteForm(product) {

    id_input = document.querySelector('.update_input_productId')
    name_input = document.querySelector('.update-input-name')
    size_input = document.querySelector('.update-input-size')
    price_input = document.querySelector('.update-input-price')

    id_input.value = product.id
    name_input.value = product.nombre
    size_input.value = product.medida
    price_input.value = product.precio



}









