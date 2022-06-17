<?php 
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");


$title = $_POST["title"];
$body = $_POST["body"];
$token = $_POST["token"];


 function sendNotification($title = "", $body="", $token="")
        {
            $key = "AAAA7JSMNf8:APA91bFEYFybMG_oN40e2zICc3zsbopyevDQfkjxUcWKZSLzzI9tmJMpHYKn8QA3_nuGCe4j-bsHYED8462_yslN6R3N85c6mu2KVv6Hv2cegzA2HcuUfemeeh_kkO9gFTzPVdEv1lqy";
            ini_set("allow_url_open", "On");
            $data = [
                "to" => $token,
                "notification" => [
                    "body"=> $body,
                    "title" => $title
                ]
            ];
            $option = array(
                'http'=> array(
                    'method' => 'POST',
                    'content' => json_encode($data),
                    'header' => "Content-Type: application/json\r\n".
                                "Accept: application/json\r\n".
                                "Authorization:key=".$key
                )
            );
            $context = stream_context_create($option);
            $result = file_get_contents("https://fcm.googleapis.com/fcm/send", false, $context);
            return json_encode($result);
        }

        sendNotification($title, $body, $token);

?>