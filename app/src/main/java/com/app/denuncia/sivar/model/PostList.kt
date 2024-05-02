package com.app.denuncia.sivar.model

import com.app.denuncia.sivar.R

val PostList = mutableListOf<PostData>(
    PostData(
        1,
        R.drawable.photoexample,
        "Roberto Loza",
        "Robo",
        "Pendiente",
        "San Salvador",
        "Hace dos horas",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        R.drawable.photodenuncia,
        5
    ),
    PostData(
        id = 2,
        userImage = R.drawable.photoexample,
        username = "María Pérez",
        category = "Incendio",
        status = "En proceso",
        location = "La Paz",
        timer = "Hace una hora",
        description = "Nulla facilisi nullam vehicula ipsum a arcu cursus vitae congue. Elementum sagittis vitae et leo duis ut diam quam nulla. Diam volutpat commodo sed egestas egestas fringilla phasellus faucibus. Arcu non odio euismod lacinia at quis risus sed vulputate.",
        image = R.drawable.captura,
        supportCount = 8
    ),
    PostData(
        id = 3,
        userImage = R.drawable.photoexample,
        username = "Ana García",
        category = "Accidente de tráfico",
        status = "Resuelto",
        location = "Madrid",
        timer = "Hace tres horas",
        description = "Ut placerat orci nulla pellentesque dignissim enim. Dui nunc mattis enim ut tellus elementum sagittis. Amet est placerat in egestas erat. Eget nunc scelerisque viverra mauris in aliquam sem.",
        image = R.drawable.bus,
        supportCount = 12
    ),
    PostData(
        id = 4,
        userImage = R.drawable.photoexample,
        username = "Juan Pérez",
        category = "Emergencia médica",
        status = "Pendiente",
        location = "Buenos Aires",
        timer = "Hace cuatro horas",
        description = "Vitae sapien pellentesque habitant morbi tristique senectus et netus. Odio ut sem nulla pharetra diam. Tempor commodo ullamcorper a lacus vestibulum sed arcu non. Egestas fringilla phasellus faucibus scelerisque. Massa massa ultricies mi quis hendrerit dolor magna.",
        image = R.drawable.photodenuncia,
        supportCount = 7
    ),
    PostData(
        id = 5,
        userImage = R.drawable.photoexample,
        username = "Laura González",
        category = "Robo",
        status = "En proceso",
        location = "Bogotá",
        timer = "Hace cinco horas",
        description = "Nisl nisi scelerisque eu ultrices vitae auctor eu. Euismod lacinia at quis risus sed vulputate odio. Sed adipiscing diam donec adipiscing tristique risus nec. Faucibus et molestie ac feugiat sed lectus vestibulum. Urna id volutpat lacus laoreet non curabitur gravida arcu ac.",
        image = R.drawable.captura,
        supportCount = 9
    ),
    PostData(
        id = 6,
        userImage = R.drawable.photoexample,
        username = "Diego Martínez",
        category = "Desaparición",
        status = "Pendiente",
        location = "Santiago",
        timer = "Hace seis horas",
        description = "Tellus in metus vulputate eu scelerisque felis imperdiet proin fermentum. Tellus at urna condimentum mattis. Amet dictum sit amet justo donec enim diam vulputate ut. Euismod in pellentesque massa placerat duis ultricies lacus. Dignissim suspendisse in est ante in nibh mauris cursus.",
        image = R.drawable.bus,
        supportCount = 11
    )

)