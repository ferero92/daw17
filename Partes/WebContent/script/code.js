function verify(password) {

  var input = document.getElementsByName('old')[0];

  if(input.value != password){
    document.getElementsByTagName('span')[0].style.visibility = "visible";
    input.value = "";
  }
  else
    document.getElementsByTagName('span')[0].style.visibility = "hidden";
}

function confirmar() {

  var password = document.getElementsByName('password')[0];
  var confirm = document.getElementsByName('confirm')[0];

  if(password.value != confirm.value){
    document.getElementsByTagName('span')[1].style.visibility = "visible";
    password.value = "";
    confirm.value = "";
  }
  else
    document.getElementsByTagName('span')[1].style.visibility = "hidden";
}

$(document).ready(function() {
  $('a.modify').click(function() {
    $row = $(this).closest('tr');
    $id = $row.find('td.id').html();
    $description = $row.find('.description').val();
    $date = $row.find('[name="date"]').val();
    $fixed = $row.find('[name="fixed"]').val();
    window.location.href = '../Modify?id='+$id+'&description='+$description+'&date='+$date+'&fixed='+$fixed;
  });
  $('a.remove').click(function() {
    $row = $(this).closest('tr');
    $id = $row.find('td.id').html();
    window.location.href = '../Delete?id='+$id;
  });
  $('a.check').click(function() {
  	$row = $(this).closest('tr');
  	$id = $row.find('td.id').html();
  	window.location.href = '../Check?id='+$id;
  });
  $('#usuarios').change(function() {
	 window.location.href = '../ModifyUser?id='+$('#usuarios').val(); 
  });
});
