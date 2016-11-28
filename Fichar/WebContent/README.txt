--FICHAR DESDE EL TRABAJO--

Existen dos tipos de usuarios: el administrador y los usuarios normales.

El método de acceso a la aplicación es mediante un login indicando correo electrónico y
contraseña, ambos campos recogidos en la Base de Datos.

Para el administrador, el email sería 'admin@ejemplo.com' y su contraseña es 'admin'.
El administrador cuenta con un panel de control desde el cual puede dar de alta un nuevo usuario,
consultar la actividad de todos los trabajadores de manera general o filtrado por usuarios y/o
fechas, y dar de baja un usuario. El usuario deshabilitado no podrá acceder a la aplicación pero
su historial de fichado se mantendrá recogido en la Base de Datos.

Los usuarios normales pueden cambiar su contraseña en cualquier momento, así como consultar su
actividad en la empresa (horas trabajadas en un intervalo de tiempo, pausas que ha hecho,
geolocalización desde dónde ha efectuado alguna acción...). Cabe destacar que si el usuario al
momento de fichar no tiene acceso a internet, la geolocalización guardará unos valores automáticos
de latitud y longitud de '0, 0', esto es así para evitar posibles fallas de la aplicación o por si
se opta de instalarla en un equipo local sin acceso a internet, por lo tanto los empleados siempre
ficharían desde su puesto de trabajo.


--Fernando Rodríguez Reyes, Desarrollo Web en Entorno Servidor - 2ª DAW curso 2016/2017

