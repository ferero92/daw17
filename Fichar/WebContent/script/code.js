function active(number) {

  var buttons = document.getElementsByTagName('button');

  for (var i = 0; i < buttons.length; i++) {
    var string = buttons[i].className.substring(0,33);
    buttons[i].className = string;
  }

  switch (number) {
    case 0:
      buttons[0].className += 'active';
      buttons[1].className += 'disabled';
      buttons[2].className += 'disabled';
      buttons[3].className += 'disabled';
      break;
    case 1:
      buttons[0].className += 'disabled';
      buttons[1].className += 'active';
      buttons[2].className += 'disabled';
      buttons[3].className += 'active';
      break;
    case 2:
      buttons[0].className += 'disabled';
      buttons[1].className += 'disabled';
      buttons[2].className += 'active';
      buttons[3].className += 'disabled';
      break;
  }
}

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

function position() {

  navigator.geolocation.getCurrentPosition(success, failure);
}

function success(location){

	var latitude = location.coords.latitude;
	var longitude = location.coords.longitude;
	var accuracy = location.coords.accuracy;

	var input = document.getElementsByName('position')[0];
	input.value = latitude + ',' + longitude + ',' + accuracy;

	var map = document.getElementById('map');
	var url = 'http://maps.google.com/maps/api/staticmap?center='
		  	  + latitude + ',' + longitude +
		  	  '&zoom=18&size=800x600&sensor=false&markers=' + latitude + ',' + longitude +
		  	  '&key=AIzaSyAtz3Oc21yeJ13PTp0pyLWsykVHjhMmVtI';

  map.setAttribute('href', url);
  map.getElementsByTagName('img')[0].setAttribute('src', url);
  var caption = document.getElementsByTagName('small')[0];
  caption.innerHTML = 'Precisión de ' + accuracy + ' metros'
}

function failure(){

	var latitude = 0;
	var longitude = 0;
	var accuracy = 0;

	var input = document.getElementsByName('position')[0];
	input.value = latitude + ',' + longitude + ',' + accuracy;
}
