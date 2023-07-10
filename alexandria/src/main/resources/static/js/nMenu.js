
let boton_plus = document.getElementsByClassName(' bi-plus-circle-fill');
let boton_minus = document.getElementsByClassName('bi-dash-circle-fill');
let contador = document.getElementsByClassName('menu-item-amount');
let monto = document.querySelector('.btn-amount');
let valores = document.getElementsByClassName('menu-item-price');
let boton_order = document.querySelector('.btn-purchase')
let numero_mesa= document.querySelector('#numero_mesa').textContent;
let intValue = parseInt(numero_mesa);
let resultado = 0;
let sweets = [];
let cafes = [];



for (let i = 0; i < boton_plus.length; i++) {
    boton_plus[i].addEventListener('click', function () {
        contador[i].innerHTML++;

        sumarMonto(valores[i].innerHTML)


        llenarLista(contador[i].getAttribute('value'),
            contador[i].getAttribute('name'))



    })
    boton_minus[i].addEventListener('click', function () {
        contador[i].innerHTML--;
        restarMonto(valores[i].innerHTML)
        eliminarElementoLista(contador[i].getAttribute('value'), contador[i].getAttribute('name'))
    })
}


function sumarMonto(valor) {

    let contenido = valor.replace("$", "");

    let suma = parseFloat(contenido)
    if (!isNaN(suma)) {
        resultado += suma;
    }
    monto.textContent = '$' + resultado.toFixed(2);
}

function restarMonto(valor) {
    let contenido = valor.replace("$", "");
    let suma = parseFloat(contenido)
    if (!isNaN(suma)) {
        resultado -= suma;
    }

    monto.textContent = '$' + resultado.toFixed(2);
}

function llenarLista(valor, nombre) {
    if (nombre == "coffee") {
        cafes.push(valor)
    }
    if (nombre == "sweet") {
        sweets.push(valor)
    }
}

function eliminarElementoLista(valor, nombre) {
    if (nombre == "coffee") {
        let nuevoArreglo = cafes.filter(function (e) {
            return e !== valor;
        })

        cafes = nuevoArreglo;
    }
    if (nombre == "sweet") {
        let nuevoArreglo = sweets.filter(function (e) {
            return e !== valor;
        })

        sweets = nuevoArreglo;
    }
}

boton_order.addEventListener('click', function () {

    const url = '/pedidos/altaPedido'



    const data = {
        arreglo_cafes: cafes,
        arreglo_sweets: sweets,
        monto_orden: resultado,
        numero_mesa: intValue
    }

    const settings = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }




    if(confirm("Confirmar Orden: $" +resultado )){
        fetch(url, settings)
        .then((response) => {
            return response.json()
        })
        .then((responseData) => {
            console.log(responseData)

            setTimeout(function() {
              window.location.href = '/pedidos/pedidoExitoso/' + responseData.id;
            }, 1000);

        })
    }


})