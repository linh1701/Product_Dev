<?php

use PHPMailer\PHPMailer\PHPMailer;

include_once $_SERVER['DOCUMENT_ROOT'] . '/utilities/PHPMailer-master/src/PHPMailer.php';
include_once $_SERVER['DOCUMENT_ROOT'] . '/utilities/PHPMailer-master/src/SMTP.php';
include_once $_SERVER['DOCUMENT_ROOT'] . '/utilities/PHPMailer-master/src/Exception.php';

include_once $_SERVER['DOCUMENT_ROOT'] . '/services/user_service.php';

class UserController
{
    private $user_service;
    public function __construct()
    {
        $this->user_service = new UserService();
    }

    public function register($email, $password, $name)
    {
        $user = $this->user_service->getUserByEmail($email);
        if (!$user) {
            $hash = password_hash($password, PASSWORD_BCRYPT);
            return $this->user_service->register($email, $hash, $name);
        }
        return false;
    }

    public function login($email, $password)
    {
        $user = $this->user_service->getUserByEmail($email);
        if ($user) {
            $check = password_verify($password, $user->getPassword());
            return $check;
        }
        return false;
    }
}
