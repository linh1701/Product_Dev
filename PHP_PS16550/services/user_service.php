<?php

include_once $_SERVER['DOCUMENT_ROOT'].'/database/config.php';
include_once $_SERVER['DOCUMENT_ROOT'].'/models/user.php';

class UserService {
    private $connection;

    public function __construct(){
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    // sql injection
    public function register($email, $password, $name){
        try {
            $query = "insert into tbl_users set email=:email, password=:password, name=:name";

            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':password', $password);
            $stmt->bindParam(':name', $name);

            $this->connection->beginTransaction();
            if($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollback();
                return false;
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return false;
    }

    public function getUserByEmail($email){
        try {
            $query = "select id, email, password, name from tbl_users where email =:email";                                               
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':email', $email);            
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                extract($row);                
                return new User($id, $email, $password, $name);
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return null;
    }

    // nhập email - tạo 1 link chứa token gửi qua email người dùng
    // kiểm tra token có hợp lệ
    // change password
    // clear token

    public function generateToken($email) {
        $token = md5($email).rand(10, 9999);
        try {
            $query = "insert into tbl_ResetPassword set email=:email, token=:token";

            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':token', $token);

            $this->connection->beginTransaction();
            if($stmt->execute()) {
                $this->connection->commit();
                return $token;
            } else {
                $this->connection->rollback();
                return null;
            }
        } catch (Exception $e) {
            echo $e;
        }
        return null;
    }


    
}

?>