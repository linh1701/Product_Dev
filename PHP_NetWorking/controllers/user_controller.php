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

    public function register($email, $password)
    {
        $user = $this->user_service->getUserByEmail($email);
        if (!$user) {
            $hash = password_hash($password, PASSWORD_BCRYPT);
            return $this->user_service->register($email, $hash);
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

    public function sendEmailResetPassword($email)
    {
        $user = $this->user_service->getUserByEmail($email);
        if ($user) {
            try {
                $token = $this->user_service->generateToken($email);
                $link = "<a href='http://192.168.1.4:8585/views/reset-password.php?email=" 
                . $email . "&token=" . $token . "'>Click to reset password</a>";                
                $mail = new PHPMailer();
                $mail->CharSet = "utf-8";
                $mail->isSMTP();
                $mail->SMTPAuth = true;
                $mail->Username = "dinhnhatlinh170102";
                $mail->Password = "Dnl170102";
                $mail->SMTPSecure = "ssl";
                $mail->Host = "ssl://smtp.gmail.com";
                $mail->Port = "465";
                $mail->From = "dinhnhatlinh170102@gmail.com";
                $mail->FromName = "Nguyễn Anh Hùng";
                $mail->addAddress($email, 'Hello');
                $mail->Subject = "Reset Password";
                $mail->isHTML(true);
                $mail->Body = "Click on this link to reset password " .$link . " ";
           
                $res = $mail->Send();
                if ($res ) {
                    return true;
                }
            } catch (Exception $e) {
                echo $e;
            }
        }
        return false;
    }

    public function getByToken($email, $token) {
        return $this->user_service->getByToken($email, $token);
    }

    public function changePassword($email, $password, $token) {
        $check = $this->user_service->getByToken($email, $token);
        if($check) {
            $hash = password_hash($password, PASSWORD_BCRYPT);
            $this->user_service->changePassword($email, $hash);
            $this->user_service->clearToken($token);
            return true;
        }
        return false;
    }
}
