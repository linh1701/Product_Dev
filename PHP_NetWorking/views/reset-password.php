<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
</head>

<body>

    <?php
    // http://127.0.0.1:8585/views/reset-password.php?email=channn3@fpt.edu.vn&token=cbdf5db3cc7854b0f7286e7c53d753ee3113
    include_once $_SERVER['DOCUMENT_ROOT'] . '/controllers/user_controller.php';

    $email = $_GET["email"];
    $token = $_GET["token"];

    $result = (new UserController())->getByToken($email, $token);

    if ($result) { ?>
        <form method="post" action="change-password.php">
            <input type="password" name="password" placeholder="Enter your password" /> <br>
            <input type="hidden" name="token" value="<?php echo $token; ?>" /> <br>
            <input type="hidden" name="email" value="<?php echo $email; ?>" /> <br>
            <input type="submit" value="Save" />
        </form>
    <?php } else { ?>
        <h1>Đường dẫn không hợp lệ</h1>
    <?php } ?>
</body>

</html>