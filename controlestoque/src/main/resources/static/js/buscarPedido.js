var btn = $('#pedidoBuscar');
var container = $('.tabela_pedido');
	console.log('caregou');
	console.log(container.style.display)
btn.addEventListener('click', function() {    
  if(container.style.display === 'none') {
      container.style.display = 'block';
  } else {
      container.style.display = 'none';
  }
});