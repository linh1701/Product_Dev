<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once $_SERVER['DOCUMENT_ROOT'].'/controllers/product_controller.php';

$id = $_GET["id"];

$result = (new ProductController())->deleteProduct($id);
if ($result) {
        http_response_code(200);
        echo json_encode(array(
            "status" => true,
            "result" => $result
        ));
     
    }else {
        http_response_code(200);
        echo json_encode(array(
            "status" => false,
            "result" => $result
        ));
    }


?>