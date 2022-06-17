<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

$body = json_decode(file_get_contents("php://input"));
$img = base64_decode($body->base64);
$name = md5(uniqid(rand(),true));
$fp = fopen("upload/".$name.".png", "w");
fwrite($fp, $img);
if(fclose($fp)){
    echo json_encode(
        array(
            "error" => false,
            "message" => "Upload successful",
            "path" => "http://10.82.74.26:8585/views/upload/".$name.".png"
        )
    );
} else {
    echo json_encode(
        array(
            "error" => true,
            "message" => "Upload failed",
            "path" => null
        )
    );
}
?>
