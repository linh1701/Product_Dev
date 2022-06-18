<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once $_SERVER['DOCUMENT_ROOT'] . '/controllers/product_controller.php';

$body = json_decode(file_get_contents("php://input"));
$id = $body->id;
$name = $body->name;
$price = $body->price;
$quantity = $body->quantity;

$result = (new ProductController())->updateProduct($id, $name, $price, $quantity);

http_response_code(200);
echo json_encode(array(
    "status" => true,
    "result" => $result
));
