<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">


        <title>Вход</title>

        <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">
        <!-- Bootstrap core CSS -->
        <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="https://getbootstrap.com/docs/4.0/examples/sign-in/signin.css" rel="stylesheet">
    </head>

    <body class="text-center">
        <form class="form-signin" action="j_security_check" method="post">
            <img class="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
            <h1 class="h3 mb-3 font-weight-normal">Регистрация в системе</h1>
            <label for="j_username" class="sr-only">Имя пользователя</label>

            <input type="text" id="j_username" name="j_username" class="form-control" placeholder="Логин" required autofocus>

            <label for="j_password" class="sr-only">Пароль</label>
            <input type="password" id="j_password" name="j_password" class="form-control" placeholder="Пароль" required>
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Запомнить меня
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Вход</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2021-2022</p>
        </form>
    </body>
</html>
