<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once $_SERVER['DOCUMENT_ROOT'] . '/controllers/user_controller.php';

$password = $_POST["password"];
$email = $_POST["email"];
$token = $_POST["token"];

$result = (new UserController())->changePassword($email, $password, $token);

http_response_code(200);
echo json_encode(array(
    "status" => true,
    "result" => $result
));