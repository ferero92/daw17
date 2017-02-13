$(document).ready(function() {
  var $length = 0;
  var $candidates = [];
  var $print;
  if($('#votation').attr('data-category') != null){
    switch ($('#votation').attr('data-category')) {
      case '1':
        $length = 8;
        break;
      case '2':
        $length = 5;
        break;
      case '3':
        $length = 1;
        break;
      case '4':
        $length = 5;
        break;
    }
  }
  if($('#votation').attr('data-category') != 'null')
	  $('button.btn-warning').removeClass('disabled');
  else
	  $('button.btn-warning').addClass('disabled');
  $('select[name="category"]').each(function(index, el) {
    $(this).prop('selectedIndex', $(this).attr('data-category'));
  });
  $('.alert').html('Seleccione '+$length+' candidatos máximo');
  $('select[name="filter_curse"]').change(function(event) {
    if($(this).val() != "-")
      $('button[name="remove"]').removeClass('disabled');
    else
      $('button[name="remove"]').addClass('disabled');
  });
  $('button[name="filter"]').click(function(event) {
    filter = 0;
    url = 'Controller?submit=1';

    if($('select[name="filter_category"]').val() != "-"){
      filter++;
      url += '&filter_category=' + $('select[name="filter_category"]').val();
    }
    if($('select[name="filter_curse"]').val() != "-"){
      filter += 2;
      url += '&filter_curse=' + $('select[name="filter_curse"]').val();
    }
    if(filter != 0)
      url += '&filter='+filter;

    window.location.href = url;
  });
  $('button[name="remove"]').click(function(event) {
    window.location.href = 'Controller?submit=4&remove_curse='+$('select[name="filter_curse"]').val();
  });
  $('span.glyphicon-pencil').click(function(event) {
    $row = $(this).closest('tr');
    $id = $row.attr('data-id');
    $first_name = $row.find('input[name="first_name"]').val();
    $last_name = $row.find('input[name="last_name"]').val();
    $category = $row.find('select[name="category"]').val();

    url = 'Controller?submit=2&id='+$id+'&first_name='+$first_name+'&last_name='+$last_name+'&category='+$category;
    window.location.href = url;
  });
  $('span.glyphicon-remove').click(function(event) {
    $row = $(this).closest('tr');
    $id = $row.attr('data-id');

    url = 'Controller?submit=3&id='+$id;
    window.location.href = url;
  });
  $('div.btn-group button').click(function(event) {
    url = 'Votes?category='+$(this).val()+'&type='+$(this).parent().attr('data-type');
    window.location.href = url;
  });
  $('input:checkbox').change(function(event) {
    if($('input:checked').length > $length){
      $('#votation button').addClass('disabled');
    }
    else
      $('#votation button').removeClass('disabled');
  });
  $('#votation button.btn-success').click(function(event) {
    $candidates = [];
    $('input:checked').each(function(index, el) {
      $candidates.push($(this).attr('data-id')+'&'+$(this).parent().next('label').html());
    });
    // if($('input:checked').length > 0){
    //   var string = '<ul>';
    //   $.each($candidates, function(index, el) {
    //     string += '<li>'+el.split('&')[1]+'</li>';
    //   });
    //   string += '</ul>';
    //   $('.modal-body').html(string);
    // }
    // else
    //   $('.modal-body').html('¿Desea votar en blanco?');
    html2canvas($('.print'), {
      onrendered: function(canvas) {
        $('.modal-body').html('');
        $('.modal-body').append(canvas);
        $print = canvas.toDataURL('image/png');
      }
    });
  });
  $('.modal-footer button').click(function(event) {
    url = 'Votes?candidates=';
    if($candidates.length > 0){
      $.each($candidates, function(index, el) {
        url += el.split('&')[0]+',';
      });
    }
    else
      url += '0';
    url += '&type=0&category='+$('#votation').attr('data-category');

    var tWindow = window.open('');
    $(tWindow.document.body).html("<img id='Image' src=" + $print + " style='width:100%;'></img>").ready(function () {
      //tWindow.focus();
      tWindow.print();
    });

    window.location.href = url;
  });
  $('button.btn-warning').click(function(event) {
    url = 'Votes?type=1&clean=1&category='+$('#votation').attr('data-category');
    window.location.href = url;
  });
  $('div.percentage').each(function(index, el) {
    $(this).css({
      width: $(this).html()
    });
    if($(this).html() == '0.0%')
      $(this).css({
        color: 'black'
      });
  });
});
