document.addEventListener("DOMContentLoaded", function () {

    let prevScrollPos = window.pageYOffset;
    let isComponentVisible = false;
    let isComponentLoginVisible = false;
    
    const body = document.body;
    const componente = document.getElementById("componenteReg");
    const componenteLogin = document.getElementById("componenteLogin");
    const enlaceReg = document.getElementById("mostrarRegistro");
    const estiloPrevio = enlaceReg.style;
    const fondoPrincipal = `https://news.airbnb.com/wp-content/uploads/sites/4/2019/06/PJM020719Q202_Luxe_WanakaNZ_LivingRoom_0264-LightOn_R1.jpg?fit=2500%2C1666`;
    const fondoFamilias = `https://d22dvihj4pfop3.cloudfront.net/wp-content/uploads/sites/4/2016/09/30184806/11699066_1031817343509055_4153953790175570550_o.jpg`
    const fondoClientes = `https://media.cnn.com/api/v1/images/stellar/prod/230818133200-04-italy-tourism-high-prices.jpg?c=original`

    enlaceReg.addEventListener("click", function (event) {
        event.preventDefault();

        if (isComponentLoginVisible) {
            componenteLogin.style.maxHeight = "0";
            componenteLogin.style.opacity = "0";
            componenteLogin.style.visibility = "hidden";
            enlaceLogin.style = estiloPrevio;
            isComponentLoginVisible = false;
        }

        if (!isComponentVisible) {
            componente.style.maxHeight = "60%"; // Ajusta la altura máxima deseada
            componente.style.opacity = "1";
            componente.style.visibility = "visible";
            enlaceReg.style.color = "#96C0B7";
            enlaceReg.style.fontSize = "16px";
            isComponentVisible = true;
        } else {
            componente.style.maxHeight = "0";
            componente.style.opacity = "0";
            componente.style.visibility = "hidden";
            enlaceReg.style = estiloPrevio;
            isComponentVisible = false;
        }
    });

    const enlaceLogin = document.getElementById("mostrarLogin");

    enlaceLogin.addEventListener("click", function (event) {
        event.preventDefault();

        if (isComponentVisible) {
            componente.style.maxHeight = "0";
            componente.style.opacity = "0";
            componente.style.visibility = "hidden";
            enlaceReg.style = estiloPrevio;
            isComponentVisible = false;
        }

        if (!isComponentLoginVisible) {
            componenteLogin.style.maxHeight = "60%";
            componenteLogin.style.opacity = "1";
            componenteLogin.style.visibility = "visible";
            enlaceLogin.style.color = "#96C0B7";
            enlaceLogin.style.fontSize = "16px";
            isComponentLoginVisible = true;
        } else {
            componenteLogin.style.maxHeight = "0";
            componenteLogin.style.opacity = "0";
            componenteLogin.style.visibility = "hidden";
            enlaceLogin.style = estiloPrevio;
            isComponentLoginVisible = false;
        }
    });

    const ojoAbierto = `<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
    <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
    <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
  </svg>`;
    const ojoCerrado = `<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-eye-slash" viewBox="0 0 16 16">
    <path d="M13.359 11.238C15.06 9.72 16 8 16 8s-3-5.5-8-5.5a7.028 7.028 0 0 0-2.79.588l.77.771A5.944 5.944 0 0 1 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.134 13.134 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755-.165.165-.337.328-.517.486l.708.709z"/>
    <path d="M11.297 9.176a3.5 3.5 0 0 0-4.474-4.474l.823.823a2.5 2.5 0 0 1 2.829 2.829l.822.822zm-2.943 1.299.822.822a3.5 3.5 0 0 1-4.474-4.474l.823.823a2.5 2.5 0 0 0 2.829 2.829z"/>
    <path d="M3.35 5.47c-.18.16-.353.322-.518.487A13.134 13.134 0 0 0 1.172 8l.195.288c.335.48.83 1.12 1.465 1.755C4.121 11.332 5.881 12.5 8 12.5c.716 0 1.39-.133 2.02-.36l.77.772A7.029 7.029 0 0 1 8 13.5C3 13.5 0 8 0 8s.939-1.721 2.641-3.238l.708.709zm10.296 8.884-12-12 .708-.708 12 12-.708.708z"/>
  </svg>`;
    const passwordField = document.getElementById("claveLogin");
    const togglePassword = document.getElementById("togglePassword");

    togglePassword.addEventListener("click", function () {
        if (claveLogin.type === "password") {
            claveLogin.type = "text";
            togglePassword.innerHTML = ojoAbierto;
        } else {
            passwordField.type = "password";
            togglePassword.innerHTML = ojoCerrado;
        }
    });

    /*const enlaceFamilias = document.getElementById("familiasLink");
    const enlaceClientes = document.getElementById("clientesLink");
    const enlaceLogo = document.getElementById("principalLink")

    enlaceLogo.addEventListener("click", cambiarFondo(fondoPrincipal));
    enlaceFamilias.addEventListener("click", cambiarFondo(fondoFamilias));
    enlaceClientes.addEventListener("click", cambiarFondo(fondoClientes));*/
    
    const cambiarFondo = (url) => body.style.backgroundImage = `url('${url}')`;

    window.onscroll = function () {

        let currentScrollPos = window.pageYOffset;
        const navbar = document.querySelector(".navbar");

        if (prevScrollPos > currentScrollPos) {
            navbar.style.top = "0"; // Muestra la navbar al desplazarse hacia arriba
            enlaceReg.style = estiloPrevio;
            enlaceLogin.style = estiloPrevio;
        } else {
            navbar.style.top = "-100px"; // Oculta la navbar al desplazarse hacia abajo
            componente.style.maxHeight = "0"; // Oculta el componente cuando el scroll está en la parte superior
            componente.style.opacity = "0";
            componente.style.visibility = "hidden";
            componenteLogin.style.maxHeight = "0"; // Oculta el componente cuando el scroll está en la parte superior
            componenteLogin.style.opacity = "0";
            componenteLogin.style.visibility = "hidden";
            passwordField.type = "password"; // Cambia el tipo de campo a "password" para ocultar la contraseña
            togglePassword.innerHTML = ojoCerrado; // Cambia el icono a un ojo cerrado
            isComponentVisible = false;
            isComponentLoginVisible = false;
        }

        prevScrollPos = currentScrollPos;

        const principal = document.getElementById("principal");
        const familias = document.getElementById("familias");
        const clientes = document.getElementById("clientes");
        
        const posPrincipal = principal.getBoundingClientRect().top;
        const posFamilias = familias.getBoundingClientRect().top;
        const posClientes = clientes.getBoundingClientRect().top;

        if (posPrincipal <= 200 && posPrincipal >= -200) {
            cambiarFondo(fondoPrincipal);
        } else if (posFamilias <= 200 && posFamilias >= -200) {
            cambiarFondo(fondoFamilias);
        } else if (posClientes <= 200 && posClientes >= -200) {
            cambiarFondo(fondoClientes);
        }

    };

});