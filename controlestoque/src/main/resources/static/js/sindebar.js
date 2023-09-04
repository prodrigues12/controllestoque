document.addEventListener("DOMContentLoaded", function () {
  const showNavbar = (toggleId, navId, bodyId, headerId) => {
    const toggle = document.getElementById(toggleId);
    const nav = document.getElementById(navId);
    const bodypd = document.getElementById(bodyId);
    const headerpd = document.getElementById(headerId);

    if (toggle && nav && bodypd && headerpd) {
      toggle.addEventListener('click', () => {
        nav.classList.toggle('show-barra');
        toggle.classList.toggle('bx-x');
        bodypd.classList.toggle('body-pd');
        headerpd.classList.toggle('body-pd');
        headerpd.classList.toggle('header-expanded');

//         Verifica se o header_toggle está ativado e ajusta o tamanho do cabeçalho
        if (toggle.classList.contains('bx-x')) {
          headerpd.style.height = '50px'; // Ajuste o valor conforme necessário
        } else {
          headerpd.style.height = ''; // Volta ao tamanho original (removendo a altura definida)
        }
      });
    }
  }

  showNavbar('header-toggle', 'nav-bar', 'body-pd', 'header');

  function colorLink() {
    const linkColor = document.querySelectorAll('.nav_link');
    linkColor.forEach(link => link.classList.remove('active'));
    this.classList.add('active');
  }

  const linkColor = document.querySelectorAll('.nav_link');
  linkColor.forEach(link => link.addEventListener('click', colorLink));
});
