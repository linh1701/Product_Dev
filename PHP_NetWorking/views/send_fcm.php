<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Send FCM</title>
</head>

<body>
    <form method="post" action="fcm.php">
        <input type="text" name="token" placeholder="Enter your token" /> <br>
        <input type="text" name="title" placeholder="Enter your title" /> <br>
        <input type="text" name="body" placeholder="Enter your body" /> <br>
        <input type="submit" value="Send" />
    </form>
</body>

</html>