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
