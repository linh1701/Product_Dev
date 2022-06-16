<?php
    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");
    header("Access-Control-Max-Age: 3600");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    $body = json_decode(file_get_contents("php://input"));
    $a = $body->a;
    $b = $body->b;
    $c = $body->c;
    $x1 = "";
    $x2 = "";
    $ketqua;
    $delta = $b * $b - 4 * $a * $c;

    if ($a == 0) {
        if ($b == 0) {
            $ketqua = "Phương trình vô nghiệm!";
        } else {
            $ketqua= (- $c / $b);
        }
    }
    // tính delta
    $delta = $b * $b - 4 * $a * $c;
    // tính nghiệm
    if ($delta > 0) {
        $x1 = (- $b + sqrt ( $delta )) / (2 * $a);
        $x2 = (- $b - sqrt ( $delta )) / (2 * $a);
        $ketqua=  "Phương trình có 2 nghiệm là: " . "x1 = " . $x1 ." và x2 = " . $x2;
    } else if ($delta == 0) {
        $x1 = (- $b / (2 * $a));
        $ketqua =  "Phương trình có nghiệm kép: x1 = x2 = " . $x1;
    } else {
        $ketqua = "Phương trình vô nghiệm!" ;
    }
    
    // $b = $_GET["b"];

    http_response_code(200);
    echo json_encode(array(
        "status" => true,
        "result" => $ketqua
    ));