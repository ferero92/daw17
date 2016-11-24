function active(number) {

  var buttons = document.getElementsByTagName('button');

  switch (number) {
    case 0:
      buttons[0].disabled = false;
      buttons[1].disabled = true;
      buttons[2].disabled = true;
      buttons[3].disabled = true;
      break;
    case 1:
      buttons[0].disabled = true;
      buttons[1].disabled = false;
      buttons[2].disabled = true;
      buttons[3].disabled = false;
      break;
    case 2:
      buttons[0].disabled = true;
      buttons[1].disabled = true;
      buttons[2].disabled = false;
      buttons[3].disabled = true;
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

  map.setAttribute('src', url);
  var caption = document.createElement('figcaption');
  caption.innerHTML = 'Precisión de ' + accuracy + ' metros'
  map.parentNode.appendChild(caption);
}

function failure(){

	var latitude = 0;
	var longitude = 0;
	var accuracy = 0;

	var input = document.getElementsByName('position')[0];
	input.value = latitude + ',' + longitude + ',' + accuracy;
}
